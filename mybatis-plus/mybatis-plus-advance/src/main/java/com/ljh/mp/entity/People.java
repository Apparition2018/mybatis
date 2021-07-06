package com.ljh.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.ljh.mp.typehandler.WalletListTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 字段类型处理器
 */
@Data
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class People {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    // 必须开启映射注解 @TableName(autoResultMap = true)
    @TableField(typeHandler = WalletListTypeHandler.class)
    private List<Wallet> wallets;
    @TableField(typeHandler = GsonTypeHandler.class)
    private OtherInfo otherInfo;

    @Data
    public static class Wallet {
        private String name;
        private List<Currency> currencyList;
    }

    @Data
    public static class Currency {
        /**
         * 人民币 RMB，美金 USD
         */
        private String type;
        private Double amount;
    }

    @Data
    public static class OtherInfo {
        private String sex;
        private String city;
    }
}
