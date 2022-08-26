package com.example.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.mybatisplus.enums.SexEnum;
import lombok.Data;

@Data
public class User {

//    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableLogic
    private Integer isDelete;

    private SexEnum sex;

}
