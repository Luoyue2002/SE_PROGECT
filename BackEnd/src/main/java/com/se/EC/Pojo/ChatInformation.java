package com.se.EC.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatInformation implements Serializable {
    private Integer senderId;
    private Integer receiverId;
    private LocalDateTime time;
    private String content;
}
