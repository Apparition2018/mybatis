package com.ljh;

import com.ljh.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AssociationTest
 *
 * @author ljh
 * created on 2021/8/16 2:24
 */
@SpringBootTest
public class AssociationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.list().forEach(System.out::println);
    }
}
