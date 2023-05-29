package com.se.EC.Service.Session;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Session;
import com.se.EC.Mapper.SessionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService extends MppServiceImpl<SessionMapper, Session> implements SessionServiceInterface {
    @Resource
    private SessionMapper sessionMapper;

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
        } else if (count > 1 || count1 > 1) {  // 多于一个出错
            throw new RuntimeException("There are two session between " + senderId + " and " + receiverId);
        }
    }

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

    @Override
    public List<Integer> getSession(Integer id) {
        // 获取所有会话
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", id);
        List<Session> sessionList = sessionMapper.selectList(queryWrapper);

        // 转换
        List<Integer> idList = new ArrayList<>();
        for (var item : sessionList) {
            Integer receiverId = item.getReceiverId();
            idList.add(receiverId);
        }

        return idList;
    }

    @Override
    public LocalDateTime getUpdateTime(Integer senderId, Integer receiverId) {
        QueryWrapper<Session> sessionQueryWrapper = new QueryWrapper<>();
        sessionQueryWrapper.eq("senderId", senderId);
        sessionQueryWrapper.eq("receiverId", receiverId);
        List<Session> sessionList = sessionMapper.selectList(sessionQueryWrapper);
        if (sessionList.size() == 0) {
            throw new RuntimeException("Session does not exist");
        } else if (sessionList.size() > 1) {
            throw new RuntimeException("There are many session between " + senderId + " and " + receiverId);
        }
        return sessionList.get(0).getUpdateTime();
    }

    @Override
    public void updateUpdateTime(Integer senderId, Integer receiverId, LocalDateTime updateTime) {
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", senderId);
        queryWrapper.eq("receiverId", receiverId);
        Session session = new Session(senderId, receiverId, updateTime);
        sessionMapper.update(session, queryWrapper);
    }

    /**
     * 查看两个id建立的会话数量
     * @param senderId 发起者
     * @param receiverId 接收者
     * @return 数目
     */
    @Override
    public Long sessionCount(Integer senderId, Integer receiverId) {
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", senderId);
        queryWrapper.eq("receiverId", receiverId);
        return sessionMapper.selectCount(queryWrapper);
    }
}
