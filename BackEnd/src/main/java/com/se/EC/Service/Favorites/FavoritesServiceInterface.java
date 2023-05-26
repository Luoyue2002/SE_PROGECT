package com.se.EC.Service.Favorites;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Favorites;

public interface FavoritesServiceInterface extends IMppService<Favorites> {
    /**
     * 向收藏夹中添加商品
     *
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     */
    void addFavorites(Integer userId, Integer itemId);

    /**
     * 删除收藏夹商品
     *
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     */
    void deleteFavorites(Integer userId, Integer itemId);
}