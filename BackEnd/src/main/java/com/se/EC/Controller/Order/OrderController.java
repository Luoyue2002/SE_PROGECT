package com.se.EC.Controller.Order;

import com.se.EC.Entity.Item;
import com.se.EC.Entity.Order;
import com.se.EC.Pojo.OrderItemObject;
import com.se.EC.Pojo.OrderObject;
import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
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
    private OrderItemServiceInterface orderItemServiceInterface;
    @Resource
    private CommodityServiceInterface commodityServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;

    @Resource
    private CartServiceInterface cartServiceInterface;
    @Override
    @PostMapping("/createOrder")
    public ApiResult<OrderObject> createOrder(@RequestBody OrderObject object) {
        try {
            Integer userId = object.getBuyerId();
            checkUser(userId);
            int itemNumber = object.getItemObjectList().size();
            if (itemNumber == 0) {
                throw new RuntimeException("Item number can not be 0");
            }
            for(OrderItemObject item : object.getItemObjectList()){
                Item itemNow = itemServiceInterface.getItemById(item.getItemId());
                if(itemNow.getNumber()<item.getNumber()){
                    return ApiResult.error("库存不足,订单创建失败");
                }
            }
            OrderObject order = orderServiceInterface.createOrder(object);
            order = orderItemServiceInterface.createOrder(order);
            for(OrderItemObject item : object.getItemObjectList()){
                itemServiceInterface.changeNumber(item.getItemId(),-1 * item.getNumber());
                Integer itemId = item.getItemId();
                Integer commodityId = itemServiceInterface.getParentId(itemId);
                commodityServiceInterface.addSales(commodityId, item.getNumber());
            }
            return ApiResult.success(order);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getOrderList")
    public ApiResult<List<Order>> getOrderList(@RequestParam(value = "userId") Integer userId,
                                               @RequestParam(value = "state") Integer state) {
        try {
            checkUser(userId);
            return ApiResult.success(orderServiceInterface.getOrderList(userId, state));
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
    public ApiResult<Boolean> orderPay(@RequestParam(value = "orderId") int orderId,
                                       @RequestParam(value = "userId") int userId,
    @RequestParam(value = "totalamount") Double totalamount,
    @RequestParam(value = "password") String password
    ) {
        try {
            Order ordernow = orderServiceInterface.getOrderInfo(orderId);
            int buyerId = ordernow.getBuyer();
            if(buyerId!=userId){
                return ApiResult.error("incorrect user of order");
            }
            userServiceInterface.changeBalance(userId,totalamount*(-1),password);
            orderServiceInterface.orderPay(orderId , totalamount,password);
            List<OrderItemObject> itemObjectList = orderItemServiceInterface.getOrderInfo(orderId);
            for(OrderItemObject temp : itemObjectList){
                cartServiceInterface.deleteCart(userId,temp.getItemId());
            }
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/orderDelete")
    public ApiResult<Boolean> orderDelete(@RequestParam(value = "orderId") int orderId) {
        try {
            boolean success = orderServiceInterface.orderDelete(orderId);
            if (!success) {
                return ApiResult.error("This order is not accomplished, you can not delete it");
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
