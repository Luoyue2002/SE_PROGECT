package com.se.EC.Service.Order;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Order;
import com.se.EC.Mapper.OrderMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

@Service
public class OrderService extends MppServiceImpl<OrderMapper, Order> implements OrderServiceInterface {
    @Resource
    private OrderMapper orderMapper;
}
