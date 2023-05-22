package com.se.EC.Service.Session;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Session;

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
}
