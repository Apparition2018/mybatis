package com.imooc.dao;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MessageDao (mybatis)
 * <p>
 * 和 message 表相关的数据库操作
 *
 * @author Arsenal
 * created on 2019/8/5 10:17
 */
public class MessageDao {

    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(Map<String, Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<>();
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            // 通过 sqlSession 执行 SQL 语句
            // messageList = sqlSession.selectList("Message.queryMessageList", parameter);
            // 接口式编程 (mybatis-spring)， 上面一行替换成下面两行代码
            // 接口全名称.方法名 = namespace.id
            IMessage mapper = sqlSession.getMapper(IMessage.class);
            messageList = mapper.queryMessageList(parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(Map<String, Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<>();
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            // 通过sqlSession执行SQL语句
            IMessage imessage = sqlSession.getMapper(IMessage.class);
            messageList = imessage.queryMessageListByPage(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }

    /**
     * 根据查询条件查询消息列表的条数
     */
    public int count(Message message) {
        DBAccess dbAccess = new DBAccess();
        int result = 0;
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            // 通过sqlSession执行SQL语句
            IMessage imessage = sqlSession.getMapper(IMessage.class);
            result = imessage.count(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 单条删除
     */
    public void deleteOne(int id) {
        DBAccess dbAccess = new DBAccess();
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            // 通过 sqlSession 执行 SQL 语句
            sqlSession.delete("Message.deleteOne", id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> id) {
        DBAccess dbAccess = new DBAccess();
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            // 通过 sqlSession 执行 SQL 语句
            sqlSession.delete("Message.deleteBatch", id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
