package com.example.backend.User.Controller;



import com.example.backend.User.Entity.User;
import com.example.backend.User.Service.IUserService;
import com.example.backend.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/user") // url 指定
public class UserController {

    @Resource
    private IUserService userService;


    @RequestMapping("/findlist")
    public ApiResult findlist(@RequestParam(value = "userid" , required = true) int userid){

        ApiResult res = userService.findlist(userid);
        return res;

    }

    @PostMapping("/register") // 二级url 指定 ，前端访问要用 demo/post
    public ApiResult UserRegister(@RequestBody User user){

        ApiResult res = userService.user_register(user);
        return res;
    }

// get 方法
    @RequestMapping("/loginbyname")
    public ApiResult UserLoginByName(@RequestParam(value = "username" ,required = true )  String username,
                             @RequestParam(value = "password" ,required = true )  String password  ){
        ApiResult res = userService.user_login_by_name(username,password);
        return res;
    }

    @RequestMapping("/loginbyid")
    public ApiResult UserLoginById(@RequestParam(value = "userid" ,required = true )  String userid,
                                     @RequestParam(value = "password" ,required = true )  String password  ){
        ApiResult res = userService.user_login_by_id(userid,password);
        return res;
    }

    @RequestMapping("/loginbyphone")
    public ApiResult UserLoginByPhone(@RequestParam(value = "userphone" ,required = true )  String userphone,
                                     @RequestParam(value = "password" ,required = true )  String password  ){
        ApiResult res = userService.user_login_by_phone(userphone,password);
        return res;
    }


//    public ApiResult UserLogout( ){
//        ApiResult res = userService.user_logout();
//        return res;
//    }
//
//    public ApiResult UserChangePassword( ){
//        ApiResult res = userService.user_change_password();
//        return res;
//    }

//    public ApiResult UserResetPassword( ){
//        ApiResult res = userService.user_reset_password();
//        return res;
//    }
//
    @RequestMapping("/resetinfo")
    public ApiResult UserResetInfo(@RequestParam(value = "userid" ,required = true )  String userid,
                                   @RequestParam(value = "attribute" ,required = true )  String attribute,
                                   @RequestParam(value = "resetinfo" ,required = true )  String resetinfo

                                   ){
        ApiResult res = userService.user_reset_info( userid , attribute, resetinfo);
        return res;
    }
//
//    public ApiResult UserAccountCancellation(){
//        ApiResult res = userService.user_account_cancellation();
//        return res;
//    }
//
//
//    public ApiResult UserBeBusinessmen(){
//        ApiResult res = userService.user_be_businessmen();
//        return res;
//    }





}
