package com.se.EC.Controller.Chat;

import com.se.EC.Service.Chat.ChatServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/chat") // url 指定
public class ChatController {
    @Resource()
    private ChatServiceInterface chatServiceInterface;

    /**
     * 发送消息函数
     *
     * @param senderId：发送者id
     * @param receiverId：接收者id
     * @param content：信息
     * @return：对方是否在线
     */
    @PostMapping("/send_message")
    public ApiResult<Boolean> sendMessage(@RequestParam(value = "sender_id") Integer senderId,
                                          @RequestParam(value = "receiver_id") Integer receiverId,
                                          @RequestParam(value = "content") String content) {
        return ApiResult.success(Boolean.TRUE);
    }

    /**
     * 接受消息函数
     *
     * @param senderId：发送者id
     * @param receiverId：请求接收者的id
     * @return：
     */
    @GetMapping("/retrieve_message")
    public ApiResult<List<Information>> retrieveMessage(@RequestParam(value = "sender_id") Integer senderId,
                                             @RequestParam(value = "receiver_id") Integer receiverId) {
        return ApiResult.success();
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Information implements Serializable {
    private Date date;
    private String content;
}