package com.ljh.mp.entity;

import com.ljh.mp.enums.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用枚举
 */
@Data
@Accessors(chain = true)
public class Person {
    private Long id;
    private String name;
    private String email;
    /**
     * 1. IEnum 接口的枚举处理
     */
    private AgeEnum age;
    /**
     * 2. 原生枚举：默认使用枚举值顺序：0：MALE，1：FEMALE
     */
    private GenderEnum gender;
    /**
     * 3. 原生枚举：数据库的值对应 带@EnumValue 属性
     */
    private GradeEnum grade;
    private UserState userState;
    private StrEnum strEnum;
}