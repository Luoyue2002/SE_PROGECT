package com.se.EC.Controller.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品预览数据类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommodityPreviewObject {
    private Integer id;  // itemId
    private String name;  // 商品名称
    private String url;  // 图片url
    private Float price;  // 价格
    private Integer count;  // 数量
}
