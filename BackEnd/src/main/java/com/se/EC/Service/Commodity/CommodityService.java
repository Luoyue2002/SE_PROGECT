package com.se.EC.Service.Commodity;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Commodity;
import com.se.EC.Mapper.CommodityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CommodityService extends MppServiceImpl<CommodityMapper, Commodity> implements CommodityServiceInterface {
    @Resource
    private CommodityMapper commodityMapper;
}
