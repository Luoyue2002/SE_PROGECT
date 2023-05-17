package com.example.backend.ShoppingCart.Controller;



import com.example.backend.Commodity.Controller.CommodityController;
import com.example.backend.PictureURL.Controller.PictureUrlController;
import com.example.backend.PictureURL.Mapper.PictureUrlMapper;
import com.example.backend.Shop.Controller.ShopController;
import com.example.backend.Shop.Entity.Shop;
import com.example.backend.ShoppingCart.Entity.ShoppingCart;
import com.example.backend.ShoppingCart.Service.IShoppingCartService;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class ShoppingCartController {

    @Resource
    private IShoppingCartService shoppingCartService;

//    private ShopController shopController;
//
//    private CommodityController commodityController;
//
//    private PictureUrlController pictureUrlController;
//
//    public ApiResult AddToShoppingcart() {
//        ApiResult res = shoppingCartService.add_to_shoppingcart();
//        return res;
//    }
//    public ApiResult DeleteFromShoppingcart() {
//        ApiResult res = shoppingCartService.delete_from_shoppingcart();
//        return res;
//    }
//    public ApiResult DeleteAllFromShoppingcart() {
//        ApiResult res = shoppingCartService.delete_all_from_shoppingcart();
//        return res;
//    }
//


}
