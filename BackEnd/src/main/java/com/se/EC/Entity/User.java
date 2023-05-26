package com.se.EC.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
public class User implements Serializable {
    @MppMultiId
    @TableId("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("password")
    private String password;
    @TableField("realName")
    private String realName;
    @TableField("identity")
    private String identity;
    @TableField("school")
    private String school;
    @TableField("schoolId")
    private String schoolId;
    @TableField("gender")
    private Integer gender;
    @TableField("phone")
    private String phone;
    @TableField("image")
    private String image;
    @TableField("address1")
    private String address1;
    @TableField("address2")
    private String address2;
    @TableField("address3")
    private String address3;
    @TableField("ifShop")
    private Integer ifShop;
}
