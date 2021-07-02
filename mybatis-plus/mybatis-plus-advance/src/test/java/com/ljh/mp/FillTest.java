package com.ljh.mp;

import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * 自动填充：https://mp.baomidou.com/guide/auto-fill-metainfo.html
 */
@SpringBootTest
public class FillTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * INSERT INTO user ( id, name, age, email, manager_id, create_time )
     * VALUES
     * ( 1410775900897218561, '刘西', 30, 'lc@baomidou.com', 1088248166370832385, '2021-07-02T09:42:40.383' )
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("刘西");
        user.setAge(30);
        user.setEmail("lc@baomidou.com");
        user.setManagerId(1088248166370832385L);
        int rows = userMapper.insert(user);
        System.out.println("影响行数：" + rows);
    }

    /**
     * UPDATE user SET age=28, update_time='2021-07-02T09:37:14.603' WHERE id=1088248166370832385 AND deleted=0
     */
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