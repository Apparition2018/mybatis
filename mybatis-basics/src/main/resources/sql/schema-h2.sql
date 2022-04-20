DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id 				BIGINT(20) NOT NULL 			COMMENT '主键ID',
    name 			VARCHAR(30) NULL DEFAULT NULL 	COMMENT '姓名',
    age 			INT(11) NULL DEFAULT NULL 		COMMENT '年龄',
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders;

CREATE TABLE orders
(
    id 				BIGINT(20) NOT NULL 			COMMENT '主键ID',
    user_id         BIGINT(20) NOT NULL             COMMENT '用户ID',
    no 			    VARCHAR(30) NULL DEFAULT NULL 	COMMENT '订单号',
    PRIMARY KEY (id)
);

---
drop table if exists sys_dept;
create table sys_dept (
                          dept_id           bigint(20)      not null auto_increment    comment '部门id',
                          parent_id         bigint(20)      default 0                  comment '父部门id',
                          ancestors         varchar(50)     default ''                 comment '祖级列表',
                          dept_name         varchar(30)     default ''                 comment '部门名称',
                          order_num         int(4)          default 0                  comment '显示顺序',
                          leader            varchar(20)     default null               comment '负责人',
                          phone             varchar(11)     default null               comment '联系电话',
                          email             varchar(50)     default null               comment '邮箱',
                          status            char(1)         default '0'                comment '部门状态（0正常 1停用）',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time 	    datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          primary key (dept_id)
) engine=innodb auto_increment=200 comment = '部门表';

drop table if exists sys_user;
create table sys_user (
                          user_id           bigint(20)      not null auto_increment    comment '用户ID',
                          dept_id           bigint(20)      default null               comment '部门ID',
                          login_name        varchar(30)     not null                   comment '登录账号',
                          user_name         varchar(30)     default ''                 comment '用户昵称',
                          user_type         varchar(2)      default '00'               comment '用户类型（00系统用户 01注册用户）',
                          email             varchar(50)     default ''                 comment '用户邮箱',
                          phonenumber       varchar(11)     default ''                 comment '手机号码',
                          sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
                          avatar            varchar(100)    default ''                 comment '头像路径',
                          password          varchar(50)     default ''                 comment '密码',
                          salt              varchar(20)     default ''                 comment '盐加密',
                          status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          login_ip          varchar(128)    default ''                 comment '最后登录IP',
                          login_date        datetime                                   comment '最后登录时间',
                          pwd_update_date   datetime                                   comment '密码最后更新时间',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time       datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default null               comment '备注',
                          primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

drop table if exists sys_role;
create table sys_role (
                          role_id           bigint(20)      not null auto_increment    comment '角色ID',
                          role_name         varchar(30)     not null                   comment '角色名称',
                          role_key          varchar(100)    not null                   comment '角色权限字符串',
                          role_sort         int(4)          not null                   comment '显示顺序',
                          data_scope        char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
                          status            char(1)         not null                   comment '角色状态（0正常 1停用）',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time       datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default null               comment '备注',
                          primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

drop table if exists sys_user_role;
create table sys_user_role (
                               user_id   bigint(20) not null comment '用户ID',
                               role_id   bigint(20) not null comment '角色ID',
                               primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';