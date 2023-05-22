package com.se.EC.Service.Item;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Item;
import com.se.EC.Mapper.ItemMapper;
import jakarta.annotation.Resource;

public class ItemService extends MppServiceImpl<ItemMapper, Item> implements ItemServiceInterface {
    @Resource
    ItemMapper itemMapper;
}
