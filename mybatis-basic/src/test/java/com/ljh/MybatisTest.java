package com.ljh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljh.dao.SysDeptMapper;
import com.ljh.dao.SysUserMapper;
import com.ljh.dao.UserMapper;
import com.ljh.entity.SysUser;
import com.ljh.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MybatisTest
 *
 * @author ljh
 * created on 2021/8/16 17:30
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * <a href="https://pagehelper.github.io/docs/howtouse/">PageHelper</a>
     *
     * @see <a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#分页实现">RuoYi 分页实现</a>
     */
    @Test
    public void testPageHelper() throws JsonProcessingException {
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
     * 解决 pageHelper 不支持嵌套结果映射<br/>
     * <a href="https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/Important.md">重要提示</a>
     * <pre>
     *  1 只有紧跟在 PageHelper.startPage 方法后的第一个 Mybatis 的查询 (Select) 方法会被分页
     *  2 不要在系统中配置多个分页插件
     *  3 不支持带有 for update 语句的分页
     *  4 不支持嵌套结果映射
     * </pre>
     */
    @Test
    public void testPageHelperNestedResultMappings() throws JsonProcessingException {
        PageInfo<User> userPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> userMapper.list());
        List<User> userList = userMapper.listByIdIn(userPageInfo.getList().stream().map(User::getId).collect(Collectors.toList()));
        userPageInfo.setList(userList);
        System.out.println(objectMapper.writeValueAsString(userPageInfo));
    }

    /** &#064;MapKey */
    @Test
    public void testMapKey() {
        sysUserMapper.map(null).entrySet().forEach(user -> {
            try {
                System.out.println(objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    /** &lt;association/> 和 &lt;collection/> */
    @Test
    public void testAssociationAndCollection() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(sysUserMapper.list(null)));
    }

    @Test
    public void testAssociation() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(sysUserMapper.list2(new SysUser())));
    }
}
