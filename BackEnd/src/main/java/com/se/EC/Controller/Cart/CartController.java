package com.se.EC.Controller.Cart;

import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
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
    private FavoritesServiceInterface favoritesServiceInterface;
    @Resource
    private CommodityServiceInterface commodityServiceInterface;

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
    public ApiResult<List<CommodityPreviewObject>> getFavorites(@RequestParam(value = "userId") Integer userId) {
        try {
            List<Integer> idList = favoritesServiceInterface.getFavorites(userId);
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var item : idList) {
                String name = commodityServiceInterface.getNameById(item);
                CommodityPreviewObject commodityPreviewObject = new CommodityPreviewObject(item, name, null);
                commodityPreviewObjectList.add(commodityPreviewObject);
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
