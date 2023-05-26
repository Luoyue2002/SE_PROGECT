package com.se.EC.Controller.Order;

import com.se.EC.Service.Order.OrderServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/order") // url 指定
public class OrderController {
    @Resource
    private OrderServiceInterface orderServiceInterface;
}
