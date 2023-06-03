package com.se.EC.Service.Group;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Group;

public interface GroupServiceInterface extends IMppService<Group> {
    Integer addGroup(String name, Integer manager);
    void checkGroupManager(Integer groupId, Integer managerId);
    void deleteGroup(Integer groupId);
}
