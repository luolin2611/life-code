/*
 Navicat Premium Data Transfer

 Source Server         : local_connection
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost
 Source Database       : record_account

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : utf-8

 Date: 03/29/2021 15:28:32 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `classify_user`
-- ----------------------------
DROP TABLE IF EXISTS `classify_user`;
CREATE TABLE `classify_user` (
  `classify_user_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `income_classify_ids` varchar(255) DEFAULT NULL COMMENT '收入分类ID集合  1,2,3,4,5,6...',
  `expense_classify_ids` varchar(255) DEFAULT NULL COMMENT '收入分类ID集合  1,2,3,4,5,6...',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`classify_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Records of `classify_user`
-- ----------------------------
BEGIN;
INSERT INTO `classify_user` VALUES ('1', '45', '21,22,23,24,25,26,27,28,29,30,31,32', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20', '2021-03-17 12:58:01');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
