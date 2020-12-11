package com.ljh.mp.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * SQL注入器：
 *      1)创建定义方法的类
 *      2)创建注入器
 *      3)在 Mapper 中加入自定义方法
 */
public class MyDeleteAllMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 执行的 sql
        String sql = "delete from " + tableInfo.getTableName();
        // mapper 接口方法名
        String method = "myDeleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}