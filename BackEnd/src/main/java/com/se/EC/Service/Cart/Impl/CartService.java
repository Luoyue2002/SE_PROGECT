package com.se.EC.Service.Cart.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Cart;
import com.se.EC.Mapper.CartMapper;
import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class CartService extends ServiceImpl<CartMapper, Cart> implements CartServiceInterface {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private CartMapper cartMapper;

    @Override
    public ApiResult add_to_shoppingcart() {
        return null;
    }

    @Override
    public ApiResult delete_from_shoppingcart() {
        return null;
    }

    @Override
    public ApiResult delete_all_from_shoppingcart() {
        return null;
    }
}
