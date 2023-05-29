package com.se.EC.Controller.Favorites;

import com.se.EC.Pojo.CommodityPreviewObject;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FavoritesControllerInterface {
    /**
     * 向收藏夹中添加商品
     *
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @return 成功/失败
     */
    @RequestMapping("/addFavorites")
    ApiResult<Boolean> addFavorites(@RequestParam(value = "userId") Integer userId,
                                    @RequestParam(value = "itemId") Integer itemId);

    /**
     * 删除收藏夹商品
     *
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @return 成功/失败
     */
    @RequestMapping("/deleteFavorites")
    ApiResult<Boolean> deleteFavorites(@RequestParam(value = "userId") Integer userId,
                                       @RequestParam(value = "itemId") Integer itemId);

    /**
     * 获取收藏夹商品
     *
     * @param userId 用户 id
     * @return 预览图列表
     */
    @RequestMapping("/getFavorites")
    ApiResult<List<CommodityPreviewObject>> getFavorites(@RequestParam(value = "userId") Integer userId);
}
