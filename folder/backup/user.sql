/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost
 Source Database       : user

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : utf-8

 Date: 10/15/2021 07:41:01 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_level` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `add_user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `personal_resume` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `state` varchar(1) DEFAULT NULL COMMENT '用户状态（0 可用，1 不可用）',
  `real_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '电话',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('45', 'rollin', 'E10ADC3949BA59ABBE56E057F20F883E', '猥琐发育，别浪！', '0', '木木', '18946025623@163.com', '17645097389', '2021-10-10 14:22:41');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
