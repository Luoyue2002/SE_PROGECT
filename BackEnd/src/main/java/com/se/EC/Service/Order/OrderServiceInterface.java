package com.se.EC.Service.Order;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Controller.Order.OrderObject;
import com.se.EC.Entity.Order;

import java.util.List;

public interface OrderServiceInterface extends IMppService<Order> {


    public OrderObject createOrder(OrderObject orderCreateObject);

    public List<Order> getOrderList(int userId);


    public OrderObject getOrderInfo(int orderId);



    public boolean orderPay(int orderId);


}


