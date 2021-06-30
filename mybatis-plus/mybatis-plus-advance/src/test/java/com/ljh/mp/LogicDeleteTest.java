package com.ljh.mp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.config.MybatisPlusConfig;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LogicDeleteTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响行数：" + rows);
    }

    /**
     * 如果已配置了逻辑删除，默认只会查询出未逻辑删除的数据（自定义语句无效）
     * 1)mybatis-plus.global-config.db-config.logic-not-delete-value
     * 2)mybatis-plus.global-config.db-config.logic-delete-value
     */
    @Test
    public void selectList() {
        MybatisPlusConfig.myTableName.set("user_2019");

        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 如果已配置了逻辑删除，已逻辑删除的数据无法更新（自定义语句无效）
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(29);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数：" + rows);

        user.setId(1094592041087729666L);
        rows = userMapper.updateById(user);
        System.out.println("影响行数：" + rows);
    }

    /**
     * 即使已配置了逻辑删，自定义语句会查询出所有数据
     */
    @Test
    public void mySelectList() {
        MybatisPlusConfig.myTableName.set("user_2019");

        List<User> list = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        list.forEach(System.out::println);
    }

    @Test
    public void selectById() {
        MybatisPlusConfig.myTableName.set("user_2019");

        User user = userMapper.selectById(1087982257332887553L);
        System.out.println("user = " + user);
    }
}