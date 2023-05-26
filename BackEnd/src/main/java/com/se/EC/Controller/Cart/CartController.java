package com.se.EC.Controller.Cart;

import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController implements CartControllerInterface {
    @Resource
    private CartServiceInterface cartServiceInterface;

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
}
