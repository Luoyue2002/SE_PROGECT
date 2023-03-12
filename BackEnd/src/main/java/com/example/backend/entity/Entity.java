package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


// entity 是实体类，和数据库表对应，注解为 @TableName("表名")
@Data
@TableName("entity")
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("entityid") //指定主键
    private String entityid;

    // 成员变量名和表的成员名一致会自动装载,指定一下主键就好，注意加下划线和大写可能会出现奇怪的问题

}
