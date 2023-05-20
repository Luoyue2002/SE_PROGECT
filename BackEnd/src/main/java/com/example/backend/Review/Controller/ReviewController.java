package com.example.backend.Review.Controller;



import com.example.backend.Commodity.Controller.CommodityController;
import com.example.backend.PictureURL.Controller.PictureUrlController;
import com.example.backend.Review.Entity.Review;
import com.example.backend.Review.Service.IReviewService;
import com.example.backend.Shop.Controller.ShopController;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class ReviewController {

    @Resource
    private IReviewService reviewService;

//    private CommodityController commodityController;
//
//    private ShopController shopController;
//
//    private  PictureUrlController pictureUrlController;
//
//
//    public ApiResult GetReview( ){
//        ApiResult res = reviewService.review_list();
//        return res;
//    }
//
//    public ApiResult ReviewPublish( ){
//        ApiResult res = reviewService.review_publish();
//        return res;
//    }
//
//    public ApiResult ReviewDelete( ){
//        ApiResult res = reviewService.review_delete();
//        return res;
//    }
//
//
//

}
