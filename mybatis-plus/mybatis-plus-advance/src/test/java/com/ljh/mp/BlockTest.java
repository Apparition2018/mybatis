package com.ljh.mp;

import com.ljh.mp.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlockTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void myDeleteAll() {
        int rows = userMapper.myDeleteAll();
        System.out.println("影响行数：" + rows);
    }
}