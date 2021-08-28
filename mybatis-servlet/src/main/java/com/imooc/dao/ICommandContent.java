package com.imooc.dao;

import com.imooc.bean.CommandContent;

import java.util.List;

/**
 * ICommandContent
 * <p>
 * 与CommandContent配置文件相对应的接口
 *
 * @author Arsenal
 * created on 2019/8/7 15:45
 */
public interface ICommandContent {

    /**
     * 单条新增
     */
    void insertOne(CommandContent content);

    /**
     * 批量新增
     */
    void insertBatch(List<CommandContent> content);
}
