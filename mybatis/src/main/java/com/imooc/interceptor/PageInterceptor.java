package com.imooc.interceptor;

import com.imooc.entity.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * PageInterceptor
 * <p>
 * mybatis 分页拦截器
 *
 * @author Arsenal
 * created on 2019/8/7 11:51
 */
// type 表示拦截的类，method 表示拦截的方法，args 表示拦截的参数
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    private String test;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        // 以ByPage结尾的ID
        if (id.matches(".+ByPage$")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            // 原始的SQL语句
            String sql = boundSql.getSql();
            // 查询总条数的SQL语句
            // sqlserver: String countSql = "SELECT COUNT(*) FROM (" + sql.replace("SELECT", "SELECT TOP 100 PERCENT") + ")a";
            String countSql = "SELECT COUNT(*) FROM (" + sql + ")a";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();

            Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
            Page page = (Page) parameter.get("page");
            if (rs.next()) {
                page.setTotalNumber(rs.getInt(1));
            }
            // 改造后带分页查询的SQL语句
            // sqlserver: String pageSql = sql + " offset " + page.getDbIndex() + " rows fetch next " + page.getDbNumber() + " rows only";
            String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println(this.test);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 拿到配置文件设置的属性值
        this.test = properties.getProperty("test");
    }
}