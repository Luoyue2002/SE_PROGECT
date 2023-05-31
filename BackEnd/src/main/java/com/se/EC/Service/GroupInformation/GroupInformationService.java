package com.se.EC.Service.GroupInformation;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.GroupInformation;
import com.se.EC.Mapper.GroupInformationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupInformationService extends MppServiceImpl<GroupInformationMapper, GroupInformation> implements GroupInformationServiceInterface {
    @Resource
    private GroupInformationMapper groupInformationMapper;
}
