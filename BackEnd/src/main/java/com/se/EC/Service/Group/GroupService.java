package com.se.EC.Service.Group;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Group;
import com.se.EC.Mapper.GroupMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends MppServiceImpl<GroupMapper, Group> implements GroupServiceInterface {
    @Resource
    private GroupMapper groupMapper;
}
