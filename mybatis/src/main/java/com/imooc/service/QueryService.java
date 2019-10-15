package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.bean.Message;
import com.imooc.dao.CommandDao;
import com.imooc.dao.MessageDao;
import com.imooc.entity.Page;
import com.imooc.util.Iconst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ListService
 * <p>
 * 查询相关的业务功能
 *
 * @author Arsenal
 * created on 2019/8/5 10:19
 */
public class QueryService {

    public List<Message> queryMessageList(String command, String description, Page page) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        MessageDao messageDao = new MessageDao();
        // 根据条件查询条数
        int totalNumber = messageDao.count(message);
        // 组织分页查询参数
        page.setTotalNumber(totalNumber);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("message", message);
        parameter.put("page", page);
        // 分页查询并返回结果
        return messageDao.queryMessageList(parameter);
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(String command, String description, Page page) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("message", message);
        parameter.put("page", page);
        MessageDao messageDao = new MessageDao();
        // 分页查询并返回结果
        return messageDao.queryMessageListByPage(parameter);
    }

    /**
     * 通过指令查询自动回复的内容
     *
     * @param command 指令
     * @return 自动回复的内容
     */
    public String queryByCommand(String command) {
        CommandDao commandDao = new CommandDao();
        List<Command> commandList;
        // 如果指令为[帮组]，查出所有指令返回
        if (Iconst.HELP_COMMAND.equals(command)) {
            commandList = commandDao.queryCommandList(null, null);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < commandList.size(); i++) {
                if (i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[").append(commandList.get(i).getName()).append("]可以查看").append(commandList.get(i).getDescription());
            }
            return result.toString();
        }
        commandList = commandDao.queryCommandList(command, null);
        if (commandList.size() > 0) {
            List<CommandContent> contentList = commandList.get(0).getContentList();
            // 随机返回指令对应内容
            return contentList.get(new Random().nextInt(contentList.size())).getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }

//    public String queryByCommand(String command) {
//        MessageDao messageDao = new MessageDao();
//        List<Message> messageList;
//        // 如果指令为[帮组]，查出所有指令返回
//        if (Iconst.HELP_COMMAND.equals(command)) {
//            messageList = messageDao.queryMessageList(null, null);
//            StringBuilder result = new StringBuilder();
//            for (int i = 0; i < messageList.size(); i++) {
//                if (i != 0) {
//                    result.append("<br/>");
//                }
//                result.append("回复[").append(messageList.get(i).getCommand()).append("]可以查看").append(messageList.get(i).getDescription());
//            }
//            return result.toString();
//        }
//        messageList = messageDao.queryMessageList(command, null);
//        if (messageList.size() > 0) {
//            return messageList.get(0).getContent();
//        }
//        return Iconst.NO_MATCHING_CONTENT;
//    }
}
