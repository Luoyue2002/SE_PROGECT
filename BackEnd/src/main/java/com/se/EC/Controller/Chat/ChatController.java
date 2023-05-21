package com.se.EC.Controller.Chat;

import com.se.EC.Service.Chat.ChatServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/chat") // url 指定
public class ChatController implements ChatControllerInterface {
    @Resource
    private ChatServiceInterface chatServiceInterface;

    /**
     * 新增会话，商店界面点击`聊天`按钮与商家建立会话，建立会话后方可发送消息。
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @return 是否添加成功
     */
    @Override
    @RequestMapping("/create_session")
    public ApiResult<Boolean> createSession(@RequestParam(value = "sender_id") Integer senderId,
                                            @RequestParam(value = "receiver_id") Integer receiverId) {
        try {
            chatServiceInterface.createSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    /**
     * 删除会话，聊天界面点击`清空聊天记录`按钮删除会话。
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @return 是否删除成功
     */
    @Override
    @RequestMapping("/drop_session")
    public ApiResult<Boolean> dropSession(@RequestParam(value = "sender_id") Integer senderId,
                                          @RequestParam(value = "receiver_id") Integer receiverId) {
        try {
            chatServiceInterface.dropSession(senderId, receiverId);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    /**
     * 获取会话
     *
     * @param id id
     * @return SessionInformation的List
     */
    @Override
    @RequestMapping("/get_session")
    public ApiResult<List<SessionInformation>> getSession(@RequestParam(value = "id") Integer id) {
        List<SessionInformation> sessionInformationList = chatServiceInterface.getSession(id);
        return ApiResult.success(sessionInformationList);
    }

    /**
     * 发送消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @param content    信息
     * @return 对方是否在线
     */
    @Override
    @RequestMapping("/send_message")
    public ApiResult<Boolean> sendMessage(@RequestParam(value = "sender_id") Integer senderId,
                                          @RequestParam(value = "receiver_id") Integer receiverId,
                                          @RequestParam(value = "content") String content) {
        try {
            chatServiceInterface.sendMessage(senderId, receiverId, content);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    /**
     * 接受新增消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，从上一次update之后开始，最开始的是最近的消息
     */
    @Override
    @RequestMapping("/update_message")
    public ApiResult<List<Information>> updateMessage(@RequestParam(value = "sender_id") Integer senderId,
                                                      @RequestParam(value = "receiver_id") Integer receiverId) {
        try {
            List<Information> informationList = chatServiceInterface.updateMessage(senderId, receiverId);
            return ApiResult.success(informationList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    /**
     * 接受所有消息函数
     *
     * @param senderId   发送者id
     * @param receiverId 请求接收者的id
     * @return 一个包含信息的链表，信息包含时间和内容，最多500条，最开始的是最近的消息
     */
    @Override
    @RequestMapping("/retrieve_all_message")
    public ApiResult<List<Information>> retrieveAllMessage(@RequestParam(value = "sender_id") Integer senderId,
                                                           @RequestParam(value = "receiver_id") Integer receiverId) {
        try {
            List<Information> informationList = chatServiceInterface.retrieveAllMessage(senderId, receiverId);
            return ApiResult.success(informationList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }
}

