/*
 Navicat Premium Data Transfer

 Source Server         : 121.4.105.24
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 121.4.105.24:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 24/09/2022 16:07:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(12) DEFAULT NULL COMMENT '用户登录密码盐值',
  `personal_resume` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `state` varchar(1) DEFAULT NULL COMMENT '用户状态（0 可用，1 不可用）',
  `real_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '电话',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (45, 'rollin', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, '猥琐发育，别浪！', '0', '木木', '18946025623@163.com', '17645097389', '2021-10-10 14:22:41');
INSERT INTO `user` VALUES (46, 'admin', '0192023A7BBD73250516F069DF18B500', NULL, '酒酣胸胆尚开张，鬓微霜，又何妨？', '0', 'admin', 'admin@123.com', '17600008888', '2022-05-19 22:16:09');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
