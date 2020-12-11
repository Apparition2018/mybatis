package com.ljh.mp.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) { // 存在该字段才执行
            System.out.println("insertFill~~");
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) { // 没有设置值才自动填充
            System.out.println("updateFill~~");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}