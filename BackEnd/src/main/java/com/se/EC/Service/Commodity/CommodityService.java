package com.se.EC.Service.Commodity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Controller.Shop.CommodityObject;
import com.se.EC.Entity.Commodity;
import com.se.EC.Entity.Order;
import com.se.EC.Mapper.CommodityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService extends MppServiceImpl<CommodityMapper, Commodity> implements CommodityServiceInterface {
    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> getCommodities() {
        return commodityMapper.selectList(null);
    }

    @Override
    public String getNameById(Integer id) {
        List<Commodity> commodityList = getCommodityListById(id);
        return commodityList.get(0).getName();
    }

    @Override
    public Commodity getCommodityDetailById(Integer id) {
        List<Commodity> commodityList = getCommodityListById(id);
        return commodityList.get(0);
    }

    //LY
    @Override
    public CommodityObject addCommodity(CommodityObject commodityObject) {
        Commodity newCommodity = new Commodity(null, commodityObject.getPublisherId(), 0,
                commodityObject.getName(), commodityObject.getDescription(), commodityObject.getCategory()
        );
        commodityMapper.insert(newCommodity);
        // 获取主键
        commodityObject.setCommodityId(newCommodity.getId());
        return commodityObject;
    }

    //LY
    @Override
    public CommodityObject deleteCommodity(int commodityId) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", commodityId);
        List<Commodity> commodityList = commodityMapper.selectList(queryWrapper);
        if(commodityList.size()==0){
            throw new RuntimeException("commodity does not exist");
        }
        if(commodityList.size()>1){
            throw new RuntimeException("Duplicate id for commodity");
        }
        commodityMapper.deleteById(commodityId);
        return null;
    }


    private List<Commodity> getCommodityListById(Integer id) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<Commodity> commodityList = commodityMapper.selectList(queryWrapper);
        if (commodityList.size() > 1) {
            throw new RuntimeException("Duplicate id for different commodity");
        } else if (commodityList.size() == 0) {
            throw new RuntimeException("commodity does not exist");
        }
        return commodityList;
    }
}
