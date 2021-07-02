package com.ljh.mp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 多租户：https://mp.baomidou.com/guide/interceptor-tenant-line.html
 */
@SpringBootTest
public class TenantTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * manager_id 是本例 TenantLineHandler 设置的 tenant_id
     * <p>
     * SELECT id, name, age, email, manager_id, version, create_time, update_time FROM user WHERE deleted = 0 AND manager_id = 1088248166370832385
     */
    @Test
    public void selectList() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * UPDATE user SET age = 29, update_time = '2021-07-02T12:33:01.389'
     * WHERE manager_id = 1088248166370832385 AND id = 1088248166370832385 AND deleted = 0
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(29);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数：" + rows);
    }

    /**
     * INSERT INTO user (id, name, age, email, create_time, manager_id)
     * VALUES
     * (1410842632777183234, '李国民', 27, 'lgm@baomidou.com', '2021-07-02T14:07:50.503', 1088248166370832385)
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("李国民");
        user.setAge(27);
        user.setEmail("lgm@baomidou.com");
        int rows = userMapper.insert(user);
        System.out.println("影响行数：" + rows);
    }

    /**
     * mySelectList 添加了 @InterceptorIgnore(tenantLine = "true")，所以没有没有查询条件里没有添加 manager_id = 1088248166370832385
     * select * from user WHERE (age > 25)
     */
    @Test
    public void mySelectList() {
        List<User> list = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        list.forEach(System.out::println);
    }
}
