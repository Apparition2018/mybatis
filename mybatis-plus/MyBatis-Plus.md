# MyBatis-Plus

---
## 参考网站

1. [MyBatis-Plus](https://mp.baomidou.com/guide/#%E7%89%B9%E6%80%A7)
2. [MyBatis-Plus入门教程-慕课网](https://www.imooc.com/learn/1130)
3. [MyBatis-Plus进阶-慕课网](https://www.imooc.com/learn/1171)
4. [MyBatis-Plus + SpringBoot实现简单权限管理-慕课网](https://www.imooc.com/learn/1294)
---
## MyBatis vs JPA
1. Mybatis 优势：
    - SQL 语句可以自由控制，更灵活，性能较高
    - SQL 与代码分离，易于阅读
    - 提供 XML 标签，支持编写动态 SQL 语句
2. JPA 优势
    - JPA 移植性比较好 (JPQL)
    - 提供了很多 CRUD 方法，开发效率高
    - 对象化程度更高
---
## [配置](https://mp.baomidou.com/config/)
```yaml
mybatis-plus:
  # MyBatis Mapper 所对应的 XML 文件位置
  mapper-locations: classpath*:mapper/*.xml
  # 全局策略
  global-config:
    db-config:
      # 主键全局策略
      id-type: assign_uuid
      # 字段验证策略
      select-strategy: default
      insert-strategy: default
      update-strategy: default
      # 表名前缀
      # table-prefix: mp_
  # MyBatis 配置文件位置
  # config-location: classpath:mybatis-config.xml
  # MyBaits 别名包扫描路径
  typeAliasesPackage: com.ljh.mp.entity
  configuration:
    # 是否开启自动驼峰命名规则（camel case）映射，默认为true，不能和 config-location 同时出现
    mapUnderscoreToCamelCase: true
```
---
## 快速使用
1. Mapper
```java
public interface UserMapper extends BaseMapper<User> { }
```
2. Service
```java
public interface UserService extends IService<User> { }
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService { }
```
---
## [常用注解](https://mp.baomidou.com/guide/annotation.html)
1. @TableName
```java
// 使实体 User 对应数据表 mp_user
@TableName(value = "mp_user", schema = "mp")
public class User {
}
```
2. @TableId
```java
public class User {
    // 告诉 mp userId 是主键
    @TableId(type = IdType.ASSIGN_UUID)
    private Long userId;
}
```
3. @TableField
```java
public class User {
    // 使 realName 对应表字段 name
    @TableField("name")
    private String realName;
    // 告诉 mp remark 不是表字段
    @TableField(exist = false)
    private String remark;
    // 使用实体创建的条件构造器时，默认使用 LIKE 而不是 =
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    // 不为 null 才加入 INSERT SQL
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String email;
}
```
4. @OrderBy
```java
public class User {
    // 默认倒序排序
    @OrderBy
    private LocalDateTime createTime;
}
```
---
## 条件构造器
1. [AbstractWrapper](https://mp.baomidou.com/guide/wrapper.html#abstractwrapper)
    - QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类，用于生成 WHERE 条件
```
allEq()                                全部 eq（或个别 isNull）
inSql()                                字段 IN（SQL 语句）
func()                                 主要在 if...else 调用
apply()                                拼接 SQL
last()                                 直接拼接到 SQL 最后
```
2. [QueryWrapper](https://mp.baomidou.com/guide/wrapper.html#querywrapper)
```
QueryWrapper([T entity])               创建 QueryWrapper
Wrappers.query([T entity])             创建 QueryWrapper
LamdaQueryWrapper([T entity])          创建 LamdaQueryWrapper
Wrappers.lambdaQuery([T entity])       创建 LamdaQueryWrapper
lambda()                               创建 LamdaQueryWrapper
select()                               设置查询字段
```
3. [UpdateWrapper](https://mp.baomidou.com/guide/wrapper.html#updatewrapper)
```
UpdateWrapper([[T entity])             创建 QueryWrapper
Wrappers.update([T entity])            创建 QueryWrapper
set()                                  SQL SET 字段
setSql()                               设置 SET 部分 SQL
```
---
## 分页查询
1. MyBatisPlusConfig
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }
}
```
2. 使用
```
Page<User> page = new Page<>(1, 10);
IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
```
---
## 自定义查询
1. UserMapper.java
```
IPage<Map<String, Object>> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
```
2. UserMapper.xml
```xml
<select id="selectUserPage" resultType="java.util.Map">
   SELECT u1.id, u1.name, u2.name managerName
   FROM user u1
   LEFT JOIN user u2 ON u1.manager_id = u2.id
   ${ew.customSqlSegment}
</select>
```
---
## ActiveRecord 模式
1. 实体类继承 Model
```java
public class User extends Model<User> { }
```
2. Mapper 继承 BaseMapper
```java
public interface UserMapper extends BaseMapper<User> { }
```
3. 使用
```
User user = new User().selectById(1);
```
---