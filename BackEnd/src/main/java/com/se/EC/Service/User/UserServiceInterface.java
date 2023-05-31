package com.se.EC.Service.User;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.User;

import java.util.List;


public interface UserServiceInterface extends IMppService<User> {

    User userRegister(User userInfo);

    User userLoginByName(String username, String password);

    User userLoginById(String userId, String password);

    User userLoginByPhone(String userPhone, String password);

    User searchById(Integer id);

    User searchByName(String name);

    User searchByPhone(String phone);

    String userLogout();

    String userChangePassword();

    String userResetPassword();

    String userResetInfo(Integer userId, String attribute, String resetInfo);

    String userAccountCancellation();

    String findList(int userId);

    String userBeBusinessmen();

    String getNameById(Integer id);

    List<User> getUsers();

    Boolean ifUserExists(Integer userId);

    Boolean resetPassword(Integer userId, String oldPassword, String newPassword);

    Boolean forgetPassword(Integer userId, String phone);

    Boolean ifShop(Integer userId);

    void deleteUser(Integer userId);
}





