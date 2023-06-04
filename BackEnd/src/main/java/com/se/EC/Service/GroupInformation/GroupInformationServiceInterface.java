package com.se.EC.Service.GroupInformation;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.GroupInformation;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupInformationServiceInterface extends IMppService<GroupInformation> {
    void deleteGroup(Integer groupId);
    void sendMessage(Integer senderId, Integer groupId, String content);
    Integer updateMessageCount(Integer groupId, LocalDateTime updateTime);
    List<GroupInformation> updateMessage(Integer groupId, LocalDateTime updateTime);
    List<GroupInformation> retrieveAllMessage(Integer groupId);
}
