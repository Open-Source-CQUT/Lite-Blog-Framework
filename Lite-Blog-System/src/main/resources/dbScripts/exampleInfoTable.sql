-- 此sql文件可以用于快速创建一个模板表，从而减少大量的重复性工作
DROP TABLE IF EXISTS `example`;
CREATE TABLE `example`  (
                            `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                            `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                            `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                            `deleted` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
                            `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '乐观锁',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
