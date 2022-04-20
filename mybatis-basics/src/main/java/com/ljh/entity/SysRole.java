package com.ljh.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * SysRole
 *
 * @author ljh
 * created on 2022/4/20 10:09
 */
@Data
public class SysRole implements Serializable {
    private Long roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String dataScope;

    private String status;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}
