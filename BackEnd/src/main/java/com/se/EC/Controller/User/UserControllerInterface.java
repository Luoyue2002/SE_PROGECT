package com.se.EC.Controller.User;

import com.se.EC.Entity.User;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserControllerInterface {
    /**
     * 用户注册
     * @param user 用户
     * @return 成功/失败
     */
    @PostMapping("/register")
    ApiResult<Boolean> UserRegister(@RequestBody User user);

    /**
     * 通过名字登录
     * @param username 用户名
     * @param password 密码
     * @return 用户具体信息
     */
    @RequestMapping("/loginByName")
    ApiResult<User> UserLoginByName(@RequestParam(value = "userName") String username,
                                    @RequestParam(value = "password") String password);

    /**
     * 通过id登录
     * @param userid 用户id
     * @param password 密码
     * @return 用户具体信息
     */
    @RequestMapping("/loginById")
    ApiResult<User> UserLoginById(@RequestParam(value = "userId") String userid,
                                  @RequestParam(value = "password") String password);

    /**
     * 通过电话登录
     * @param userPhone 用户电话
     * @param password 密码
     * @return 用户具体信息
     */
    @RequestMapping("/loginByPhone")
    ApiResult<User> UserLoginByPhone(@RequestParam(value = "userPhone") String userPhone,
                                     @RequestParam(value = "password") String password);

    /**
     * 修改信息
     * @param userid 用户id
     * @param attribute 字段
     * @param resetInformation 信息
     * @return 成功/失败
     */
    @RequestMapping("/resetInformation")
    ApiResult<Boolean> UserResetInfo(@RequestParam(value = "userId") Integer userid,
                                     @RequestParam(value = "attribute") String attribute,
                                     @RequestParam(value = "resetInformation") String resetInformation);


    @RequestMapping("/resetPassword")
    ApiResult<Boolean> ResetPassword(@RequestParam(value = "userId")Integer userId,
                                     @RequestParam(value = "oldPassword") String oldPassword,
                                     @RequestParam(value = "newPassword") String newPassword);


    @RequestMapping("/forgetPassword")
    ApiResult<Boolean> ForgetPassword(@RequestParam(value = "userId")Integer userId,
                                     @RequestParam(value = "phone") String phone);



    @RequestMapping("/ifShop")
    ApiResult<Boolean> IfShop(@RequestParam(value = "userId")Integer userId);
}
