package com.se.EC.Controller.Commodity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommodityObject {
    private Integer commodityId;  // 商品id，前端传入不需要填
    private Integer publisherId;  // 发布者id
    private String name;  // 商品名称
    private String description;  // 商品描述
    private String category;  // 商品类别
    private CommoditySubclass commoditySubclass;  // 商品子分类
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class CommoditySubclass {
    private Integer itemId;  // 商品子分类id，前端传入不需要填
    private String name;  // 子分类名称
    private Integer number;  // 库存数量
    private Float price;  // 价格
}
