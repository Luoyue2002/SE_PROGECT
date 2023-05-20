package com.se.EC.Controller.Chat;

import com.se.EC.Service.Chat.ChatServiceInterface;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/chat") // url 指定
public class ChatController {
    @Resource
    private ChatServiceInterface chatServiceInterface;
}
