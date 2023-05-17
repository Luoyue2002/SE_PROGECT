package com.example.backend.Favorites.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Favorites.Entity.Favorites;
import com.example.backend.Favorites.Mapper.FavoritesMapper;
import com.example.backend.Favorites.Service.IFavoritesService;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private FavoritesMapper favoritesMapper;



    @Override
    public ApiResult favorites_add() {
        return null;
    }

    @Override
    public ApiResult favorites_delete() {
        return null;
    }
}
