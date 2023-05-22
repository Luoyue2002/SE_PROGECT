package com.se.EC.Service.Preview;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Preview;
import com.se.EC.Mapper.PreviewMapper;
import jakarta.annotation.Resource;

public class PreviewService extends MppServiceImpl<PreviewMapper, Preview> implements PreviewServiceInterface {
    @Resource
    PreviewMapper previewMapper;
}
