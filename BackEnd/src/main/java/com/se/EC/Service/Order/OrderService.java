package com.se.EC.Service.Order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Controller.Order.OrderItemObject;
import com.se.EC.Controller.Order.OrderObject;
import com.se.EC.Entity.Order;
import com.se.EC.Entity.OrderItem;
import com.se.EC.Mapper.OrderItemMapper;
import com.se.EC.Mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对于订单状态 0: 未支付
 * 1: 支付成功/未发货
 * 2: 已发货
 * 3: 已收货
 */
@Service
public class OrderService extends MppServiceImpl<OrderMapper, Order> implements OrderServiceInterface {
    @Resource
    private OrderMapper orderMapper;


    @Override
    public OrderObject createOrder(OrderObject orderCreateObject) {

        Order createOrder = new Order(null, orderCreateObject.getBuyerId(), orderCreateObject.getPrice(),
                orderCreateObject.getAddress(), 0, LocalDateTime.now()
        );

        orderMapper.insert(createOrder);
        // 获取主键
        orderCreateObject.setOrderId(createOrder.getId());
        return orderCreateObject;

    }

    @Override
    public List<Order> getOrderList(int userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer", userId);
        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public Order getOrderInfo(int orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        Order orderNow = orderMapper.selectOne(queryWrapper);

        return orderNow;

    }

    @Override
    public boolean orderPay(int orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setState(2);
        orderMapper.updateById(order);
        return true;
    }

    //LY
    @Override
    public boolean checkOrderDelete(int orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        Order orderNow = orderMapper.selectOne(queryWrapper);
        if(orderNow.getState()!=3){
            return false;
        }
        return true;
    }
    //LY
    public boolean orderDelete(int orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        orderMapper.delete(queryWrapper);
        return true;
    }

}
