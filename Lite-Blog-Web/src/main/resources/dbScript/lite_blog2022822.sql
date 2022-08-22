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

 Date: 22/08/2022 13:33:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件名',
  `original_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原始文件名',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '桶',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件访问url',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件类型，即后缀',
  `upload_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上传时间',
  `uploader` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作员',
  `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `access` int(11) NULL DEFAULT NULL COMMENT '访问权限，1是公有，0是私有',
  PRIMARY KEY (`file_name`) USING BTREE,
  UNIQUE INDEX `file_file_name_uindex`(`file_name`) USING BTREE,
  INDEX `file_user_mail_fk`(`uploader`) USING BTREE,
  CONSTRAINT `file_user_mail_fk` FOREIGN KEY (`uploader`) REFERENCES `user` (`mail`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1661091132901/e406db78a7d9d8660265a6688063080b7b783ea2/d2f753d6f59e4b40bae27438401c150c.jpg', 'girl.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/1661091132901/e406db78a7d9d8660265a6688063080b7b783ea2/d2f753d6f59e4b40bae27438401c150c.jpg', '.jpg', '2022-08-21 22:12:12', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('2fba9354655247209a38820dc97e1802.jpg', 'aaa.jpg', 'private-1308755698', 'https://private-1308755698.cos.ap-chongqing.myqcloud.com/2fba9354655247209a38820dc97e1802.jpg', '.jpg', '2022-08-20 21:30:41', '263@qq.com', 0, 0);
INSERT INTO `file` VALUES ('3897d50447604b7ea4dad5c159d0c183.jpg', '123.jpg', 'private-1308755698', 'https://private-1308755698.cos.ap-chongqing.myqcloud.com/3897d50447604b7ea4dad5c159d0c183.jpg', '.jpg', '2022-08-20 17:54:05', '263@qq.com', 0, 0);
INSERT INTO `file` VALUES ('87ac934c671d4b80a4e70c0c1fb15d87.jpg', 'bb.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/87ac934c671d4b80a4e70c0c1fb15d87.jpg', '.jpg', '2022-08-20 21:24:42', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('885a03247ade4afea71431d7f43b3e0a.jpg', 'bb.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/885a03247ade4afea71431d7f43b3e0a.jpg', '.jpg', '2022-08-20 17:46:51', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('964380d446df4ef781a1b45f2a1acec0.jpg', 'bb.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/', '.jpg', '2022-08-20 17:43:59', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('9d252394bca24a37a9e3902164bf7612.xlsx', '21级培训计划.xlsx', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/9d252394bca24a37a9e3902164bf7612.xlsx', '.xlsx', '2022-08-20 21:24:28', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('b7a31f162be547cba31b8107da3ec2e9.jpg', 'aaa.jpg', 'private-1308755698', 'https://private-1308755698.cos.ap-chongqing.myqcloud.com/b7a31f162be547cba31b8107da3ec2e9.jpg', '.jpg', '2022-08-20 21:29:09', '263@qq.com', 0, 0);
INSERT INTO `file` VALUES ('b9eabd01c088412f839b6735d561cff9.jpg', 'aaa.jpg', 'private-1308755698', 'https://private-1308755698.cos.ap-chongqing.myqcloud.com/b9eabd01c088412f839b6735d561cff9.jpg', '.jpg', '2022-08-20 21:25:04', '263@qq.com', 0, 0);
INSERT INTO `file` VALUES ('c40bf58929ec4b5fab895b4637a0f054.jpg', 'a.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/c40bf58929ec4b5fab895b4637a0f054.jpg', '.jpg', '2022-08-21 16:28:39', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('dd5802257cb845419d8abb99adaa4f9a.jpg', 'aaa.jpg', 'private-1308755698', 'https://private-1308755698.cos.ap-chongqing.myqcloud.com/dd5802257cb845419d8abb99adaa4f9a.jpg', '.jpg', '2022-08-20 21:26:13', '263@qq.com', 0, 0);
INSERT INTO `file` VALUES ('eb84f55ac2544fde90c72948ad0445c2.jpg', 'a.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/eb84f55ac2544fde90c72948ad0445c2.jpg', '.jpg', '2022-08-20 21:18:02', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('f160b3714b1d4d178e0c7574e126ba49.jpg', 'bb.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/f160b3714b1d4d178e0c7574e126ba49.jpg', '.jpg', '2022-08-21 16:24:36', '263@qq.com', 0, 1);
INSERT INTO `file` VALUES ('ff459730436c4514a9921e0c2d23a2fc.jpg', 'a.jpg', 'public-1308755698', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/ff459730436c4514a9921e0c2d23a2fc.jpg', '.jpg', '2022-08-20 18:06:35', '263@qq.com', 0, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role` int(11) NOT NULL DEFAULT 0 COMMENT '0-普通,1-管理员,2-超级管理员',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '普通用户' COMMENT '描述',
  PRIMARY KEY (`role`) USING BTREE,
  UNIQUE INDEX `role_role_uindex`(`role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (0, '普通用户');
INSERT INTO `role` VALUES (1, '普通管理');
INSERT INTO `role` VALUES (2, '超级管理');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
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
  PRIMARY KEY (`mail`) USING BTREE,
  UNIQUE INDEX `user_mail_uindex`(`mail`) USING BTREE,
  INDEX `user_role`(`roleId`) USING BTREE,
  CONSTRAINT `user_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`role`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1234568888@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', NULL, 'hsy', 1, NULL, NULL, '2022-08-15 19:00:07', '2022-08-15 19:00:07', 0, 0);
INSERT INTO `user` VALUES ('123456@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', '2633565580', 'hsy', 1, 'i am a handsome', NULL, '2022-08-15 18:56:59', '2022-08-20 21:39:02', 0, NULL);
INSERT INTO `user` VALUES ('2633565580@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', NULL, 'hsy', 1, NULL, NULL, '2022-08-21 15:56:40', '2022-08-21 15:56:40', 0, 0);
INSERT INTO `user` VALUES ('263@qq.com', '20eabe5d64b0e216796e834f52d61fd0b70332fc', 'https://public-1308755698.cos.ap-chongqing.myqcloud.com/c40bf58929ec4b5fab895b4637a0f054.jpg', 'wyh', 1, NULL, NULL, '2022-08-15 18:55:05', '2022-08-21 16:28:39', 0, NULL);
INSERT INTO `user` VALUES ('789@qq.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', NULL, 'hsy', 1, NULL, NULL, '2022-08-16 19:30:35', '2022-08-16 19:30:35', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
