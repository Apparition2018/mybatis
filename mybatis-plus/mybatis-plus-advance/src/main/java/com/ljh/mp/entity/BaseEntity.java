package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
abstract class BaseEntity<T extends Model> extends Model {
    // 创建时间
    @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
    private LocalDateTime createTime;
    // 修改时间
    @TableField(fill = FieldFill.UPDATE)    // 插入和更新时填充字段
    private LocalDateTime updateTime;
}