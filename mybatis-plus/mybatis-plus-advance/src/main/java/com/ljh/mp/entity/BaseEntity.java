package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
abstract class BaseEntity {
    /**
     * 创建时间
     */
    // 插入时填充字段
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    // 插入和更新时填充字段
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}