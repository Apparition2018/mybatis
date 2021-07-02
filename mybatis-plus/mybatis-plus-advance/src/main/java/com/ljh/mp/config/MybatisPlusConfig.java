package com.ljh.mp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.ljh.mp.util.DynamicTableName;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MybatisPlusConfig {

    /**
     * https://mp.baomidou.com/guide/interceptor.html
     * 建议使用如下顺序：
     * 1）多租户,动态表名
     * 2）分页,乐观锁
     * 3）sql性能规范,防止全表更新与删除
     * 注：对sql进行单次改造的优先放入,不对sql进行改造的最后放入
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户
        // 1. 一个租户一个数据库
        // 2. 共享数据库，一个租户一个 schema
        // 3. 共享数据库，共享 schema，共享数据表，一个租户一个租户 ID（当前实现的方法）
//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
//            /**
//             * 获取租户字段名
//             */
//            @Override
//            public String getTenantIdColumn() {
//                // 本例使用 manager_id 当作租户 ID
//                return "manager_id";
//            }
//
//            /**
//             * 获取租户 ID
//             */
//            @Override
//            public Expression getTenantId() {
//                // 这里写死了租户 ID，实际需要自定义获取当前租户的租户 ID
//                return new LongValue(1088248166370832385L);
//            }
//
//            /**
//             * 忽略拼接多租户条件
//             */
//            @Override
//            public boolean ignoreTable(String tableName) {
//                return "role".equals(tableName);
//            }
//        }));
        // 动态表名
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        Map<String, TableNameHandler> tableNameHandlerMap = new HashMap<String, TableNameHandler>() {
            private static final long serialVersionUID = 7009035918628703069L;
            {
                put("user", (sql, tableName) -> DynamicTableName.get() != null ? DynamicTableName.get() : tableName);
            }
        };
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(tableNameHandlerMap);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        // 分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // SQL 性能规范
        // interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        // 防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}