package com.se.EC.Service.OrderItem;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Pojo.OrderItemObject;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Entity.Order;
import com.se.EC.Entity.OrderItem;

import java.util.List;

public interface OrderItemServiceInterface extends IMppService<OrderItem> {
    OrderObject createOrder(OrderObject orderCreateObject);
    List<Order> getOrderList(int userId);
    List<OrderItemObject> getOrderInfo(int orderId);
    boolean orderPay(int orderId);
    boolean orderDelete(int orderId);
    boolean commodityInOrder(int commodityId);
}
