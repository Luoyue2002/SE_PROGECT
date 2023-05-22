package com.se.EC.Service.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.User;
import com.se.EC.Mapper.UserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;


@Service
public class UserService extends MppServiceImpl<UserMapper, User> implements UserServiceInterface {
    @Resource
    private UserMapper userMapper;

    @Override
    public User UserRegister(User userinfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userinfo.getName());
        User findUser = this.baseMapper.selectOne(queryWrapper);
        if (findUser != null) {
            throw new RuntimeException("exist user name !");
        }

        QueryWrapper<User> queryWrapper_email = new QueryWrapper<>();
        queryWrapper_email.eq("phone", userinfo.getPhone());
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
        userQueryWrapper.eq("name", username);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getPassword().equals(password)) {
            return now_user;
        }
        throw new RuntimeException("wrong password!");
    }

    @Override
    public User UserLoginById(String userid, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", userid);
        User now_user = userMapper.selectOne(userQueryWrapper);

        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getPassword().equals(password)) {
            return now_user;
        }

        throw new RuntimeException("wrong password!");
    }

    @Override
    public User UserLoginByPhone(String userphone, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", userphone);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getPassword().equals(password)) {
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
        userQueryWrapper.eq("id", userId);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (attribute.equals("username")) {

            QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
            usernameQueryWrapper.eq("name", resetInfo);
            User name_user = userMapper.selectOne(userQueryWrapper);
            if (name_user == null) {
                throw new RuntimeException("have this user name!");
            }
            now_user.setName(resetInfo);
        }
        if (attribute.equals("realName")) {
            now_user.setRealName(resetInfo);
        }
        if (attribute.equals("identity")) {
            now_user.setIdentity(resetInfo);
        }
        if (attribute.equals("school")) {
            now_user.setSchool(resetInfo);
        }
        if (attribute.equals("schoolId")) {
            now_user.setSchoolId(resetInfo);
        }
        if (attribute.equals("gender")) {
            now_user.setGender(Integer.valueOf(resetInfo));
        }
        if (attribute.equals("phone")) {
            now_user.setPhone(resetInfo);
        }
        if (attribute.equals("address1")) {
            now_user.setAddress1(resetInfo);
        }
        if (attribute.equals("address2")) {
            now_user.setAddress2(resetInfo);
        }
        if (attribute.equals("address3")) {
            now_user.setAddress3(resetInfo);
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
