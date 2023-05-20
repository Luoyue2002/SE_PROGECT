package com.example.backend.Order.Controller;



import com.example.backend.Commodity.Controller.CommodityController;
import com.example.backend.Order.Entity.Order;
import com.example.backend.Order.Service.IOrderService;
import com.example.backend.PictureURL.Controller.PictureUrlController;
import com.example.backend.Shop.Controller.ShopController;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class OrderController {

    @Resource
    private IOrderService orderService;
//
//    private CommodityController commodityController;
//    private ShopController shopController;
//
//    public ApiResult OrderSearch(){
//
//        ApiResult res = orderService.order_search();
//        return res;
//    }
//
//    public ApiResult OrderCreate(){
//
//        ApiResult res = orderService.order_create();
//        return res;
//    }
//
//    public ApiResult OrderCancle(){
//
//        ApiResult res = orderService.order_cancle();
//        return res;
//    }
//
//    public ApiResult OrderPay(){
//
//        ApiResult res = orderService.order_pay();
//        return res;
//    }
//





}
