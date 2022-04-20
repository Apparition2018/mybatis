package com.ljh.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * SysUser
 *
 * @author ljh
 * created on 2022/4/20 10:07
 */
@Data
public class SysUser implements Serializable {
    private Long userId;

    private Long deptId;

    private String loginName;

    private String userName;

    private String userType;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String password;

    private String salt;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private Date pwdUpdateDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    private SysDept dept;

    private List<SysRole> roles;
}