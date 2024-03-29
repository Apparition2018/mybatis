DROP TABLE IF EXISTS user3;

CREATE TABLE user3
(
    id 				BIGINT(20) NOT NULL 			COMMENT '主键ID',
    company_id 		BIGINT(20) NOT NULL 			COMMENT '公司ID',
    name 			VARCHAR(30) NULL DEFAULT NULL 	COMMENT '姓名',
    age 			INT(11) NULL DEFAULT NULL 		COMMENT '年龄',
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS company;

CREATE TABLE company
(
    id 				BIGINT(20) NOT NULL 			COMMENT '主键ID',
    name 			VARCHAR(30) NULL DEFAULT NULL 	COMMENT '公司名称',
    PRIMARY KEY (id)
);
