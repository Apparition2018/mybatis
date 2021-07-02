package com.ljh.mp;

import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * SQL 注入器：https://mp.baomidou.com/guide/sql-injector.html
 * 1）创建定义方法的类 {@link com.ljh.mp.method.DeleteAllMethod}
 * 2）创建注入器 {@link com.ljh.mp.injector.MySqlInjector}
 * 3）在 Mapper 中加入自定义方法 {@link com.ljh.mp.dao.MyMapper}
 */
@SpringBootTest
public class InjectorTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteAll() {
        int rows = userMapper.deleteAll();
        System.out.println("影响行数：" + rows);
    }

    /**
     * 批量插入时自选字段
     * 排除了逻辑删除字段
     * <p>
     * INSERT INTO user (id,name,age,email,manager_id,version,create_time,update_time) VALUES (?,?,?,?,?,?,?,?) , (?,?,?,?,?,?,?,?)
     */
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

    /**
     * 逻辑删除时填充某些字段，需要 @TableField(fill = FieldFill.UPDATE) 配合使用
     * <p>
     * UPDATE user SET age=35,update_time="2021-07-02T17:28:55.598",deleted=1 WHERE id=1094590409767661570 AND deleted=0
     */
    @Test
    public void deleteByIdWithFill() {
        User user = new User();
        user.setId(1094590409767661570L);
        user.setAge(35);

        int rows = userMapper.deleteByIdWithFill(user);
        System.out.println("影响行数：" + rows);
    }

    /**
     * 更新时自选字段
     * name 并没有更新，其它没有给值的更新为 null
     * <p>
     * UPDATE user SET age=26, email=null, manager_id=null, version=null, create_time=null, update_time='2021-07-02T17:25:11.054'
     * WHERE id=1088248166370832385 AND deleted=0
     */
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