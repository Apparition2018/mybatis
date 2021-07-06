package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 表前缀
 */
@Data
@TableName("mp_user")
public class User2 {
    @TableId
    private Long userId;
    @TableField("name")
    private String realName;
    private Integer age;
    private String email;
    private Long managerId;
    @OrderBy
    private LocalDateTime createTime;
    @TableField(exist = false)
    private String remark;
}
