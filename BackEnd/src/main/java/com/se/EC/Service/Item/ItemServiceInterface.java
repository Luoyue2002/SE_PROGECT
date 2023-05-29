package com.se.EC.Service.Item;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Item;
import com.se.EC.Pojo.CommodityObject;

import java.util.List;

public interface ItemServiceInterface extends IMppService<Item> {
    List<Item> getItemsByParentId(Integer parentId);

    CommodityObject addCommodityItem(CommodityObject commodityObject);

    public void deleteCommodity(int commodityId);

    Item getItemById(Integer id);

    Boolean ifItemExists(Integer itemId);
    Integer getParentId(Integer id);
}
