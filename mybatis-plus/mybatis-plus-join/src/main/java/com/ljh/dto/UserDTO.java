package com.ljh.dto;

import lombok.Data;

/**
 * UserDTO
 *
 * @author ljh
 * @since 2023/7/20 10:42
 */
@Data
public class UserDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    private String city;
    private String address;
}
