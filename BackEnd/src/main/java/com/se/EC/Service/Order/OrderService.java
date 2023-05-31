package com.se.EC.Service.Order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Entity.Order;
import com.se.EC.Mapper.OrderMapper;
import com.se.EC.Pojo.OrderState;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                orderCreateObject.getAddress(), OrderState.Unpaid.value(), LocalDateTime.now()
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
        return orderMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean orderPay(int orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setState(2);
        orderMapper.updateById(order);
        return true;
    }

    @Override
    public boolean orderDelete(int orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        Order orderNow = orderMapper.selectOne(queryWrapper);
        if(orderNow.getState() != 3){
            return false;
        }
        orderMapper.delete(queryWrapper);
        return true;
    }

    @Override
    public boolean changeOrderState(int orderId, int state) {
        Order order = new Order();
        order.setId(orderId);
        order.setState(state);
        orderMapper.updateById(order);
        return true;
    }
}
