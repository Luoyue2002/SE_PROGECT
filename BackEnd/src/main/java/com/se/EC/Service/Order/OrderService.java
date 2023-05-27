package com.se.EC.Service.Order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Controller.Order.OrderItemObject;
import com.se.EC.Controller.Order.OrderObject;
import com.se.EC.Entity.Order;
import com.se.EC.Entity.OrderItem;
import com.se.EC.Mapper.OrderItemMapper;
import com.se.EC.Mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderObject createOrder(OrderObject orderCreateObject) {

        List<OrderItemObject> itemList = orderCreateObject.getItemObjectList();
        int buyer = orderCreateObject.getBuyerId();

        Order createOrder = new Order(null, orderCreateObject.getBuyerId(), orderCreateObject.getPrice(),
                orderCreateObject.getAddress(), 0, orderCreateObject.getDateTime()
        );

        orderMapper.insert(createOrder);
        // 获取主键
        int orderId = createOrder.getId();

        for (OrderItemObject orderItem : itemList) {
            OrderItem item = new OrderItem(orderId, orderItem.getItemId(), orderItem.getPublisherId(), orderItem.getName(), orderItem.getNumber(), orderItem.getPrice());
            orderItemMapper.insert(item);
        }

        orderCreateObject.setOrderId(orderId);

        return orderCreateObject;
    }

    @Override
    public List<Order> getOrderList(int userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer", userId);
        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public OrderObject getOrderInfo(int orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        Order orderNow = orderMapper.selectOne(queryWrapper);
        QueryWrapper<OrderItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("orderId", orderId);
        List<OrderItem> List = orderItemMapper.selectList(itemQueryWrapper);
        List<OrderItemObject> itemList = new ArrayList<>();
        for (OrderItem item : List) {
            OrderItemObject itemObject = new OrderItemObject();
            itemObject.setItemId(item.getItemId());
            itemObject.setNumber(item.getNumber());
            itemObject.setPrice(item.getPrice());
            itemObject.setName(item.getName());

            itemList.add(itemObject);

        }
        return new OrderObject(orderNow.getId(), orderNow.getBuyer(), orderNow.getState()
                , orderNow.getAddress(), orderNow.getPrice(), orderNow.getTime(), itemList);

    }

    @Override
    public boolean orderPay(int orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setState(2);
        orderMapper.updateById(order);
        return true;
    }
}
