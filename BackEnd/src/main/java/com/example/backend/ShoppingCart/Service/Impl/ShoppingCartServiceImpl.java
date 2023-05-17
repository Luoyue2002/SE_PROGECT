package com.example.backend.ShoppingCart.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.ShoppingCart.Entity.ShoppingCart;
import com.example.backend.ShoppingCart.Mapper.ShoppingCartMapper;
import com.example.backend.ShoppingCart.Service.IShoppingCartService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private ShoppingCartMapper shoppingCartMapper;


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
