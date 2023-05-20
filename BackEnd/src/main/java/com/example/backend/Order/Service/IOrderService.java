package com.example.backend.Order.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.Order.Entity.Order;
import com.example.backend.utils.ApiResult;

public interface IOrderService extends IService<Order> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult order_search();


    ApiResult order_create();


    ApiResult order_cancle();

    ApiResult order_pay();

}


