package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity<User> {

    // 主键
    private Long id;
    // 姓名
    private String name;
    // 年龄
    @TableField(fill = FieldFill.UPDATE)
    private Integer age;
    // 邮箱
    private String email;
    // 直属上级
    private Long managerId;
    // 版本
    @Version
    private Integer version;
    // 逻辑删除表示(0.未删除 1.已删除)
    @TableLogic
    @TableField(select = false)           // select=false，表示该字段查询的时候不显示（自定义语句无效）
    private Integer deleted;

}