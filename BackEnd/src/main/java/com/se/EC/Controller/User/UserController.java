package com.se.EC.Controller.User;

import com.se.EC.Entity.User;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/user") // url 指定
public class UserController {
    @Resource
    private UserServiceInterface userServiceInterface;

    @RequestMapping("/findlist")
    public <T> ApiResult<T> findlist(@RequestParam(value = "userid") int userid) {
        return userServiceInterface.findList(userid);
    }

    @PostMapping("/register") // 二级url 指定 ，前端访问要用 demo/post
    public ApiResult<User> UserRegister(@RequestBody User user) {
        return userServiceInterface.user_register(user);
    }

    // get 方法
    @RequestMapping("/loginbyname")
    public ApiResult<User> UserLoginByName(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password) {
        return userServiceInterface.user_login_by_name(username, password);
    }

    @RequestMapping("/loginbyid")
    public ApiResult<User> UserLoginById(@RequestParam(value = "userid") String userid,
                                   @RequestParam(value = "password") String password) {
        return userServiceInterface.user_login_by_id(userid, password);
    }

    @RequestMapping("/loginbyphone")
    public ApiResult<User> UserLoginByPhone(@RequestParam(value = "userphone") String userphone,
                                      @RequestParam(value = "password") String password) {
        return userServiceInterface.user_login_by_phone(userphone, password);
    }

    @RequestMapping("/resetinfo")
    public ApiResult<User> UserResetInfo(@RequestParam(value = "userid") String userid,
                                   @RequestParam(value = "attribute") String attribute,
                                   @RequestParam(value = "resetinfo") String resetinfo

    ) {
        return userServiceInterface.user_reset_info(userid, attribute, resetinfo);
    }
}
