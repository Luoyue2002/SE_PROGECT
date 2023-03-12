package com.example.backend.Controller;



import com.example.backend.Service.IEntityService;
import com.example.backend.utils.ApiResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/demo") // url 指定
public class Controller {

    @Resource
    private IEntityService entityService;



    @PostMapping("/post") // 二级url 指定 ，前端访问要用 demo/post
    public ApiResult demopost(@RequestBody int demo){

        ApiResult res = entityService.Demo(demo);
        return res;
    }

// get 方法
    @RequestMapping("/get") // 二级url 指定 ，前端访问要用 demo/get
    public ApiResult demoget(@RequestParam(value = "demo" ,required = false )  int demo){
                        // 指明get 方法穿过来的参数的对应关系和是否必须
        ApiResult res = entityService.Demo(demo);
        return res;
    }





}
