package com.se.EC.Controller.Shop;

import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ShopConttollerInterface {

    /**
     * 添加商品
     * @param commodityObject 商品类型
     * @return 商品类型
     */
    public ApiResult<CommodityObject> addCommodity( CommodityObject commodityObject);


    /**
     * 删除商品
     * @param commodityId 商品id
     * @return 成功/失败
     */

    public ApiResult<String> deleteCommodity(Integer commodityId);
}
