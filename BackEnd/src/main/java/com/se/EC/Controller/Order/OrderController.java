package com.se.EC.Controller.Order;

import com.se.EC.Service.Order.OrderServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/order") // url 指定
public class OrderController implements OrderControllerInterface{
    @Resource
    private OrderServiceInterface orderServiceInterface;

    @Override
    @PostMapping("/createOrder")
    public ApiResult<OrderObject> createOrder(OrderObject object ) {
        try {
            return ApiResult.success(orderServiceInterface.createOrder(object));
        }
        catch (Exception e){
            return ApiResult.error("unknown error!");
        }

    }

    @Override
    @RequestMapping("/getOrderList")
    public ApiResult getOrderList( int userId) {
        try {
            return ApiResult.success(orderServiceInterface.getOrderList(userId));
        }
        catch (Exception e){
            return ApiResult.error("unknown error!");
        }
    }
    @RequestMapping("/getOrderInfo")
    public ApiResult getOrderInfo( int userId) {
        try {
            return ApiResult.success(orderServiceInterface.getOrderInfo(userId));
        }
        catch (Exception e){
            return ApiResult.error("unknown error!");
        }
    }


    @RequestMapping("/orderPay")
    public ApiResult orderPay( int userId) {
        try {
            return ApiResult.success(orderServiceInterface.orderPay(userId));
        }
        catch (Exception e){
            return ApiResult.error("unknown error!");
        }
    }

}
