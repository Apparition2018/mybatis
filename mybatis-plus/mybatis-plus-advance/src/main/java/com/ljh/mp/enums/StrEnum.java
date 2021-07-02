package com.ljh.mp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StrEnum {
    ONE("one"),
    TWO("two");
    
    private final String value;
}
