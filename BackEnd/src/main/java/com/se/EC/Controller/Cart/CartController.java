package com.se.EC.Controller.Cart;

import com.se.EC.Entity.Cart;
import com.se.EC.Entity.Item;
import com.se.EC.Pojo.CommodityPreviewObject;
import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController implements CartControllerInterface {
    @Resource
    private CartServiceInterface cartServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;

    @Override
    @RequestMapping("/addCart")
    public ApiResult<Boolean> addCart(@RequestParam(value = "userId") Integer userId,
                                      @RequestParam(value = "itemId") Integer itemId,
                                      @RequestParam(value = "itemNumber") Integer itemNumber) {
        try {
            checkUser(userId);
            checkItem(itemId);
            checkItemNumber(itemNumber);

            cartServiceInterface.addCart(userId, itemId, itemNumber);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/modifyCart")
    public ApiResult<Boolean> modifyCart(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "itemId") Integer itemId,
                                         @RequestParam(value = "itemNumber") Integer itemNumber) {
        try {
            checkUser(userId);
            checkItem(itemId);
            checkItemNumber(itemNumber);
            cartServiceInterface.modifyCart(userId, itemId, itemNumber);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/deleteCart")
    public ApiResult<Boolean> deleteCart(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "itemId") Integer itemId) {
        try {
            checkUser(userId);
            checkItem(itemId);
            cartServiceInterface.deleteCart(userId, itemId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getCart")
    public ApiResult<List<CommodityPreviewObject>> getCart(@RequestParam(value = "userId") Integer userId) {
        try {
            checkUser(userId);
            List<Cart> cartList = cartServiceInterface.getCart(userId);
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var cart : cartList) {
                Integer id = cart.getItem();
                Item item = itemServiceInterface.getById(id);
                String name = item.getName();
                Float price = item.getPrice();
                Integer count = cart.getNumber();
                CommodityPreviewObject commodityPreviewObject = new CommodityPreviewObject(id, name, null, price, count);
                commodityPreviewObjectList.add(commodityPreviewObject);
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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

    /**
     * 检查商品是否存在
     * @param itemId 商品id
     */
    private void checkItem(Integer itemId) {
        if (!itemServiceInterface.ifItemExists(itemId)) {
            throw new RuntimeException("Item " + itemId + " does not exist");
        }
    }

    /**
     * 检查商品数目是否合法
     * @param number 数目
     */
    private void checkItemNumber(Integer number) {
        if (number <= 0) {
            throw new RuntimeException("The number of item is less than 0");
        }
        if (number >= 1000) {
            throw new RuntimeException("The number of item is too large");
        }
    }
}
