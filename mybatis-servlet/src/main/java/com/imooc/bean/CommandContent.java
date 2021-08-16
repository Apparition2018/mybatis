package com.imooc.bean;

/**
 * CommandContent
 * <p>
 * 与指令内容表对应的实体类
 *
 * @author Arsenal
 * created on 2019/8/6 10:35
 */
public class CommandContent {
    /**
     * 主键
     */
    private String id;

    /**
     * 自动回复的内容
     */
    private String content;

    /**
     * 关联的指令表主键
     */
    private String commandId;

    private Command command;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }
}
