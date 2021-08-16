package com.ljh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * @author ljh
 * created on 2021/8/16 2:35
 */
@SpringBootApplication
@MapperScan("com.ljh.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
