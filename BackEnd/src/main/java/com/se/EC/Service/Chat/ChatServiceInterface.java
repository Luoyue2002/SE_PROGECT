package com.se.EC.Service.Chat;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Pojo.ChatInformation;
import com.se.EC.Entity.Chat;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatServiceInterface extends IMppService<Chat> {
    /**
     * 发送消息
     *
     * @param senderId   发起者id
     * @param receiverId 被发起者id
     * @param content    消息内容
     */
    void sendMessage(Integer senderId, Integer receiverId, String content);

    /**
     * 获取更新消息
     *
     * @param senderId   发起者id
     * @param receiverId 请求接收者id
     * @return error信息
     */
    List<ChatInformation> updateMessage(Integer senderId, Integer receiverId, LocalDateTime updateTime);

    /**
     * 获取更新消息的数量
     *
     * @param senderId   发起者id
     * @param receiverId 请求接收者id
     * @return error信息
     */
    Integer updateMessageCount(Integer senderId, Integer receiverId, LocalDateTime updateTime);

    /**
     * 获取近500条消息
     *
     * @param senderId   发起者id
     * @param receiverId 请求接收者id
     * @return error信息
     */
    List<ChatInformation> retrieveAllMessage(Integer senderId, Integer receiverId);
}