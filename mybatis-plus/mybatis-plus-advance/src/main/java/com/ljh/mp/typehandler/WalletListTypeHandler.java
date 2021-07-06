package com.ljh.mp.typehandler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ljh.mp.entity.People;

import java.util.List;

/**
 * 自定义类型处理器
 */
public class WalletListTypeHandler extends JacksonTypeHandler {
    public WalletListTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected Object parse(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<List<People.Wallet>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
