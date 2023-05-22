package com.se.EC.Service.Detail;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Detail;
import com.se.EC.Mapper.DetailMapper;
import jakarta.annotation.Resource;

public class DetailService extends MppServiceImpl<DetailMapper, Detail> implements DetailServiceInterface {
    @Resource
    DetailMapper detailMapper;
}
