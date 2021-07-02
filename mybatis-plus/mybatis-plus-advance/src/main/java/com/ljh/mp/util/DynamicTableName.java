package com.ljh.mp.util;

public class DynamicTableName {

    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    public static void set(String name) {
        myTableName.set(name);
    }

    public static String get() {
        return myTableName.get();
    }
}
