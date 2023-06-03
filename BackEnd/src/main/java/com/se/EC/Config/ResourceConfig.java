package com.se.EC.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ResourceConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射static路径的请求到static目录下
        // 静态资源访问路径和存放路径配置
        // 通过image访问本地的图片
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:E:\\WorkField\\BS\\ElectronicCommerce\\BackEnd\\src\\main\\resources\\static\\image\\");
    }
}
