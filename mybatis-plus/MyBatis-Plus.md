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
      # 逻辑未删除值
      # logic-not-delete-value: 0
      # 逻辑已删除值
      # logic-delete-value: 1
  # MyBatis 配置文件位置
  # config-location: classpath:mybatis-config.xml
  # MyBaits 别名包扫描路径
  type-aliases-package: com.ljh.mp.entity
  configuration:
    # 是否开启自动驼峰命名规则（camel case）映射，默认为true，不能和 config-location 同时出现
    map-underscore-to-camel-case: true
  # 枚举类扫描路径；支持统配符 * 或者 ; 分割
  # type-enums-package: com.ljh.mp.enums
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
3. [@TableField](https://mp.baomidou.com/guide/annotation.html#tablefield)
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
    // 查询的时候不显示该字段（自定义语句无效）
    @TableField(select = false)
    private Integer deleted;
    // 插入时填充字段
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 插入和更新时填充字段
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
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
5. @TableLogic
```java
public class User {
    // 逻辑删除
    @TableLogic
    private Integer deleted;
}
```
6. @Version
```java
public class User {
    // 乐观锁注解
    @Version
    private Integer version;
}
```
7. @InterceptorIgnore
```java
public interface UserMapper extends BaseMapper<User> {
    // 拦截忽略：
    // tenantLine       行级租户
    // dynamicTableName 动态表名
    // blockAttack      攻击 SQL 阻断解析器,防止全表更新与删除
    // illegalSql       垃圾SQL拦截
    // sharding         分表
    // ......
    @InterceptorIgnore(tenantLine = "true")
    List<User> mySelectList(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
```
---
## [条件构造器](https://mp.baomidou.com/guide/wrapper.html)
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
## [分页查询](https://mp.baomidou.com/guide/page.html)
1. MybatisPlusInterceptor
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
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
## [逻辑删除](https://mp.baomidou.com/guide/logic-delete.html)
1. application.yml 配置
```yaml
mybatis-plus:
  global-config:
    db-config:
      # 全局逻辑删除字段
      # logic-delete-field: deleted
      # 逻辑未删除值
      logic-not-delete-value: 0
      # 逻辑已删除值
      logic-delete-value: 1
```
2. @TableLogic
```
@TableLogic
private Integer deleted;
```
---
## [自动填充](https://mp.baomidou.com/guide/auto-fill-metainfo.html)
1. 自定义实现 MetaObjectHandler
```java
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        // 有 createTime 字段才填充
        if (hasSetter) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        // updateTime 没有设置值才填充
        if (val == null) {
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        }
    }
}
```
2. 注解填充字段
```java
abstract class BaseEntity {
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
```
---
## [乐观锁](https://mp.baomidou.com/guide/interceptor-optimistic-locker.html)
- 当要更新一条记录的时候，希望这条记录没有被别人更新
- 乐观锁实现方式：
   1. 取出记录时，获取当前 version
   2. 更新时，带上这个 version
   3. 执行更新时，set version = newVersion where version = oldVersion
   4. 如果 version 不对，就更新失败
1. MybatisPlusInterceptor
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
```
2. @Version
```java
public class User {
    @Version
    private Integer version;
}
```
3. 使用
```
User user = new User();
user.setId(1183568091535720449L);
user.setEmail("ly2@baomidou.com");
user.setVersion(1);
// UPDATE user SET email='ly2@baomidou.com', version=2, WHERE id=1183568091535720449 AND version=1 AND deleted=0
int rows = userMapper.updateById(user);
```
---
## [多租户](https://mp.baomidou.com/guide/interceptor-tenant-line.html)
1. MybatisPlusInterceptor
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户实现方式：共享数据库，共享 schema，共享数据表，一个租户一个租户 ID
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public String getTenantIdColumn() {
                // 本例使用 manager_id 当作租户 ID
                return "manager_id";
            }
            @Override
            public Expression getTenantId() {
                // 这里写死了租户 ID，实际需要自定义获取当前租户的租户 ID
                return new LongValue(1088248166370832385L);
            }
            @Override
            public boolean ignoreTable(String tableName) {
                // 忽略拼接多租户条件
                return "role".equals(tableName);
            }
        }));
        return interceptor;
    }
}
```
---
## [动态表名](https://mp.baomidou.com/guide/interceptor-dynamic-table-name.html)
- 应用场景：数据库有一类表名相类似，比如：sys_log_20210701，sys_log_20210702，sys_log_20210703 等
1. MybatisPlusInterceptor
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        Map<String, TableNameHandler> tableNameHandlerMap = new HashMap<String, TableNameHandler>(2) {
            private static final long serialVersionUID = 7009035918628703069L;
            {
                // 自定义替换逻辑
                put("user", (sql, tableName) -> DynamicTableName.get() != null ? DynamicTableName.get() : tableName);
            }
        };
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(tableNameHandlerMap);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }
}
```
2. DynamicTableName
```java
public class DynamicTableName {
    public static ThreadLocal<String> myTableName = new ThreadLocal<>();
    public static void set(String name) {
        myTableName.set(name);
    }
    public static String get() {
        return myTableName.get();
    }
}

```
3. 使用
```        
DynamicTableName.set("user_2019");
// SELECT id,name,age,email,manager_id,version,create_time,update_time FROM user_2019 WHERE deleted=0
List<User> list = userMapper.selectList(null);
```
---
## [SQL 注入器](https://mp.baomidou.com/guide/sql-injector.html)
1. 创建定义方法的类
```java
public class DeleteAllMethod extends AbstractMethod {
    private static final long serialVersionUID = 6166068393991215997L;
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 执行的 SQL
        String sql = "DELETE FROM " + tableInfo.getTableName();
        // 方法名
        String method = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
```
2. 创建注入器
```java
@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 添加自定义方法的类
        methodList.add(new DeleteAllMethod());
        // mp 自带自定义方法，批量插入时自选字段
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete()));
        // mp 自带自定义方法，逻辑删除时填充某些字段，需要 @TableField(fill = FieldFill.UPDATE) 配合使用
        methodList.add(new LogicDeleteByIdWithFill());
        // mp 自带自定义方法，更新时自选字段
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !"name".equals(t.getColumn())));
        return methodList;
    }
}

```
3. 在 Mapper 中加入自定义方法
```java
public interface MyMapper<T> extends BaseMapper<T> {
    int deleteAll();
    int insertBatchSomeColumn(List<T> list);
    int deleteByIdWithFill(T entity);
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
}
```
---
## [通用枚举](https://mp.baomidou.com/guide/enum.html)
未完待续

---
## SQL 性能规范
1. MybatisPlusInterceptor
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        return interceptor;
    }
}
```
2. 使用
```
// MybatisPlusException: 非法SQL，SQL未使用到索引, table:user, columnName:deleted
List<User> list = userMapper.selectList(null);
```
---
## 防止全表更新与删除
1. MybatisPlusInterceptor
```java
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}
```
2. 使用
```
// MybatisPlusException: Prohibition of full table deletion
int rows = userMapper.deleteAll();
```
---
## [执行 SQL 分析打印](https://mp.baomidou.com/guide/p6spy.html)
1. p6spy 依赖引入
```
<dependency>
    <groupId>p6spy</groupId>
    <artifactId>p6spy</artifactId>
    <version>最新版本</version>
</dependency>
```
2. application.yml 配置
```yaml
spring:
  datasource:
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:mysql://localhost:3306/mp_advance?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=utf-8
```
3. spy.properties 配置
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