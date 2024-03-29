/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 28/08/2021 17:04:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for command
-- ----------------------------
DROP TABLE IF EXISTS `command`;
CREATE TABLE `command`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指令名称',
  `DESCRIPTION` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of command
-- ----------------------------
INSERT INTO `command` VALUES (1, '段子', '精彩段子');
INSERT INTO `command` VALUES (2, '娱乐', '娱乐新闻');

-- ----------------------------
-- Table structure for command_content
-- ----------------------------
DROP TABLE IF EXISTS `command_content`;
CREATE TABLE `command_content`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CONTENT` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `COMMAND_ID` int(0) NULL DEFAULT NULL COMMENT '指令表主键',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of command_content
-- ----------------------------
INSERT INTO `command_content` VALUES (1, '精彩段子1', 1);
INSERT INTO `command_content` VALUES (2, '精彩段子2', 1);
INSERT INTO `command_content` VALUES (3, '精彩段子3', 1);
INSERT INTO `command_content` VALUES (4, '精彩段子4', 1);
INSERT INTO `command_content` VALUES (5, '精彩段子5', 1);
INSERT INTO `command_content` VALUES (6, '娱乐新闻1', 2);
INSERT INTO `command_content` VALUES (7, '娱乐新闻2', 2);
INSERT INTO `command_content` VALUES (8, '娱乐新闻3', 2);
INSERT INTO `command_content` VALUES (9, '娱乐新闻4', 2);
INSERT INTO `command_content` VALUES (10, '娱乐新闻5', 2);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `COMMAND` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指令名称',
  `DESCRIPTION` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `CONTENT` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '查看', '精彩内容', '精彩内容');
INSERT INTO `message` VALUES (2, '段子', '精彩段子', '如果你的月薪是3000块钱，请记得分成五份，一份用来买书，一份给家人，一份给女朋友买化妆品和衣服，一份请朋友们吃饭，一份作为同事的各种婚丧嫁娶的份子钱。剩下的2999块钱藏起来，不要告诉任何人');
INSERT INTO `message` VALUES (3, '新闻', '今日头条', '7月17日，马来西亚一架载有298人的777客机在乌克兰靠近俄罗斯边界坠毁。另据国际文传电讯社消息，坠毁机型为一架波音777客机，机载约280名乘客和15个机组人员。\r\n乌克兰空管部门随后证实马航MH17航班坠毁。乌克兰内政部幕僚表示，这一航班在顿涅茨克地区上空被击落。马来西亚航空公司确认，该公司从阿姆斯特丹飞往吉隆坡的MH17航班失联，并称最后与该客机取得联系的地点在乌克兰上空。图为马航客机坠毁现场。');
INSERT INTO `message` VALUES (4, '娱乐', '娱乐新闻', '昨日，邓超在微博分享了自己和孙俪的书法。夫妻同样写幸福，但差距很大。邓超自己都忍不住感慨字丑：左边媳妇写的。右边是我写的。看完我再也不幸福了。');
INSERT INTO `message` VALUES (5, '电影', '近日上映大片', '《忍者神龟》[2]真人电影由美国派拉蒙影业发行，《洛杉矶之战》导演乔纳森·里贝斯曼执导。 \r\n片中四只神龟和老鼠老师都基于漫画和卡通重新绘制，由动作捕捉技术实现。\r\n其中皮特·普劳泽克饰演达芬奇(武器：武士刀)，诺尔·费舍饰演米开朗基罗(武器：双节棍)，阿伦·瑞奇森饰演拉斐尔(武器：铁叉)，杰瑞米·霍华德饰演多拉泰罗(武器：武士棍)。\r\n该片计划于2014年8月8日在北美上映。');
INSERT INTO `message` VALUES (6, '彩票', '中奖号码', '查啥呀查，你不会中奖的！');

SET FOREIGN_KEY_CHECKS = 1;
