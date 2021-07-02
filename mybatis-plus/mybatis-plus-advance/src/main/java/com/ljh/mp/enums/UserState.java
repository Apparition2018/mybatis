package com.ljh.mp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserState implements IBaseEnum<Integer> {
    ACTIVE(1, "A"),
    INACTIVE(2, "I");

    private final int state;
    private final String desc;

    @Override
    public Integer getValue() {
        return state;
    }

    @Override
    public String getDescription() {
        return desc;
    }
}