package com.ljh.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ljh.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @author ljh
 * @since 2023/7/20 10:43
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {
}
