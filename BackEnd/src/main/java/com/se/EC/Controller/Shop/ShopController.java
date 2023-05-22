package com.se.EC.Controller.Shop;

import com.se.EC.Service.Merchant.ShopServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/shop") // url 指定
public class ShopController {
    @Resource
    private ShopServiceInterface shopServiceInterface;
}
