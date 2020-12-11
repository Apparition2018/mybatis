package com.ljh.mp.enums.interface2;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public interface IBaseEnum<T extends Serializable> extends IEnum<T> {

    String getDescription();
}
