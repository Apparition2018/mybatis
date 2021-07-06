package com.ljh.mp.component;

import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实现 CommandLineRunner，项目启动后自动执行
 */
@Component
@Order(1)
public class MpJsonConfig implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        JacksonTypeHandler.setObjectMapper(new ObjectMapper());
        GsonTypeHandler.setGson(new Gson());
    }
}
