package com.ljh.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;
import com.ljh.mp.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 创建注入器
 * <p>
 * 选其中之一：
 * 1. implements ISqlInjector
 * 2. extends AbstractSqlInjector
 * 3. extends DefaultSqlInjector
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 添加自定义方法的类
        methodList.add(new DeleteAllMethod());
        // mp 自带自定义方法，批量插入时自选字段
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete()));
        // mp 自带自定义方法，逻辑删除时填充某些字段，需要 @TableField(fill = FieldFill.UPDATE) 配合使用
        methodList.add(new LogicDeleteByIdWithFill());
        // mp 自带自定义方法，更新时自选字段
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !"name".equals(t.getColumn())));
        return methodList;
    }
}