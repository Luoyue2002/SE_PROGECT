package com.se.EC.Service.Favorites;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Favorites;
import com.se.EC.Mapper.FavoritesMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FavoritesService extends MppServiceImpl<FavoritesMapper, Favorites> implements FavoritesServiceInterface {
    @Resource
    private FavoritesMapper favoritesMapper;

    @Override
    public void addFavorites(Integer userId, Integer itemId) {
        // 检查是否已经添加了此商品
        QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Long count = favoritesMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("You have already add this item to the favorites");
        }

        // 向数据库中添加商品
        Favorites favorites = new Favorites(userId, itemId);
        favoritesMapper.insert(favorites);
    }

    @Override
    public void deleteFavorites(Integer userId, Integer itemId) {
        // 检查是否已经添加了此商品
        QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user", userId);
        queryWrapper.eq("item", itemId);
        Long count = favoritesMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new RuntimeException("You have not add this item to the favorites yet");
        }

        // 删除商品
        favoritesMapper.delete(queryWrapper);
    }
}
