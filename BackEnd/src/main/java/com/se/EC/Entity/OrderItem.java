package com.se.EC.Entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@TableName("orderitem")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @TableId("orderId")
    private Integer orderId;


    @TableField("commodityId")
    private Integer commodityId;

    @TableField("itemId")
    private Integer itemId;


    @TableField("name")
    private String name;
    @TableField("number")
    private Integer number;
    @TableField("price")
    private Float price;
}
