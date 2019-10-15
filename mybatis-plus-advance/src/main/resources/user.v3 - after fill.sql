/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : mp_advance

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/10/2019 10:59:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 1 COMMENT '版本',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `manager_fk`(`manager_id`) USING BTREE,
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20', NULL, 1, 0);
INSERT INTO `user` VALUES (1088248166370832385, '王天风', 29, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22', '2019-10-14 10:35:33', 1, 0);
INSERT INTO `user` VALUES (1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16', NULL, 1, 0);
INSERT INTO `user` VALUES (1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15', NULL, 1, 0);
INSERT INTO `user` VALUES (1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16', NULL, 1, 1);
INSERT INTO `user` VALUES (1183566309287809026, '刘明超', 31, 'lmc@baomidou.com', 1088248166370832385, '2019-10-14 10:12:52', NULL, 1, 0);
INSERT INTO `user` VALUES (1183567862212108289, '刘西', 30, 'lx@baomidou.com', 1088248166370832385, '2019-10-14 10:19:03', NULL, 1, 0);
INSERT INTO `user` VALUES (1183568091535720449, '李玉', 25, 'ly@baomidou.com', 1088248166370832385, NULL, NULL, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
