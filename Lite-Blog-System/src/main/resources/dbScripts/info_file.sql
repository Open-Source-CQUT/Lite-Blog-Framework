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

 Date: 07/09/2022 20:42:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for info_file
-- ----------------------------
DROP TABLE IF EXISTS `info_file`;
CREATE TABLE `info_file`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件名',
  `original_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原始文件名',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '桶',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件访问url',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件类型，即后缀',
  `upload_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上传时间',
  `uploader` bigint(0) UNSIGNED NOT NULL COMMENT '操作员',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `access` int(0) NULL DEFAULT NULL COMMENT '访问权限，1是公有，0是私有',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `file_id_uindex`(`id`) USING BTREE,
  INDEX `file_upload`(`uploader`) USING BTREE,
  CONSTRAINT `file_upload` FOREIGN KEY (`uploader`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
