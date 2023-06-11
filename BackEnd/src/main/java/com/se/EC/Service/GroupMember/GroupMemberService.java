package com.se.EC.Service.GroupMember;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.GroupMember;
import com.se.EC.Mapper.GroupMemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMemberService extends MppServiceImpl<GroupMemberMapper, GroupMember> implements GroupMemberServiceInterface {
    @Resource
    GroupMemberMapper groupMemberMapper;

    @Override
    public void addGroup(Integer groupId, Integer userId, LocalDateTime time) {
        GroupMember groupMember = new GroupMember(groupId, userId, time);
        groupMemberMapper.insert(groupMember);
    }

    @Override
    public void deleteGroup(Integer groupId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        groupMemberMapper.delete(queryWrapper);
    }

    @Override
    public void exitGroup(Integer groupId, Integer userId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        queryWrapper.eq("userId", userId);
        groupMemberMapper.delete(queryWrapper);
    }

    @Override
    public Long groupMemberNumber(Integer groupId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        return groupMemberMapper.selectCount(queryWrapper);
    }

    @Override
    public Boolean isMember(Integer groupId, Integer userId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        queryWrapper.eq("userId", userId);
        Long count = groupMemberMapper.selectCount(queryWrapper);
        return count != 0;
    }

    @Override
    public List<GroupMember> getGroup(Integer userId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        return groupMemberMapper.selectList(queryWrapper);
    }

    @Override
    public LocalDateTime getUpdateTime(Integer groupId, Integer userId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        queryWrapper.eq("userId", userId);
        GroupMember groupMember = groupMemberMapper.selectOne(queryWrapper);
        return groupMember.getUpdateTime();
    }

    @Override
    public void updateUpdateTime(Integer groupId, Integer receiverId, LocalDateTime currentTime) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        queryWrapper.eq("userId", receiverId);
        GroupMember groupMember = new GroupMember(groupId, receiverId, currentTime);
        groupMemberMapper.update(groupMember, queryWrapper);
    }

    @Override
    public List<Integer> getGroupMemberById(Integer groupId) {
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        List<GroupMember> groupMemberList = groupMemberMapper.selectList(queryWrapper);
        List<Integer> idList = new ArrayList<>();
        for (var item : groupMemberList) {
            idList.add(item.getUserId());
        }
        return idList;
    }
}
