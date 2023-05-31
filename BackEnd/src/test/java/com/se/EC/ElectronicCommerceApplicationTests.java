package com.se.EC;

import com.se.EC.Mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ElectronicCommerceApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        Integer[][] a = new Integer[][]{{1, 2, 3}, {4, 5, 6}};
        Integer[][] b;
        b = new Integer[2][];
        for (int i = 0; i < 2; i++) {
            b[i] = Arrays.copyOf(a[i], a[i].length);
        }
        b[1][1] = 999;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }
}
