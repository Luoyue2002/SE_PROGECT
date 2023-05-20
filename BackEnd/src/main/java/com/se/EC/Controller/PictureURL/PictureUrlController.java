package com.se.EC.Controller.PictureURL;

import com.se.EC.Service.PictureURL.PictureUrlServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class PictureUrlController {
    @Resource
    private PictureUrlServiceInterface pictureUrlServiceInterface;
}
