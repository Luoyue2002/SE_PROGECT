package com.se.EC.Service.Chat.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Chat;
import com.se.EC.Entity.User;
import com.se.EC.Mapper.ChatMapper;
import com.se.EC.Mapper.UserMapper;
import com.se.EC.Service.Chat.ChatServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Date;

@Service
public class ChatService extends ServiceImpl<ChatMapper, Chat> implements ChatServiceInterface {
    @Resource
    private ChatMapper chatMapper;
//
//    @Override
//    public ApiResult sendMessage(Integer senderId, Integer receiverId, String content) {
//        Date date = new Date();
//        return ApiResult.success();
//    }
}
