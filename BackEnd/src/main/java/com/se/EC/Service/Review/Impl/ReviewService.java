package com.se.EC.Service.Review.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Review;
import com.se.EC.Mapper.ReviewMapper;
import com.se.EC.Service.Review.ReviewServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ReviewService extends ServiceImpl<ReviewMapper, Review> implements ReviewServiceInterface {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private ReviewMapper reviewMapper;


    @Override
    public ApiResult review_list() {
        return null;
    }

    @Override
    public ApiResult review_publish() {
        return null;
    }

    @Override
    public ApiResult review_delete() {
        return null;
    }
}
