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

 Date: 03/29/2021 15:28:38 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `icon`
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon` (
  `icon_id` int NOT NULL AUTO_INCREMENT COMMENT '图标ID',
  `icon_name` varchar(20) DEFAULT NULL COMMENT '图标名称',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`icon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `icon`
-- ----------------------------
BEGIN;
INSERT INTO `icon` VALUES ('1', 'canyin', '2021-03-15 15:52:43'), ('2', 'yanjiulingshi', '2021-03-15 15:52:44'), ('3', 'gouwu', '2021-03-15 15:47:47'), ('4', 'zhufang', '2021-03-15 15:47:47'), ('5', 'jiaotong', '2021-03-15 15:47:47'), ('6', 'yule', '2021-03-15 15:47:47'), ('7', 'wenjiao', '2021-03-15 15:47:47'), ('8', 'qiche', '2021-03-15 15:47:47'), ('9', 'tongxun', '2021-03-15 15:47:47'), ('10', 'yuer', '2021-03-15 15:47:47'), ('11', 'renqing', '2021-03-15 15:47:47'), ('12', 'yiliao', '2021-03-15 15:47:47'), ('13', 'lvxing', '2021-03-15 15:47:47'), ('14', 'touzi', '2021-03-15 15:47:47'), ('15', 'touzikuisun', '2021-03-15 15:47:47'), ('16', 'jiechu', '2021-03-15 15:47:47'), ('17', 'huanzhai', '2021-03-15 15:47:47'), ('18', 'lixizhichu', '2021-03-15 15:47:47'), ('19', 'qita', '2021-03-16 16:22:28'), ('20', 'shezhi-zhichu', '2021-03-16 16:22:28'), ('21', 'xinzi', '2021-03-16 16:22:28'), ('22', 'jiangjin', '2021-03-16 16:22:28'), ('23', 'jieru', '2021-03-16 16:22:28'), ('24', 'shouzhai', '2021-03-16 16:22:28'), ('25', 'lixishouru', '2021-03-16 16:22:28'), ('26', 'touzihuishou', '2021-03-16 16:22:28'), ('27', 'touzishouyi', '2021-03-16 16:22:28'), ('28', 'baoxiaoshouru', '2021-03-16 16:22:28'), ('29', 'tuikuan', '2021-03-16 16:22:28'), ('30', 'yiwaisuode', '2021-03-16 16:22:28'), ('31', 'qitashouru', '2021-03-16 16:22:28'), ('32', 'shezhi-zhichu', '2021-03-16 16:22:28');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
