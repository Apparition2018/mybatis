package com.ljh.dao;

import com.ljh.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author ljh
 * created on 2021/8/16 2:22
 */
@Repository
public interface UserMapper {

    List<User> list();
}
