package com.se.EC.Service.Group;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Group;
import com.se.EC.Mapper.GroupMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends MppServiceImpl<GroupMapper, Group> implements GroupServiceInterface {
    @Resource
    private GroupMapper groupMapper;

    @Override
    public Integer addGroup(String name, Integer manager) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("managerId", manager);
        queryWrapper.eq("groupName", name);
        Long count = groupMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("Can not create two group with the same name");
        }

        Group group = new Group(null, manager, name);
        groupMapper.insert(group);

        return groupMapper.selectOne(queryWrapper).getGroupId();
    }

    @Override
    public void checkGroupManager(Integer groupId, Integer managerId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        Group group = groupMapper.selectOne(queryWrapper);
        if (!group.getManagerId().equals(managerId)) {
            throw new RuntimeException("User " + managerId + " is not the manager of the group " + group.getGroupName());
        }
    }

    @Override
    public void deleteGroup(Integer groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupId", groupId);
        groupMapper.delete(queryWrapper);
    }
}
