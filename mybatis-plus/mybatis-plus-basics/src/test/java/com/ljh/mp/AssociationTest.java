package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljh.mp.entity.User3;
import com.ljh.mp.service.User3Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 联表查询
 */
@SpringBootTest
public class AssociationTest {

    @Autowired
    User3Service user3Service;

    @Test
    public void selectList() {
        user3Service.list().forEach(u -> System.out.println(u.getCompany()));
    }
    
    @Test
    public void insert() {
        List<User3> userList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            User3.Company cmp = new User3.Company();
            cmp.setId(1L);
            User3 user = new User3();
            user.setId(100L + i);
            user.setCompany(cmp);
            user.setName("Han Meimei" + i);
            user.setEmail(user.getName() + "@baomidou.com");
            user.setAge(18);
            userList.add(user);
        }
        user3Service.saveBatch(userList);
        user3Service.list().forEach(System.out::println);
        selectPage();
        update();
    }
    
    private void selectPage() {
        QueryWrapper<User3> wrapper = new QueryWrapper<>();
        wrapper.eq("u.company_id", 1);
        int pageSize = 3;
        IPage<User3> page = new Page<>(1, pageSize);
        List<User3> userList = user3Service.selectUserPage(page, wrapper);
        for (int i = 1; i <= page.getPages(); i++) {
            page = new Page<>(i, pageSize);
            userList = user3Service.selectUserPage(page, wrapper);
            System.out.printf("======>共%d条数据,当前显示第%d页，每页%d条，共%d页=====>\n", page.getTotal(), page.getCurrent(), page.getSize(), page.getPages());
            userList.forEach(System.out::println);
        }
    }

    private void update() {
        System.out.println("=====开始更新=====>");
        UpdateWrapper<User3> wrapper = new UpdateWrapper<>();
        wrapper.eq("company_id", 1);
        User3 user = new User3();
        user.setName(new Date().getTime() + "");
        user3Service.update(user, wrapper);
        selectPage();
    }
}
