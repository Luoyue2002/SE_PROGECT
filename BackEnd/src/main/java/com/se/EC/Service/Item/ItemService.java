package com.se.EC.Service.Item;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Item;
import com.se.EC.Mapper.ItemMapper;
import com.se.EC.Pojo.CommodityObject;
import com.se.EC.Pojo.ItemObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService extends MppServiceImpl<ItemMapper, Item> implements ItemServiceInterface {
    @Resource
    ItemMapper itemMapper;

    @Override
    public List<Item> getItemsByParentId(Integer parentId) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", parentId);
        return itemMapper.selectList(queryWrapper);
    }

    //LY
    @Override
    public CommodityObject addCommodityItem(CommodityObject commodityObject) {
        List<ItemObject> itemList = commodityObject.getItemObjectList();
        int index = 0;
        for (ItemObject commodityItem : itemList) {
            Item item = new Item(null, commodityObject.getCommodityId(), commodityItem.getName(), commodityItem.getNumber(), commodityItem.getPrice());
            itemMapper.insert(item);
            itemList.get(index).setItemId(item.getId());
        }
        commodityObject.setItemObjectList(itemList);
        return commodityObject;
    }

    @Override
    public void deleteCommodity(int commodityId) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", commodityId);
        itemMapper.delete(queryWrapper);
        return;
    }

    @Override
    public Item getItemById(Integer id) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return itemMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean ifItemExists(Integer itemId) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", itemId);
        Long count = itemMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public Integer getParentId(Integer id) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Item item = itemMapper.selectOne(queryWrapper);
        return item.getParentId();
    }
}
