package com.ljh.mp.entity;

import com.ljh.mp.enums.AgeEnum;
import com.ljh.mp.enums.GenderEnum;
import com.ljh.mp.enums.GradeEnum;
import com.ljh.mp.enums.UserState;
import lombok.Data;

/**
 * 演示枚举的使用
 */
@Data
public class User2 {

    private String name;

    private String email;

    /**
     * IEnum接口的枚举处理
     */
    private AgeEnum age;

    /**
     * 原生枚举： 默认使用枚举值顺序： 0：MALE， 1：FEMALE
     */
    private GenderEnum gender;

    /**
     * 原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
    private GradeEnum grade;

    private UserState userState;
}