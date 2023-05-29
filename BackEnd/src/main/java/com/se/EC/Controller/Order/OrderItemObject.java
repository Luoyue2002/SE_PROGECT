package com.se.EC.Controller.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemObject {
    private Integer itemId;  // 商品子分类id，前端传入不需要填
    private Integer publisherId;
    private String name;  // 子分类名称
    private Integer number;  // 库存数量
    private Float price;  // 价格
}
