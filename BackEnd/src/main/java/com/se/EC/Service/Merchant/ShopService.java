package com.se.EC.Service.Merchant;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Merchant;
import com.se.EC.Mapper.MerchantMapper;
import com.se.EC.Service.Merchant.MerchantServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;


@Service
public class ShopService extends ServiceImpl<MerchantMapper, Merchant> implements MerchantServiceInterface {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private MerchantMapper merchantMapper;


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
