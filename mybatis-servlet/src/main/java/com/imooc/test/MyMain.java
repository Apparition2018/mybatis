package com.imooc.test;

import java.util.List;

/**
 * MyMain
 * <p>
 * 作者模拟 mybatis 接口式编程
 *
 * @author Arsenal
 * created on 2019/8/6 18:16
 */
public class MyMain {

    public static void main(String[] args) {
        System.out.println("加载配置信息……");
        System.out.println("通过加载配置信息加载一个代理工厂Map：");
        System.out.println("这个Map存放的是接口Class与对应的代理工厂");
        SqlSession sqlSession = new SqlSession();
        MyInterface myInterface = sqlSession.getMapper(MyInterface.class);
        List<Object> list = myInterface.query(new Object());
        System.out.println(list.size());
    }
}
