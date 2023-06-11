package com.se.EC.Service.Order;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Entity.Order;
import java.util.List;

public interface OrderServiceInterface extends IMppService<Order> {
    OrderObject createOrder(OrderObject orderCreateObject);
    List<Order> getOrderList(int userId, int state);
    Order getOrderInfo(int orderId);
    boolean orderPay(int orderId, Double totalamount , String password);
    boolean orderDelete(int orderId);
    boolean changeOrderState(int orderId , int state);
}


