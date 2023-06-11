package com.se.EC.Controller.Chat;

import com.se.EC.Entity.Friend;
import com.se.EC.Entity.Group;
import com.se.EC.Entity.GroupMember;
import com.se.EC.Entity.User;
import com.se.EC.Pojo.*;
import com.se.EC.Service.Chat.ChatServiceInterface;
import com.se.EC.Service.Friend.FriendServiceInterface;
import com.se.EC.Service.Group.GroupServiceInterface;
import com.se.EC.Service.GroupInformation.GroupInformationServiceInterface;
import com.se.EC.Service.GroupMember.GroupMemberServiceInterface;
import com.se.EC.Service.Session.SessionServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController implements ChatControllerInterface {
    @Resource
    private ChatServiceInterface chatServiceInterface;
    @Resource
    private SessionServiceInterface sessionServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;
    @Resource
    private FriendServiceInterface friendServiceInterface;
    @Resource
    private GroupServiceInterface groupServiceInterface;
    @Resource
    private GroupMemberServiceInterface groupMemberServiceInterface;
    @Resource
    private GroupInformationServiceInterface groupInformationServiceInterface;

    @Override
    @RequestMapping("/createSession")
    public ApiResult<Boolean> createSession(@RequestParam(value = "senderId") Integer senderId,
                                            @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            if (senderId.equals(receiverId)) {
                throw new RuntimeException("Can not send message to yourself");
            }
            checkUser(senderId);
            checkUser(receiverId);
            sessionServiceInterface.createSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/dropSession")
    public ApiResult<Boolean> dropSession(@RequestParam(value = "senderId") Integer senderId,
                                          @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            sessionServiceInterface.dropSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/getSession")
    public ApiResult<List<SessionInformation>> getSession(@RequestParam(value = "id") Integer id) {
        try {
            checkUser(id);

            List<Integer> idList = sessionServiceInterface.getSession(id);
            List<SessionInformation> sessionInformationList = new ArrayList<>();
            for (var item : idList) {
                LocalDateTime updateTime = sessionServiceInterface.getUpdateTime(item, id);
                Integer unreadCount = chatServiceInterface.updateMessageCount(item, id, updateTime);
                String name = userServiceInterface.getNameById(item);
                SessionInformation sessionInformation = new SessionInformation(item, unreadCount, name);
                sessionInformationList.add(sessionInformation);
            }
            return ApiResult.success(sessionInformationList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/sendMessage")
    public ApiResult<Boolean> sendMessage(@RequestParam(value = "senderId") Integer senderId,
                                          @RequestParam(value = "receiverId") Integer receiverId,
                                          @RequestParam(value = "content") String content) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkContent(content);
            checkSession(senderId, receiverId);
            chatServiceInterface.sendMessage(senderId, receiverId, content);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/updateMessage")
    public ApiResult<List<ChatInformation>> updateMessage(@RequestParam(value = "senderId") Integer senderId,
                                                          @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkSession(senderId, receiverId);

            LocalDateTime updateTime = sessionServiceInterface.getUpdateTime(senderId, receiverId);
            List<ChatInformation> chatInformationList1 = chatServiceInterface.updateMessage(senderId, receiverId, updateTime);
            List<ChatInformation> chatInformationList2 = chatServiceInterface.updateMessage(receiverId, senderId, updateTime);

            if (chatInformationList1.size() != 0 || chatInformationList2.size() != 0) {
                LocalDateTime currentTime = LocalDateTime.now();
                sessionServiceInterface.updateUpdateTime(senderId, receiverId, currentTime);
            }

            return ApiResult.success(mergeSort(chatInformationList1, chatInformationList2));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/retrieveAllMessage")
    public ApiResult<List<ChatInformation>> retrieveAllMessage(@RequestParam(value = "senderId") Integer senderId,
                                                               @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkSession(senderId, receiverId);

            List<ChatInformation> chatInformationList1 = chatServiceInterface.retrieveAllMessage(senderId, receiverId);
            List<ChatInformation> chatInformationList2 = chatServiceInterface.retrieveAllMessage(receiverId, senderId);

            LocalDateTime currentTime = LocalDateTime.now();
            sessionServiceInterface.updateUpdateTime(senderId, receiverId, currentTime);

            return ApiResult.success(mergeSort(chatInformationList1, chatInformationList2));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/unreadMessageCount")
    public ApiResult<Integer> unreadMessageCount(@RequestParam(value = "senderId") Integer senderId,
                                                 @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(senderId);
            checkUser(receiverId);
            checkSession(senderId, receiverId);

            checkSession(senderId, receiverId);
            LocalDateTime updateTime = sessionServiceInterface.getUpdateTime(senderId, receiverId);
            return ApiResult.success(chatServiceInterface.updateMessageCount(senderId, receiverId, updateTime));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("searchPeopleByName")
    public ApiResult<User> searchPeopleByName(@RequestParam(value = "userName") String userName) {
        try {
            User user = userServiceInterface.searchByName(userName);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("searchPeopleById")
    public ApiResult<User> searchPeopleById(@RequestParam(value = "userId") Integer userId) {
        try {
            User user = userServiceInterface.searchById(userId);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("searchPeopleByPhone")
    public ApiResult<User> searchPeopleByPhone(@RequestParam(value = "phone") String phone) {
        try {
            User user = userServiceInterface.searchByPhone(phone);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("requestAddFriend")
    public ApiResult<Boolean> requestAddFriend(@RequestParam(value = "adderId") Integer adderId,
                                               @RequestParam(value = "friendId") Integer friendId) {
        try {
            friendServiceInterface.requestAddFriend(adderId, friendId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("getFriendRequestList")
    public ApiResult<List<User>> getFriendRequestList(@RequestParam(value = "userId") Integer userId) {
        try {
            List<Friend> friendList = friendServiceInterface.getFriendRequestList(userId);
            List<User> userList = new ArrayList<>();
            for (var friend : friendList) {
                Integer id = friend.getUser1();
                User user = userServiceInterface.getById(id);
                userList.add(user);
            }
            return ApiResult.success(userList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("commitFriend")
    public ApiResult<Boolean> commitFriend(@RequestParam(value = "launcher") Integer launcherId,
                                           @RequestParam(value = "answerer") Integer answererId) {
        try {
            friendServiceInterface.commitFriend(launcherId, answererId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getFriend")
    public ApiResult<List<User>> getFriendList(@RequestParam(value = "userId") Integer userId) {
        try {
            List<Friend> friendList = friendServiceInterface.getFriendList(userId);
            List<User> userList = new ArrayList<>();
            for (var friend : friendList) {
                Integer id = friend.getUser2();
                User user = userServiceInterface.getById(id);
                userList.add(user);
            }
            return ApiResult.success(userList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("addGroup")
    public ApiResult<Boolean> addGroup(@RequestParam(value = "groupName") String groupName,
                                       @RequestParam(value = "manager") Integer manager,
                                       @RequestParam(value = "memberIdList") List<Integer> memberIdList) {
        try {
            Integer groupId = groupServiceInterface.addGroup(groupName, manager);
            LocalDateTime time = LocalDateTime.now();
            groupMemberServiceInterface.addGroup(groupId, manager, time);
            for (var id : memberIdList) {
                groupMemberServiceInterface.addGroup(groupId, id, time);
            }
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("deleteGroup")
    public ApiResult<Boolean> deleteGroup(@RequestParam(value = "groupId") Integer groupId,
                                          @RequestParam(value = "managerId") Integer managerId) {
        try {
            groupServiceInterface.checkGroupManager(groupId, managerId);
            groupMemberServiceInterface.deleteGroup(groupId);
            groupInformationServiceInterface.deleteGroup(groupId);
            groupServiceInterface.deleteGroup(groupId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("exitGroup")
    public ApiResult<Boolean> exitGroup(@RequestParam(value = "groupId") Integer groupId,
                                        @RequestParam(value = "userId") Integer userId) {
        try {
            groupMemberServiceInterface.exitGroup(groupId, userId);
            Long count = groupMemberServiceInterface.groupMemberNumber(groupId);
            if (count == 0) {
                Group group = groupServiceInterface.getById(groupId);
                deleteGroup(group.getGroupId(), group.getManagerId());
            }
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/sendGroupMessage")
    public ApiResult<Boolean> sendGroupMessage(@RequestParam(value = "senderId") Integer senderId,
                                               @RequestParam(value = "groupId") Integer groupId,
                                               @RequestParam(value = "content") String content) {
        try {
            checkUser(senderId);
            checkContent(content);
            checkGroupMember(groupId, senderId);

            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getGroup")
    public ApiResult<List<GroupInformation>> getGroup(@RequestParam(value = "id") Integer id) {
        try {
            checkUser(id);

            List<GroupMember> groupMemberList = groupMemberServiceInterface.getGroup(id);
            List<GroupInformation> groupInformationList = new ArrayList<>();

            for (var item : groupMemberList) {
                Integer groupId = item.getGroupId();
                LocalDateTime updateTime = groupMemberServiceInterface.getUpdateTime(groupId, id);
                Integer unreadCount = groupInformationServiceInterface.updateMessageCount(groupId, updateTime);
                String name = groupServiceInterface.getById(groupId).getGroupName();
                GroupInformation groupInformation = new GroupInformation(groupId, unreadCount, name);
                groupInformationList.add(groupInformation);
            }
            return ApiResult.success(groupInformationList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getGroupMember")
    public ApiResult<List<GroupMemberInformation>> getGroupMember(@RequestParam(value = "userId") Integer userId,
                                                            @RequestParam(value = "groupId") Integer groupId) {
        try {
            checkUser(userId);
            List<Integer> idList = groupMemberServiceInterface.getGroupMemberById(groupId);
            List<GroupMemberInformation> groupMemberInformationList = new ArrayList<>();
            for (var item : idList) {
                User user = userServiceInterface.getById(item);
                GroupMemberInformation groupMemberInformation = new GroupMemberInformation(item, user.getName(), user.getImage());
                groupMemberInformationList.add(groupMemberInformation);
            }
            return ApiResult.success(groupMemberInformationList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/updateGroupMessage")
    public ApiResult<List<GroupChatInformation>> updateGroupMessage(@RequestParam(value = "groupId") Integer groupId,
                                                                    @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(receiverId);
            checkGroupMember(groupId, receiverId);

            LocalDateTime updateTime = groupMemberServiceInterface.getUpdateTime(groupId, receiverId);
            List<com.se.EC.Entity.GroupInformation> chatInformationList = groupInformationServiceInterface.updateMessage(groupId, updateTime);

            if (chatInformationList.size() != 0) {
                LocalDateTime currentTime = LocalDateTime.now();
                groupMemberServiceInterface.updateUpdateTime(groupId, receiverId, currentTime);
            }

            return ApiResult.success(sortAndPack(chatInformationList));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/retrieveGroupAllMessage")
    public ApiResult<List<GroupChatInformation>> retrieveGroupAllMessage(@RequestParam(value = "groupId") Integer groupId,
                                                                         @RequestParam(value = "receiverId") Integer receiverId) {
        try {
            checkUser(receiverId);
            checkGroupMember(groupId, receiverId);

            List<com.se.EC.Entity.GroupInformation> chatInformationList = groupInformationServiceInterface.retrieveAllMessage(groupId);

            LocalDateTime currentTime = LocalDateTime.now();
            groupMemberServiceInterface.updateUpdateTime(groupId, receiverId, currentTime);

            return ApiResult.success(sortAndPack(chatInformationList));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    /**
     * 排序并打包
     * @param chatInformationList Entity的列表
     * @return Pojo的列表
     */
    private List<GroupChatInformation> sortAndPack(List<com.se.EC.Entity.GroupInformation> chatInformationList) {
        chatInformationList.sort((o1, o2) -> {
            if (o1.getTime().isAfter(o2.getTime())) {
                return -1;
            } else if (o1.getTime().equals(o2.getTime())) {
                return 0;
            } else {
                return 1;
            }
        });

        List<GroupChatInformation> groupChatInformationList = new ArrayList<>();
        for (var item : chatInformationList) {
            Integer groupId = item.getGroupId();
            Integer senderId = item.getSenderId();
            LocalDateTime time = item.getTime();
            String content = item.getContent();
            GroupChatInformation groupChatInformation = new GroupChatInformation(groupId, senderId, time, content);
            groupChatInformationList.add(groupChatInformation);
        }

        return groupChatInformationList;
    }

    /**
     * 归并排序，按照时间从近到远
     *
     * @param chatInformationList1 第一个消息列表
     * @param chatInformationList2 第二个消息列表
     * @return 排好序的消息列表
     */
    private List<ChatInformation> mergeSort(List<ChatInformation> chatInformationList1, List<ChatInformation> chatInformationList2) {
        chatInformationList1.addAll(chatInformationList2);
        chatInformationList1.sort((o1, o2) -> {
            if (o1.getTime().isAfter(o2.getTime())) {
                return -1;
            } else if (o1.getTime().equals(o2.getTime())) {
                return 0;
            } else {
                return 1;
            }
        });
        return chatInformationList1;
    }

    /**
     * 检查用户是否存在
     *
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }

    /**
     * 检查消息长度是否合法
     *
     * @param content 消息内容
     */
    private void checkContent(String content) {
        if (content.length() > 128) {
            throw new RuntimeException("Content is too long");
        }
    }

    /**
     * 检查两者会话是否存在
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     */
    private void checkSession(Integer senderId, Integer receiverId) {
        if (sessionServiceInterface.sessionCount(senderId, receiverId) <= 0) {
            throw new RuntimeException("There are no session created between " + senderId + " and " + receiverId);
        }
    }

    /**
     * 检查用户是否在这个群聊中
     *
     * @param groupId 群聊id
     * @param userId  用户id
     */
    private void checkGroupMember(Integer groupId, Integer userId) {
        if (!groupMemberServiceInterface.isMember(groupId, userId)) {
            throw new RuntimeException("User " + userId + " is not a member of group " + groupId);
        }
    }
}

