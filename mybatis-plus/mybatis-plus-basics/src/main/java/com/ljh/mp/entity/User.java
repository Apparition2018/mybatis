package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

// extends Model<T> 是为了实现ActiveRecord模式
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

    private static final Long serialVersionUID = 1L;

    // 主键
//    @TableId(type = IdType.AUTO)              // 主键全局策略在yml配置，主键策略详细可查看 IdType.java
//    @TableId(type = IdType.NONE)
//    @TableId(type = IdType.ID_WORKER);        // 雪花算法id
//    @TableId(type = IdType.UUID)
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    // 姓名
    @TableField(condition = SqlCondition.LIKE)  // RetrieveTest: selectList12() 中name字段默认变成了like查询
    private String name;
    // 年龄
    @TableField(condition = "%s&lt;#{%s}")      // RetrieveTest: selectList12() 中age字段默认变成了<查询
    private Integer age;
    // 邮箱
    private String email;
    // 直属上级
    private Long managerId;
    // 创建时间
    private LocalDateTime createTime;

}
