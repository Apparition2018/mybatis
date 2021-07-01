package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mp_user")
public class User2 {
    /**
     * 主键
     */
    @TableId
    private Long userId;
    /**
     * 姓名
     */
    @TableField("name")
    private String realName;
    /**
     * 年龄
     */
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
     * 创建时间
     */
    @OrderBy
    private LocalDateTime createTime;
    /**
     * 备注
     */
    @TableField(exist = false)
    private String remark;

}
