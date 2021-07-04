package com.ljh.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StrEnum {
    ONE("one"),
    TWO("two");
    
    @EnumValue
    private final String value;
}
