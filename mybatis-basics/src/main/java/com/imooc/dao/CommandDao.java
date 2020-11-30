package com.imooc.dao;

import com.imooc.bean.Command;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CommandDao
 * <p>
 * 与指令表对应的数据库操作类
 *
 * @author Arsenal
 * created on 2019/8/6 11:06
 */
public class CommandDao {

    /**
     * 根据查询条件查询指令列表
     */
    public List<Command> queryCommandList(String name, String description) {
        DBAccess dbAccess = new DBAccess();
        List<Command> commandList = new ArrayList<>();
        try (SqlSession sqlSession = dbAccess.getSqlSession()) {
            Command command = new Command();
            command.setName(name);
            command.setDescription(description);
            // 通过sqlSession执行SQL语句
            commandList = sqlSession.selectList("Command.queryCommandList", command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandList;
    }
}
