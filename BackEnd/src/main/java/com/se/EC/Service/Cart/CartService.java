package com.se.EC.Service.Cart;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Cart;
import com.se.EC.Mapper.CartMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CartService extends MppServiceImpl<CartMapper, Cart> implements CartServiceInterface {
    @Resource
    CartMapper cartMapper;
}
