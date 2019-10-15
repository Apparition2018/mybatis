package com.ljh.mp;

import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FillTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("李国民");
        user.setAge(27);
        user.setEmail("lgm@baomidou.com");
        int rows = userMapper.insert(user);
        System.out.println("影响行数：" + rows);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(29);
//        user.setUpdateTime(LocalDateTime.now());
        int rows = userMapper.updateById(user);
        System.out.println("影响行数：" + rows);
    }

}