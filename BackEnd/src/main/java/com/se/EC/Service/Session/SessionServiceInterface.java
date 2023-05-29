package com.se.EC.Service.Session;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionServiceInterface extends IMppService<Session> {
    /**
     * 创建会话
     *
     * @param senderId   发起者id
     * @param receiverId 被发起者id
     */
    void createSession(Integer senderId, Integer receiverId);

    /**
     * 删除会话
     *
     * @param senderId   发起者id
     * @param receiverId 被发起者id
     */
    void dropSession(Integer senderId, Integer receiverId);

    /**
     * 获取会话
     *
     * @param id id
     * @return id List
     */
    List<Integer> getSession(Integer id);

    /**
     * 获取会话更新时间
     *
     * @param senderId 发送者id
     * @param receiverId 接收者id
     * @return id List
     */
    LocalDateTime getUpdateTime(Integer senderId, Integer receiverId);

    /**
     * 获取会话更新时间
     *
     * @param senderId 发送者id
     * @param receiverId 接收者id
     * @param updateTime 新的更新时间
     */
    void updateUpdateTime(Integer senderId, Integer receiverId, LocalDateTime updateTime);

    /**
     * 查看两个id建立的会话数量
     * @param senderId 发起者
     * @param receiverId 接收者
     * @return 数目
     */
    Long sessionCount(Integer senderId, Integer receiverId);
}
