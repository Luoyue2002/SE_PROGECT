package com.example.backend.PictureURL.Controller;



import com.example.backend.PictureURL.Entity.PictureUrl;
import com.example.backend.PictureURL.Service.IPictureUrlService;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class PictureUrlController {

    @Resource
    private IPictureUrlService pictureUrlService;


//    ApiResult SavePicture(){
//
//        ApiResult res = pictureUrlService.save_picture();
//        return res;
//    }
//
//
//    ApiResult GetPicture(){
//
//        ApiResult res = pictureUrlService.get_picture();
//        return res;
//    }






}
