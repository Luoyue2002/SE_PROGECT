package com.se.EC.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureSearch {
    Integer id;
    List<String> content;
}
