package com.ljh.mp.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.statement.delete.Delete;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MybatisPlusConfiguration {

    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    /**
     * 配置乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        ArrayList<ISqlParser> iSqlParserList = new ArrayList<>();

        /*
           多租户实现方法有三种：
                1)一个租户一个数据库
                2)多个或所有租户共享Database，但是每个租户一个Schema
                3)租户共享同一个Database、同一个Schema，在表中增加TenantID多租户的数据字段 (当前实现的方法)
         */
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId(boolean where) {
//                return new LongValue(1088248166370832385L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "manager_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                return "role".equals(tableName); // 过滤role表，不增加租户实现
//            }
//        });
//        iSqlParserList.add(tenantSqlParser);

        /* 动态表名 */
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        tableNameHandlerMap.put("user", (metaObject, sql, tableName) -> myTableName.get());
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        iSqlParserList.add(dynamicTableNameParser);

        /* 攻击 SQL 阻断解析器 */
        iSqlParserList.add(new BlockAttackSqlParser() {
            // 可以选择是否重写父类方法
            @Override
            public void processDelete(Delete delete) {
                if ("role".equals(delete.getTable().getName())) {
                    return;
                }
                super.processDelete(delete);
            }
        });

        paginationInterceptor.setSqlParserList(iSqlParserList);
        paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
            return "com.ljh.mp.dao.UserMapper.selectById".equals(mappedStatement.getId()); // 过滤selectById()，不增加租户实现和动态表名实现
        });

        return paginationInterceptor;
    }

}