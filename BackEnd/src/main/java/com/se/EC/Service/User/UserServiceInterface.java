package com.se.EC.Service.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.User;

public interface UserServiceInterface extends IService<User> {

    User UserRegister(User userInfo);

    User UserLoginByName(String username, String password);

    User UserLoginById(String userId, String password);

    User UserLoginByPhone(String userPhone, String password);

    String UserLogout();

    String UserChangePassword();

    String UserResetPassword();

    String UserResetInfo(String userId, String attribute, String resetInfo);

    String UserAccountCancellation();

    String FindList(int userId);

    String UserBeBusinessmen();
}





