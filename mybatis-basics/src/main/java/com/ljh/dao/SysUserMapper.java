package com.ljh.dao;

import com.ljh.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysUserMapper
 *
 * @author ljh
 * created on 2022/4/20 10:04
 */
@Mapper
public interface SysUserMapper {
    List<SysUser> list(SysUser sysUser);
}
