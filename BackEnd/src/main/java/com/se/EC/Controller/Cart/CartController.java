package com.se.EC.Controller.Cart;

import com.se.EC.Entity.Cart;
import com.se.EC.Entity.Item;
import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

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

    @Override
    @RequestMapping("/addCart")
    public ApiResult<Boolean> addCart(@RequestParam(value = "userId") Integer userId,
                               @RequestParam(value = "itemId") Integer itemId,
                               @RequestParam(value = "itemNumber") Integer itemNumber) {
        try {
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
}
