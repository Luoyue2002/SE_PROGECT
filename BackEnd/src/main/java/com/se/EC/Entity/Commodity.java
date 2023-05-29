package com.se.EC.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@TableName("commodity")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Commodity implements Serializable {
    @MppMultiId
    @TableId("id")
    private Integer id;
    @TableField("publisher")
    private Integer publisher;
    @TableField("sales")
    private Integer sales;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
    @TableField("category")
    private String category;
}
