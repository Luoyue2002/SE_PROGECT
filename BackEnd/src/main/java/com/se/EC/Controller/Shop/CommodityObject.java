package com.se.EC.Controller.Shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommodityObject {
    private Integer commodityId;  // 商品id，前端传入不需要填
    private Integer publisherId;  // 发布者id
    private String name;  // 商品名称
    private String description;  // 商品描述
    private String category;  // 商品类别
    private List<ItemObject> itemObjectList;  // 商品子分类
    private List<String> pictureList;  // 图片路径
}

