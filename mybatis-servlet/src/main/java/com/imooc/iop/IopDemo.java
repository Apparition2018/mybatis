package com.imooc.iop;

import java.util.List;

/**
 * IopDemo
 * <p>
 * 模拟 mybatis 接口式编程
 *
 * @author Arsenal
 * created on 2019/8/6 18:16
 */
public class IopDemo {

    public static void main(String[] args) {
        System.out.println("加载配置信息……");
        System.out.println("通过加载配置信息加载一个代理工厂Map：");
        System.out.println("这个Map存放的是接口Class与对应的代理工厂");
        SqlSession sqlSession = new SqlSession();
        MyMapper mapper = sqlSession.getMapper(MyMapper.class);
        List<Object> list = mapper.query(new Object());
        System.out.println(list);
    }
}
