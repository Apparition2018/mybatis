package com.imooc.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * DBAccess
 *
 * 访问数据库类
 *
 * @author Arsenal
 * created on 2019/8/5 10:49
 */
public class DBAccess {

    public SqlSession getSqlSession() throws IOException {
        // 通过配置文件获取数据库连接信息
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        // 通过配置信息构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 通过 SqlSessionFactory 打开一个数据库会话
        return sqlSessionFactory.openSession();
    }
}
