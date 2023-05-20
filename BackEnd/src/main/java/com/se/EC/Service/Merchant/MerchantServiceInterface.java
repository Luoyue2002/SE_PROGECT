package com.se.EC.Service.Merchant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.Merchant;
import com.se.EC.Utils.ApiResult;

public interface MerchantServiceInterface extends IService<Merchant> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult shop_register();



    ApiResult shop_reset_info( );

    ApiResult shop_add_commodity();

    ApiResult shop_delete_commodity();

    ApiResult shop_change_commodity_info();

}


