package com.ljh.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 自定义删除方法
     * @return 影响行数
     */
    int myDeleteAll();

    /**
     * mp自带，但需要添加，除 mysql 外慎用
     */
    int insertBatchSomeColumn(List<T> list);

    /**
     * mp自带，但需要添加
     */
    int deleteByIdWithFill(T entity);

    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
}
