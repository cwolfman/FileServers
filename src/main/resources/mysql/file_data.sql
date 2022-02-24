SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `file_data`;

CREATE TABLE `file_data`
(
    `id`          BIGINT(0)                                               NOT NULL AUTO_INCREMENT COMMENT 'id',
    `size`        INT(0)                                                  NOT NULL COMMENT '文件大小',
    `type`        VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
    `old_name`    VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件原始名',
    `new_name`    VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件新名',
    `file_path`   VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
    `create_time` DATETIME(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time` DATETIME(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

INSERT INTO `file_data`
VALUES (20, 12, 'multipart/form-data', '1.txt', 'tb2047f9adcd4ci3b4d7506fd4052c88',
        './src/main/resources/file/20220222/tb2047f9adcd4ci3b4d7506fd4052c88.txt', '2022-02-22 09:32:29',
        '2022-02-22 09:32:29');

SET FOREIGN_KEY_CHECKS = 1;
