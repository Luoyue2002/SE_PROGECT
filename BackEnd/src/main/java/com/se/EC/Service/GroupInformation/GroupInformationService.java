package com.se.EC.Service.GroupInformation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.GroupInformation;
import com.se.EC.Mapper.GroupInformationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupInformationService extends MppServiceImpl<GroupInformationMapper, GroupInformation> implements GroupInformationServiceInterface {
    @Resource
    private GroupInformationMapper groupInformationMapper;

    @Override
    public void deleteGroup(Integer groupId) {
        QueryWrapper<GroupInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        groupInformationMapper.delete(queryWrapper);
    }

    @Override
    public void sendMessage(Integer senderId, Integer groupId, String content) {
        LocalDateTime currentTime = LocalDateTime.now();
        QueryWrapper<GroupInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("senderId", senderId);
        queryWrapper.eq("groupId", groupId);
        queryWrapper.eq("time", currentTime);
        Long count = groupInformationMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("Send message too frequent, please try again later");
        } else {
            GroupInformation groupInformation = new GroupInformation(groupId, senderId, content, currentTime);
            groupInformationMapper.insert(groupInformation);
        }
    }

    @Override
    public Integer updateMessageCount(Integer groupId, LocalDateTime updateTime) {
        QueryWrapper<GroupInformation> chatQueryWrapper = new QueryWrapper<>();
        chatQueryWrapper.eq("groupId", groupId);
        chatQueryWrapper.ge("time", updateTime);
        return groupInformationMapper.selectCount(chatQueryWrapper).intValue();
    }

    @Override
    public List<GroupInformation> updateMessage(Integer groupId, LocalDateTime updateTime) {
        QueryWrapper<GroupInformation> chatQueryWrapper = new QueryWrapper<>();
        chatQueryWrapper.eq("groupId", groupId);
        chatQueryWrapper.ge("time", updateTime);
        return groupInformationMapper.selectList(chatQueryWrapper);
    }

    @Override
    public List<GroupInformation> retrieveAllMessage(Integer groupId) {
        QueryWrapper<GroupInformation> chatQueryWrapper = new QueryWrapper<>();
        chatQueryWrapper.eq("groupId", groupId);
        return groupInformationMapper.selectList(chatQueryWrapper);
    }
}
