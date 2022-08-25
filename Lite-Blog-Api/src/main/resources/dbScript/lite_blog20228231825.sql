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

 Date: 23/08/2022 18:24:30
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
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT ' 创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
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
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
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
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `file_id_uindex`(`id`) USING BTREE,
  INDEX `file_upload`(`uploader`) USING BTREE,
  CONSTRAINT `file_upload` FOREIGN KEY (`uploader`) REFERENCES `info_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

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
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_label
-- ----------------------------

-- ----------------------------
-- Table structure for info_role
-- ----------------------------
DROP TABLE IF EXISTS `info_role`;
CREATE TABLE `info_role`  (
  `role` int(11) NOT NULL DEFAULT 0 COMMENT '0-普通,1-管理员,2-超级管理员',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '普通用户' COMMENT '描述',
  PRIMARY KEY (`role`) USING BTREE,
  UNIQUE INDEX `role_role_uindex`(`role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户权限信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_role
-- ----------------------------
INSERT INTO `info_role` VALUES (0, '普通用户');
INSERT INTO `info_role` VALUES (1, '普通管理');
INSERT INTO `info_role` VALUES (2, '超级管理');

-- ----------------------------
-- Table structure for info_share
-- ----------------------------
DROP TABLE IF EXISTS `info_share`;
CREATE TABLE `info_share`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分享的内容',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_share
-- ----------------------------

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
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字段创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字段更新时间',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '逻辑删除,0代表未删除，1代表已删除',
  `roleId` int(11) NULL DEFAULT 0 COMMENT '用户权限',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role`(`roleId`) USING BTREE,
  CONSTRAINT `role` FOREIGN KEY (`roleId`) REFERENCES `info_role` (`role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_user
-- ----------------------------
INSERT INTO `info_user` VALUES (00000000000000000010, '2633565580@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', '2633565580', 'wyh', 1, 'i am a handsome', NULL, '2022-08-23 18:22:30', '2022-08-23 18:24:03', 0, 0);

-- ----------------------------
-- Table structure for releation_article_category
-- ----------------------------
DROP TABLE IF EXISTS `releation_article_category`;
CREATE TABLE `releation_article_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `articleId` bigint(20) UNSIGNED NOT NULL COMMENT '文件ID',
  `categoryId` bigint(20) UNSIGNED NOT NULL COMMENT '分类ID',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_article_id_uindex`(`id`) USING BTREE,
  INDEX `articleKey`(`articleId`) USING BTREE,
  INDEX `categoryKey`(`categoryId`) USING BTREE,
  CONSTRAINT `articleKey` FOREIGN KEY (`articleId`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION,
  CONSTRAINT `categoryKey` FOREIGN KEY (`categoryId`) REFERENCES `info_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-分类关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_article_category
-- ----------------------------

-- ----------------------------
-- Table structure for releation_article_user
-- ----------------------------
DROP TABLE IF EXISTS `releation_article_user`;
CREATE TABLE `releation_article_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `articleId` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
  `userId` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `article_user_id_uindex`(`id`) USING BTREE,
  INDEX `article_key`(`articleId`) USING BTREE,
  INDEX `user_key`(`userId`) USING BTREE,
  CONSTRAINT `article_key` FOREIGN KEY (`articleId`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_key` FOREIGN KEY (`userId`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_article_user
-- ----------------------------

-- ----------------------------
-- Table structure for releation_comment_article
-- ----------------------------
DROP TABLE IF EXISTS `releation_comment_article`;
CREATE TABLE `releation_comment_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `commentId` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
  `articleId` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `comment_article_id_uindex`(`id`) USING BTREE,
  INDEX `commentKey`(`commentId`) USING BTREE,
  INDEX `c_artilceKey`(`articleId`) USING BTREE,
  CONSTRAINT `commentKey` FOREIGN KEY (`commentId`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `c_artilceKey` FOREIGN KEY (`articleId`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-文章关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_comment_article
-- ----------------------------

-- ----------------------------
-- Table structure for releation_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `releation_comment_reply`;
CREATE TABLE `releation_comment_reply`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `commentId` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
  `replyId` bigint(20) UNSIGNED NOT NULL COMMENT '回复ID',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `replyKey`(`replyId`) USING BTREE,
  INDEX `reply_commentKey`(`commentId`) USING BTREE,
  CONSTRAINT `replyKey` FOREIGN KEY (`replyId`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `reply_commentKey` FOREIGN KEY (`commentId`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-回复关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for releation_comment_user
-- ----------------------------
DROP TABLE IF EXISTS `releation_comment_user`;
CREATE TABLE `releation_comment_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `commentId` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
  `userId` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `created_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `comment_user_id_uindex`(`id`) USING BTREE,
  INDEX `user_commentKey`(`commentId`) USING BTREE,
  INDEX `comment_userKey`(`userId`) USING BTREE,
  CONSTRAINT `comment_userKey` FOREIGN KEY (`userId`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_commentKey` FOREIGN KEY (`commentId`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-动态关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_comment_user
-- ----------------------------

-- ----------------------------
-- Table structure for releation_label_article
-- ----------------------------
DROP TABLE IF EXISTS `releation_label_article`;
CREATE TABLE `releation_label_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `labelId` bigint(20) UNSIGNED NOT NULL COMMENT '标签ID',
  `articleId` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `label_article_id_uindex`(`id`) USING BTREE,
  INDEX `labelKey`(`labelId`) USING BTREE,
  INDEX `label_articleKey`(`articleId`) USING BTREE,
  CONSTRAINT `labelKey` FOREIGN KEY (`labelId`) REFERENCES `info_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `label_articleKey` FOREIGN KEY (`articleId`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标签-文章关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_label_article
-- ----------------------------

-- ----------------------------
-- Table structure for releation_share_user
-- ----------------------------
DROP TABLE IF EXISTS `releation_share_user`;
CREATE TABLE `releation_share_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键iD',
  `shareId` bigint(20) UNSIGNED NOT NULL COMMENT '分享ID',
  `userId` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shareKey`(`shareId`) USING BTREE,
  INDEX `share_userKey`(`userId`) USING BTREE,
  CONSTRAINT `shareKey` FOREIGN KEY (`shareId`) REFERENCES `info_share` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `share_userKey` FOREIGN KEY (`userId`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_share_user
-- ----------------------------

-- ----------------------------
-- Table structure for releation_user_fans
-- ----------------------------
DROP TABLE IF EXISTS `releation_user_fans`;
CREATE TABLE `releation_user_fans`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `fansId` bigint(20) UNSIGNED NOT NULL COMMENT '粉丝ID',
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除\r\n',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`userId`) USING BTREE,
  INDEX `fans`(`fansId`) USING BTREE,
  CONSTRAINT `user` FOREIGN KEY (`userId`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fans` FOREIGN KEY (`fansId`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-粉丝关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_user_fans
-- ----------------------------

-- ----------------------------
-- Table structure for releation_user_label
-- ----------------------------
DROP TABLE IF EXISTS `releation_user_label`;
CREATE TABLE `releation_user_label`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `labelId` bigint(20) UNSIGNED NOT NULL COMMENT '标签ID',
  `created_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `label_userKey`(`userId`) USING BTREE,
  INDEX `user_labelKey`(`labelId`) USING BTREE,
  CONSTRAINT `label_userKey` FOREIGN KEY (`userId`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_labelKey` FOREIGN KEY (`labelId`) REFERENCES `info_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-标签关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of releation_user_label
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
