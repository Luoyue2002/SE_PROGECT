package com.se.EC.Controller.Favorites;

import com.se.EC.Entity.Commodity;
import com.se.EC.Entity.Item;
import com.se.EC.Pojo.CommodityPreviewObject;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
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
    @Resource
    private ItemServiceInterface itemServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;

    @Override
    @RequestMapping("/addFavorites")
    public ApiResult<Boolean> addFavorites(Integer userId, Integer itemId) {
        try {
            checkUser(userId);
            checkItem(itemId);
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
            checkUser(userId);
            checkItem(itemId);
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
            checkUser(userId);
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
                Integer sales = commodity.getSales();
                CommodityPreviewObject commodityPreviewObject = new CommodityPreviewObject(item, name, null, price, sales);
                commodityPreviewObjectList.add(commodityPreviewObject);
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 检查用户是否存在
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }

    /**
     * 检查商品是否存在
     * @param itemId 商品id
     */
    private void checkItem(Integer itemId) {
        if (!itemServiceInterface.ifItemExists(itemId)) {
            throw new RuntimeException("Item " + itemId + " does not exist");
        }
    }
}
