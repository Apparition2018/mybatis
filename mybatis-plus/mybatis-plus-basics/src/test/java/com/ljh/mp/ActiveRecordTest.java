package com.ljh.mp;

import com.ljh.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ActiveRecordTest {

    @Test
    public void insert() {
        User user = new User();
        user.setName("钱大忘6");
        user.setAge(26);
        user.setEmail("qd6@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        boolean insert = user.insert();
        System.out.println("是否插入成功：" + insert);
        System.out.println("主键：" + user.getId());
    }

//    @Test
//    public void selectById() {
//        User user = new User();
//        User userSelect = user.selectById(1182466166538932226L);
//        System.out.println(userSelect == user);
//        System.out.println("userSelect = " + userSelect);
//    }
//
//    @Test
//    public void selectById2() {
//        User user = new User();
//        user.setId(1182466166538932226L);
//        User userSelect = user.selectById();
//        System.out.println(userSelect == user);
//        System.out.println("userSelect = " + userSelect);
//    }
//
//    @Test
//    public void updateById() {
//        User user = new User();
//        user.setId(1182466166538932226L);
//        user.setName("张草草");
//        boolean updateById = user.updateById();
//        System.out.println("更新是否成功：" + updateById);
//    }
//
//    @Test
//    public void deleteById() {
//        User user = new User();
//        boolean deleteById = user.deleteById(1182466166538932226L);
//        System.out.println("删除是否成功：" + deleteById);
//    }
//
//    @Test
//    public void insertOrUpdate() {
//        User user = new User();
//        user.setId(1182468429223342082L);
//        user.setName("张强");
//        user.setAge(24);
//        user.setEmail("zq@baomidou.com");
//        user.setManagerId(1088248166370832385L);
//        user.setCreateTime(LocalDateTime.now());
//        boolean insert = user.insertOrUpdate(); // 不设置id或id不存在则insert，否则为update
//        System.out.println("是否插入成功：" + insert);
//    }

}
