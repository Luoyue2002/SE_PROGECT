package com.se.EC.Controller.Favorites;

import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import com.se.EC.Service.Preview.PreviewServiceInterface;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/favorites")
public class FavoritesController implements FavoritesControllerInterface {
    @Resource
    private FavoritesServiceInterface favoritesServiceInterface;
    @Resource
    private CommodityServiceInterface commodityServiceInterface;

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

    @Override
    @RequestMapping("/getFavorites")
    public ApiResult<List<CommodityPreviewObject>> getFavorites(@RequestParam(value = "userId") Integer userId) {
        try {
            List<Integer> idList = favoritesServiceInterface.getFavorites(userId);
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var item : idList) {
                String name = commodityServiceInterface.getNameById(item);
                CommodityPreviewObject commodityPreviewObject = new CommodityPreviewObject(item, name, null);
                commodityPreviewObjectList.add(commodityPreviewObject);
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
