package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 乐观锁：https://mp.baomidou.com/guide/interceptor-optimistic-locker.html
 * <p>
 * 数据库锁：
 * 1）悲观锁：数据库锁机制实现，适用多写场景
 * 2）乐观锁：version，适用多读场景
 * <p>
 * 支持类型：int, Integer, long, Long, Date, Timestamp, LocalDateTime
 * 整数类型下 newVersion = oldVersion + 1
 * newVersion 会回写到 entity 中
 */
@SpringBootTest
public class OptimisticLockerInnerInterceptorTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 当版本号正确时，更新数据后会自动将版本号加1
     * UPDATE user SET email='ly2@baomidou.com', version=2, update_time='2021-07-02T10:13:19.952' WHERE id=1183568091535720449 AND version=1 AND deleted=0
     */
    @Test
    public void updateById() {
        int version = 1;

        User user = new User();
        user.setId(1183568091535720449L);
        user.setEmail("ly2@baomidou.com");
        user.setVersion(version);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数：" + rows);
    }

    /**
     * 仅支持 updateById(id) 与 update(entity, wrapper)
     * 乐观锁 在 update(entity, wrapper) 下，wrapper 不能复用!!!
     */
    @Test
    public void update() {
        // UPDATE user SET email='ly3@baomidou.com', version=3, update_time='2021-07-02T10:17:32.363' WHERE deleted=0 AND (name = '李玉' AND version = 2)
        int version = 2;

        User user = new User();
        user.setEmail("ly3@baomidou.com");
        user.setVersion(version);

        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("name", "李玉");

        int rows = userMapper.update(user, wrapper);
        System.out.println("影响行数：" + rows);


        // UPDATE user SET email='ly4@baomidou.com', version=4, update_time='2021-07-02T10:17:32.709'
        // WHERE deleted=0 AND (name = '李玉' AND version = 2 AND age = 25 AND version = 3)
        version = 3;

        user = new User();
        user.setEmail("ly4@baomidou.com");
        user.setVersion(version);
        // 这里复用了上面的 wrapper，所以出现了 version = 2 AND version = 3 的错误
        wrapper.eq("age", 25);

        rows = userMapper.update(user, wrapper);
        System.out.println("影响行数：" + rows);
    }

}