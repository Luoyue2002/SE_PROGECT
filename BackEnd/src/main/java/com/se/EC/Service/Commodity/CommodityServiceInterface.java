package com.se.EC.Service.Commodity;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Controller.Shop.CommodityObject;
import com.se.EC.Entity.Commodity;

import java.util.List;

public interface CommodityServiceInterface extends IMppService<Commodity> {
    List<Commodity> getCommodities();
    String getNameById(Integer id);
    Commodity getCommodityDetailById(Integer id);



    //LY
    CommodityObject  addCommodity(CommodityObject commodityObject);

    //LY
    CommodityObject  deleteCommodity(int commodityId);

}


