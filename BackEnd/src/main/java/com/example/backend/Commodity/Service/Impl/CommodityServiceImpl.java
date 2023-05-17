package com.example.backend.Commodity.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Commodity.Entity.Commodity;
import com.example.backend.Commodity.Mapper.CommodityMapper;
import com.example.backend.Commodity.Service.ICommodityService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private CommodityMapper commodityMapper;


    @Override
    public ApiResult add_commodity() {
        return null;
    }

    @Override
    public ApiResult delete_commodity() {
        return null;
    }

    @Override
    public ApiResult change_commodity_info() {
        return null;
    }

    @Override
    public ApiResult search_commodity_info() {
        return null;
    }
}
