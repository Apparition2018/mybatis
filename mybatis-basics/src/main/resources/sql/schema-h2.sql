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