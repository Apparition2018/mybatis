package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * extends Model<T>，为了实现 ActiveRecord 模式
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user", schema = "mp")
public class User extends Model<User> {
    private static final long serialVersionUID = -5093082689837098069L;
    /**
     * 主键
     * AUTO             数据库 ID 自增
     * NONE             跟随全局
     * INPUT            用户输入 ID
     * ASSIGN_ID        类型为 Number (Long 或 Integer) 或 String，使用接口 IdentifierGenerator 的 nextId()，默认实现类为 DefaultIdentifierGenerator 雪花算法
     * ASSIGN_UUID      类型为 String，使用接口 IdentifierGenerator 的 nextUUID()
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 姓名
     * @see com.ljh.mp.RetrieveTest#selectListByWrapperEntity()
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    /**
     * 年龄
     * @see com.ljh.mp.RetrieveTest#selectListByWrapperEntity()
     */
    @TableField(condition = "%s&lt;#{%s}")
    private Integer age;
    /**
     * 邮箱
     */
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String email;
    /**
     * 直属上级
     */
    private Long managerId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
