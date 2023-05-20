package com.se.EC.Service.Favorites.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.Favorites;
import com.se.EC.Mapper.FavoritesMapper;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class FavoritesService extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesServiceInterface {
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
