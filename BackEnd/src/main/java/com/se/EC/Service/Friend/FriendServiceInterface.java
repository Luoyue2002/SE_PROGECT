package com.se.EC.Service.Friend;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Friend;

import java.util.List;

public interface FriendServiceInterface extends IMppService<Friend> {
    void requestAddFriend(Integer adderId, Integer friendId);
    List<Friend> getFriendRequestList(Integer userId);
    void commitFriend(Integer launcherId, Integer answererId);
    List<Friend> getFriendList(Integer userId);
}
