package com.se.EC.Controller.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information implements Serializable {
    private Integer senderId;
    private Integer receiverId;
    private LocalDateTime time;
    private String content;
}
