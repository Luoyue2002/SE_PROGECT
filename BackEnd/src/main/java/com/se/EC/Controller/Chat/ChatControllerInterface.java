package com.se.EC.Controller.Chat;

import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/create_session")
    ApiResult<Boolean> createSession(@RequestParam(value = "sender_id") Integer senderId,
                                     @RequestParam(value = "receiver_id") Integer receiverId);

    /**
     * 删除会话，聊天界面点击`清空聊天记录`按钮删除会话。
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @return 是否删除成功
     */
    @PostMapping("/drop_session")
    ApiResult<Boolean> dropSession(@RequestParam(value = "sender_id") Integer senderId,
                                   @RequestParam(value = "receiver_id") Integer receiverId);

    /**
     * 发送消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @param content    信息
     * @return 对方是否在线
     */
    @PostMapping("/send_message")
    ApiResult<Boolean> sendMessage(@RequestParam(value = "sender_id") Integer senderId,
                                   @RequestParam(value = "receiver_id") Integer receiverId,
                                   @RequestParam(value = "content") String content);

    /**
     * 接受新增消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，从上一次update之后开始，最开始的是最近的消息
     */
    @GetMapping("/update_message")
    ApiResult<List<Information>> updateMessage(@RequestParam(value = "sender_id") Integer senderId,
                                               @RequestParam(value = "receiver_id") Integer receiverId);

    /**
     * 接受所有消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，最开始的是最近的消息
     */
    @GetMapping("/retrieve_all_message")
    ApiResult<List<Information>> retrieveAllMessage(@RequestParam(value = "sender_id") Integer senderId,
                                                    @RequestParam(value = "receiver_id") Integer receiverId);
}
