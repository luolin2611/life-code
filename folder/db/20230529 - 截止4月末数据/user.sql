/*
 Navicat Premium Data Transfer

 Source Server         : 121.4.105.24
 Source Server Type    : MySQL
 Source Server Version : 50724 (5.7.24)
 Source Host           : 121.4.105.24:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50724 (5.7.24)
 File Encoding         : 65001

 Date: 29/05/2023 07:44:06
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

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名 - 唯一',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(12) DEFAULT NULL COMMENT '用户登录密码盐值',
  `personal_resume` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `state` varchar(1) DEFAULT NULL COMMENT '用户状态（0 可用，1 不可用）',
  `real_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '电话',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`,`user_name`) USING BTREE,
  UNIQUE KEY `user_name_idx` (`user_name`) USING BTREE COMMENT '使用用户名作为索引',
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (45, 'rollin', '$1$4lvsbRAK$s3Y3xkLyGbjeNSu5/o.Ea0', '$1$4lvsbRAK', '猥琐发育，别浪！', '0', '木木', '18946025623@163.com', '17645097389', '2021-10-10 14:22:41');
INSERT INTO `t_user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (46, 'admin', '0192023A7BBD73250516F069DF18B500', NULL, '酒酣胸胆尚开张，鬓微霜，又何妨？', '0', 'admin', 'admin@123.com', '17600008888', '2022-05-19 22:16:09');
INSERT INTO `t_user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (56, 'zs', '$1$dwN59Emt$VRiEME9T.Q2sR2Jya8Nqr0', '$1$dwN59Emt', NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (58, 'testdestroy01', '$1$xON8apS7$JtOC1dF37KnzWyDLSbpe8.', '$1$xON8apS7', NULL, '0', NULL, NULL, NULL, '2022-10-20 23:44:46');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (45, 'rollin', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, '猥琐发育，别浪！', '0', '木木', '18946025623@163.com', '17645097389', '2021-10-10 14:22:41');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (46, 'admin', '0192023A7BBD73250516F069DF18B500', NULL, '酒酣胸胆尚开张，鬓微霜，又何妨？', '0', 'admin', 'admin@123.com', '17600008888', '2022-05-19 22:16:09');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `salt`, `personal_resume`, `state`, `real_name`, `email`, `mobile`, `update_time`) VALUES (47, 'A', '202CB962AC59075B964B07152D234B70', NULL, '哈哈', '0', '把', 'aa', '13265868448', '2023-03-13 16:11:04');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
