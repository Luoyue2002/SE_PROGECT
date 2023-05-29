package com.se.EC.Service.Cart;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Cart;

import java.util.List;


public interface CartServiceInterface extends IMppService<Cart> {
    /**
     * 向购物车中添加商品
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @param itemNumber 商品数量
     */
    void addCart(Integer userId, Integer itemId, Integer itemNumber);

    /**
     * 修改购物车商品数量
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     * @param itemNumber 商品数量
     */
    void modifyCart(Integer userId, Integer itemId, Integer itemNumber);

    /**
     * 删除购物车商品
     * @param userId 用户 id
     * @param itemId 商品细分类 id
     */
    void deleteCart(Integer userId, Integer itemId);

    /**
     * 获取购物车商品
     * @param userId 用户 id
     * @return cart列表
     */
    List<Cart> getCart(Integer userId);
}


