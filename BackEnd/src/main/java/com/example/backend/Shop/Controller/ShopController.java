package com.example.backend.Shop.Controller;



import com.example.backend.Commodity.Controller.CommodityController;
import com.example.backend.PictureURL.Controller.PictureUrlController;
import com.example.backend.Shop.Entity.Shop;
import com.example.backend.Shop.Service.IShopService;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class ShopController {

    @Resource
    private IShopService shopService;

//    private CommodityController commodityController;
//
//    private PictureUrlController pictureUrlController;
//
//    public ApiResult ShopRegister(Shop userinfo) {
//        ApiResult res = shopService.shop_register();
//        return res;
//    }
//
//
//    public ApiResult ShopResetInfo() {
//        ApiResult res = shopService.shop_reset_info();
//        return res;
//    }
//
//
//    public ApiResult ShopAddCommodity() {
//        ApiResult res = shopService.shop_add_commodity();
//        return res;
//    }
//
//
//    public ApiResult ShopDeleteCommodity() {
//        ApiResult res = shopService.shop_delete_commodity();
//        return res;
//    }
//
//
//    public ApiResult ShopChangeCommodityInfo() {
//        ApiResult res = shopService.shop_change_commodity_info();
//        return res;
//    }
//
//


}
