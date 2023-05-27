package com.se.EC.Service.Item;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Item;
import com.se.EC.Mapper.ItemMapper;
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

    @Override
    public Item getItemById(Integer id) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return itemMapper.selectOne(queryWrapper);
    }
}
