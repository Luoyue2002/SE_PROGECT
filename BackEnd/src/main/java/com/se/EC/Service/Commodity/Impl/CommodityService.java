package com.se.EC.Service.Commodity.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Commodity;
import com.se.EC.Mapper.CommodityMapper;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> implements CommodityServiceInterface {
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
