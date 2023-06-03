package com.se.EC.Service.Commodity;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Commodity;
import com.se.EC.Pojo.CommodityObject;

import java.util.List;

public interface CommodityServiceInterface extends IMppService<Commodity> {
    List<Commodity> getCommodities();

    String getNameById(Integer id);

    Commodity getCommodityDetailById(Integer id);

    CommodityObject addCommodity(CommodityObject commodityObject);

    CommodityObject deleteCommodity(int commodityId);

    CommodityObject changeCommodityInfo(CommodityObject commodityObject);

    List<Commodity> getCommoditiesByCategory(String category);

    List<Commodity> getCommoditiesByPublisher(Integer id);

    List<Commodity> getCommoditiesByFuzzyContent(String content);

    Boolean ifCommodityExists(Integer id);

    void addSales(Integer commodityId, Integer number);
}


