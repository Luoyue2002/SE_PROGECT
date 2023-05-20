package com.se.EC.Service.Order.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Order;
import com.se.EC.Mapper.OrderMapper;
import com.se.EC.Service.Order.OrderServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;


@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> implements OrderServiceInterface {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private OrderMapper orderMapper;

    @Override
    public ApiResult order_search() {
        return null;
    }

    @Override
    public ApiResult order_create() {
        return null;
    }

    @Override
    public ApiResult order_cancle() {
        return null;
    }

    @Override
    public ApiResult order_pay() {
        return null;
    }
}
