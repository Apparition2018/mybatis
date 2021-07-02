package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    private static final long serialVersionUID = 777948939903573949L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    // @TableField(fill = FieldFill.UPDATE)
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 直属上级
     */
    private Long managerId;
    /**
     * 版本
     */
    @Version
    private Integer version;
    /**
     * 逻辑删除（0：未删除 1：已删除）
     */
    @TableLogic
    // 查询的时候不显示该字段（自定义语句无效）
    @TableField(select = false)
    private Integer deleted;
}