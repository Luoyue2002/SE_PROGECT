package com.example.backend.Shop.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Shop.Entity.Shop;
import com.example.backend.Shop.Mapper.ShopMapper;
import com.example.backend.Shop.Service.IShopService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private ShopMapper shopMapper;


    @Override
    public ApiResult shop_register() {
        return null;
    }

    @Override
    public ApiResult shop_reset_info() {
        return null;
    }

    @Override
    public ApiResult shop_add_commodity() {
        return null;
    }

    @Override
    public ApiResult shop_delete_commodity() {
        return null;
    }

    @Override
    public ApiResult shop_change_commodity_info() {
        return null;
    }
}
