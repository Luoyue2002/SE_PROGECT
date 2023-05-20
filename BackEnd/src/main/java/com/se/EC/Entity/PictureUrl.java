package com.se.EC.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

// entity 是实体类，和数据库表对应，注解为 @TableName("表名")
@Data
@TableName("user")
public class PictureUrl implements Serializable {


}
