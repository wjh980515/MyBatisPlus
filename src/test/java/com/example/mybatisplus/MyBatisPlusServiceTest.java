package com.example.mybatisplus;

import com.example.mybatisplus.pojo.User;
import com.example.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCount(){
        long count = userService.count();
        System.out.println(count);
    }
    
    @Test
    public void testInsertBatch(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("用户" + i);
            user.setAge(20 + i);
            userList.add(user);
        }
        boolean flag = userService.saveBatch(userList);
        System.out.println(flag);
    }

}
