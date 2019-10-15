/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : mp

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 09/10/2019 16:32:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `manager_fk2`(`manager_id`) USING BTREE,
  CONSTRAINT `manager_fk2` FOREIGN KEY (`manager_id`) REFERENCES `mp_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mp_user
-- ----------------------------
INSERT INTO `mp_user` VALUES (1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20');
INSERT INTO `mp_user` VALUES (1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22');
INSERT INTO `mp_user` VALUES (1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16');
INSERT INTO `mp_user` VALUES (1094590409767661570, '张雨琪', 31, 'zyq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15');
INSERT INTO `mp_user` VALUES (1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');
INSERT INTO `mp_user` VALUES (1181808237263532034, '刘明强', 31, NULL, 1088248166370832385, '2019-10-09 13:46:55');
INSERT INTO `mp_user` VALUES (1181808551433715713, '向北', 26, NULL, 1088248166370832385, '2019-10-09 13:48:10');
INSERT INTO `mp_user` VALUES (1181809371382341634, '向南', 27, 'xn@baomidou.com', 1088248166370832385, '2019-10-09 13:51:26');
INSERT INTO `mp_user` VALUES (1181811679172300801, '向东', 27, 'xd@baomidou.com', 1088248166370832385, '2019-10-09 14:00:36');
INSERT INTO `mp_user` VALUES (1181812129686745089, '向西', 27, 'xs@baomidou.com', 1088248166370832385, '2019-10-09 14:02:23');
INSERT INTO `mp_user` VALUES (1181813257467969538, '向前', 25, 'xq@baomidou.com', 1088248166370832385, '2019-10-09 14:06:52');
INSERT INTO `mp_user` VALUES (1181813309871587329, '向后', 25, 'xh@baomidou.com', 1088248166370832385, '2019-10-09 14:07:05');
INSERT INTO `mp_user` VALUES (1181813706942091266, '向中', 25, 'xz@baomidou.com', 1088248166370832385, '2019-10-09 14:08:39');

SET FOREIGN_KEY_CHECKS = 1;
