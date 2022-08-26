package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.Mapper.ProductMapper;
import com.example.mybatisplus.Mapper.UserMapper;
import com.example.mybatisplus.pojo.Product;
import com.example.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page,null);
        System.out.println(page);
    }

    @Test
    public void test02(){
        Page<User> page = new Page<>(1,5);
        userMapper.selectPageVo(page,20);
        System.out.println(page);
    }

    @Test
    public void test03(){
        //小李查询的商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李：" + productLi.getPrice());
        //小王查询的商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王：" + productWang.getPrice());
        //小李将商品价格 + 50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        //小王将商品价格 - 30
        productWang.setPrice(productWang.getPrice() - 30);
        int res = productMapper.updateById(productWang);
        if(res == 0){
            Product product = productMapper.selectById(1);
            product.setPrice(product.getPrice() - 30);
            productMapper.updateById(product);
        }
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板：" + productBoss.getPrice());
    }

}
