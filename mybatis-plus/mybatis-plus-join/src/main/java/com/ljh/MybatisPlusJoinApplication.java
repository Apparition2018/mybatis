package com.ljh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MybatisPlusJoinApplication
 * <p><a href="https://mybatisplusjoin.com/pages/quickstart/js.html">Mybatis-Plus-Join</a>
 *
 * @author ljh
 * created on 2022/5/20 17:33
 */
@SpringBootApplication
@MapperScan("com.ljh.mapper")
public class MybatisPlusJoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusJoinApplication.class);
    }
}
