package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljh.mp.entity.User3;
import com.ljh.mp.entity.User3.Company;
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
    public void insert() {
        List<User3> userList = new ArrayList<>();
        Company company1 = new Company().setId(1L).setName("Google");
        Company company2 = new Company().setId(2L).setName("Baidu");
        userList.add(new User3().setId(4L).setName("Patricia").setAge(18).setEmail("Patricia@baomidou.com").setCompany(company2));
        userList.add(new User3().setId(5L).setName("Robert").setAge(20).setEmail("Robert@baomidou.com").setCompany(company1));
        userList.add(new User3().setId(6L).setName("Linda").setAge(22).setEmail("Linda@baomidou.com").setCompany(company2));
        user3Service.saveBatch(userList);
        selectPage();
        update();
    }
    
    private void selectPage() {
        QueryWrapper<User3> queryWrapper = new QueryWrapper<>();
        int pageSize = 3;
        IPage<User3> page = new Page<>(1, pageSize);
        List<User3> userList = user3Service.selectUserPage(page, queryWrapper);
        for (int i = 1; i <= page.getPages(); i++) {
            page = new Page<>(i, pageSize);
            userList = user3Service.selectUserPage(page, queryWrapper);
            System.out.printf("====== 共%d条数据,当前显示第%d页，每页%d条，共%d页 =====\n", page.getTotal(), page.getCurrent(), page.getSize(), page.getPages());
            userList.forEach(System.out::println);
        }
        System.out.println();
    }

    private void update() {
        System.out.println("====== 开始更新 ======");
        UpdateWrapper<User3> wrapper = new UpdateWrapper<>();
        wrapper.eq("company_id", 1);
        User3 user = new User3();
        user.setAge(28);
        user3Service.update(user, wrapper);
        selectPage();
    }
}
