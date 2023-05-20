package com.se.EC.Service.PictureURL.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.PictureUrl;
import com.se.EC.Mapper.PictureUrlMapper;
import com.se.EC.Service.PictureURL.PictureUrlServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class PictureUrlService extends ServiceImpl<PictureUrlMapper, PictureUrl> implements PictureUrlServiceInterface {
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
