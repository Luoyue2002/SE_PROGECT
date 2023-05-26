package com.se.EC.Controller.Chat;

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
    ApiResult<List<Information>> updateMessage(@RequestParam(value = "senderId") Integer senderId,
                                               @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 接受所有消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，最开始的是最近的消息
     */
    @RequestMapping("/retrieveAllMessage")
    ApiResult<List<Information>> retrieveAllMessage(@RequestParam(value = "senderId") Integer senderId,
                                                    @RequestParam(value = "receiverId") Integer receiverId);

    /**
     * 未读消息数量
     *
     * @param senderId 发送者id
     * @param receiverId 接收者id，即发送请求的那个id
     * @return 未读消息数量
     */
    @RequestMapping("/unreadMessageCount")
    ApiResult<Integer> unreadMessageCount(@RequestParam(value = "senderId") Integer senderId,
                                          @RequestParam(value = "receiverId") Integer receiverId);
}
