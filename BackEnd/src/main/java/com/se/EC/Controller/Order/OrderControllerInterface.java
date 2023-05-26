package com.se.EC.Controller.Order;

import com.se.EC.Utils.ApiResult;

public interface OrderControllerInterface {

    /**
     * 生成订单，将生成的订单信息返回给前端
     * @param object 订单对象
     * @return
     */
    public ApiResult<OrderObject> createOrder(OrderObject object );

    /**
     * 获取所有订单列表
     * @return
     */
    public ApiResult getOrderList( int userId );
}
