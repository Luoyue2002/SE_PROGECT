package com.se.EC.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInformation implements Serializable {
    private Integer id;
    private Integer unreadCount;
    private String name;
}