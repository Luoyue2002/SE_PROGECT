package com.se.EC.Controller.Favorites;

import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/favorites")
public class FavoritesController implements FavoritesControllerInterface {
    @Resource
    private FavoritesServiceInterface favoritesServiceInterface;

    @Override
    @RequestMapping("/addFavorites")
    public ApiResult<Boolean> addFavorites(Integer userId, Integer itemId) {
        try {
            favoritesServiceInterface.addFavorites(userId, itemId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/deleteFavorites")
    public ApiResult<Boolean> deleteFavorites(Integer userId, Integer itemId) {
        try {
            favoritesServiceInterface.deleteFavorites(userId, itemId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }
}
