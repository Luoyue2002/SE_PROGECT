package com.se.EC.Controller.Order;

import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderControllerInterface {

    /**
     * 生成订单，将生成的订单信息返回给前端
     * @param object 订单对象
     * @return 订单对象
     */
    public ApiResult<OrderObject> createOrder(OrderObject object );

    /**
     * 获取所有订单列表
     * @return
     */
    public ApiResult getOrderList( int userId );


    /**
     * 获取一个订单的详细信息
     * @param orderId 订单Id
     * @return  订单的详细信息 OrderObject
     */
    public ApiResult<OrderObject> getOrderInfo(int orderId );


    public ApiResult<Boolean> orderPay(int orderId );

    /**
     *  删除订单
     * @param orderId 订单Id
     * @return 成功/失败
     */
    public ApiResult<Boolean> orderDelete(int orderId );
}
