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
    @Resource //依赖注入,必须要注入对应的mapper
    private UserMapper userMapper;

    @Override
    public User UserRegister(User userinfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userinfo.getUserName());
        User findUser = this.baseMapper.selectOne(queryWrapper);
        if (findUser != null) {
            throw new RuntimeException("exist user name !");
        }

        QueryWrapper<User> queryWrapper_email = new QueryWrapper<>();
        queryWrapper_email.eq("user_phone", userinfo.getUserPhone());
        User find = this.baseMapper.selectOne(queryWrapper_email);
        if (find != null) {
            throw new RuntimeException("phone had been used");
        }

        userMapper.insert(userinfo);
        return userinfo;
    }

    @Override
    public User UserLoginByName(String username, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", username);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getUserPassword().equals(password)) {
            return now_user;
        }
        throw new RuntimeException("wrong password!");
    }

    @Override
    public User UserLoginById(String userid, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userid);
        User now_user = userMapper.selectOne(userQueryWrapper);

        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getUserPassword().equals(password)) {
            return now_user;
        }

        throw new RuntimeException("wrong password!");
    }

    @Override
    public User UserLoginByPhone(String userphone, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_phone", userphone);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getUserPassword().equals(password)) {
            return now_user;
        }

        throw new RuntimeException("wrong password!");
    }

    @Override
    public String UserLogout() {
        return null;
    }

    @Override
    public String UserChangePassword() {
        return null;
    }

    @Override
    public String UserResetPassword() {
        return null;
    }

    @Override
    public String UserResetInfo(String userId, String attribute, String resetInfo) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (attribute.equals("username")) {

            QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
            usernameQueryWrapper.eq("user_name", resetInfo);
            User name_user = userMapper.selectOne(userQueryWrapper);
            if (name_user == null) {
                throw new RuntimeException("have this user name!");
            }
            now_user.setUserName(resetInfo);
        }
        if (attribute.equals("userrealname")) {
            now_user.setUserRealname(resetInfo);
        }
        if (attribute.equals("usericid")) {
            now_user.setUserIcid(resetInfo);
        }
        if (attribute.equals("userschool")) {
            now_user.setUserSchool(resetInfo);
        }
        if (attribute.equals("usersid")) {
            now_user.setUserSid(resetInfo);
        }
        if (attribute.equals("usergender")) {
            now_user.setUserGender(resetInfo);
        }
        if (attribute.equals("userphone")) {
            now_user.setUserPhone(resetInfo);
        }
        if (attribute.equals("useradd1")) {
            now_user.setUserAdd1(resetInfo);
        }
        if (attribute.equals("useradd2")) {
            now_user.setUserAdd2(resetInfo);
        }
        if (attribute.equals("useradd3")) {
            now_user.setUserAdd3(resetInfo);
        }

        userMapper.updateById(now_user);
        return "success";
    }

    @Override
    public String UserAccountCancellation() {
        return null;
    }

    @Override
    public String FindList(int userId) {
        return null;
    }


    @Override
    public String UserBeBusinessmen() {
        return null;
    }


}
