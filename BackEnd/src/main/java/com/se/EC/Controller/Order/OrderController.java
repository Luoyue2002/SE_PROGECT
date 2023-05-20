package com.se.EC.Controller.Order;

import com.se.EC.Service.Order.OrderServiceInterface;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class OrderController {
    @Resource
    private OrderServiceInterface orderServiceInterface;
}
