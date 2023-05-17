package com.example.backend.Chat.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Chat.Entity.Chat;
import com.example.backend.Chat.Mapper.ChatMapper;
import com.example.backend.Chat.Service.IChatService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private ChatMapper chatMapper;




    @Override
    public ApiResult start_chat() {
        return null;
    }

    @Override
    public ApiResult look_history() {
        return null;
    }

    @Override
    public ApiResult send_chat() {
        return null;
    }

    @Override
    public ApiResult recieve_chat() {
        return null;
    }
}
