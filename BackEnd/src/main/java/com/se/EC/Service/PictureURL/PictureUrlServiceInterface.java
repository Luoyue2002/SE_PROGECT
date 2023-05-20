package com.se.EC.Service.PictureURL;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.PictureUrl;
import com.se.EC.Utils.ApiResult;

public interface PictureUrlServiceInterface extends IService<PictureUrl> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult save_picture();

    ApiResult get_picture();

}


