package com.ljh.mp;

import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class InjectorTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void myDeleteAll() {
        int rows = userMapper.myDeleteAll();
        System.out.println("影响行数：" + rows);
    }

    @Test
    public void insertBatchSomeColumn() {
        User user1 = new User();
        user1.setName("李兴华2");
        user1.setAge(34);
        user1.setManagerId(1088248166370832385L);

        User user2 = new User();
        user2.setName("杨红2");
        user2.setAge(29);
        user2.setManagerId(1088248166370832385L);

        List<User> userList = Arrays.asList(user1, user2);
        int rows = userMapper.insertBatchSomeColumn(userList);
        System.out.println("影响行数：" + rows);
    }

    @Test
    public void deleteByIdWithFill() {
        User user = new User();
        user.setId(1183934094471901186L);
        user.setAge(35);

        int rows = userMapper.deleteByIdWithFill(user);
        System.out.println("影响行数：" + rows);
    }

    @Test
    public void alwaysUpdateSomeColumnById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setName("王第风");

        int rows = userMapper.alwaysUpdateSomeColumnById(user);
        System.out.println("影响行数：" + rows);
    }
}