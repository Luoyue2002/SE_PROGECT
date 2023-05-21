package com.se.EC.Service.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.User;
import com.se.EC.Utils.ApiResult;

import java.util.List;

public interface UserServiceInterface extends IService<User> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    User UserRegister( User userInfo);
    User UserLoginByName( String username  ,String password);
    User UserLoginById( String userId  ,String password);
    User UserLoginByPhone( String userPhone  ,String password);


    String UserLogout( );

    String UserChangePassword( );

    String UserResetPassword( );

    String UserResetInfo( String userId, String  attribute, String resetInfo);

    String UserAccountCancellation();

    String FindList( int userId);

    String UserBeBusinessmen();
}





