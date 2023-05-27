package com.se.EC;

import com.se.EC.Entity.User;
import com.se.EC.Mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectronicCommerceApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User(null, "xhy", "123", "123", "123", "123", "123", 0, "123", "123", "123", "123", "123", 0);
        User user2 = new User(null, "x2hy", "123", "123", "123", "123", "123", 1, "123", "123", "123", "123", "123", 0);
        userMapper.insert(user);
        userMapper.insert(user2);
    }

}
