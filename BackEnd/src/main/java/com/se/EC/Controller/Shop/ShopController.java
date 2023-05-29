package com.se.EC.Controller.Shop;

import com.se.EC.Pojo.CommodityObject;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.OrderItem.OrderItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController implements ShopControllerInterface {
    @Resource
    private CommodityServiceInterface commodityServiceInterface;
    @Resource
    private OrderItemServiceInterface orderItemServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;

    @PostMapping("/addCommodity")
    public ApiResult<CommodityObject> addCommodity(@RequestBody CommodityObject commodityObject) {
        try {
            Integer userId = commodityObject.getPublisherId();
            checkUser(userId);
            CommodityObject commodity = commodityServiceInterface.addCommodity(commodityObject);
            commodity = itemServiceInterface.addCommodityItem(commodity);
            return ApiResult.success(commodity);
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @RequestMapping("/deleteCommodity")
    public ApiResult<String> deleteCommodity(@RequestParam(value = "CommodityId") Integer commodityId) {
        checkCommodity(commodityId);
        boolean couldDelete = orderItemServiceInterface.commodityInOrder(commodityId);
        if (couldDelete) {
            commodityServiceInterface.deleteCommodity(commodityId);
            itemServiceInterface.deleteCommodity(commodityId);
        } else {
            return ApiResult.error("have unfinished order");
        }
        return ApiResult.success("delete success");
    }

    /**
     * 检查商品是否存在
     * @param id 商品id
     */
    private void checkCommodity(Integer id) {
        if (!commodityServiceInterface.ifCommodityExists(id)) {
            throw new RuntimeException("Commodity " + id + " does not exist");
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
