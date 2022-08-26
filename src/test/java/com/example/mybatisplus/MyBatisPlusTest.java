package com.example.mybatisplus;

import com.example.mybatisplus.Mapper.UserMapper;
import com.example.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testList(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("wjh");
        user.setAge(24);
        user.setEmail("1779066624@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        int res = userMapper.deleteById(1549735468384788481L);
        System.out.println(res);
    }

    @Test
    public void testSelect(){
        Map<String,Object> map = userMapper.selectMapById(1L);
        System.out.println(map);;
    }

}
