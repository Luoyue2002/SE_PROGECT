package com.se.EC.Controller.Chat;

import com.se.EC.Entity.User;
import com.se.EC.Pojo.ChatInformation;
import com.se.EC.Pojo.GroupChatInformation;
import com.se.EC.Pojo.GroupInformation;
import com.se.EC.Pojo.SessionInformation;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ChatControllerInterface {
    /**
     * 新增会话，商店界面点击`聊天`按钮与商家建立会话，建立会话后方可发送消息。
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @return 是否添加成功
     */
    @RequestMapping("/createSession")
    ApiResult<Boolean> createSession(@RequestParam(value = "senderId") Integer senderId,
                                     @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 删除会话，聊天界面点击`清空聊天记录`按钮删除会话。
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @return 是否删除成功
     */
    @RequestMapping("/dropSession")
    ApiResult<Boolean> dropSession(@RequestParam(value = "senderId") Integer senderId,
                                   @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 获取会话
     *
     * @param id id
     */
    @RequestMapping("/getSession")
    ApiResult<List<SessionInformation>> getSession(@RequestParam(value = "id") Integer id);

    /**
     * 发送消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @param content    信息
     * @return 对方是否在线
     */
    @RequestMapping("/sendMessage")
    ApiResult<Boolean> sendMessage(@RequestParam(value = "senderId") Integer senderId,
                                   @RequestParam(value = "receiverId") Integer receiverId,
                                   @RequestParam(value = "content") String content);

    /**
     * 接受新增消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，从上一次update之后开始，最开始的是最近的消息
     */
    @RequestMapping("/updateMessage")
    ApiResult<List<ChatInformation>> updateMessage(@RequestParam(value = "senderId") Integer senderId,
                                                   @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 接受所有消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，最开始的是最近的消息
     */
    @RequestMapping("/retrieveAllMessage")
    ApiResult<List<ChatInformation>> retrieveAllMessage(@RequestParam(value = "senderId") Integer senderId,
                                                        @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 未读消息数量
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id，即发送请求的那个id
     * @return 未读消息数量
     */
    @RequestMapping("/unreadMessageCount")
    ApiResult<Integer> unreadMessageCount(@RequestParam(value = "senderId") Integer senderId,
                                          @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 查找朋友
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @RequestMapping("searchPeopleByName")
    ApiResult<User> searchPeopleByName(@RequestParam(value = "userName") String userName);

    @RequestMapping("searchPeopleById")
    ApiResult<User> searchPeopleById(@RequestParam(value = "userId") Integer userId);

    @RequestMapping("searchPeopleByPhone")
    ApiResult<User> searchPeopleByPhone(@RequestParam(value = "phone") String phone);

    /**
     * 请求添加朋友
     *
     * @param adderId  请求发起者
     * @param friendId 请求接收者
     * @return 成功/失败
     */
    @RequestMapping("requestAddFriend")
    ApiResult<Boolean> requestAddFriend(@RequestParam(value = "adderId") Integer adderId,
                                        @RequestParam(value = "friendId") Integer friendId);

    /**
     * 获取朋友请求列表
     *
     * @param userId 用户id
     * @return 朋友请求列表
     */
    @RequestMapping("getFriendRequestList")
    ApiResult<List<User>> getFriendRequestList(@RequestParam(value = "userId") Integer userId);

    /**
     * 确认朋友
     *
     * @param launcherId 请求发起者
     * @param answererId 请求接收者
     * @return 成功/失败
     */
    @RequestMapping("commitFriend")
    ApiResult<Boolean> commitFriend(@RequestParam(value = "launcher") Integer launcherId,
                                    @RequestParam(value = "answerer") Integer answererId);

    /**
     * 获取朋友列表
     *
     * @param userId 用户id
     * @return 朋友列表
     */
    @RequestMapping("/getFriend")
    ApiResult<List<User>> getFriendList(@RequestParam(value = "userId") Integer userId);

    /**
     * 创建群聊
     *
     * @param groupName    群聊名
     * @param manager      群主id
     * @param memberIdList 成员id
     * @return 成功/失败
     */
    @RequestMapping("addGroup")
    ApiResult<Boolean> addGroup(@RequestParam(value = "groupName") String groupName,
                                @RequestParam(value = "manager") Integer manager,
                                @RequestParam(value = "memberIdList") List<Integer> memberIdList);

    /**
     * 删除群聊
     *
     * @param groupId   群聊id
     * @param managerId 群主id
     * @return 成功/失败
     */
    @RequestMapping("deleteGroup")
    ApiResult<Boolean> deleteGroup(@RequestParam(value = "groupId") Integer groupId,
                                   @RequestParam(value = "managerId") Integer managerId);

    /**
     * 退出群聊
     *
     * @param groupId 群聊id
     * @param userId  用户id
     * @return 成功/失败
     */
    @RequestMapping("exitGroup")
    ApiResult<Boolean> exitGroup(@RequestParam(value = "groupId") Integer groupId,
                                 @RequestParam(value = "userId") Integer userId);

    /**
     * 发送群聊消息
     *
     * @param senderId 发送者id
     * @param groupId  群聊id
     * @param content  内容
     * @return 成功/失败
     */
    @RequestMapping("/sendGroupMessage")
    ApiResult<Boolean> sendGroupMessage(@RequestParam(value = "senderId") Integer senderId,
                                        @RequestParam(value = "groupId") Integer groupId,
                                        @RequestParam(value = "content") String content);

    /**
     * 获取群聊
     *
     * @param id id
     */
    @RequestMapping("/getGroup")
    ApiResult<List<GroupInformation>> getGroup(@RequestParam(value = "id") Integer id);

    /**
     * 接受新增消息函数
     *
     * @param groupId    群聊id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，从上一次update之后开始，最开始的是最近的消息
     */
    @RequestMapping("/updateGroupMessage")
    ApiResult<List<GroupChatInformation>> updateGroupMessage(@RequestParam(value = "groupId") Integer groupId,
                                                             @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 接受所有消息函数
     *
     * @param groupId    群聊id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，最开始的是最近的消息
     */
    @RequestMapping("/retrieveGroupAllMessage")
    ApiResult<List<GroupChatInformation>> retrieveGroupAllMessage(@RequestParam(value = "groupId") Integer groupId,
                                                                  @RequestParam(value = "receiverId") Integer receiverId);
}
