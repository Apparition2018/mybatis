# [Mybatis](https://mybatis.org/mybatis-3/zh/index.html)

---
## [Configuration](https://mybatis.org/mybatis-3/zh/configuration.html)
- [Configuration.xml](src/main/resources/Configuration.xml)
---
## [SqlSession](https://mybatis.org/mybatis-3/zh/java-api.html#sqlsession)
1. SqlSession 的作用
    1. 向 SQL 语句传入参数
    2. 执行 SQL 语句
    3. 获取执行 SQL 语句的结果
    4. 事务的控制
2. 获取 SqlSession 实例:
```java
public class Demo {
    public SqlSession getSqlSession() throws IOException {     
        // 1. 通过配置文件获取数据库连接信息
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        // 2. 通过配置信息构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 3. 通过 SqlSessionFactory 打开数据库会话
        return sqlSessionFactory.openSession();
    }     
}
```
---
## [OGNL](https://commons.apache.org/proper/commons-ognl/)
- Object-Graph Navigation Language，可以存取对象的任意属性，调用对象的方法，遍历整个对象的结构图，实现字段类型转化等功能。
- [MyBatis 中的 OGNL](https://www.cnblogs.com/lxl57610/p/7436648.html)
<table>
    <tr>
        <th colspan="4">Mybatis 中的 OGNL 表达式</th>
    </tr>
    <tr>
        <td>取值范围</td>
        <td colspan="3">标签的属性中</td>
    </tr>
    <tr>
        <td rowspan="5">取值写法</td>
        <td colspan="2">String 与基本数据类型</td>
        <td>_parameter</td>
    </tr>
    <tr>
        <td colspan="2">自定义类型 (Message)</td>
        <td>属性名 (content)</td>
    </tr>
    <tr>
        <td rowspan="3" colspan="2">集合</td>
        <td>数组: array</td>
    </tr>
    <tr>
        <td>List: list</td>
    </tr>
    <tr>
        <td>Map: _parameter</td>
    </tr>
    <tr>
        <td rowspan="2">操作符</td>
        <td colspan="2">java 常用操作符</td>
        <td>+, -, *, /, ==, !=, ||, &&, ! 等</td>
    </tr>
    <tr>
        <td colspan="2">ognl 特有操作符</td>
        <td>and, or, mod, in, not in, lt, lte, not</td>
    </tr>
    <tr>
        <td rowspan="3">从集合中取出一条数据</td>
        <td colspan="2">数组</td>
        <td>arr[索引] 或 arr[索引].属性名</td>
    </tr>
    <tr>
        <td colspan="2">List</td>
        <td>list.get(索引) 或 list.get(索引).属性名</td>
    </tr>
    <tr>
        <td colspan="2">Map</td>
        <td>[_parameter].key 或 [_parameter].key.属性名</td>
    </tr>
    <tr>
        <td rowspan="4">foreach 标签</td>
        <td colspan="3">&lt;foreach collection="list" item="item" open="(" separator="," close=")"></td>
    </tr>
    <tr>
        <td>数组</td>
        <td rowspan="2">i:索引（下标）</td>
        <td rowspan="3">item 或 item.属性名</td>
    </tr>
    <tr>
        <td>List</td>
    </tr>
    <tr>
        <td>Map</td>
        <td>i:key</td>
    </tr>
</table>

- [xml 转义字符](https://www.cnblogs.com/ypppt/p/12912980.html)
---
## [XML 映射器](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html)
1. [\<resultMap>](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#结果映射): 配置查询结果集中列名和 java 对象属性的对应关系
    1. \<collection>：`<collection property="child" resultMap="Child.Child"/>`
    2. \<association>：`<association property="parent" resultMap="Parent.Parent"/>`
2. [\<sql>](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#sql): 可被其它语句引用的可重用语句块
    - `<sql id="test"/>`
    - `<include refid="test"/>`
3. [\<cache/>](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#cache)：二级缓存
---
## [动态 SQL](https://mybatis.org/mybatis-3/zh/dynamic-sql.html)
1. \<if>：`<if test=""/>`
2. \<choose>
    ```xml
    <choose>
        <when test=""></when>
        <when test=""></when>
        <otherwise></otherwise>
    </choose>
3. \<trim>  
   5.1 在前面添加 prefix 的内容，在后面添加 suffix 的内容  
   5.2 切掉前面 prefixOverrides 的内容，切掉后面 suffixOverrides 的内容
    ```xml
    <demo>
        <!-- 代替 <where> -->
        <trim prefix="where" prefixOverrides="and|or"/>
        <!-- 代替 <set> -->
        <trim prefix="set" suffixOverrides=","/>
    </demo>
    ```
4. \<where>: 代替 where 关键字，会去除第一个 and|or
5. \<set>: 代替 set 关键字，会去掉最后一个 ","
6. \<foreach>  
   `<foreach collection="list" item="item" open="(" separator="," close=")">`
---
## 元素属性
| 属性               | 元素                     | 描述                                                   |
|------------------|------------------------|------------------------------------------------------|
| timeout          | all                    | 超时秒数                                                 |
| useGeneratedKeys | insert, update         | 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键，默认 false |
| keyProperty      | insert, update         | 唯一识别对象的属性                                            |
| fetchSize        | select                 | 结果行数                                                 |
| useCache         | select                 | 启用二级缓存，默认 true                                       |
| flushCache       | select                 | 清空本地缓存和二级缓存，默认 false                                 |
| flushCache       | insert, update, delete | 清空本地缓存和二级缓存，默认 true                                  |
---
## [缓存](https://tech.meituan.com/2018/01/19/mybatis-cache.html)
1. 一级缓存：本地缓存
    - `<setting name="localCacheScope" value="SESSION(default)|STATEMENT"/>`
    - 最多缓存 1024 条 SQL
    - 存储结构是一个 HashMap，key 为 CacheKey，value 为 查询结果，没有容量限制
    - 生命周期和 SqlSession 一致
    - 有多个 SqlSession 或分布式环境下可能出现脏数据，建议设置缓存级别为 STATEMENT，相当于关闭一级缓存
2. 二级缓存
    - `<setting name="cacheEnabled" value="true"/>`
    - 映射 XML 中配置 \<cache/> 或 \<cache-ref/>
    - 存储结构也是一个 HashMap
    - 相对于一级缓存来说，实现了 SqlSession 之间共享缓存，粒度可到 namespace 级别
    - 多表查询时可能出现脏数据，按情况可使用 \<cache-ref/> 解决
    - 分布式环境下需要整合其它分布式缓存，还不如直接使用其它分布式缓存
---
## 多表关联
1. [一对多](src/main/resources/mapper/Command.xml)
2. [一对一](src/main/resources/mapper/CommandContent.xml)
---
## #{} 与 ${}
1. \#{}: 参数占位符 ?，防止 SQL 注入
2. ${}: 不转移的字符串，一般用于传入数据库对象
---
## 面向接口编程
- [模拟 mybatis 接口式编程](src/main/java/com/imooc/iop/IopDemo.java)
---
## 拦截器
- [拦截器实现分页](src/main/java/com/imooc/interceptor/PageInterceptor.java)
---
