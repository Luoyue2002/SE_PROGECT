package com.se.EC.Service.Cart;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Cart;
import com.se.EC.Mapper.CartMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CartService extends MppServiceImpl<CartMapper, Cart> implements CartServiceInterface {
    @Resource
    CartMapper cartMapper;

    @Override
    public void addCart(Integer userId, Integer itemId, Integer itemNumber) {
        // 检查是否已经添加了此商品
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("You have already add this item to the cart");
        }

        // 向数据库中添加商品
        Cart cart = new Cart(userId, itemId, itemNumber);
        cartMapper.insert(cart);
    }

    @Override
    public void modifyCart(Integer userId, Integer itemId, Integer itemNumber) {
        // 检查是否已经添加了此商品
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new RuntimeException("You have not add this item to the cart yet");
        }

        // 修改商品数量
        Cart cart = new Cart(userId, itemId, itemNumber);
        cartMapper.update(cart, queryWrapper);
    }

    @Override
    public void deleteCart(Integer userId, Integer itemId) {
        // 检查是否已经添加了此商品
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new RuntimeException("You have not add this item to the cart yet");
        }

        // 删除商品
        cartMapper.delete(queryWrapper);
    }
}
