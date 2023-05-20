package com.se.EC.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

// entity 是实体类，和数据库表对应，注解为 @TableName("表名")
@Data
@TableName("user")
public class User implements Serializable {
    @TableId("user_id")
    private Integer user_id;
    @TableField("user_name")
    private String user_name;
    @TableField("user_password")
    private String user_password;
    @TableField("user_realname")
    private String user_realname;
    @TableField("user_icid")
    private String user_icid;
    @TableField("user_school")
    private String user_school;
    @TableField("user_sid")
    private String user_sid;
    @TableField("user_gender")
    private String user_gender;
    @TableField("user_phone")
    private String user_phone;
    @TableField("user_image")
    private String user_image;
    @TableField("user_add1")
    private String user_add1;
    @TableField("user_add2")
    private String user_add2;
    @TableField("user_add3")
    private String user_add3;
    @TableField("user_ifsell")
    private int user_ifsell;
}
