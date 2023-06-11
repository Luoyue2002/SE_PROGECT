package com.se.EC.Controller.Order;

import com.se.EC.Entity.Order;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderControllerInterface {

    /**
     * 生成订单，将生成的订单信息返回给前端
     *
     * @param object 订单对象
     * @return 订单对象
     */
    @PostMapping("/createOrder")
    ApiResult<OrderObject> createOrder(OrderObject object);

    /**
     * 获取所有订单列表
     *
     * @return 获取订单列表
     */
    @RequestMapping("/getOrderList")
    ApiResult<List<Order>> getOrderList(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "state") Integer state);


    /**
     * 获取一个订单的详细信息
     *
     * @param orderId 订单Id
     * @return 订单的详细信息 OrderObject
     */
    @RequestMapping("/getOrderInfo")
    ApiResult<OrderObject> getOrderInfo(int orderId);

    @RequestMapping("/orderPay")
    public ApiResult<Boolean> orderPay(@RequestParam(value = "orderId") int orderId,
                                       @RequestParam(value = "userId") int userId,
                                       @RequestParam(value = "totalamount") Double totalamount,
                                       @RequestParam(value = "password") String password
    );

    /**
     * 删除订单
     *
     * @param orderId 订单Id
     * @return 成功/失败
     */
    @RequestMapping("/orderDelete")
    ApiResult<Boolean> orderDelete(int orderId);
}
