package com.se.EC.Service.Cart;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Cart;
import com.se.EC.Mapper.CartMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService extends MppServiceImpl<CartMapper, Cart> implements CartServiceInterface {
    @Resource
    CartMapper cartMapper;

    @Override
    public void addCart(Integer userId, Integer itemId, Integer itemNumber) {
        if (checkCartNumber(userId, itemId) != 0) {
            throw new RuntimeException("You have already add this item to the cart");
        }

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count > 500) {
            throw new RuntimeException("You have added too many things to your cart");
        }

        Cart cart = new Cart(userId, itemId, itemNumber);
        cartMapper.insert(cart);
    }

    @Override
    public void modifyCart(Integer userId, Integer itemId, Integer itemNumber) {
        if (checkCartNumber(userId, itemId) == 0) {
            throw new RuntimeException("You have not add this item to the cart yet");
        }

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Cart cart = new Cart(userId, itemId, itemNumber);
        cartMapper.update(cart, queryWrapper);
    }

    @Override
    public void deleteCart(Integer userId, Integer itemId) {
        if (checkCartNumber(userId, itemId) == 0) {
            throw new RuntimeException("You have not add this item to the cart yet");
        }

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        cartMapper.delete(queryWrapper);
    }

    @Override
    public List<Cart> getCart(Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        return cartMapper.selectList(queryWrapper);
    }

    /**
     * 返回某个用户购物车中是否有这个商品
     * @param userId 用户id
     * @param itemId 物品id
     * @return 数量
     */
    private Long checkCartNumber(Integer userId, Integer itemId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count > 1) {
            throw new RuntimeException("duplicate entry in cart");
        }
        return count;
    }
}
