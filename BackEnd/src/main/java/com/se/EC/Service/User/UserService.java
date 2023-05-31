package com.se.EC.Service.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.User;
import com.se.EC.Mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends MppServiceImpl<UserMapper, User> implements UserServiceInterface {
    @Resource
    private UserMapper userMapper;

    @Override
    public User userRegister(User userinfo) {
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
    public User userLoginByName(String username, String password) {
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
    public User userLoginById(String userid, String password) {
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
    public User userLoginByPhone(String userPhone, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", userPhone);
        User now_user = userMapper.selectOne(userQueryWrapper);
        if (now_user == null) {
            throw new RuntimeException("no such user!");
        } else if (now_user.getPassword().equals(password)) {
            return now_user;
        }

        throw new RuntimeException("wrong password!");
    }

    @Override
    public String userLogout() {
        return null;
    }

    @Override
    public String userChangePassword() {
        return null;
    }

    @Override
    public String userResetPassword() {
        return null;
    }

    @Override
    public String userResetInfo(Integer userId, String attribute, String resetInfo) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", userId);
        User now_user = userMapper.selectOne(userQueryWrapper);
        switch (attribute) {
            case "username" -> {
                QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
                usernameQueryWrapper.eq("name", resetInfo);
                User name_user = userMapper.selectOne(userQueryWrapper);
                if (name_user == null) {
                    throw new RuntimeException("this user name does not exist!");
                }
                now_user.setName(resetInfo);
            }
            case "realName" -> now_user.setRealName(resetInfo);
            case "name" -> {
                QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
                usernameQueryWrapper.eq("name", resetInfo);
                Long count = userMapper.selectCount(userQueryWrapper);
                if (count != 0) {
                    throw new RuntimeException("this phone number has already exist");
                }
                now_user.setName(resetInfo);
            }
            case "password" -> now_user.setPassword(resetInfo);
            case "identity" -> now_user.setIdentity(resetInfo);
            case "school" -> now_user.setSchool(resetInfo);
            case "schoolId" -> now_user.setSchoolId(resetInfo);
            case "gender" -> {
                int gender = Integer.parseInt(resetInfo);
                if (gender != 0 && gender != 1) {
                    throw new RuntimeException("Gender must either be 0 or 1");
                }
                now_user.setGender(gender);
            }
            case "phone" -> {
                QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
                usernameQueryWrapper.eq("phone", resetInfo);
                Long count = userMapper.selectCount(userQueryWrapper);
                if (count != 0) {
                    throw new RuntimeException("this phone number has already exist");
                }
                now_user.setPhone(resetInfo);
            }
            case "address1" -> now_user.setAddress1(resetInfo);
            case "address2" -> now_user.setAddress2(resetInfo);
            case "address3" -> now_user.setAddress3(resetInfo);
            default -> throw new RuntimeException("Invalid attribute");
        }

        userMapper.updateById(now_user);
        return "success";
    }

    @Override
    public String userAccountCancellation() {
        return null;
    }

    @Override
    public String findList(int userId) {
        return null;
    }

    @Override
    public String userBeBusinessmen() {
        return null;
    }

    @Override
    public String getNameById(Integer id) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", id);
        User user = userMapper.selectOne(userQueryWrapper);
        return user.getName();
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public Boolean ifUserExists(Integer userId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", userId);
        Long count = userMapper.selectCount(userQueryWrapper);
        return count > 0;
    }

    @Override
    public Boolean resetPassword(Integer userId, String oldPassword, String newPassword) {
        boolean exist = ifUserExists(userId);
        if (exist) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("id", userId);
            User user = userMapper.selectOne(userQueryWrapper);
            if (!user.getPassword().equals(oldPassword)) {
                throw new RuntimeException("password not match!");
            }
            user.setPassword(newPassword);
            userMapper.updateById(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean forgetPassword(Integer userId, String phone) {
        boolean exist = ifUserExists(userId);
        if (exist) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("phone", phone);
            User user = userMapper.selectOne(userQueryWrapper);
            if (!user.getPhone().equals(phone)) {
                throw new RuntimeException("phone not match!");
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean ifShop(Integer userId) {
        boolean exist = ifUserExists(userId);
        if (exist) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("id", userId);
            User user = userMapper.selectOne(userQueryWrapper);
            if (user.getIfShop() == 1) {
                return true;
            } else {
                user.setIfShop(1);
                userMapper.updateById(user);
                return false;
            }
        }
        return false;
    }

    @Override
    public void deleteUser(Integer userId) {
        if (ifUserExists(userId)) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("id", userId);
            userMapper.delete(userQueryWrapper);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public User searchById(Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User searchByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User searchByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return userMapper.selectOne(queryWrapper);
    }
}
