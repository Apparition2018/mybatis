/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : mp_advance

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 04/07/2021 15:22:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `gender` int(0) NULL DEFAULT NULL COMMENT '性别,0:MALE, 1:FEMALE',
  `grade` int(0) NULL DEFAULT NULL COMMENT '年级',
  `user_state` int(0) NULL DEFAULT NULL COMMENT '用户状态',
  `str_enum` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, 'Billie', 'test5@baomidou.com', 2, NULL, 3, 1, 'two');
INSERT INTO `person` VALUES (2, 'Jack', 'test2@baomidou.com', 3, 0, 1, 1, 'one');
INSERT INTO `person` VALUES (3, 'Tom', 'test3@baomidou.com', 1, 1, 2, 1, 'one');

SET FOREIGN_KEY_CHECKS = 1;
