package com.se.EC.Controller.Favorites;

import com.se.EC.Entity.Commodity;
import com.se.EC.Entity.Item;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Resource
    private ItemServiceInterface itemServiceInterface;

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
                Commodity commodity = commodityServiceInterface.getCommodityDetailById(item);
                String name = commodity.getName();
                List<Item> itemList = itemServiceInterface.getItemsByParentId(item);
                itemList.sort((o1, o2) -> {
                    if (o1.getPrice() > o2.getPrice()) {
                        return 1;
                    } else if (o1.getPrice().equals(o2.getPrice())) {
                        return 0;
                    } else {
                        return -1;
                    }
                });
                Float price = itemList.get(0).getPrice();
                CommodityPreviewObject commodityPreviewObject = new CommodityPreviewObject(item, name, null, price);
                commodityPreviewObjectList.add(commodityPreviewObject);
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
