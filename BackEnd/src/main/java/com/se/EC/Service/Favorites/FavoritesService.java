package com.se.EC.Service.Favorites;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Favorites;
import com.se.EC.Mapper.FavoritesMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class FavoritesService extends MppServiceImpl<FavoritesMapper, Favorites> implements FavoritesServiceInterface {
    @Resource
    private FavoritesMapper favoritesMapper;
}
