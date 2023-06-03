package com.se.EC.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatInformation implements Serializable {
    private Integer groupId;
    private Integer senderId;
    private LocalDateTime time;
    private String content;
}
