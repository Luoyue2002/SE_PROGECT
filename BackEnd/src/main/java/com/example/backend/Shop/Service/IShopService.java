package com.example.backend.Shop.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.Shop.Entity.Shop;
import com.example.backend.utils.ApiResult;

public interface IShopService extends IService<Shop> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult shop_register();



    ApiResult shop_reset_info( );

    ApiResult shop_add_commodity();

    ApiResult shop_delete_commodity();

    ApiResult shop_change_commodity_info();

}


