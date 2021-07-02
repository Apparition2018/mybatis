package com.ljh.mp;

import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import com.ljh.mp.util.DynamicTableName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 动态表名：https://mp.baomidou.com/guide/interceptor-dynamic-table-name.html
 */
@SpringBootTest
public class DynamicTableNameTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * SELECT id,name,age,email,manager_id,version,create_time,update_time FROM user_2019 WHERE deleted=0
     */
    @Test
    public void selectList() {
        DynamicTableName.set("user_2019");
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
