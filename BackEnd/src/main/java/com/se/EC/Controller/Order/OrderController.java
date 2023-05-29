package com.se.EC.Controller.Order;

import com.se.EC.Entity.Order;
import com.se.EC.Pojo.OrderItemObject;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Service.Order.OrderServiceInterface;
import com.se.EC.Service.OrderItem.OrderItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController implements OrderControllerInterface {
    @Resource
    private OrderServiceInterface orderServiceInterface;
    @Resource
    OrderItemServiceInterface orderItemServiceInterface;
    @Resource
    UserServiceInterface userServiceInterface;

    @Override
    @PostMapping("/createOrder")
    public ApiResult<OrderObject> createOrder(@RequestBody OrderObject object) {
        try {
            Integer userId = object.getBuyerId();
            checkUser(userId);
            OrderObject order = orderServiceInterface.createOrder(object);
            order = orderItemServiceInterface.createOrder(order);
            return ApiResult.success(order);
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @Override
    @RequestMapping("/getOrderList")
    public ApiResult<List<Order>> getOrderList(@RequestParam(value = "userId") int userId) {
        try {
            checkUser(userId);
            return ApiResult.success(orderServiceInterface.getOrderList(userId));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/getOrderInfo")
    public ApiResult<OrderObject> getOrderInfo(@RequestParam(value = "orderId") int orderId) {
        try {
            Order orderNow = orderServiceInterface.getOrderInfo(orderId);
            List<OrderItemObject> itemList = orderItemServiceInterface.getOrderInfo(orderId);

            return ApiResult.success(
                    new OrderObject(orderNow.getId(), orderNow.getBuyer(), orderNow.getState()
                            , orderNow.getAddress(), orderNow.getPrice(), orderNow.getTime(), itemList)
            );
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @RequestMapping("/orderPay")
    public ApiResult<Boolean> orderPay(@RequestParam(value = "orderId") int orderId) {
        try {
            boolean success = orderServiceInterface.orderPay(orderId);
            if (!success) {
                return ApiResult.error("failed");
            }
            return ApiResult.error("success");
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @RequestMapping("/orderDelete")
    public ApiResult<Boolean> orderDelete(@RequestParam(value = "orderId") int orderId) {
        try {
            boolean success = orderServiceInterface.orderDelete(orderId);
            if (!success) {
                return ApiResult.error("failed");
            }
            orderItemServiceInterface.orderDelete(orderId);
            return ApiResult.error("success");
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    /**
     * 检查用户是否存在
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }
}