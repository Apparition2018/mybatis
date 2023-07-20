package com.ljh.entity;

import lombok.Data;

/**
 * Address
 *
 * @author ljh
 * @since 2023/7/20 10:42
 */
@Data
public class Address {
    private Long id;
    private Long userId;
    private String city;
    private String address;
}
