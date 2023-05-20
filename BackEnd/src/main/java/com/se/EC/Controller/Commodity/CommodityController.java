package com.se.EC.Controller.Commodity;

import com.se.EC.Service.Commodity.CommodityServiceInterface;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class CommodityController {
    @Resource
    private CommodityServiceInterface commodityServiceInterface;
}
