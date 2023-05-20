package com.se.EC.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

// entity 是实体类，和数据库表对应，注解为 @TableName("表名")
@Data
@TableName("chat")
public class Chat implements Serializable {
    @TableId("chat_id")
    private Integer chatId;
    @TableField("chat_time")
    private Date date;
    @TableField("chat_content")
    private String content;
    @TableField("chat_sender")
    private Integer sender;
    @TableField("chat_receiver")
    private Integer receiver;
}