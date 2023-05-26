package com.se.EC.Controller.Chat;

import com.se.EC.Service.Chat.ChatServiceInterface;
import com.se.EC.Service.Session.SessionService;
import com.se.EC.Service.User.UserService;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController implements ChatControllerInterface {
    @Resource
    private ChatServiceInterface chatServiceInterface;
    @Resource
    private SessionService sessionService;
    @Resource
    private UserService userService;

    @Override
    @RequestMapping("/createSession")
    public ApiResult<Boolean> createSession(@RequestParam(value = "senderId") Integer senderId,
                                            @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            sessionService.createSession(senderId, receiverId);
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
            sessionService.dropSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/getSession")
    public ApiResult<List<SessionInformation>> getSession(@RequestParam(value = "id") Integer id) {
        List<Integer> idList = sessionService.getSession(id);
        List<SessionInformation> sessionInformationList = new ArrayList<>();
        for (var item: idList) {
            String name = userService.getNameById(item);
            SessionInformation sessionInformation = new SessionInformation(item, name);
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
            chatServiceInterface.sendMessage(senderId, receiverId, content);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/updateMessage")
    public ApiResult<List<Information>> updateMessage(@RequestParam(value = "senderId") Integer senderId,
                                                      @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            LocalDateTime updateTime = sessionService.getUpdateTime(senderId, receiverId);
            List<Information> informationList1 = chatServiceInterface.updateMessage(senderId, receiverId, updateTime);
            List<Information> informationList2 = chatServiceInterface.updateMessage(receiverId, senderId, updateTime);

            LocalDateTime currentTime = LocalDateTime.now();
            sessionService.updateUpdateTime(senderId, receiverId, currentTime);

            return ApiResult.success(mergeSort(informationList1, informationList2));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/retrieveAllMessage")
    public ApiResult<List<Information>> retrieveAllMessage(@RequestParam(value = "senderId") Integer senderId,
                                                           @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            List<Information> informationList1 = chatServiceInterface.retrieveAllMessage(senderId, receiverId);
            List<Information> informationList2 = chatServiceInterface.retrieveAllMessage(receiverId, senderId);

            return ApiResult.success(mergeSort(informationList1, informationList2));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/unreadMessageCount")
    public ApiResult<Integer> unreadMessageCount(@RequestParam(value = "senderId") Integer senderId,
                                                 @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            LocalDateTime updateTime = sessionService.getUpdateTime(senderId, receiverId);
            return ApiResult.success(chatServiceInterface.updateMessageCount(senderId, receiverId, updateTime));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    private List<Information> mergeSort(List<Information> informationList1, List<Information> informationList2) {
        informationList1.addAll(informationList2);
        informationList1.sort((o1, o2) -> {
            if (o1.getTime().isAfter(o2.getTime())) {
                return -1;
            } else if (o1.getTime().equals(o2.getTime())) {
                return 0;
            } else {
                return 1;
            }
        });
        return informationList1;
    }
}

