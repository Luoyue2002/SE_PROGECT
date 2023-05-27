package com.se.EC.Controller.Shop;

import com.se.EC.Controller.Order.OrderObject;
import com.se.EC.Entity.Item;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.OrderItem.OrderItemServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/shop") // url 指定
public class ShopController {

    @Resource
    private CommodityServiceInterface commodityServiceInterface;

    @Resource
    private  OrderItemServiceInterface orderItemServiceInterface;

    @Resource
    private ItemServiceInterface itemServiceInterface;
    @PostMapping("/addCommodity")
    public ApiResult<CommodityObject> addCommodity(@RequestBody CommodityObject commodityObject) {
        try {
            CommodityObject commodity = commodityServiceInterface.addCommodity(commodityObject);
            commodity = itemServiceInterface.addCommodityItem(commodity);
            return ApiResult.success(commodity);

        } catch (Exception e) {
            return ApiResult.error("unknown error!");
        }
    }


    @RequestMapping ("/deleteCommodity")
    public ApiResult<String> deleteCommodity(@RequestParam(value = "Commodityid") Integer commodityId) {
            boolean couldDelete = orderItemServiceInterface.commodityInOrder(commodityId);
            if(couldDelete){
                commodityServiceInterface.deleteCommodity(commodityId);
                itemServiceInterface.deleteCommodity(commodityId);
            }else{
                return ApiResult.error("have unfinished order");
            }
            return ApiResult.success("delete success");
    }

}
