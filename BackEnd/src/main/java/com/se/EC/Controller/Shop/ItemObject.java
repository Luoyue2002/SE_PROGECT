package com.se.EC.Controller.Shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemObject {
    private Integer itemId;  // 商品子分类id，前端传入不需要填
    private String name;  // 子分类名称
    private Integer number;  // 库存数量
    private Float price;  // 价格
}
