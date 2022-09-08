/*
 Navicat Premium Data Transfer

 Source Server         : dcm
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : lite_blog

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/09/2022 20:41:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for relation_sys_api_controller
-- ----------------------------
DROP TABLE IF EXISTS `relation_sys_api_controller`;
CREATE TABLE `relation_sys_api_controller`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `controller_id` bigint(0) UNSIGNED NOT NULL COMMENT 'Ctrl ID',
  `api_id` bigint(0) UNSIGNED NOT NULL COMMENT 'API ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `controller_key`(`controller_id`) USING BTREE,
  INDEX `api_key`(`api_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 206 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'API-Controller关系表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
