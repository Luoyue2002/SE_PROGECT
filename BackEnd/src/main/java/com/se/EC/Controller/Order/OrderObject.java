package com.se.EC.Controller.Order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderObject {

    private Integer orderId;  // 订单id
    private Integer buyerId;  // 用户id
    private Integer state;  // 总金额

    private String  address;  // 地址
    private Float price;  // 总金额

    private LocalDateTime dateTime; //时间

    private List<OrderItemObject> itemObjectList;  // 购物商品列表
}





