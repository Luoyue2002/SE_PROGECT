package com.se.EC.Controller.Shop;

import com.se.EC.Pojo.CommodityObject;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ShopControllerInterface {

    /**
     * 添加商品
     * @param commodityObject 商品类型
     * @return 商品类型
     */
    @PostMapping("/addCommodity")
    ApiResult<CommodityObject> addCommodity(@RequestBody CommodityObject commodityObject);


    /**
     * 删除商品
     * @param commodityId 商品id
     * @return 成功/失败
     */
    @RequestMapping("/deleteCommodity")
    ApiResult<String> deleteCommodity(@RequestParam(value = "commodityId") Integer commodityId , Integer userid);


    @RequestMapping("/changeCommodityInfo")
    ApiResult<CommodityObject> changeCommodityInfo(@RequestBody CommodityObject commodityObject);


    @RequestMapping("/sendOrder")
    ApiResult<Boolean> sendOrder(@RequestParam(value = "orderId") Integer orderId , @RequestParam(value = "shopId") Integer shopId);

    @RequestMapping("/receiveOrder")
    ApiResult<Boolean> receiveOrder(@RequestParam(value = "orderId") Integer orderId , @RequestParam(value = "shopId") Integer shopId);


}
