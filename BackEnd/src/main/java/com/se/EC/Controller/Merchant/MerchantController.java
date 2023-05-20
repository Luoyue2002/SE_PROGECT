package com.se.EC.Controller.Merchant;

import com.se.EC.Service.Merchant.MerchantServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class MerchantController {
    @Resource
    private MerchantServiceInterface shopServiceInterface;
}
