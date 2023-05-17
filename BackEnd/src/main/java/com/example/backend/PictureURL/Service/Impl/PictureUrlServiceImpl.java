package com.example.backend.PictureURL.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.PictureURL.Entity.PictureUrl;
import com.example.backend.PictureURL.Mapper.PictureUrlMapper;
import com.example.backend.PictureURL.Service.IPictureUrlService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class PictureUrlServiceImpl extends ServiceImpl<PictureUrlMapper, PictureUrl> implements IPictureUrlService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private PictureUrlMapper urlMapper;



    @Override
    public ApiResult save_picture() {
        return null;
    }

    @Override
    public ApiResult get_picture() {
        return null;
    }
}
