package com.ljh.mp;

import com.ljh.mp.dao.User2Mapper;
import com.ljh.mp.entity.User2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {

    @Autowired
    private User2Mapper userMapper;

    @Test
    public void insert() {
        User2 user = new User2();
        user.setRealName("向中");
        user.setAge(25);
        user.setEmail("xz@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("我是一个备注信息哦~~");
        int rows = userMapper.insert(user);
        System.out.println("影响记录数：" + rows);
    }

}
