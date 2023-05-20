package com.se.EC.Service.Review;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.Review;
import com.se.EC.Utils.ApiResult;

public interface ReviewServiceInterface extends IService<Review> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult review_list( );

    ApiResult review_publish( );

    ApiResult review_delete( );

}


