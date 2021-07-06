package com.ljh.mp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.entity.User;
import com.ljh.mp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 通用 Service
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService UserService;

    @Test
    public void getOne() {
        // false 表示当结果多余一个时，是否报错
        User user = UserService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25), false);
        System.out.println("user = " + user);
    }

    @Test
    public void saveBatch() {
        User user1 = new User();
        user1.setName("徐丽1");
        user1.setAge(28);

        User user2 = new User();
        user2.setName("徐丽2");
        user2.setAge(29);

        boolean saveBatch = UserService.saveBatch(Arrays.asList(user1, user2));
        System.out.println("是否保存成功：" + saveBatch);
    }

    @Test
    public void saveOrUpdateBatch() {
        User user1 = new User();
        user1.setName("徐丽3");
        user1.setAge(28);

        User user2 = new User();
        user2.setId("1182493411299102722");
        user2.setName("徐力2");
        user2.setAge(30);

        boolean saveBatch = UserService.saveOrUpdateBatch(Arrays.asList(user1, user2));
        System.out.println("是否操作成功：" + saveBatch);
    }

    @Test
    public void lambdaQuery() {
        List<User> userList = UserService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void lambdaUpdate() {
        boolean update = UserService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
        System.out.println("是否更新成功：" + update);
    }

    @Test
    public void lambdaUpdate2() {
        boolean remove = UserService.lambdaUpdate().eq(User::getAge, 24).remove();
        System.out.println("是否删除成功：" + remove);
    }

}