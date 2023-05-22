package com.se.EC.Controller.Review;

import com.se.EC.Service.Review.ReviewServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/review") // url 指定
public class ReviewController {
    @Resource
    private ReviewServiceInterface reviewServiceInterface;
}
