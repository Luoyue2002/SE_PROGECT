package com.se.EC.Service.Cart;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.Cart;
import com.se.EC.Utils.ApiResult;

public interface CartServiceInterface extends IService<Cart> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult add_to_shoppingcart();

    ApiResult delete_from_shoppingcart();

    ApiResult delete_all_from_shoppingcart();
}


