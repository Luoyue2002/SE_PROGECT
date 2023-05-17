package com.example.backend.Chat.Controller;



import com.example.backend.Chat.Entity.Chat;
import com.example.backend.Chat.Service.IChatService;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class ChatController {

    @Resource
    private IChatService chatService;



//
//    public ApiResult StartChat(){
//
//        ApiResult res = chatService.start_chat();
//        return res;
//    }
//
//// get 方法
//
//    public ApiResult LookHistory(){
//
//        ApiResult res = chatService.look_history();
//        return res;
//    }
//
//    public ApiResult SendChat(){
//
//        ApiResult res = chatService.send_chat();
//        return res;
//    }
//
//    public ApiResult RecieveChat(){
//
//        ApiResult res = chatService.recieve_chat();
//        return res;
//    }



}
