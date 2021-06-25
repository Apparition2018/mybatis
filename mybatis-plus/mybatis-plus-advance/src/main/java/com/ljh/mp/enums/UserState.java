package com.ljh.mp.enums;

import lombok.Getter;

@Getter
public enum UserState implements IBaseEnum<Integer> {

    ACTIVE(1, "A"),
    INACTIVE(2, "I");

    private final int state;
    private final String desc;

    UserState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return state;
    }


    @Override
    public String getDescription() {
        return desc;
    }
}