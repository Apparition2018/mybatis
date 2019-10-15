package com.ljh.mp.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

@Getter
public enum GenderEnum implements IEnum<String> {

    MALE("男"),
    FEMALE("女");

    private final String gender;

    GenderEnum(final String gender) {
        this.gender = gender;
    }


    @Override
    public String getValue() {
        return gender;
    }
}