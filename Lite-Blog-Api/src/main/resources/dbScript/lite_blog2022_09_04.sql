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

 Date: 04/09/2022 21:57:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for info_article
-- ----------------------------
DROP TABLE IF EXISTS `info_article`;
CREATE TABLE `info_article`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章标题',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章封面',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章摘要',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章内容',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  `status_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '状态ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_status`(`status_id`) USING BTREE,
  CONSTRAINT `article_status` FOREIGN KEY (`status_id`) REFERENCES `info_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '博客文章信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_article
-- ----------------------------
INSERT INTO `info_article` VALUES (35, '456', '456', NULL, '456', '2022-09-04 21:17:08', NULL, 0, 0, 1);
INSERT INTO `info_article` VALUES (38, '勇敢牛牛不怕困难', NULL, NULL, '中华人民共和国', '2022-09-04 21:26:56', '2022-09-04 21:30:49', 0, 2, 2);
INSERT INTO `info_article` VALUES (39, '勇敢牛牛不怕困难', NULL, NULL, '中华人民共和国', '2022-09-04 21:28:30', '2022-09-04 21:28:30', 0, 0, 1);

-- ----------------------------
-- Table structure for info_category
-- ----------------------------
DROP TABLE IF EXISTS `info_category`;
CREATE TABLE `info_category`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '别名',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章分类信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_category
-- ----------------------------
INSERT INTO `info_category` VALUES (1, '前端', 'font_end', '2022-09-03 21:15:26', '2022-09-03 21:15:26', 0, 0);
INSERT INTO `info_category` VALUES (2, '后端', 'back_end', '2022-09-03 21:15:33', '2022-09-03 21:15:33', 0, 0);
INSERT INTO `info_category` VALUES (3, 'Android', 'android', '2022-09-03 21:16:32', '2022-09-03 21:16:32', 0, 0);
INSERT INTO `info_category` VALUES (4, 'IOS', 'ios', '2022-09-03 21:16:38', '2022-09-03 21:16:38', 0, 0);
INSERT INTO `info_category` VALUES (5, '人工智能', 'ai', '2022-09-03 21:16:48', '2022-09-03 21:16:48', 0, 0);
INSERT INTO `info_category` VALUES (6, '开发工具', 'dev_tool', '2022-09-03 21:16:55', '2022-09-03 21:16:55', 0, 0);
INSERT INTO `info_category` VALUES (7, '代码人生', 'cod_life', '2022-09-03 21:17:13', '2022-09-03 21:17:13', 0, 0);
INSERT INTO `info_category` VALUES (8, '阅读', 'read', '2022-09-03 21:17:18', '2022-09-03 21:17:18', 0, 0);

-- ----------------------------
-- Table structure for info_comment
-- ----------------------------
DROP TABLE IF EXISTS `info_comment`;
CREATE TABLE `info_comment`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
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

-- ----------------------------
-- Records of info_file
-- ----------------------------
INSERT INTO `info_file` VALUES (25, '1661759660628/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/203ca5029bcf4d3386d7c4ef3561fb70.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661759660628/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/203ca5029bcf4d3386d7c4ef3561fb70.png', '.png', '2022-08-29 15:54:21.552', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (26, '1661759719440/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/817409309fc74b328f0fb15b6b2bf565.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661759719440/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/817409309fc74b328f0fb15b6b2bf565.png', '.png', '2022-08-29 15:55:19.669', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (27, '1661759737072/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/7402ecdcf3c943269b0b9de5860684f9.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661759737072/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/7402ecdcf3c943269b0b9de5860684f9.png', '.png', '2022-08-29 15:55:37.158', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (28, '1661759789673/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/dd475f0e6eba4714b23a441d380b2540.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661759789673/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/dd475f0e6eba4714b23a441d380b2540.png', '.png', '2022-08-29 15:56:29.787', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (29, '1661760621808/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/696c48690a074ab8a871c6243c68ef63.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661760621808/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/696c48690a074ab8a871c6243c68ef63.png', '.png', '2022-08-29 16:10:22.268', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (30, '1661760646301/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/a064c6b5897d47af9042c1e7e512b6a5.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661760646301/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/a064c6b5897d47af9042c1e7e512b6a5.png', '.png', '2022-08-29 16:10:46.531', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (31, '1661760700038/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/75d3f0341cca4662ad7ad28ea811db56.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661760700038/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/75d3f0341cca4662ad7ad28ea811db56.png', '.png', '2022-08-29 16:11:40.143', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (32, '1661761141242/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/636a2294a5ef4aef91c37e5b434b2040.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661761141242/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/636a2294a5ef4aef91c37e5b434b2040.png', '.png', '2022-08-29 16:19:01.812', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (33, '1661761220107/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/1b21ff93a507450d81a423c2c462a51d.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661761220107/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/1b21ff93a507450d81a423c2c462a51d.png', '.png', '2022-08-29 16:20:20.413', 13, 0, 1, 0);
INSERT INTO `info_file` VALUES (34, '1661762919346/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/6cc3ce1ade9340a4a298a40c79a429dd.png', 'image.png', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661762919346/ad21a73b2b13093fa2cca7fe85a3f460aaa08a43/6cc3ce1ade9340a4a298a40c79a429dd.png', '.png', '2022-08-29 16:48:40.028', 13, 0, 1, 0);

-- ----------------------------
-- Table structure for info_label
-- ----------------------------
DROP TABLE IF EXISTS `info_label`;
CREATE TABLE `info_label`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '别名',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_label
-- ----------------------------
INSERT INTO `info_label` VALUES (1, '前端', 'font_end', '2022-09-03 21:01:12', '2022-09-03 21:01:12', 0, 0);
INSERT INTO `info_label` VALUES (2, '后端', 'back_end', '2022-09-03 21:01:36', '2022-09-03 21:01:36', 0, 0);
INSERT INTO `info_label` VALUES (3, 'JavaScript', 'js', '2022-09-03 21:21:56', '2022-09-03 21:21:59', 0, 0);
INSERT INTO `info_label` VALUES (4, 'C++', 'c++', '2022-09-03 21:22:02', '2022-09-03 21:22:04', 0, 0);
INSERT INTO `info_label` VALUES (5, '设计模式', 'design_patterns', '2022-09-03 21:22:06', '2022-09-03 21:22:09', 0, 0);
INSERT INTO `info_label` VALUES (6, '算法', 'algroithm', '2022-09-03 21:22:11', '2022-09-03 21:22:13', 0, 0);
INSERT INTO `info_label` VALUES (7, 'Vue', 'vue', '2022-09-03 21:22:16', '2022-09-03 21:22:18', 0, 0);
INSERT INTO `info_label` VALUES (8, 'React', 'react', '2022-09-03 21:22:20', '2022-09-03 21:22:22', 0, 0);
INSERT INTO `info_label` VALUES (9, '操作系统', 'os', '2022-09-03 21:22:24', '2022-09-03 21:22:26', 0, 0);
INSERT INTO `info_label` VALUES (10, '面试', 'interview', '2022-09-03 21:22:29', '2022-09-03 21:22:31', 0, 0);
INSERT INTO `info_label` VALUES (11, '工作', 'work', '2022-09-03 21:22:33', '2022-09-03 21:22:35', 0, 0);
INSERT INTO `info_label` VALUES (12, '学习', 'lean', '2022-09-03 21:22:37', '2022-09-03 21:22:39', 0, 0);
INSERT INTO `info_label` VALUES (13, 'GO', 'golang', '2022-09-03 21:22:41', '2022-09-03 21:22:43', 0, 0);

-- ----------------------------
-- Table structure for info_share
-- ----------------------------
DROP TABLE IF EXISTS `info_share`;
CREATE TABLE `info_share`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分享的内容',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_share
-- ----------------------------

-- ----------------------------
-- Table structure for info_status
-- ----------------------------
DROP TABLE IF EXISTS `info_status`;
CREATE TABLE `info_status`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '别名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章状态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_status
-- ----------------------------
INSERT INTO `info_status` VALUES (1, 'unpublished', '未发布', '2022-09-02 19:34:38', '2022-09-02 19:34:40', 0, 0);
INSERT INTO `info_status` VALUES (2, 'review', '审核中', '2022-09-02 19:36:18', '2022-09-02 19:36:20', 0, 0);
INSERT INTO `info_status` VALUES (3, 'failed', '未通过', '2022-09-02 19:36:49', '2022-09-02 19:36:51', 0, 0);
INSERT INTO `info_status` VALUES (4, 'published', '已发布', '2022-09-02 19:37:12', '2022-09-02 19:37:14', 0, 0);

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
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统API信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_sys_api
-- ----------------------------
INSERT INTO `info_sys_api` VALUES (77, 'HelloWorld', 'com.lite.api.controller.HelloWorldController#HelloWorld/hello', '/api/hello', 'GET,POST,DELETE', 1, 1, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, 0);
INSERT INTO `info_sys_api` VALUES (78, 'HelloWorld', 'com.lite.api.controller.HelloWorldController#HelloWorld/helloSpring', '/api/helloSpring', 'GET,POST,DELETE', 1, 1, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, 0);
INSERT INTO `info_sys_api` VALUES (79, 'HelloWorld', 'com.lite.api.controller.HelloWorldController#HelloWorld/helloWorld', '/api/helloWorld', 'GET,POST,DELETE', 1, 1, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, 0);
INSERT INTO `info_sys_api` VALUES (80, 'changePassword', 'com.lite.api.controller.auth.AuthController#changePassword/auth/changePassword', '/api/auth/changePassword', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (81, 'login', 'com.lite.api.controller.auth.AuthController#login/auth/login', '/api/auth/login', 'GET', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (82, 'logout', 'com.lite.api.controller.auth.AuthController#logout/auth/logout', '/api/auth/logout', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (83, 'refreshToken', 'com.lite.api.controller.auth.AuthController#refreshToken/auth/refreshToken', '/api/auth/refreshToken', 'GET', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (84, 'register', 'com.lite.api.controller.auth.AuthController#register/auth/register', '/api/auth/register', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (85, 'updateInfo', 'com.lite.api.controller.auth.AuthController#updateInfo/auth/updateInfo', '/api/auth/updateInfo', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (86, 'createDraft', 'com.lite.api.controller.bussiness.article.ArticleController#createDraft/article/createDraft', '/api/article/createDraft', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-03 20:32:09', 0, 1);
INSERT INTO `info_sys_api` VALUES (87, 'getArticleList', 'com.lite.api.controller.bussiness.article.ArticleController#getArticleList/article/getArticleList', '/api/article/getArticleList', 'GET', 0, 1, '2022-09-01 20:36:32', '2022-09-03 20:32:09', 0, 1);
INSERT INTO `info_sys_api` VALUES (88, 'publishArticle', 'com.lite.api.controller.bussiness.article.ArticleController#publishArticle/article/publishArticle', '/api/article/publishArticle', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-03 20:32:09', 0, 1);
INSERT INTO `info_sys_api` VALUES (89, 'saveDraft', 'com.lite.api.controller.bussiness.article.ArticleController#saveDraft/article/saveDraft', '/api/article/saveDraft', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-03 20:32:09', 0, 1);
INSERT INTO `info_sys_api` VALUES (90, 'getPreSignedDownloadUrl', 'com.lite.api.controller.cos.CosController#getPreSignedDownloadUrl/cos/download/preSigned', '/api/cos/download/preSigned', 'GET', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (91, 'uploadAvatar', 'com.lite.api.controller.cos.CosController#uploadAvatar/cos/upload/avatar', '/api/cos/upload/avatar', 'PUT', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (92, 'uploadPrivateFile', 'com.lite.api.controller.cos.CosController#uploadPrivateFile/cos/upload/private', '/api/cos/upload/private', 'PUT', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (93, 'uploadPublicFile', 'com.lite.api.controller.cos.CosController#uploadPublicFile/cos/upload/public', '/api/cos/upload/public', 'PUT', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (94, 'error', 'com.lite.api.controller.error.LiteBlogExceptionController#error/error', '/api/error', '', 0, 1, '2022-09-01 20:36:32', '2022-09-03 13:30:10', 0, 1);
INSERT INTO `info_sys_api` VALUES (95, 'sendAuthMail', 'com.lite.api.controller.mail.MailController#sendAuthMail/mail/sendAuthMail', '/api/mail/sendAuthMail', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (96, 'sendHtmlMail', 'com.lite.api.controller.mail.MailController#sendHtmlMail/mail/sendHtmlMail', '/api/mail/sendHtmlMail', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (97, 'sendSimpleTextMail', 'com.lite.api.controller.mail.MailController#sendSimpleTextMail/mail/sendSimpleMail', '/api/mail/sendSimpleMail', 'POST', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', 0, 1);
INSERT INTO `info_sys_api` VALUES (98, 'getArticleDetails', 'com.lite.api.controller.bussiness.article.ArticleController#getArticleDetails/article/getArticleDetails', '/api/article/getArticleDetails', 'GET', 0, 1, '2022-09-03 11:29:25', '2022-09-03 20:32:09', 0, 1);
INSERT INTO `info_sys_api` VALUES (99, 'error', 'com.lite.api.controller.admin.error.LiteBlogExceptionController#error/error', '/api/error', '', 0, 1, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, 0);
INSERT INTO `info_sys_api` VALUES (100, 'openapiJson', 'org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource#openapiJson/v3/api-docs/{group}', '/api/v3/api-docs/{group}', 'GET', 0, 1, '2022-09-03 13:30:10', '2022-09-03 13:44:43', 0, 1);
INSERT INTO `info_sys_api` VALUES (101, 'openapiYaml', 'org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource#openapiYaml/v3/api-docs.yaml/{group}', '/api/v3/api-docs.yaml/{group}', 'GET', 0, 1, '2022-09-03 13:30:10', '2022-09-03 13:44:43', 0, 1);
INSERT INTO `info_sys_api` VALUES (102, 'openapiJson', 'org.springdoc.webmvc.api.OpenApiWebMvcResource#openapiJson/v3/api-docs', '/api/v3/api-docs', 'GET', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', 0, 1);
INSERT INTO `info_sys_api` VALUES (103, 'openapiYaml', 'org.springdoc.webmvc.api.OpenApiWebMvcResource#openapiYaml/v3/api-docs.yaml', '/api/v3/api-docs.yaml', 'GET', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', 0, 1);
INSERT INTO `info_sys_api` VALUES (104, 'openapiJson', 'org.springdoc.webmvc.ui.SwaggerConfigResource#openapiJson/v3/api-docs/swagger-config', '/api/v3/api-docs/swagger-config', 'GET', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', 0, 1);
INSERT INTO `info_sys_api` VALUES (105, 'redirectToUi', 'org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc#redirectToUi/swagger-ui.html', '/api/swagger-ui.html', 'GET', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', 0, 1);
INSERT INTO `info_sys_api` VALUES (106, 'createDraft', 'com.lite.api.controller.publics.bussiness.article.ArticleController#createDraft/article/createDraft', '/api/article/createDraft', 'POST', 0, 1, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, 0);
INSERT INTO `info_sys_api` VALUES (107, 'getArticleDetails', 'com.lite.api.controller.publics.bussiness.article.ArticleController#getArticleDetails/article/getArticleDetails', '/api/article/getArticleDetails', 'GET', 0, 1, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, 0);
INSERT INTO `info_sys_api` VALUES (108, 'getArticleList', 'com.lite.api.controller.publics.bussiness.article.ArticleController#getArticleList/article/getArticleList', '/api/article/getArticleList', 'GET', 0, 1, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, 0);
INSERT INTO `info_sys_api` VALUES (109, 'publishArticle', 'com.lite.api.controller.publics.bussiness.article.ArticleController#publishArticle/article/publishArticle', '/api/article/publishArticle', 'POST', 0, 1, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, 0);
INSERT INTO `info_sys_api` VALUES (110, 'saveDraft', 'com.lite.api.controller.publics.bussiness.article.ArticleController#saveDraft/article/saveDraft', '/api/article/saveDraft', 'POST', 0, 1, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, 0);
INSERT INTO `info_sys_api` VALUES (111, 'changePassword', 'com.lite.api.controller.publics.auth.AuthController#changePassword/auth/changePassword', '/api/auth/changePassword', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (112, 'login', 'com.lite.api.controller.publics.auth.AuthController#login/auth/login', '/api/auth/login', 'GET', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (113, 'logout', 'com.lite.api.controller.publics.auth.AuthController#logout/auth/logout', '/api/auth/logout', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (114, 'refreshToken', 'com.lite.api.controller.publics.auth.AuthController#refreshToken/auth/refreshToken', '/api/auth/refreshToken', 'GET', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (115, 'register', 'com.lite.api.controller.publics.auth.AuthController#register/auth/register', '/api/auth/register', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (116, 'updateInfo', 'com.lite.api.controller.publics.auth.AuthController#updateInfo/auth/updateInfo', '/api/auth/updateInfo', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (117, 'getPreSignedDownloadUrl', 'com.lite.api.controller.publics.cos.CosController#getPreSignedDownloadUrl/cos/download/preSigned', '/api/cos/download/preSigned', 'GET', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (118, 'uploadAvatar', 'com.lite.api.controller.publics.cos.CosController#uploadAvatar/cos/upload/avatar', '/api/cos/upload/avatar', 'PUT', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (119, 'uploadPrivateFile', 'com.lite.api.controller.publics.cos.CosController#uploadPrivateFile/cos/upload/private', '/api/cos/upload/private', 'PUT', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (120, 'uploadPublicFile', 'com.lite.api.controller.publics.cos.CosController#uploadPublicFile/cos/upload/public', '/api/cos/upload/public', 'PUT', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (121, 'sendAuthMail', 'com.lite.api.controller.publics.mail.MailController#sendAuthMail/mail/sendAuthMail', '/api/mail/sendAuthMail', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (122, 'sendHtmlMail', 'com.lite.api.controller.publics.mail.MailController#sendHtmlMail/mail/sendHtmlMail', '/api/mail/sendHtmlMail', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (123, 'sendSimpleTextMail', 'com.lite.api.controller.publics.mail.MailController#sendSimpleTextMail/mail/sendSimpleMail', '/api/mail/sendSimpleMail', 'POST', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, 0);
INSERT INTO `info_sys_api` VALUES (124, 'getArticleUpdateInfo', 'com.lite.api.controller.publics.bussiness.article.ArticleController#getArticleUpdateInfo/article/getArticleUpdateInfo', '/api/article/getArticleUpdateInfo', 'GET', 0, 1, '2022-09-04 17:14:38', '2022-09-04 17:14:38', 0, 0);
INSERT INTO `info_sys_api` VALUES (125, 'updateArticle', 'com.lite.api.controller.publics.bussiness.article.ArticleController#updateArticle/article/updateArticle', '/api/article/updateArticle', 'POST', 0, 1, '2022-09-04 20:43:15', '2022-09-04 20:43:15', 0, 0);

-- ----------------------------
-- Table structure for info_sys_controller
-- ----------------------------
DROP TABLE IF EXISTS `info_sys_controller`;
CREATE TABLE `info_sys_controller`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模块url',
  `simple_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `package_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包名',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '完整名称',
  `permission_id` int(0) NULL DEFAULT NULL COMMENT '权限ID',
  `enable` int(0) NULL DEFAULT NULL COMMENT '是否启用',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统controller信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_sys_controller
-- ----------------------------
INSERT INTO `info_sys_controller` VALUES (21, '', 'HelloWorldController', 'com.lite.api.controller', 'com.lite.api.controller.HelloWorldController', 1, 1, '2022-09-01 20:36:32', '2022-09-01 20:36:32', '0', 0);
INSERT INTO `info_sys_controller` VALUES (22, '/auth', 'AuthController', 'com.lite.api.controller.auth', 'com.lite.api.controller.auth.AuthController', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', '0', 1);
INSERT INTO `info_sys_controller` VALUES (23, '/article', 'ArticleController', 'com.lite.api.controller.bussiness.article', 'com.lite.api.controller.bussiness.article.ArticleController', 0, 1, '2022-09-01 20:36:32', '2022-09-03 20:32:09', '0', 1);
INSERT INTO `info_sys_controller` VALUES (24, '/cos', 'CosController', 'com.lite.api.controller.cos', 'com.lite.api.controller.cos.CosController', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', '0', 1);
INSERT INTO `info_sys_controller` VALUES (25, '', 'LiteBlogExceptionController', 'com.lite.api.controller.error', 'com.lite.api.controller.error.LiteBlogExceptionController', 0, 1, '2022-09-01 20:36:32', '2022-09-03 13:30:10', '0', 1);
INSERT INTO `info_sys_controller` VALUES (26, '/mail', 'MailController', 'com.lite.api.controller.mail', 'com.lite.api.controller.mail.MailController', 0, 1, '2022-09-01 20:36:32', '2022-09-04 11:03:33', '0', 1);
INSERT INTO `info_sys_controller` VALUES (27, '${springfox.documentation.open-api.v3.path:/v3/api-docs}', 'OpenApiControllerWebMvc', 'springfox.documentation.oas.web', 'springfox.documentation.oas.web.OpenApiControllerWebMvc', 0, 1, '2022-09-03 12:14:10', '2022-09-03 13:30:10', '0', 1);
INSERT INTO `info_sys_controller` VALUES (28, '${springfox.documentation.swagger-ui.base-url:}/swagger-resources', 'ApiResourceController', 'springfox.documentation.swagger.web', 'springfox.documentation.swagger.web.ApiResourceController', 0, 1, '2022-09-03 12:14:10', '2022-09-03 13:30:10', '0', 1);
INSERT INTO `info_sys_controller` VALUES (29, '${springfox.documentation.swagger.v2.path:/v2/api-docs}', 'Swagger2ControllerWebMvc', 'springfox.documentation.swagger2.web', 'springfox.documentation.swagger2.web.Swagger2ControllerWebMvc', 0, 1, '2022-09-03 12:14:10', '2022-09-03 13:30:10', '0', 1);
INSERT INTO `info_sys_controller` VALUES (30, '', 'LiteBlogExceptionController', 'com.lite.api.controller.admin.error', 'com.lite.api.controller.admin.error.LiteBlogExceptionController', 0, 1, '2022-09-03 13:30:10', '2022-09-03 13:30:10', '0', 0);
INSERT INTO `info_sys_controller` VALUES (31, '', 'MultipleOpenApiWebMvcResource', 'org.springdoc.webmvc.api', 'org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource', 0, 1, '2022-09-03 13:30:10', '2022-09-03 13:44:43', '0', 1);
INSERT INTO `info_sys_controller` VALUES (32, '', 'OpenApiWebMvcResource', 'org.springdoc.webmvc.api', 'org.springdoc.webmvc.api.OpenApiWebMvcResource', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', '0', 1);
INSERT INTO `info_sys_controller` VALUES (33, '', 'SwaggerConfigResource', 'org.springdoc.webmvc.ui', 'org.springdoc.webmvc.ui.SwaggerConfigResource', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', '0', 1);
INSERT INTO `info_sys_controller` VALUES (34, '', 'SwaggerWelcomeWebMvc', 'org.springdoc.webmvc.ui', 'org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc', 0, 1, '2022-09-03 13:30:10', '2022-09-03 19:47:11', '0', 1);
INSERT INTO `info_sys_controller` VALUES (35, '/article', 'ArticleController', 'com.lite.api.controller.publics.bussiness.article', 'com.lite.api.controller.publics.bussiness.article.ArticleController', 0, 1, '2022-09-03 20:32:09', '2022-09-03 20:32:09', '0', 0);
INSERT INTO `info_sys_controller` VALUES (36, '/auth', 'AuthController', 'com.lite.api.controller.publics.auth', 'com.lite.api.controller.publics.auth.AuthController', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', '0', 0);
INSERT INTO `info_sys_controller` VALUES (37, '/cos', 'CosController', 'com.lite.api.controller.publics.cos', 'com.lite.api.controller.publics.cos.CosController', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', '0', 0);
INSERT INTO `info_sys_controller` VALUES (38, '/mail', 'MailController', 'com.lite.api.controller.publics.mail', 'com.lite.api.controller.publics.mail.MailController', 0, 1, '2022-09-04 11:03:33', '2022-09-04 11:03:33', '0', 0);

-- ----------------------------
-- Table structure for info_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `info_sys_permission`;
CREATE TABLE `info_sys_permission`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` int(0) NOT NULL COMMENT '权限代码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限描述',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户权限信息表' ROW_FORMAT = Dynamic;

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
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(0) NOT NULL COMMENT '性别',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户的自我介绍',
  `birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '出生日期',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属公司',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职业',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除,0代表未删除，1代表已删除',
  `permission_id` int(0) NULL DEFAULT 0 COMMENT '用户权限',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_user
-- ----------------------------
INSERT INTO `info_user` VALUES (11, '123', '123', NULL, '123', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `info_user` VALUES (13, '2633565580@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', '2633565580', 'wyh', 1, 'i am a handsome', NULL, NULL, NULL, '2022-08-26 15:00:06', '2022-09-01 17:14:07', 0, 0, NULL);

-- ----------------------------
-- Table structure for relation_article_category
-- ----------------------------
DROP TABLE IF EXISTS `relation_article_category`;
CREATE TABLE `relation_article_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(0) UNSIGNED NOT NULL COMMENT '文件ID',
  `category_id` bigint(0) UNSIGNED NOT NULL COMMENT '分类ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_article_id_uindex`(`id`) USING BTREE,
  INDEX `articleKey`(`article_id`) USING BTREE,
  INDEX `categoryKey`(`category_id`) USING BTREE,
  CONSTRAINT `articleKey` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `categoryKey` FOREIGN KEY (`category_id`) REFERENCES `info_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-分类关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_article_category
-- ----------------------------

-- ----------------------------
-- Table structure for relation_article_draft
-- ----------------------------
DROP TABLE IF EXISTS `relation_article_draft`;
CREATE TABLE `relation_article_draft`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(0) UNSIGNED NOT NULL COMMENT '模块ID',
  `draft_id` bigint(0) UNSIGNED NOT NULL COMMENT 'URLID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-草稿关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_article_draft
-- ----------------------------
INSERT INTO `relation_article_draft` VALUES (5, 38, 38, '2022-09-04 21:26:59', '2022-09-04 21:26:59', 0, 0);
INSERT INTO `relation_article_draft` VALUES (6, 38, 39, '2022-09-04 21:28:30', '2022-09-04 21:28:30', 0, 0);

-- ----------------------------
-- Table structure for relation_article_user
-- ----------------------------
DROP TABLE IF EXISTS `relation_article_user`;
CREATE TABLE `relation_article_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(0) UNSIGNED NOT NULL COMMENT '文章ID',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article`(`article_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `article` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_article_user
-- ----------------------------
INSERT INTO `relation_article_user` VALUES (2, 38, 13, '2022-09-04 21:26:58', '2022-09-04 21:26:58', 0, 0);
INSERT INTO `relation_article_user` VALUES (3, 39, 13, '2022-09-04 21:28:30', '2022-09-04 21:28:30', 0, 0);

-- ----------------------------
-- Table structure for relation_category_label
-- ----------------------------
DROP TABLE IF EXISTS `relation_category_label`;
CREATE TABLE `relation_category_label`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` bigint(0) UNSIGNED NOT NULL COMMENT '模块ID',
  `label_id` bigint(0) UNSIGNED NOT NULL COMMENT 'URLID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `relation_category_label_fk_categoryKey`(`category_id`) USING BTREE,
  INDEX `relation_category_label_fk_labelKey`(`label_id`) USING BTREE,
  CONSTRAINT `relation_category_label_fk_categoryKey` FOREIGN KEY (`category_id`) REFERENCES `info_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relation_category_label_fk_labelKey` FOREIGN KEY (`label_id`) REFERENCES `info_label` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '分类-标签关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_category_label
-- ----------------------------
INSERT INTO `relation_category_label` VALUES (1, 1, 1, '2022-09-03 21:25:59', '2022-09-03 21:25:59', 0, '0');
INSERT INTO `relation_category_label` VALUES (2, 1, 7, '2022-09-03 21:26:40', '2022-09-03 21:26:40', 0, '0');
INSERT INTO `relation_category_label` VALUES (3, 1, 8, '2022-09-03 21:27:02', '2022-09-03 21:27:02', 0, '0');
INSERT INTO `relation_category_label` VALUES (4, 1, 3, '2022-09-03 21:27:19', '2022-09-03 21:27:19', 0, '0');
INSERT INTO `relation_category_label` VALUES (5, 2, 2, '2022-09-03 21:29:18', '2022-09-03 21:29:18', 0, '0');
INSERT INTO `relation_category_label` VALUES (6, 2, 9, '2022-09-03 21:29:38', '2022-09-03 21:29:38', 0, '0');
INSERT INTO `relation_category_label` VALUES (7, 2, 4, '2022-09-03 21:29:57', '2022-09-03 21:29:57', 0, '0');
INSERT INTO `relation_category_label` VALUES (8, 2, 13, '2022-09-03 21:30:05', '2022-09-03 21:30:05', 0, '0');
INSERT INTO `relation_category_label` VALUES (9, 7, 10, '2022-09-03 21:32:17', '2022-09-03 21:32:17', 0, '0');
INSERT INTO `relation_category_label` VALUES (10, 7, 11, '2022-09-03 21:32:25', '2022-09-03 21:32:25', 0, '0');
INSERT INTO `relation_category_label` VALUES (11, 7, 12, '2022-09-03 21:32:38', '2022-09-03 21:32:38', 0, '0');
INSERT INTO `relation_category_label` VALUES (12, 8, 5, '2022-09-03 21:37:52', '2022-09-03 21:37:52', 0, '0');

-- ----------------------------
-- Table structure for relation_comment_article
-- ----------------------------
DROP TABLE IF EXISTS `relation_comment_article`;
CREATE TABLE `relation_comment_article`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `comment_id` bigint(0) UNSIGNED NOT NULL COMMENT '评论ID',
  `article_id` bigint(0) UNSIGNED NOT NULL COMMENT '文章ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
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
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `comment_id` bigint(0) UNSIGNED NOT NULL COMMENT '评论ID',
  `reply_id` bigint(0) UNSIGNED NOT NULL COMMENT '回复ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `replyKey`(`reply_id`) USING BTREE,
  INDEX `reply_commentKey`(`comment_id`) USING BTREE,
  CONSTRAINT `reply_commentKey` FOREIGN KEY (`comment_id`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `replyKey` FOREIGN KEY (`reply_id`) REFERENCES `info_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论-回复关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for relation_comment_user
-- ----------------------------
DROP TABLE IF EXISTS `relation_comment_user`;
CREATE TABLE `relation_comment_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `comment_id` bigint(0) UNSIGNED NOT NULL COMMENT '评论ID',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
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
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `label_id` bigint(0) UNSIGNED NOT NULL COMMENT '标签ID',
  `article_id` bigint(0) UNSIGNED NOT NULL COMMENT '文章ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `label_article_id_uindex`(`id`) USING BTREE,
  INDEX `labelKey`(`label_id`) USING BTREE,
  INDEX `label_articleKey`(`article_id`) USING BTREE,
  CONSTRAINT `label_articleKey` FOREIGN KEY (`article_id`) REFERENCES `info_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `labelKey` FOREIGN KEY (`label_id`) REFERENCES `info_label` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标签-文章关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_label_article
-- ----------------------------

-- ----------------------------
-- Table structure for relation_share_user
-- ----------------------------
DROP TABLE IF EXISTS `relation_share_user`;
CREATE TABLE `relation_share_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键iD',
  `share_id` bigint(0) UNSIGNED NOT NULL COMMENT '分享ID',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shareKey`(`share_id`) USING BTREE,
  INDEX `share_userKey`(`user_id`) USING BTREE,
  CONSTRAINT `share_userKey` FOREIGN KEY (`user_id`) REFERENCES `info_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shareKey` FOREIGN KEY (`share_id`) REFERENCES `info_share` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_share_user
-- ----------------------------

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
  INDEX `api_key`(`api_id`) USING BTREE,
  CONSTRAINT `api_key` FOREIGN KEY (`api_id`) REFERENCES `info_sys_api` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `controller_key` FOREIGN KEY (`controller_id`) REFERENCES `info_sys_controller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 174 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'API-Controller关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_sys_api_controller
-- ----------------------------
INSERT INTO `relation_sys_api_controller` VALUES (125, 21, 77, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (126, 21, 78, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (127, 21, 79, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (128, 22, 80, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (129, 22, 81, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (130, 22, 82, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (131, 22, 83, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (132, 22, 84, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (133, 22, 85, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (134, 23, 86, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (135, 23, 87, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (136, 23, 88, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (137, 23, 89, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (138, 24, 90, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (139, 24, 91, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (140, 24, 92, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (141, 24, 93, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (142, 25, 94, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (143, 26, 95, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (144, 26, 96, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (145, 26, 97, '2022-09-01 20:36:32', '2022-09-01 20:36:32', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (146, 23, 98, '2022-09-03 11:29:25', '2022-09-03 11:29:25', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (147, 30, 99, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (148, 31, 100, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (149, 31, 101, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (150, 32, 102, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (151, 32, 103, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (152, 33, 104, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (153, 34, 105, '2022-09-03 13:30:10', '2022-09-03 13:30:10', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (154, 35, 106, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (155, 35, 107, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (156, 35, 108, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (157, 35, 109, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (158, 35, 110, '2022-09-03 20:32:09', '2022-09-03 20:32:09', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (159, 36, 111, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (160, 36, 112, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (161, 36, 113, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (162, 36, 114, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (163, 36, 115, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (164, 36, 116, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (165, 37, 117, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (166, 37, 118, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (167, 37, 119, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (168, 37, 120, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (169, 38, 121, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (170, 38, 122, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (171, 38, 123, '2022-09-04 11:03:33', '2022-09-04 11:03:33', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (172, 35, 124, '2022-09-04 17:14:38', '2022-09-04 17:14:38', 0, '0');
INSERT INTO `relation_sys_api_controller` VALUES (173, 35, 125, '2022-09-04 20:43:15', '2022-09-04 20:43:15', 0, '0');

-- ----------------------------
-- Table structure for relation_user_fans
-- ----------------------------
DROP TABLE IF EXISTS `relation_user_fans`;
CREATE TABLE `relation_user_fans`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `fans_id` bigint(0) UNSIGNED NOT NULL COMMENT '粉丝ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除\r\n',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
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
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `label_id` bigint(0) UNSIGNED NOT NULL COMMENT '标签ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT 0 COMMENT '乐观锁',
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
