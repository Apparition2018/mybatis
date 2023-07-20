package com.ljh.entity;

import lombok.Data;

/**
 * User
 *
 * @author ljh
 * @since 2023/7/20 10:41
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
