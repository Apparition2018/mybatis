package com.ljh.mp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 逻辑删除：https://mp.baomidou.com/guide/logic-delete.html
 */
@SpringBootTest
public class LogicDeleteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * UPDATE user SET deleted=1 WHERE id=1094592041087729666 AND deleted=0
     */
    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响行数：" + rows);
    }

    /**
     * 如果已配置了逻辑删除，默认只会查询出未逻辑删除的数据（自定义语句无效）
     * SELECT id,name,age,email,manager_id,version,create_time,update_time FROM user WHERE deleted=0
     */
    @Test
    public void selectList() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 如果已配置了逻辑删除，已逻辑删除的数据无法更新（自定义语句无效）
     * UPDATE user SET age=29 WHERE id=1088248166370832385 AND deleted=0
     * UPDATE user SET age=29 WHERE id=1094592041087729666 AND deleted=0
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
     * 即使已添加了注解 @TableField(select = false)，自定义语句还是会查询出所有数据
     * select * from user WHERE (age > 25)
     */
    @Test
    public void mySelectList() {
        List<User> list = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        list.forEach(System.out::println);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println("user = " + user);
    }
}