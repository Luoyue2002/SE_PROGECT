package com.se.EC.Service.Order;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Controller.Order.OrderObject;
import com.se.EC.Entity.Order;
import java.util.List;

public interface OrderServiceInterface extends IMppService<Order> {
    OrderObject createOrder(OrderObject orderCreateObject);
    List<Order> getOrderList(int userId);
    Order getOrderInfo(int orderId);
    boolean orderPay(int orderId);


    boolean checkOrderDelete(int orderId);

    boolean orderDelete(int orderId);

}


