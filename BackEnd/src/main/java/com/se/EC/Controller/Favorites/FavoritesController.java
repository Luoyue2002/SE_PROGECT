package com.se.EC.Controller.Favorites;

import com.se.EC.Service.Favorites.FavoritesServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class FavoritesController {
    @Resource
    private FavoritesServiceInterface favoritesServiceInterface;
}
