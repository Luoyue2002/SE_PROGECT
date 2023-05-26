package com.se.EC.Controller.Cart;

import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CartControllerInterface {
    /**
     * 向购物车中添加商品
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @param itemNumber 商品数量
     * @return 成功/失败
     */
    @RequestMapping("/addCart")
    ApiResult<Boolean> addCart(@RequestParam(value = "userId") Integer userId,
                               @RequestParam(value = "itemId") Integer itemId,
                               @RequestParam(value = "itemNumber") Integer itemNumber);

    /**
     * 修改购物车商品数量
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @param itemNumber 商品数量
     * @return 成功/失败
     */
    @RequestMapping("/modifyCart")
    ApiResult<Boolean> modifyCart(@RequestParam(value = "userId") Integer userId,
                                  @RequestParam(value = "itemId") Integer itemId,
                                  @RequestParam(value = "itemNumber") Integer itemNumber);

    /**
     * 删除购物车商品
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @return 成功/失败
     */
    @RequestMapping("/deleteCart")
    ApiResult<Boolean> deleteCart(@RequestParam(value = "userId") Integer userId,
                                  @RequestParam(value = "itemId") Integer itemId);

    /**
     * 获取购物车商品
     *
     * @param userId 用户 id
     * @return 预览图列表
     */
    @RequestMapping("/getCart")
    ApiResult<List<CommodityPreviewObject>> getFavorites(@RequestParam(value = "userId") Integer userId);
}
