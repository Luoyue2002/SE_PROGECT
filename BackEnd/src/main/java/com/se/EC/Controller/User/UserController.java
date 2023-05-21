package com.se.EC.Controller.User;

import com.se.EC.Entity.User;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/user") // url 指定
public class UserController {
    @Resource
    private UserServiceInterface userServiceInterface;


    @PostMapping("/register") // 二级url 指定 ，前端访问要用 demo/post
    public ApiResult<Boolean> UserRegister(@RequestBody User user) {

        try {
            userServiceInterface.UserRegister(user);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    // get 方法
    @RequestMapping("/loginbyname")
    public ApiResult<User> UserLoginByName(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password) {

        try {
            User user = userServiceInterface.UserLoginByName(username, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("/loginbyid")
    public ApiResult<User> UserLoginById(@RequestParam(value = "userid") String userid,
                                   @RequestParam(value = "password") String password) {

        try {
            User user = userServiceInterface.UserLoginById(userid, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("/loginbyphone")
    public ApiResult<User> UserLoginByPhone(@RequestParam(value = "userphone") String userphone,
                                      @RequestParam(value = "password") String password) {

        try {
            User user = userServiceInterface.UserLoginByPhone(userphone, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }

    }

    @RequestMapping("/resetinfo")
    public ApiResult<Boolean> UserResetInfo(@RequestParam(value = "userid") String userid,
                                   @RequestParam(value = "attribute") String attribute,
                                   @RequestParam(value = "resetinfo") String resetinfo

    ) {

        try {
            userServiceInterface.UserResetInfo(userid, attribute, resetinfo);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }

    }
}
