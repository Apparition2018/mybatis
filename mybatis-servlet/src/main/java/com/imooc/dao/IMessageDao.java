package com.imooc.dao;

import com.imooc.bean.Message;

import java.util.List;
import java.util.Map;

/**
 * IMessageDao
 * <p>
 * 与 Message.xml 配置文件相对应的接口
 *
 * @author Arsenal
 * created on 2019/8/6 17:08
 */
public interface IMessageDao {

    /**
     * 根据查询条件查询消息列表
     */
    List<Message> queryMessageList(Map<String, Object> parameter);

    /**
     * 根据查询条件查询消息列表的条数
     */
    int count(Message message);

    /**
     * 根据查询条件分页查询消息列表
     */
    List<Message> queryMessageListByPage(Map<String, Object> parameter);
}
