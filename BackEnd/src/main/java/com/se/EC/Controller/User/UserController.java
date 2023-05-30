package com.se.EC.Controller.User;

import com.se.EC.Entity.User;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerInterface {
    @Resource
    private UserServiceInterface userServiceInterface;

    @PostMapping("/register")
    public ApiResult<Boolean> userRegister(@RequestBody User user) {
        try {
            userServiceInterface.userRegister(user);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @RequestMapping("/loginByName")
    public ApiResult<User> userLoginByName(@RequestParam(value = "userName") String username,
                                           @RequestParam(value = "password") String password) {
        try {
            User user = userServiceInterface.userLoginByName(username, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("/loginById")
    public ApiResult<User> userLoginById(@RequestParam(value = "userId") String userid,
                                         @RequestParam(value = "password") String password) {
        try {
            User user = userServiceInterface.userLoginById(userid, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }
    }

    @RequestMapping("/loginByPhone")
    public ApiResult<User> userLoginByPhone(@RequestParam(value = "userPhone") String userPhone,
                                            @RequestParam(value = "password") String password) {
        try {
            User user = userServiceInterface.userLoginByPhone(userPhone, password);
            return ApiResult.success(user);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), null);
        }

    }

    @RequestMapping("/resetInformation")
    public ApiResult<Boolean> userResetInfo(@RequestParam(value = "userId") Integer userid,
                                            @RequestParam(value = "attribute") String attribute,
                                            @RequestParam(value = "resetInformation") String resetInformation) {
        try {
            checkUser(userid);
            userServiceInterface.userResetInfo(userid, attribute, resetInformation);
            return ApiResult.success(Boolean.TRUE);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }


    @RequestMapping("/resetPassword")
    public ApiResult<Boolean> resetPassword(@RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "oldPassword") String oldPassword,
                                            @RequestParam(value = "newPassword") String newPassword) {
        try {
            boolean success = userServiceInterface.resetPassword(userId, oldPassword, newPassword);
            if (success) {
                return ApiResult.success(true);
            }
            else {
                return ApiResult.error("false");
            }
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/forgetPassword")
    public ApiResult<Boolean> forgetPassword(@RequestParam(value = "userId") Integer userId,
                                             @RequestParam(value = "phone") String phone) {
        try {
            userServiceInterface.forgetPassword(userId, phone);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/ifShop")
    public ApiResult<Boolean> ifShop(@RequestParam(value = "userId") Integer userId) {
        try {
            userServiceInterface.ifShop(userId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    @Override
    @RequestMapping("/deleteUser")
    public ApiResult<Boolean> deleteUser(@RequestParam(value = "userId") Integer userId) {
        try {
            userServiceInterface.deleteUser(userId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage(), Boolean.FALSE);
        }
    }

    /**
     * 检查用户是否存在
     *
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }
}
