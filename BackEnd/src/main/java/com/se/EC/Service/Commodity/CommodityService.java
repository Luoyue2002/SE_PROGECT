package com.se.EC.Service.Commodity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Commodity;
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

    @Override
    public List<Commodity> getCommoditiesByCategory(String category) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", category);
        return commodityMapper.selectList(queryWrapper);
    }

    @Override
    public List<Commodity> getCommoditiesByPublisher(Integer id) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("publisher", id);
        return commodityMapper.selectList(queryWrapper);
    }

    @Override
    public List<Commodity> getCommoditiesByFuzzyContent(String content) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", content);
        return commodityMapper.selectList(queryWrapper);
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
