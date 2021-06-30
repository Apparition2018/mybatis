package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 乐观锁插件测试类
 * <p>
 * 支持类型：int, Integer, long, Long, Date, Timestamp, LocalDateTime
 * 整数类型下 newVersion = oldVersion + 1
 * newVersion 会回写到 entity 中
 * 仅支持 updateById(id) 与 update(entity, wrapper)
 * 在 update(entity, wrapper) 下，wrapper 不能复用!!!
 */
@SpringBootTest
public class OptTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 当版本号正确时，更新数据后会自动将版本号加1
     * UPDATE user SET update_time=?, version=2, email=? WHERE id=? AND version=1 AND deleted=0
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
     * 乐观锁 在 update(entity, wrapper) 下，wrapper 不能复用!!!
     */
    @Test
    public void update() {
        // UPDATE user SET update_time=?, version=?, email=? WHERE deleted=0 AND (name = ? AND version = ?)
        int version = 2;

        User user = new User();
        user.setEmail("ly3@baomidou.com");
        user.setVersion(version);

        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("name", "李玉");


        int rows = userMapper.update(user, wrapper);
        System.out.println("影响行数：" + rows);


        // UPDATE user SET update_time=?, version=?, email=? WHERE deleted=0 AND (name = ? AND version = ? AND age = ? AND version = ?)
        version = 3;

        user = new User();
        user.setEmail("ly4@baomidou.com");
        user.setVersion(version);
        wrapper.eq("age", 25); // 这里不能服用上面的 wrapper

        rows = userMapper.update(user, wrapper);
        System.out.println("影响行数：" + rows);
    }

}