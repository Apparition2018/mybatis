package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateByIds() {
        User user = new User();
//        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * UPDATE user SET age=?, email=? WHERE (name = ? AND age = ?)
     */
    @Test
    public void updateWrapper1() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);

        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * UPDATE user SET age=?, email=? WHERE name LIKE CONCAT('%',?,'%') AND (age = ?)
     */
    @Test
    public void updateWrapper2() {
        User whereUser = new User();
        whereUser.setName("李艺伟");

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>(whereUser);
        updateWrapper.eq("age", 28);

        User user = new User();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);

        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * UPDATE user SET age=? WHERE (name = ? AND age = ?)
     */
    @Test
    public void updateWrapper3() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 29).set("age", 30);

        int rows = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * UPDATE user SET age=? WHERE (name = ? AND age = ?)
     */
    @Test
    public void lambda() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "李艺伟").eq(User::getAge, 30).set(User::getAge, 31);

        int rows = userMapper.update(null, lambdaUpdate);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * UPDATE user SET age=? WHERE (name = ? AND age = ?)
     */
    @Test
    public void lambdaQueryChainWrapper() {
        boolean update = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(User::getName, "李艺伟").eq(User::getAge, 31).set(User::getAge, 32).update();
        System.out.println("是否更新成功：" + update);
    }

}
