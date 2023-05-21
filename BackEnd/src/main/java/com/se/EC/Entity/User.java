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
    private Integer UserId;
    @TableField("user_name")
    private String UserName;
    @TableField("user_password")
    private String UserPassword;
    @TableField("user_realname")
    private String UserRealname;
    @TableField("user_icid")
    private String UserIcid;
    @TableField("user_school")
    private String UserSchool;
    @TableField("user_sid")
    private String UserSid;
    @TableField("user_gender")
    private String UserGender;
    @TableField("user_phone")
    private String UserPhone;
    @TableField("user_image")
    private String UserImage;
    @TableField("user_add1")
    private String UserAdd1;
    @TableField("user_add2")
    private String UserAdd2;
    @TableField("user_add3")
    private String UserAdd3;
    @TableField("user_ifsell")
    private Integer UserIfSell;
}
