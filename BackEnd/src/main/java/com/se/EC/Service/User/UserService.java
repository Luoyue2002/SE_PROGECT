package com.se.EC.Service.User;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.se.EC.Entity.User;
import com.se.EC.Mapper.UserMapper;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;


@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserServiceInterface {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private UserMapper userMapper;


    @Override
    public ApiResult user_register(User userinfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userinfo.getUser_name());
        User finduser = this.baseMapper.selectOne(queryWrapper);
        if (finduser != null) {
            return ApiResult.error("exist user name");
        }


        QueryWrapper<User> queryWrapper_email = new QueryWrapper();
        queryWrapper_email.eq("user_phone", userinfo.getUser_phone());
        User finduser_eamil = this.baseMapper.selectOne(queryWrapper_email);
        if (finduser_eamil != null) {
            return ApiResult.error("phone had been used");
        }


        userMapper.insert(userinfo);
        return ApiResult.success("register succsee!");
    }

    @Override
    public ApiResult user_login_by_name(String username, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", username);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            return ApiResult.error("no such user!");
        } else if (now_user.getUser_password().equals(password)) {
            return ApiResult.success("login success!");
        }

        return ApiResult.error("wrong password!");
    }

    @Override
    public ApiResult user_login_by_id(String userid, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userid);
        User now_user = userMapper.selectOne(userQueryWrapper);

        if (now_user == null) {
            return ApiResult.error("no such user!");
        } else if (now_user.getUser_password().equals(password)) {
            return ApiResult.success("login success!");
        }

        return ApiResult.error("wrong password!");
    }

    @Override
    public ApiResult user_login_by_phone(String userphone, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_phone", userphone);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            return ApiResult.error("no such user!");
        } else if (now_user.getUser_password().equals(password)) {
            return ApiResult.success("login success!");
        }

        return ApiResult.error("wrong password!");
    }

    @Override
    public ApiResult user_logout() {
        return null;
    }

    @Override
    public ApiResult user_change_password() {
        return null;
    }

    @Override
    public ApiResult user_reset_password() {
        return null;
    }

    @Override
    public ApiResult user_reset_info(String userid, String attribute, String resetinfo) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userid);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (attribute.equals("username")) {

            QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
            usernameQueryWrapper.eq("user_name", resetinfo);
            User name_user = userMapper.selectOne(userQueryWrapper);
            if (name_user == null) {
                return ApiResult.error("have this user name");
            }
            now_user.setUser_name(resetinfo);
        }
        if (attribute.equals("userrealname")) {
            now_user.setUser_realname(resetinfo);
        }
        if (attribute.equals("usericid")) {
            now_user.setUser_icid(resetinfo);
        }
        if (attribute.equals("userschool")) {
            now_user.setUser_school(resetinfo);
        }
        if (attribute.equals("usersid")) {
            now_user.setUser_sid(resetinfo);
        }
        if (attribute.equals("usergender")) {
            now_user.setUser_gender(resetinfo);
        }
        if (attribute.equals("userphone")) {
            now_user.setUser_phone(resetinfo);
        }
        if (attribute.equals("useradd1")) {
            now_user.setUser_add1(resetinfo);
        }
        if (attribute.equals("useradd2")) {
            now_user.setUser_add2(resetinfo);
        }
        if (attribute.equals("useradd3")) {
            now_user.setUser_add3(resetinfo);
        }

        userMapper.updateById(now_user);
        return ApiResult.success();
    }

    @Override
    public ApiResult user_account_cancellation() {
        return null;
    }

    @Override
    public ApiResult findList(int userid) {
        List<User> users = userMapper.selectList(null);
        return ApiResult.success(users);
    }

    @Override
    public ApiResult user_be_businessmen() {
        return null;
    }
}
