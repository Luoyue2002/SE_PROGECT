package com.se.EC.Controller.Chat;

import com.se.EC.Pojo.ChatInformation;
import com.se.EC.Pojo.SessionInformation;
import com.se.EC.Service.Chat.ChatServiceInterface;
import com.se.EC.Service.Session.SessionServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController implements ChatControllerInterface {
    @Resource
    private ChatServiceInterface chatServiceInterface;
    @Resource
    private SessionServiceInterface sessionServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;

    @Override
    @RequestMapping("/createSession")
    public ApiResult<Boolean> createSession(@RequestParam(value = "senderId") Integer senderId,
                                            @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            sessionServiceInterface.createSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/dropSession")
    public ApiResult<Boolean> dropSession(@RequestParam(value = "senderId") Integer senderId,
                                          @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            sessionServiceInterface.dropSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/getSession")
    public ApiResult<List<SessionInformation>> getSession(@RequestParam(value = "id") Integer id) {
        checkUser(id);

        List<Integer> idList = sessionServiceInterface.getSession(id);
        List<SessionInformation> sessionInformationList = new ArrayList<>();
        for (var item: idList) {
            LocalDateTime updateTime = sessionServiceInterface.getUpdateTime(item, id);
            Integer unreadCount = chatServiceInterface.updateMessageCount(item, id, updateTime);
            String name = userServiceInterface.getNameById(item);
            SessionInformation sessionInformation = new SessionInformation(item, unreadCount, name);
            sessionInformationList.add(sessionInformation);
        }
        return ApiResult.success(sessionInformationList);
    }

    @Override
    @RequestMapping("/sendMessage")
    public ApiResult<Boolean> sendMessage(@RequestParam(value = "senderId") Integer senderId,
                                          @RequestParam(value = "receiverId") Integer receiverId,
                                          @RequestParam(value = "content") String content) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkContent(content);
            checkSession(senderId, receiverId);
            chatServiceInterface.sendMessage(senderId, receiverId, content);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/updateMessage")
    public ApiResult<List<ChatInformation>> updateMessage(@RequestParam(value = "senderId") Integer senderId,
                                                          @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkSession(senderId, receiverId);

            LocalDateTime updateTime = sessionServiceInterface.getUpdateTime(senderId, receiverId);
            List<ChatInformation> chatInformationList1 = chatServiceInterface.updateMessage(senderId, receiverId, updateTime);
            List<ChatInformation> chatInformationList2 = chatServiceInterface.updateMessage(receiverId, senderId, updateTime);

            LocalDateTime currentTime = LocalDateTime.now();
            sessionServiceInterface.updateUpdateTime(senderId, receiverId, currentTime);

            return ApiResult.success(mergeSort(chatInformationList1, chatInformationList2));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/retrieveAllMessage")
    public ApiResult<List<ChatInformation>> retrieveAllMessage(@RequestParam(value = "senderId") Integer senderId,
                                                               @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkSession(senderId, receiverId);

            List<ChatInformation> chatInformationList1 = chatServiceInterface.retrieveAllMessage(senderId, receiverId);
            List<ChatInformation> chatInformationList2 = chatServiceInterface.retrieveAllMessage(receiverId, senderId);

            return ApiResult.success(mergeSort(chatInformationList1, chatInformationList2));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/unreadMessageCount")
    public ApiResult<Integer> unreadMessageCount(@RequestParam(value = "senderId") Integer senderId,
                                                 @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkSession(senderId, receiverId);

            checkSession(senderId, receiverId);
            LocalDateTime updateTime = sessionServiceInterface.getUpdateTime(senderId, receiverId);
            return ApiResult.success(chatServiceInterface.updateMessageCount(senderId, receiverId, updateTime));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    /**
     * 归并排序，按照时间从近到远
     * @param chatInformationList1 第一个消息列表
     * @param chatInformationList2 第二个消息列表
     * @return 排好序的消息列表
     */
    private List<ChatInformation> mergeSort(List<ChatInformation> chatInformationList1, List<ChatInformation> chatInformationList2) {
        chatInformationList1.addAll(chatInformationList2);
        chatInformationList1.sort((o1, o2) -> {
            if (o1.getTime().isAfter(o2.getTime())) {
                return -1;
            } else if (o1.getTime().equals(o2.getTime())) {
                return 0;
            } else {
                return 1;
            }
        });
        return chatInformationList1;
    }

    /**
     * 检查用户是否存在
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }

    /**
     * 检查消息长度是否合法
     * @param content 消息内容
     */
    private void checkContent(String content) {
        if (content.length() > 128) {
            throw new RuntimeException("Content is too long");
        }
    }

    /**
     * 检查两者会话是否存在
     * @param senderId 发送者id
     * @param receiverId 接收者id
     */
    private void checkSession(Integer senderId, Integer receiverId) {
        if (sessionServiceInterface.sessionCount(senderId, receiverId) <= 0) {
            throw new RuntimeException("There are no session created between " + senderId + " and " + receiverId);
        }
    }
}

