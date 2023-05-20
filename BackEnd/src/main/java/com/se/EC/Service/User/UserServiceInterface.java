package com.se.EC.Service.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.se.EC.Entity.User;
import com.se.EC.Utils.ApiResult;

import java.util.List;

public interface UserServiceInterface extends IService<User> {

    // Service 层接口， impl 里是具体实现，有一说一分开似乎没啥用，不过我学的教程是分开的
    // Service 层为Controller 层提供服务，是mapper 的上一层

    ApiResult user_register( User userinfo);
    ApiResult user_login_by_name( String username  ,String password);
    ApiResult user_login_by_id( String userid  ,String password);
    ApiResult user_login_by_phone( String userphone  ,String password);


    ApiResult user_logout( );

    ApiResult user_change_password( );

    ApiResult user_reset_password( );

    ApiResult user_reset_info( String userid, String  attribute, String resetinfo);

    ApiResult user_account_cancellation();

    ApiResult findList( int userid);

    ApiResult user_be_businessmen();
}





