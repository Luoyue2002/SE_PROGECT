package com.se.EC.Controller.Shop;

import com.se.EC.Entity.Item;
import com.se.EC.Pojo.Category;
import com.se.EC.Pojo.CommodityObject;
import com.se.EC.Pojo.OrderState;
import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
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
@RequestMapping("/shop")
public class ShopController implements ShopControllerInterface {
    @Resource
    private CommodityServiceInterface commodityServiceInterface;
    @Resource
    private OrderItemServiceInterface orderItemServiceInterface;
    @Resource
    private OrderServiceInterface orderServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;
    @Resource
    private CartServiceInterface cartServiceInterface;
    @Resource
    private FavoritesServiceInterface favoritesServiceInterface;

    @PostMapping("/addCommodity")
    public ApiResult<CommodityObject> addCommodity(@RequestBody CommodityObject commodityObject) {
        try {
            checkIfShop(commodityObject.getPublisherId());
            checkCommodity(commodityObject);
            CommodityObject commodity = commodityServiceInterface.addCommodity(commodityObject);
            commodity = itemServiceInterface.addCommodityItem(commodity);
            return ApiResult.success(commodity);
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @RequestMapping("/deleteCommodity")
    public ApiResult<String> deleteCommodity(@RequestParam(value = "CommodityId") Integer commodityId, @RequestParam(value = "userId") Integer userId) {
        try {
            checkIfShop(userId);
            checkCommodity(commodityId);
            boolean couldDelete = orderItemServiceInterface.commodityInOrder(commodityId);
            if (!couldDelete) {
                throw new RuntimeException("This commodity has unfinished order");
            }

            List<Item> itemList = itemServiceInterface.getItemsByParentId(commodityId);
            for (var item : itemList) {
                // 级联删除购物车
                Integer itemId = item.getId();
                cartServiceInterface.deleteOnItemId(itemId);
                // 级联删除收藏夹
                favoritesServiceInterface.deleteFavoritesOnItemId(itemId);
            }

            itemServiceInterface.deleteCommodity(commodityId);
            commodityServiceInterface.deleteCommodity(commodityId);
            return ApiResult.success("delete success");
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @PostMapping("/changeCommodityInfo")
    public ApiResult<CommodityObject> changeCommodityInfo(CommodityObject commodityObject) {
        try {
            checkIfShop(commodityObject.getPublisherId());
            checkCommodity(commodityObject);
            CommodityObject commodity = commodityServiceInterface.changeCommodityInfo(commodityObject);
            commodity = itemServiceInterface.changeItemInfo(commodity);
            return ApiResult.success(commodity);
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @RequestMapping("/sendOrder")
    public ApiResult<Boolean> sendOrder(@RequestParam(value = "orderId") Integer orderId,
                                        @RequestParam(value = "shopId") Integer shopId) {
        try {
            checkIfShop(shopId);
            orderServiceInterface.changeOrderState(orderId, OrderState.Send.value());
            return ApiResult.success(true);
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    @RequestMapping("/receiveOrder")
    public ApiResult<Boolean> receiveOrder(@RequestParam(value = "orderId") Integer orderId,
                                           @RequestParam(value = "shopId") Integer shopId) {
        try {
            checkIfShop(shopId);
            orderServiceInterface.changeOrderState(orderId, OrderState.Received.value());
            return ApiResult.success(true);
        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }

    /**
     * 检测商品商家信息
     *
     * @param commodityObject pojo
     */
    private void checkCommodity(CommodityObject commodityObject) {
        Integer userId = commodityObject.getPublisherId();
        checkUser(userId);
        String name = commodityObject.getName();
        if (name.length() > 128) {
            throw new RuntimeException("Name is too long");
        }
        String description = commodityObject.getDescription();
        if (description.length() > 512) {
            throw new RuntimeException("Description is too long");
        }
        String category = commodityObject.getCategory();
        try {
            Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown category");
        }
    }

    /**
     * 检查商品是否存在
     *
     * @param id 商品id
     */
    private void checkCommodity(Integer id) {
        if (!commodityServiceInterface.ifCommodityExists(id)) {
            throw new RuntimeException("Commodity " + id + " does not exist");
        }
    }

    /**
     * 检查用户是否存在
     *
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }

    private void checkIfShop(Integer userId) {
        if (!userServiceInterface.ifShop(userId)) {
            throw new RuntimeException("User " + userId + " is not shop");
        }
    }
}
