package com.example.backend.Order.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Order.Entity.Order;
import com.example.backend.Order.Mapper.OrderMapper;
import com.example.backend.Order.Service.IOrderService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
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
