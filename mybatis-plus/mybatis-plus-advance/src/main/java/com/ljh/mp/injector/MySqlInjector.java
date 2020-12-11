package com.ljh.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.ljh.mp.method.MyDeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new MyDeleteAllMethod()); // 添加自定义方法
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !"age".equals(t.getColumn()))); // mp 自带方法
        methodList.add(new LogicDeleteByIdWithFill()); // mp 自带方法
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !"name".equals(t.getColumn()))); // mp 自带方法
        return methodList;
    }
}