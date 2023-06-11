package com.se.EC.Service.GroupMember;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.GroupMember;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupMemberServiceInterface extends IMppService<GroupMember> {
    void addGroup(Integer groupId, Integer userId, LocalDateTime time);
    void deleteGroup(Integer groupId);
    void exitGroup(Integer groupId, Integer userId);
    Long groupMemberNumber(Integer groupId);
    Boolean isMember(Integer groupId, Integer userId);
    List<GroupMember> getGroup(Integer userId);
    LocalDateTime getUpdateTime(Integer groupId, Integer userId);
    void updateUpdateTime(Integer groupId, Integer receiverId, LocalDateTime currentTime);
    List<Integer> getGroupMemberById(Integer groupId);
}
