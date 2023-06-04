package com.se.EC.Service.Friend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Friend;
import com.se.EC.Mapper.FriendMapper;
import com.se.EC.Pojo.FriendState;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService extends MppServiceImpl<FriendMapper, Friend> implements FriendServiceInterface {
    @Resource
    private FriendMapper friendMapper;

    @Override
    public void requestAddFriend(Integer adderId, Integer friendId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user1", adderId);
        queryWrapper.eq("user2", friendId);
        Long count = friendMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("You have given an request or been friends with this people");
        }
        Friend friend = new Friend(adderId, friendId, FriendState.Request.value());
        Friend friend1 = new Friend(friendId, adderId, FriendState.Wait.value());
        friendMapper.insert(friend);
        friendMapper.insert(friend1);
    }

    @Override
    public List<Friend> getFriendRequestList(Integer userId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user2", userId);
        queryWrapper.eq("state", FriendState.Request.value());
        return friendMapper.selectList(queryWrapper);
    }

    @Override
    public void commitFriend(Integer launcherId, Integer answererId) {
        QueryWrapper<Friend> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user1", launcherId);
        queryWrapper1.eq("user2", answererId);
        Friend friend = friendMapper.selectOne(queryWrapper1);
        if (friend.getState() != FriendState.Request.value()) {
            throw new RuntimeException("The user " + launcherId + " does not request to add friend with user " + answererId);
        }

        Friend friend1 = new Friend(launcherId, answererId, FriendState.Commit.value());
        friendMapper.update(friend1, queryWrapper1);

        QueryWrapper<Friend> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user1", answererId);
        queryWrapper2.eq("user2", launcherId);

        Friend friend2 = new Friend(answererId, launcherId, FriendState.Commit.value());
        friendMapper.update(friend2, queryWrapper2);
    }

    @Override
    public List<Friend> getFriendList(Integer userId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user1", userId);
        queryWrapper.eq("state", FriendState.Commit.value());
        return friendMapper.selectList(queryWrapper);
    }
}
