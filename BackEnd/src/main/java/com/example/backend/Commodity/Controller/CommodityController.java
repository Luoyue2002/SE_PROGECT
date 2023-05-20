package com.example.backend.Commodity.Controller;



import com.example.backend.Commodity.Entity.Commodity;
import com.example.backend.Commodity.Service.ICommodityService;
import com.example.backend.PictureURL.Controller.PictureUrlController;
import com.example.backend.Shop.Entity.Shop;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class CommodityController {

    @Resource
    private ICommodityService commodityService;
//
//
//    private PictureUrlController pictureUrlController;
//
//    public ApiResult AddCommodity() {
//        ApiResult res = commodityService.add_commodity();
//        return res;
//    }
//
//
//    public ApiResult DeleteCommodity() {
//        ApiResult res = commodityService.delete_commodity();
//        return res;
//    }
//    public ApiResult ChangeCommodityInfo() {
//        ApiResult res = commodityService.change_commodity_info();
//        return res;
//    }
//    public ApiResult SearchCommodityInfo() {
//        ApiResult res = commodityService.search_commodity_info();
//        return res;
//    }





}
