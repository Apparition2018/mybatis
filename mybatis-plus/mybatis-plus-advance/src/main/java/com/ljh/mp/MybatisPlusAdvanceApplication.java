package com.ljh.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljh.mp.dao")
public class MybatisPlusAdvanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusAdvanceApplication.class, args);
    }
}
