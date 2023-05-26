package com.se.EC.Service.Preview;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Preview;
import com.se.EC.Mapper.PreviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreviewService extends MppServiceImpl<PreviewMapper, Preview> implements PreviewServiceInterface {
    @Resource
    PreviewMapper previewMapper;
}
