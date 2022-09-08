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

 Date: 07/09/2022 20:41:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for info_sys_api
-- ----------------------------
DROP TABLE IF EXISTS `info_sys_api`;
CREATE TABLE `info_sys_api`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `simple_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口名称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '完整名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口URL',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求类型',
  `permission_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '访问权限',
  `enable` int(0) NULL DEFAULT NULL COMMENT '是否启用',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统API信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
