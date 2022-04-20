package com.ljh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.dao.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MybatisTest
 *
 * @author ljh
 * created on 2022/4/20 10:28
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAssociationAndCollection() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(sysUserMapper.list(null)));
    }
}
