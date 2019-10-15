package com.imooc.dao;

import com.imooc.bean.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MessageDao (jdbc)
 * <p>
 * 和 message 表相关的数据库操作
 * <p>
 * dao 层应该应该只做如下操作（这里是样例，所以加载驱动和获取连接都写在了这里）
 * 1.执行 SQL 语句
 * 2.获取操作结果封装信息
 * 3.返回操作结果
 *
 * @author Arsenal
 * created on 2019/8/5 10:17
 */
public class MessageDao2 {

    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(String command, String description) {
        List<Message> messageList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.134:1433;DatabaseName=app", "test", "bpmlink");

            StringBuilder sql = new StringBuilder("SELECT ID, COMMAND, DESCRIPTION, CONTENT FROM MESSAGE WHERE 1 = 1");
            List<String> paramList = new ArrayList<>();
            if (command != null && !"".equalsIgnoreCase(command.trim())) {
                sql.append(" and COMMAND = ?");
                paramList.add(command);
            }
            if (description != null && !"".equalsIgnoreCase(description.trim())) {
                sql.append(" and DESCRIPTION like ?");
                paramList.add("%" + description + "%");
            }
            System.out.println(sql.toString());
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i + 1, paramList.get(i));
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                messageList.add(message);
                message.setId(rs.getString("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
