package com.se.EC.Service.Chat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Controller.Chat.Information;
import com.se.EC.Controller.Chat.SessionInformation;
import com.se.EC.Entity.Chat;
import com.se.EC.Entity.Session;
import com.se.EC.Entity.User;
import com.se.EC.Mapper.ChatMapper;
import com.se.EC.Mapper.SessionMapper;
import com.se.EC.Mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService extends MppServiceImpl<ChatMapper, Chat> implements ChatServiceInterface {
    @Resource
    private ChatMapper chatMapper;
    @Resource
    private SessionMapper sessionMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 创建会话
     *
     * @param senderId   发起者id
     * @param receiverId 被发起者id
     */
    @Override
    public void createSession(Integer senderId, Integer receiverId) {
        // 查看这个会话是否已经存在
        Long count = sessionCount(senderId, receiverId);
        Long count1 = sessionCount(receiverId, senderId);

        if (count == 0 && count1 == 0) {  // 如果不存在就新建Session，并返回True
            LocalDateTime currentTime = LocalDateTime.now();
            Session session = new Session(senderId, receiverId, currentTime);
            Session session1 = new Session(receiverId, senderId, currentTime);
            sessionMapper.insert(session);
            sessionMapper.insert(session1);
        } else if (count != 1) {  // 多于一个出错
            throw new RuntimeException("There are two session between " + senderId + " and " + receiverId);
        }
    }

    /**
     * 删除会话
     *
     * @param senderId   发起者id
     * @param receiverId 被发起者id
     */
    @Override
    public void dropSession(Integer senderId, Integer receiverId) {
        // 查看这个会话是否已经存在
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", senderId);
        queryWrapper.eq("receiverId", receiverId);
        Long count = sessionMapper.selectCount(queryWrapper);

        QueryWrapper<Session> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("receiverId", senderId);
        queryWrapper.eq("senderId", receiverId);
        Long count1 = sessionMapper.selectCount(queryWrapper);

        if (count == 0 && count1 == 0) {  // 如果不存在返回错误
            throw new RuntimeException("Session does not exist");
        } else if (count == 1 && count1 == 1) {  // 如果已经存在就删除
            sessionMapper.delete(queryWrapper);
            sessionMapper.delete(queryWrapper1);
        } else {  // 如果多于一个返回错误
            throw new RuntimeException("There are many session between " + senderId + " and " + receiverId);
        }
    }

    /**
     * 获取会话
     *
     * @param id id
     */
    @Override
    public List<SessionInformation> getSession(Integer id) {
        // 获取所有会话
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", id);
        List<Session> sessionList = sessionMapper.selectList(queryWrapper);

        // 转换
        List<SessionInformation> sessionInformationList = new ArrayList<>();
        for (var item : sessionList) {
            Integer receiverId = item.getReceiverId();
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", receiverId);
            User user = userMapper.selectOne(userQueryWrapper);
            SessionInformation sessionInformation = new SessionInformation(receiverId, user.getName());
            sessionInformationList.add(sessionInformation);
        }

        return sessionInformationList;
    }

    /**
     * 发送消息
     *
     * @param senderId   发起者id
     * @param receiverId 被发起者id
     * @param content    消息内容
     */
    @Override
    public void sendMessage(Integer senderId, Integer receiverId, String content) {
        // 查找有没有会话，没有就返回错误
        Long count = sessionCount(senderId, receiverId);
        if (count == 0) {
            throw new RuntimeException("Session does not exist");
        }

        // 将消息存入数据库
        LocalDateTime currentTime = LocalDateTime.now();
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", senderId);
        queryWrapper.eq("receiverId", receiverId);
        queryWrapper.eq("time", currentTime);
        count = chatMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("Send message too frequent, please try again later");
        } else {
            Chat chat = new Chat(currentTime, content, senderId, receiverId);
            chatMapper.insert(chat);
        }
    }

    /**
     * 获取更新消息
     *
     * @param senderId   发起者id
     * @param receiverId 请求接收者id
     * @return error信息
     */
    @Override
    public List<Information> updateMessage(Integer senderId, Integer receiverId) {
        // 判断会话是否存在并获取最近的更新消息的时间
        QueryWrapper<Session> sessionQueryWrapper = new QueryWrapper<>();
        sessionQueryWrapper.eq("senderId", senderId);
        sessionQueryWrapper.eq("receiverId", receiverId);
        List<Session> sessionList = sessionMapper.selectList(sessionQueryWrapper);
        if (sessionList.size() == 0) {
            throw new RuntimeException("Session does not exist");
        } else if (sessionList.size() > 1) {
            throw new RuntimeException("There are many session between " + senderId + " and " + receiverId);
        }
        LocalDateTime updateTime = sessionList.get(0).getUpdateTime();

        // 筛选数据库中比这个时间更新的数据
        QueryWrapper<Chat> chatQueryWrapper = new QueryWrapper<>();
        chatQueryWrapper.eq("senderId", senderId);
        chatQueryWrapper.eq("receiverId", receiverId);
        chatQueryWrapper.ge("time", updateTime);
        List<Chat> chatList = chatMapper.selectList(chatQueryWrapper);

        // 更新更新时间
        LocalDateTime currentTime = LocalDateTime.now();
        Session session = new Session(senderId, receiverId, currentTime);
        sessionMapper.update(session, sessionQueryWrapper);

        // 按照时间排序返回
        return packData(chatList);
    }

    /**
     * 获取近500条消息
     *
     * @param senderId   发起者id
     * @param receiverId 请求接收者id
     * @return error信息
     */
    @Override
    public List<Information> retrieveAllMessage(Integer senderId, Integer receiverId) {
        // 判断会话是否存在
        QueryWrapper<Session> sessionQueryWrapper = new QueryWrapper<>();
        sessionQueryWrapper.eq("senderId", senderId);
        sessionQueryWrapper.eq("receiverId", receiverId);
        Long count = sessionMapper.selectCount(sessionQueryWrapper);
        if (count == 0) {
            throw new RuntimeException("Session does not exist");
        }

        // 得到两人聊天的所有数据
        QueryWrapper<Chat> chatQueryWrapper = new QueryWrapper<>();
        chatQueryWrapper.eq("senderId", senderId);
        chatQueryWrapper.eq("receiverId", receiverId);
        List<Chat> chatList = chatMapper.selectList(chatQueryWrapper);

        // 按照时间排序返回
        return packData(chatList);
    }

    /**
     * 将Chat List按照时间排序并包装成Information List
     *
     * @param chatList List<Chat>
     * @return List<Information>
     */
    private List<Information> packData(List<Chat> chatList) {
        List<Information> informationList = new ArrayList<>();
        for (var item : chatList) {
            informationList.add(new Information(item.getTime(), item.getContent()));
        }
        informationList.sort((o1, o2) -> {
            if (o1.getTime().isAfter(o2.getTime())) {
                return -1;
            } else if (o1.getTime().equals(o2.getTime())) {
                return 0;
            } else {
                return 1;
            }
        });
        return informationList;
    }

    /**
     * 查看两个id建立的会话数量
     * @param senderId 发起者
     * @param receiverId 接收者
     * @return 数目
     */
    private Long sessionCount(Integer senderId, Integer receiverId) {
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", senderId);
        queryWrapper.eq("receiverId", receiverId);
        return sessionMapper.selectCount(queryWrapper);
    }
}
