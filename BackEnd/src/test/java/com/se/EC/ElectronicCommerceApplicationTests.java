package com.se.EC;

import com.se.EC.Controller.Commodity.CommodityController;
import com.se.EC.Entity.User;
import com.se.EC.Mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ElectronicCommerceApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        CommodityController commodityController = new CommodityController();
        String text = "iphone 13 pro max 星空紫";
        List<String> result = commodityController.tokenizer(text);
        System.out.println(result);
    }
}
