package com.ljh.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.mp.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
