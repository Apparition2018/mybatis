package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 联表查询
 */
@Setter
@Getter
@ToString
@TableName("user3")
public class User3 {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "company_id", property = "company.id")
    private Company company;
    private String name;
    private Integer age;
    private String email;
    @Data
    @TableName("company")
    public static class Company {
        private Long id;
        private String name;
    }
}
