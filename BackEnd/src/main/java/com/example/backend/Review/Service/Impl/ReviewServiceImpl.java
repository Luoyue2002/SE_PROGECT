package com.example.backend.Review.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Review.Entity.Review;
import com.example.backend.Review.Mapper.ReviewMapper;
import com.example.backend.Review.Service.IReviewService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements IReviewService {
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
