package com.imooc.dao;

import com.imooc.bean.CommandContent;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * CommandContentDao
 * <p>
 * 和 command_content 表相关的数据库操作
 *
 * @author Arsenal
 * created on 2019/8/7 15:38
 */
public class CommandContentDao {

    /**
     * 通过JDBC方式批量新增
     */
    public void insertBatchByJdbc(List<CommandContent> contentList) {
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.134:1433;DatabaseName=app", "test", "bpmlink");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=false", "ljh", "123456");
            String insertSql = "INSERT INTO COMMAND_CONTENT(CONTENT,COMMAND_ID) VALUES(?, ?)";
            PreparedStatement statement = conn.prepareStatement(insertSql);
            for (CommandContent content : contentList) {
                statement.setString(1, content.getContent());
                statement.setString(2, content.getCommandId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量新增
     */
    public void insertBatch(List<CommandContent> contentList) {
        DBAccess dbAccess = new DBAccess();
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            // 通过sqlSession执行SQL语句
            ICommandContent commandContent = sqlSession.getMapper(ICommandContent.class);
            commandContent.insertBatch(contentList);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
