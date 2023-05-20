package com.se.EC.Controller.Cart;

import com.se.EC.Service.Cart.CartServiceInterface;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class CartController {
    @Resource
    private CartServiceInterface cartServiceInterface;
}
