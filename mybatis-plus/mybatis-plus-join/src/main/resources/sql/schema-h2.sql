CREATE TABLE user
(
    id    BIGINT(20) NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS address;

CREATE TABLE address
(
    id      BIGINT(20) NOT NULL COMMENT '主键ID',
    user_id BIGINT(20) NULL DEFAULT NULL COMMENT '用户id',
    city    VARCHAR(50) NULL DEFAULT NULL COMMENT '城市',
    address VARCHAR(50) NULL DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (id)
);