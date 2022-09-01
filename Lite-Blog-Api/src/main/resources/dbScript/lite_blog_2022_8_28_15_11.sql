/*
 Navicat Premium Data Transfer

 Source Server         : dcm
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : lite_blog

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 28/08/2022 15:11:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for info_article
-- ----------------------------
DROP TABLE IF EXISTS `info_article`;
CREATE TABLE `info_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章标题',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章内容',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '博客文章信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_article
-- ----------------------------

-- ----------------------------
-- Table structure for info_category
-- ----------------------------
DROP TABLE IF EXISTS `info_category`;
CREATE TABLE `info_category`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '别名',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章分类信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_category
-- ----------------------------

-- ----------------------------
-- Table structure for info_comment
-- ----------------------------
DROP TABLE IF EXISTS `info_comment`;
CREATE TABLE `info_comment`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_comment
-- ----------------------------

-- ----------------------------
-- Table structure for info_file
-- ----------------------------
DROP TABLE IF EXISTS `info_file`;
CREATE TABLE `info_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件名',
  `original_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原始文件名',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '桶',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件访问url',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件类型，即后缀',
  `upload_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上传时间',
  `uploader` bigint(20) UNSIGNED NOT NULL COMMENT '操作员',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `access` int(11) NULL DEFAULT NULL COMMENT '访问权限，1是公有，0是私有',
  `version` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `file_id_uindex`(`id`) USING BTREE,
  INDEX `file_upload`(`uploader`) USING BTREE,
  CONSTRAINT `file_upload` FOREIGN KEY (`uploader`) REFERENCES `info_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_file
-- ----------------------------

-- ----------------------------
-- Table structure for info_label
-- ----------------------------
DROP TABLE IF EXISTS `info_label`;
CREATE TABLE `info_label`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '别名',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_label
-- ----------------------------

-- ----------------------------
-- Table structure for info_share
-- ----------------------------
DROP TABLE IF EXISTS `info_share`;
CREATE TABLE `info_share`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分享的内容',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_share
-- ----------------------------

-- ----------------------------
-- Table structure for info_sys_api
-- ----------------------------
DROP TABLE IF EXISTS `info_sys_api`;
CREATE TABLE `info_sys_api`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `simple_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口名称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '完整名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口URL',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求类型',
  `permission_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '访问权限',
  `enable` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_sys_api
-- ----------------------------
INSERT INTO `info_sys_api` VALUES (1, 'Hello', 'com.lite.api.controller.HelloSpringControllerr#Hello/hello', '/api/hello', 'GET,POST,DELETE', 1, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (2, 'Hello', 'com.lite.api.controller.HelloSpringControllerr#Hello/helloSpring', '/api/helloSpring', 'GET,POST,DELETE', 1, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (3, 'Hello', 'com.lite.api.controller.HelloSpringControllerr#Hello/helloWorld', '/api/helloWorld', 'GET,POST,DELETE', 1, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (4, 'changePassword', 'com.lite.api.controller.auth.AuthController#changePassword/auth/changePassword', '/api/auth/changePassword', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (5, 'login', 'com.lite.api.controller.auth.AuthController#login/auth/login', '/api/auth/login', 'GET', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (6, 'logout', 'com.lite.api.controller.auth.AuthController#logout/auth/logout', '/api/auth/logout', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (7, 'refreshToken', 'com.lite.api.controller.auth.AuthController#refreshToken/auth/refreshToken', '/api/auth/refreshToken', 'GET', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (8, 'register', 'com.lite.api.controller.auth.AuthController#register/auth/register', '/api/auth/register', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (9, 'updateInfo', 'com.lite.api.controller.auth.AuthController#updateInfo/auth/updateInfo', '/api/auth/updateInfo', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (10, 'getPreSignedDownloadUrl', 'com.lite.api.controller.cos.CosController#getPreSignedDownloadUrl/cos/download/preSigned', '/api/cos/download/preSigned', 'GET', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (11, 'uploadAvatar', 'com.lite.api.controller.cos.CosController#uploadAvatar/cos/upload/avatar', '/api/cos/upload/avatar', 'PUT', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (12, 'uploadPrivateFile', 'com.lite.api.controller.cos.CosController#uploadPrivateFile/cos/upload/private', '/api/cos/upload/private', 'PUT', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (13, 'uploadPublicFile', 'com.lite.api.controller.cos.CosController#uploadPublicFile/cos/upload/public', '/api/cos/upload/public', 'PUT', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (14, 'error', 'com.lite.api.controller.error.LiteBlogExceptionController#error/error', '/api/error', '', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (15, 'sendAuthMail', 'com.lite.api.controller.mail.MailController#sendAuthMail/mail/sendAuthMail', '/api/mail/sendAuthMail', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (16, 'sendHtmlMail', 'com.lite.api.controller.mail.MailController#sendHtmlMail/mail/sendHtmlMail', '/api/mail/sendHtmlMail', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);
INSERT INTO `info_sys_api` VALUES (17, 'sendSimpleTextMail', 'com.lite.api.controller.mail.MailController#sendSimpleTextMail/mail/sendSimpleMail', '/api/mail/sendSimpleMail', 'POST', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:12:26', 0, 1);

-- ----------------------------
-- Table structure for info_sys_controller
-- ----------------------------
DROP TABLE IF EXISTS `info_sys_controller`;
CREATE TABLE `info_sys_controller`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模块url',
  `simple_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `package_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包名',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '完整名称',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  `enable` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_sys_controller
-- ----------------------------
INSERT INTO `info_sys_controller` VALUES (1, '', 'HelloWorldController', 'com.lite.api.controller', 'com.lite.api.controller.HelloSpringControllerr', 1, 1, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `info_sys_controller` VALUES (2, '/auth', 'AuthController', 'com.lite.api.controller.auth', 'com.lite.api.controller.auth.AuthController', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `info_sys_controller` VALUES (3, '/cos', 'CosController', 'com.lite.api.controller.cos', 'com.lite.api.controller.cos.CosController', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `info_sys_controller` VALUES (4, '', 'LiteBlogExceptionController', 'com.lite.api.controller.error', 'com.lite.api.controller.error.LiteBlogExceptionController', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `info_sys_controller` VALUES (5, '/mail', 'MailController', 'com.lite.api.controller.mail', 'com.lite.api.controller.mail.MailController', 0, 1, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');

-- ----------------------------
-- Table structure for info_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `info_sys_permission`;
CREATE TABLE `info_sys_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` int(11) NOT NULL COMMENT '权限代码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限描述',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_sys_permission
-- ----------------------------
INSERT INTO `info_sys_permission` VALUES (1, 0, '普通用户', '2022-08-25 20:46:27', '2022-08-25 20:46:32', 0, '0');
INSERT INTO `info_sys_permission` VALUES (2, 1, '普通管理', '2022-08-25 20:46:59', '2022-08-25 20:47:01', 0, '0');
INSERT INTO `info_sys_permission` VALUES (3, 2, '超级管理', '2022-08-25 20:47:24', '2022-08-25 20:47:24', 0, '0');

-- ----------------------------
-- Table structure for info_user
-- ----------------------------
DROP TABLE IF EXISTS `info_user`;
CREATE TABLE `info_user`  (
  `id` bigint(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(11) NOT NULL COMMENT '性别',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户的自我介绍',
  `birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '出生日期',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属公司',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职业',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '逻辑删除,0代表未删除，1代表已删除',
  `permission_id` int(11) NULL DEFAULT 0 COMMENT '用户权限',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_user
-- ----------------------------
INSERT INTO `info_user` VALUES (00000000000000000011, '123', '123', NULL, '123', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `info_user` VALUES (00000000000000000013, '2633565580@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', NULL, 'wyh', 1, NULL, NULL, NULL, NULL, '2022-08-26 15:00:06', '2022-08-26 15:00:06', 0, 0, NULL);

-- ----------------------------
-- Table structure for relation_article_category
-- ----------------------------
DROP TABLE IF EXISTS `relation_article_category`;
CREATE TABLE `relation_article_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文件ID',
  `category_id` bigint(20) UNSIGNED NOT NULL COMMENT '分类ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_article_id_uindex`(`id`) USING BTREE,
  INDEX `articleKey`(`article_id`) USING BTREE,
  INDEX `categoryKey`(`category_id`) USING BTREE,
  CONSTRAINT `articleKey` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION,
  CONSTRAINT `categoryKey` FOREIGN KEY (`category_id`) REFERENCES `info_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-分类关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_article_category
-- ----------------------------

-- ----------------------------
-- Table structure for relation_article_user
-- ----------------------------
DROP TABLE IF EXISTS `relation_article_user`;
CREATE TABLE `relation_article_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `article_user_id_uindex`(`id`) USING BTREE,
  INDEX `article_key`(`article_id`) USING BTREE,
  INDEX `user_key`(`user_id`) USING BTREE,
  CONSTRAINT `article_key` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_key` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_article_user
-- ----------------------------

-- ----------------------------
-- Table structure for relation_comment_article
-- ----------------------------
DROP TABLE IF EXISTS `relation_comment_article`;
CREATE TABLE `relation_comment_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `comment_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
  `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `comment_article_id_uindex`(`id`) USING BTREE,
  INDEX `commentKey`(`comment_id`) USING BTREE,
  INDEX `c_artilceKey`(`article_id`) USING BTREE,
  CONSTRAINT `c_artilceKey` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commentKey` FOREIGN KEY (`comment_id`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-文章关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_comment_article
-- ----------------------------

-- ----------------------------
-- Table structure for relation_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `relation_comment_reply`;
CREATE TABLE `relation_comment_reply`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `comment_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
  `reply_id` bigint(20) UNSIGNED NOT NULL COMMENT '回复ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `replyKey`(`reply_id`) USING BTREE,
  INDEX `reply_commentKey`(`comment_id`) USING BTREE,
  CONSTRAINT `replyKey` FOREIGN KEY (`reply_id`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `reply_commentKey` FOREIGN KEY (`comment_id`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-回复关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for relation_comment_user
-- ----------------------------
DROP TABLE IF EXISTS `relation_comment_user`;
CREATE TABLE `relation_comment_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `comment_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `comment_user_id_uindex`(`id`) USING BTREE,
  INDEX `user_commentKey`(`comment_id`) USING BTREE,
  INDEX `comment_userKey`(`user_id`) USING BTREE,
  CONSTRAINT `comment_userKey` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_commentKey` FOREIGN KEY (`comment_id`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-动态关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_comment_user
-- ----------------------------

-- ----------------------------
-- Table structure for relation_label_article
-- ----------------------------
DROP TABLE IF EXISTS `relation_label_article`;
CREATE TABLE `relation_label_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `label_id` bigint(20) UNSIGNED NOT NULL COMMENT '标签ID',
  `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `label_article_id_uindex`(`id`) USING BTREE,
  INDEX `labelKey`(`label_id`) USING BTREE,
  INDEX `label_articleKey`(`article_id`) USING BTREE,
  CONSTRAINT `labelKey` FOREIGN KEY (`label_id`) REFERENCES `info_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `label_articleKey` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标签-文章关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_label_article
-- ----------------------------

-- ----------------------------
-- Table structure for relation_share_user
-- ----------------------------
DROP TABLE IF EXISTS `relation_share_user`;
CREATE TABLE `relation_share_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键iD',
  `share_id` bigint(20) UNSIGNED NOT NULL COMMENT '分享ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shareKey`(`share_id`) USING BTREE,
  INDEX `share_userKey`(`user_id`) USING BTREE,
  CONSTRAINT `shareKey` FOREIGN KEY (`share_id`) REFERENCES `info_share` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `share_userKey` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_share_user
-- ----------------------------

-- ----------------------------
-- Table structure for relation_sys_api_controller
-- ----------------------------
DROP TABLE IF EXISTS `relation_sys_api_controller`;
CREATE TABLE `relation_sys_api_controller`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `controller_id` bigint(20) UNSIGNED NOT NULL COMMENT 'Ctrl ID',
  `api_id` bigint(20) UNSIGNED NOT NULL COMMENT 'API ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `controller_key`(`controller_id`) USING BTREE,
  INDEX `api_key`(`api_id`) USING BTREE,
  CONSTRAINT `api_key` FOREIGN KEY (`api_id`) REFERENCES `info_sys_api` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `controller_key` FOREIGN KEY (`controller_id`) REFERENCES `info_sys_controller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_sys_api_controller
-- ----------------------------
INSERT INTO `relation_sys_api_controller` VALUES (49, 1, 1, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (50, 1, 2, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (51, 1, 3, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (52, 2, 4, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (53, 2, 5, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (54, 2, 6, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (55, 2, 7, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (56, 2, 8, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (57, 2, 9, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (58, 3, 10, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (59, 3, 11, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (60, 3, 12, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (61, 3, 13, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (62, 4, 14, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (63, 5, 15, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (64, 5, 16, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (65, 5, 17, '2022-08-28 14:00:27', '2022-08-28 14:00:27', 0, '0');

-- ----------------------------
-- Table structure for relation_user_fans
-- ----------------------------
DROP TABLE IF EXISTS `relation_user_fans`;
CREATE TABLE `relation_user_fans`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `fans_id` bigint(20) UNSIGNED NOT NULL COMMENT '粉丝ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除\r\n',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user_id`) USING BTREE,
  INDEX `fans`(`fans_id`) USING BTREE,
  CONSTRAINT `fans` FOREIGN KEY (`fans_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-粉丝关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_user_fans
-- ----------------------------

-- ----------------------------
-- Table structure for relation_user_label
-- ----------------------------
DROP TABLE IF EXISTS `relation_user_label`;
CREATE TABLE `relation_user_label`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `label_id` bigint(20) UNSIGNED NOT NULL COMMENT '标签ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `label_userKey`(`user_id`) USING BTREE,
  INDEX `user_labelKey`(`label_id`) USING BTREE,
  CONSTRAINT `label_userKey` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_labelKey` FOREIGN KEY (`label_id`) REFERENCES `info_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-标签关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_user_label
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
