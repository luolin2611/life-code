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

 Date: 03/29/2021 15:28:19 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `classify`
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
  `classify_id` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `classify_name` varchar(20) DEFAULT NULL COMMENT '分类名称',
  `type` varchar(1) DEFAULT NULL COMMENT '支付类型 (0-支出，1-收入)',
  `add_type` varchar(1) DEFAULT NULL COMMENT '分类类型 (0-预设，1-用户新增)',
  `icon_id` int DEFAULT NULL COMMENT '图标ID',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `updat_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`classify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `classify`
-- ----------------------------
BEGIN;
INSERT INTO `classify` VALUES ('1', '餐饮', '0', '0', '1', null, '2021-01-20 16:10:22'), ('2', '零食烟酒', '0', '0', '2', null, '2021-01-20 16:10:22'), ('3', '购物', '0', '0', '3', null, '2021-01-20 16:10:22'), ('4', '住房', '0', '0', '4', null, '2021-01-20 16:10:22'), ('5', '交通', '0', '0', '5', null, '2021-01-20 16:10:22'), ('6', '娱乐', '0', '0', '6', null, '2021-01-20 16:10:22'), ('7', '文教', '0', '0', '7', null, '2021-01-20 16:10:22'), ('8', '汽车', '0', '0', '8', null, '2021-01-20 16:10:22'), ('9', '通讯', '0', '0', '9', null, '2021-01-20 16:10:22'), ('10', '育儿', '0', '0', '10', null, '2021-01-20 16:10:22'), ('11', '人情', '0', '0', '11', null, '2021-01-20 16:10:22'), ('12', '医疗', '0', '0', '12', null, '2021-01-20 16:10:22'), ('13', '旅行', '0', '0', '13', null, '2021-01-20 16:10:22'), ('14', '投资', '0', '0', '14', null, '2021-01-20 16:10:22'), ('15', '投资亏损', '0', '0', '15', null, '2021-01-20 16:10:22'), ('16', '借出', '0', '0', '16', null, '2021-01-20 16:10:22'), ('17', '还债', '0', '0', '17', null, '2021-01-20 16:10:22'), ('18', '利息支出', '0', '0', '18', null, '2021-01-20 16:10:22'), ('19', '其它', '0', '0', '19', null, '2021-01-20 16:10:22'), ('20', '设置', '0', '0', '20', null, '2021-01-20 16:10:22'), ('21', '薪资', '1', '0', '21', null, '2021-01-20 16:14:58'), ('22', '奖金', '1', '0', '22', null, '2021-01-20 16:14:58'), ('23', '借入', '1', '0', '23', null, '2021-01-20 16:14:58'), ('24', '收债', '1', '0', '24', null, '2021-01-20 16:14:58'), ('25', '利息收入', '1', '0', '25', null, '2021-01-20 16:14:58'), ('26', '投资回收', '1', '0', '26', null, '2021-01-20 16:14:58'), ('27', '投资收益', '1', '0', '27', null, '2021-01-20 16:14:58'), ('28', '报销收入', '1', '0', '28', null, '2021-01-20 16:14:58'), ('29', '退款', '1', '0', '29', null, '2021-01-20 16:14:58'), ('30', '意外所得', '1', '0', '30', null, '2021-01-20 16:14:58'), ('31', '其他收入', '1', '0', '31', null, '2021-01-20 16:14:58'), ('32', '设置', '1', '0', '32', null, '2021-01-20 16:14:58');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
