package com.ljh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljh.dao.SysDeptMapper;
import com.ljh.dao.UserMapper;
import com.ljh.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PageHelperTest
 * 如何使用分页插件：https://pagehelper.github.io/docs/howtouse/
 * 重要提示：https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/Important.md
 *
 * @author ljh
 * created on 2021/8/16 17:30
 */
@SpringBootTest
public class PageHelperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {
        /* 返回 Page */
        Page<SysDeptMapper> deptPage = PageHelper.startPage(1, 2).doSelectPage(() -> sysDeptMapper.list(null));
        System.out.println(objectMapper.writeValueAsString(deptPage));
        System.out.println("====================");

        /* 返回 PageInfo */
        PageInfo<SysDeptMapper> deptPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> sysDeptMapper.list(null));
        System.out.println(objectMapper.writeValueAsString(deptPageInfo));
        System.out.println("====================");

        /* 返回 sql 返回数据条数 */
        long count = PageHelper.count(() -> sysDeptMapper.list(null));
        System.out.println(count);
    }

    /**
     * 解决 pageHelper 不支持嵌套结果映射
     */
    @Test
    public void test2() throws JsonProcessingException {
        PageInfo<User> userPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> userMapper.list());
        List<User> userList = userMapper.listByIdIn(userPageInfo.getList().stream().map(User::getId).collect(Collectors.toList()));
        userPageInfo.setList(userList);
        System.err.println(objectMapper.writeValueAsString(userPageInfo));
    }
}
