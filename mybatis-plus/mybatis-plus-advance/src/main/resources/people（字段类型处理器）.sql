/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : mp_advance

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 06/07/2021 10:04:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `wallets` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '钱包',
  `other_info` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES (1, 'Jone', 18, 'test1@baomidou.com', '[{\r\n    \"name\": \"支付宝钱包\",\r\n    \"currencyList\": [{\r\n        \"type\": \"USD\",\r\n        \"amount\": 999.19\r\n    },{\r\n        \"type\": \"RMB\",\r\n        \"amount\": 1000.19\r\n    }]\r\n},{\r\n    \"name\": \"微信钱包\",\r\n    \"currencyList\": [{\r\n        \"type\": \"USD\",\r\n        \"amount\": 888.18\r\n    },{\r\n        \"type\": \"RMB\",\r\n        \"amount\": 1000.18\r\n    }]\r\n}]', '{\r\n        \"sex\": \"男\",\r\n        \"city\": \"南昌\"\r\n}');
INSERT INTO `people` VALUES (2, 'Jack', 20, 'test2@baomidou.com', '[{\r\n    \"name\": \"银联钱包\",\r\n    \"currencyList\": [{\r\n        \"type\": \"USD\",\r\n        \"amount\": 888.18\r\n    },{\r\n        \"type\": \"RMB\",\r\n        \"amount\": 1000.18\r\n    }]\r\n}]', '{\r\n        \"sex\": \"男\",\r\n        \"city\": \"青岛\"\r\n}');

SET FOREIGN_KEY_CHECKS = 1;
