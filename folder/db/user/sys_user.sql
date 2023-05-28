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

 Date: 24/09/2022 16:08:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
