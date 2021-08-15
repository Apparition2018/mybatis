package com.ljh.entity;

import lombok.Data;

import java.util.List;

/**
 * User
 *
 * @author ljh
 * created on 2021/8/16 2:11
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private List<Orders> ordersList;
}
