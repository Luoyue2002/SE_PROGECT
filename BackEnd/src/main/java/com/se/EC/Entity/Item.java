package com.se.EC.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@TableName("item")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    @TableId("id")
    private Integer id;
    @TableField("parentId")
    private Integer parentId;
    @TableField("name")
    private String name;
    @TableField("number")
    private Integer number;
    @TableField("price")
    private Float price;
}
