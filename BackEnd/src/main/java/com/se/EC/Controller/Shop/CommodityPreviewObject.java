package com.se.EC.Controller.Shop;

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
    private Integer id;  // 商品id，前端传入不需要填
    private String name;  // 商品名称
    private String url;  // 图片url
}