package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljh.mp.dao.UserMapper;
import com.ljh.mp.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RetrieveTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIds() {
        List<Long> idList = Arrays.asList(1094592041087729666L, 1088248166370832385L, 1181808237263532034L);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("manager_id", 1088248166370832385L);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    /**
     * 1.名字包含雨，且年龄小于40
     * name LIKE %雨% AND age < 40
     */
    @Test
    public void selectListByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        QueryWrapper<Object> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 2.名字包含雨，且年龄大于等于20，且小于等于40且email不为空
     * name LIKE %雨% AND age BETWEEN 20 AND 40 AND email IS NOT NULL
     */
    @Test
    public void selectListByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 3.名字为王姓，或者年龄大于等于25，按年龄降序排序，年龄相同按id升序排序
     * name LIKE '王%' OR age >= 25 ORDER BY age DESC, id
     */
    @Test
    public void selectListByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 4.创建日期为2019年2月14日，且直属上级名字为王姓
     * date_format(create_time, '%Y-%m-%d') = "2019-02-14" AND manager_id IN (SELECT id FROM user WHERE name LIKE '王%')
     */
    @Test
    public void selectListByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // {0} 防止 SQL 注入
        queryWrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", "2019-02-14").inSql("manager_id", "SELECT id FROM user WHERE name LIKE '王%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 5.名字为王姓，且（年龄小于40或邮箱不为空）
     * name LIKE '王%' AND (age < 40 OR email IS NOT NULL)
     */
    @Test
    public void selectListByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // and(Consumer<Param> consumer)
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 6.名字为王姓，或者（年龄小于40且年龄大于20且邮箱不为空）
     * name LIKE '王%' OR (age < 40 AND age > 20 AND email IS NOT NULL)
     */
    @Test
    public void selectListByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 7.（年龄小于40或邮箱不为空），且名字为王姓
     * (age < 40 OR email IS NOT NULL) AND name LIKE '王%'
     * or 优先级小于 and，所以要括号
     */
    @Test
    public void selectListByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 8.年龄为30、31、34、35
     * age IN (30, 31, 34, 35)
     */
    @Test
    public void selectListByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 9.只返回满足条件的其中一条语句即可
     * LIMIT 1
     */
    @Test
    public void selectListByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // last：只能使用一次，多次调用以最后一次为准，有 sql 注入的风险，请谨慎使用
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("LIMIT 1");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 10.名字包含雨，且年龄小于40
     * 1）select id, name
     * 2）select id, name, age, email
     */
    @Test
    public void selectListByWrapperSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

        queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 40)
                // select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)
                .select(User.class, user -> !user.getColumn().equals("create_time") && !user.getColumn().equals("manager_id"));
        userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 11.根据传进来的值是否不为为""，来添加where条件
     */
    @Test
    public void selectListByWrapperCondition() {
        String name = "";
        String email = "x";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // like(boolean condition, R column, Object val)
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 12.根据实体查询
     * name = '刘红雨' AND age = 33 变成了
     * name LIKE CONCAT('%','刘红雨','%') AND age < 33
     */
    @Test
    public void selectListByWrapperEntity() {
        User user = new User();
        user.setName("刘红雨");
        user.setAge(33);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 13.根据map或BiPredicate查询
     * allEq(Map<R, V> params, boolean null2IsNull)
     * allEq(BiPredicate<R, V> filter, Map<R, V> params)
     */
    @Test
    public void selectListByWrapperAllEq() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);
        // false 表示当值为 null 时，忽略不查询
        // name = "王天风"
        queryWrapper.allEq(params, false);
        // name = ? AND age IS NULL
        queryWrapper.allEq((k, v) -> !k.equals("name"), params);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 14.根据lambda构造器查询
     * 优点：防止字段名写错
     */
    @Test
    public void selectListByLambdaQueryWrapper() {
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.like(User::getName, "雨").lt(User::getAge, 40);
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * ChainQuery.list()
     * ChainQuery.one()
     * ChainQuery.count()
     */
    @Test
    public void chainQuery() {
        List<User> userList = new LambdaQueryChainWrapper<>(userMapper).like(User::getName, "雨").lt(User::getAge, 40).list();
        userList.forEach(System.out::println);

        User user = new LambdaQueryChainWrapper<>(userMapper).like(User::getName, "刘红雨").lt(User::getAge, 40).one();
        System.out.println("user = " + user);
    }

    @Test
    public void selectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);
        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 按直属上级分组，查询每组的平均年龄、最大年龄、最小年龄，并且只取年龄总和小于500的组
     * SELECT avg(age) avg_age, min(age) min_age, max(age) max_age FROM user GROUP BY manager_id HAVING sum(age) < 500
     */
    @Test
    public void selectMaps2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age, min(age) min_age, max(age) max_age").groupBy("manager_id").having("sum(age) < {0}", 500);
        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);
        // 即使查询了两列，也只返回查询的第一列数据
        List<Object> userList = userMapper.selectObjs(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 40);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count = " + count);
    }

    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "刘红雨").lt("age", 40);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("user = " + user);
    }

    @Test
    public void selectCustom() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectAll(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 26);

        // 第三个参数表示是否查询总记录数
        Page<User> page = new Page<>(1, 2, true);

        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数" + iPage.getPages());
        System.out.println("总记录数" + iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectMapsPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 26);

        Page<Map<String, Object>> page = new Page<>(1, 2, false);

        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page, queryWrapper);
        System.out.println("总页数" + iPage.getPages());
        System.out.println("总记录数" + iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectCustomPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("u1.age", 26);

        Page<User> page = new Page<>(1, 3);

        IPage<Map<String, Object>> iPage = userMapper.selectUserPage(page, queryWrapper);
        System.out.println("总页数" + iPage.getPages());
        System.out.println("总记录数" + iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }
}