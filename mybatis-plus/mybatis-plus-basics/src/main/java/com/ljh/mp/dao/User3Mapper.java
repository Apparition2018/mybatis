package com.ljh.mp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ljh.mp.entity.User3;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface User3Mapper extends BaseMapper<User3> {

    List<User3> selectUserPage(IPage<User3> page, @Param(Constants.WRAPPER) QueryWrapper<User3> wrapper);
}
