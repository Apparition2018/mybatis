package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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

    @Test
    public void update() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);

        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void update2() {
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

    @Test
    public void update3() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 29).set("age", 30);

        int rows = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 根据lambda构造器更新
     */
    @Test
    public void update4() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "李艺伟").eq(User::getAge, 30).set(User::getAge, 31);

        int rows = userMapper.update(null, lambdaUpdate);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void lambdaQueryChainWrapper() {
        boolean update = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(User::getName, "李艺伟").eq(User::getAge, 31).set(User::getAge, 32).update();
        System.out.println("是否更新成功：" + update);
    }

}
