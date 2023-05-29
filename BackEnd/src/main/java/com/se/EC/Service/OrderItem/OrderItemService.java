package com.se.EC.Service.OrderItem;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Pojo.OrderItemObject;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Entity.Order;
import com.se.EC.Entity.OrderItem;
import com.se.EC.Mapper.OrderItemMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService extends MppServiceImpl<OrderItemMapper, OrderItem> implements OrderItemServiceInterface{

    @Resource
    private OrderItemMapper orderItemMapper;
    @Override
    public OrderObject createOrder( OrderObject orderCreateObject) {


        List<OrderItemObject> itemList = orderCreateObject.getItemObjectList();
        int buyer = orderCreateObject.getBuyerId();
        int orderId = orderCreateObject.getOrderId();
        for (OrderItemObject orderItem : itemList) {
            OrderItem item = new OrderItem(orderId,orderItem.getCommodityId() ,orderItem.getItemId(), orderItem.getPublisherId(), orderItem.getName(), orderItem.getNumber(), orderItem.getPrice());
            orderItemMapper.insert(item);
        }

        orderCreateObject.setOrderId(orderId);

        return orderCreateObject;
    }

    @Override
    public List<Order> getOrderList(int userId) {
        return null;
    }

    @Override
    public List<OrderItemObject> getOrderInfo(int orderId) {
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
        return itemList;

    }

    @Override
    public boolean orderPay(int orderId) {
        return false;
    }

    @Override
    public boolean orderDelete(int orderId) {
        QueryWrapper<OrderItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("orderId", orderId);
        orderItemMapper.delete(itemQueryWrapper);
        return true;
    }

    @Override
    public boolean commodityInOrder(int commodityId) {
        List<OrderItem> orderItemList = orderItemMapper.haveCommodityInOrder(commodityId);
        if(orderItemList.size()==0){
            return true;
        }else {
            return  false;
        }
    }

}
