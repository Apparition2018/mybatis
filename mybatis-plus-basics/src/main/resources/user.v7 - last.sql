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

 Date: 11/10/2019 11:26:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `manager_fk`(`manager_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1087982257332887553', '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20');
INSERT INTO `user` VALUES ('1088248166370832385', '王天风', 26, 'wtf2@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22');
INSERT INTO `user` VALUES ('1088250446457389058', '李艺伟', 32, 'lyw2019@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16');
INSERT INTO `user` VALUES ('1094590409767661570', '张雨琪', 31, 'zyq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15');
INSERT INTO `user` VALUES ('1094592041087729666', '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');
INSERT INTO `user` VALUES ('112233xxx', '钱大忘3', 26, 'qdw3@baomidou.com', 1088248166370832385, '2019-10-11 10:01:02');
INSERT INTO `user` VALUES ('1181808237263532034', '刘明强', 31, NULL, 1088248166370832385, '2019-10-09 13:46:55');
INSERT INTO `user` VALUES ('1181808551433715713', '向北', 26, NULL, 1088248166370832385, '2019-10-09 13:48:10');
INSERT INTO `user` VALUES ('1182465946912563201', '刘花', 29, 'lh@baomidou.com', 1088248166370832385, '2019-10-11 09:20:25');
INSERT INTO `user` VALUES ('1182468429223342082', '张强', 23, 'zq@baomidou.com', 1088248166370832385, '2019-10-11 09:31:43');
INSERT INTO `user` VALUES ('1182468429223342083', '钱明', 26, 'qm@baomidou.com', 1088248166370832385, '2019-10-11 09:48:18');
INSERT INTO `user` VALUES ('1182468429223342084', '钱福', 26, 'qf@baomidou.com', 1088248166370832385, '2019-10-11 09:49:10');
INSERT INTO `user` VALUES ('1182475780156379138', '钱大忘2', 26, 'qdw2@baomidou.com', 1088248166370832385, '2019-10-11 09:59:30');
INSERT INTO `user` VALUES ('1182477066612293634', '钱大忘5', 26, 'qdw5@baomidou.com', 1088248166370832385, '2019-10-11 10:04:37');
INSERT INTO `user` VALUES ('1182488579309948930', '钱大忘6', 26, 'qd6@baomidou.com', 1088248166370832385, '2019-10-11 10:50:21');
INSERT INTO `user` VALUES ('1182493411085193217', '徐丽1', 28, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('1182493411299102722', '徐力2', 30, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('1182494050611752961', '徐丽3', 28, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('123456789', '钱大福', 26, 'qdf@baomidou.com', 1088248166370832385, '2019-10-11 09:50:41');
INSERT INTO `user` VALUES ('5facbf40086bcb0062c1e4002e973177', '钱大忘', 26, 'qdw@baomidou.com', 1088248166370832385, '2019-10-11 09:58:39');
INSERT INTO `user` VALUES ('c975b600f4acc288f27b072c884abfc9', '钱大忘4', 26, 'qdw4@baomidou.com', 1088248166370832385, '2019-10-11 10:02:58');

SET FOREIGN_KEY_CHECKS = 1;
