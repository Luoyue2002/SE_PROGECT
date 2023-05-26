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

    @PostMapping("/register")
    public ApiResult<Boolean> UserRegister(@RequestBody User user) {
        try {
            userServiceInterface.userRegister(user);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @RequestMapping("/loginByName")
    public ApiResult<User> UserLoginByName(@RequestParam(value = "userName") String username,
                                     @RequestParam(value = "password") String password) {
        try {
            User user = userServiceInterface.userLoginByName(username, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("/loginById")
    public ApiResult<User> UserLoginById(@RequestParam(value = "userId") String userid,
                                   @RequestParam(value = "password") String password) {
        try {
            User user = userServiceInterface.userLoginById(userid, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("/loginByPhone")
    public ApiResult<User> UserLoginByPhone(@RequestParam(value = "userPhone") String userphone,
                                      @RequestParam(value = "password") String password) {
        try {
            User user = userServiceInterface.userLoginByPhone(userphone, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }

    }

    @RequestMapping("/resetInformation")
    public ApiResult<Boolean> UserResetInfo(@RequestParam(value = "userId") String userid,
                                   @RequestParam(value = "attribute") String attribute,
                                   @RequestParam(value = "resetInformation") String resetInformation

    ) {
        try {
            userServiceInterface.userResetInfo(userid, attribute, resetInformation);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }
}
