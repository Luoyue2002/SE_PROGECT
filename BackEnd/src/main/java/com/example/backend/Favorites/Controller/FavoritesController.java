package com.example.backend.Favorites.Controller;



import com.example.backend.Commodity.Controller.CommodityController;
import com.example.backend.Favorites.Entity.Favorites;
import com.example.backend.Favorites.Service.IFavoritesService;
import com.example.backend.Shop.Controller.ShopController;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class FavoritesController {

    @Resource
    private IFavoritesService favoritesService;

//    private ShopController shopController;
//
//    private CommodityController commodityController;
//
//    public ApiResult FavoritesAdd(){
//
//        ApiResult res = favoritesService.favorites_add();
//        return res;
//    }
//
//    public ApiResult FavoritesDelete(){
//
//        ApiResult res = favoritesService.favorites_delete();
//        return res;
//    }



}
