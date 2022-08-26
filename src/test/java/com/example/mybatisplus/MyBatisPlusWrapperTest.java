package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.Mapper.UserMapper;
import com.example.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        //查询名字中包含 a 年龄 > 20 并且 Email ！= null
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test2(){
        //查询用户信息 按照年龄降序 年龄相同 按照id升序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test3(){
        //删除邮箱地址为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email")
                .isNull("age");
        int res = userMapper.delete(queryWrapper);
        System.out.println(res);
    }

    @Test
    public void test4(){
        //将（用户的年龄>20并且名字中带有a）或者email为null的信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20).like("name","a")
                .or().isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("123123123");
        int res = userMapper.update(user,queryWrapper);
        System.out.println(res);
    }

    @Test
    public void test5(){
        //将名字中带有a并且（用户的年龄>20或者email为null）的信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setName("小白");
        user.setEmail("123123123");
        int res = userMapper.update(user,queryWrapper);
        System.out.println(res);
    }

    @Test
    public void test6(){
        //查询用户的用户名，年龄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        List<Map<String,Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test7(){
        //查询id < 100的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from user where id <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
