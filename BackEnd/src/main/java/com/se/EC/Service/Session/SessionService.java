package com.se.EC.Service.Session;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Session;
import com.se.EC.Mapper.SessionMapper;
import jakarta.annotation.Resource;

public class SessionService extends MppServiceImpl<SessionMapper, Session> implements SessionServiceInterface {
    @Resource
    private SessionMapper sessionMapper;
}
