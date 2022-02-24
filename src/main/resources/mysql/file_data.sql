SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_data
-- ----------------------------
DROP TABLE IF EXISTS `file_data`;

CREATE TABLE `file_data`  (
                              `id` BIGINT(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `size` INT(0) NOT NULL COMMENT '文件大小',
                              `type` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
                              `old_name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件原始名',
                              `new_name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件新名',
                              `file_path` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
                              `create_time` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                              `update_time` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of file_data
-- ----------------------------
INSERT INTO `file_data` VALUES (10, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', '264764ed8bec45638896e5655d9373a6', 'd:/file/20211102/264764ed8bec45638896e5655d9373a6.jpg', '2021-11-02 23:42:14', '2021-11-02 23:42:14');
INSERT INTO `file_data` VALUES (11, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', 'a84a39b361ae4c908c95168964e406d4', './src/main/resources/file/20211102/a84a39b361ae4c908c95168964e406d4.jpg', '2021-11-02 23:50:22', '2021-11-02 23:50:22');
INSERT INTO `file_data` VALUES (12, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', 'ed3136858910460b86e368847fe2371f', '$(files.path.upload)/20211102/ed3136858910460b86e368847fe2371f.jpg', '2021-11-02 23:54:35', '2021-11-02 23:54:35');
INSERT INTO `file_data` VALUES (13, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', '7c253cd79ef44e46a4b9d97639fecf23', '$(files.path.upload)/20211102/7c253cd79ef44e46a4b9d97639fecf23.jpg', '2021-11-02 23:55:08', '2021-11-02 23:55:08');
INSERT INTO `file_data` VALUES (14, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', 'b16ad589ad11461bae978ca464367e74', './src/main/resources/file/20211102/b16ad589ad11461bae978ca464367e74.jpg', '2021-11-02 23:56:19', '2021-11-02 23:56:19');
INSERT INTO `file_data` VALUES (15, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', '6691005690874cb194adefadde9c3bed', '$(files.path.upload)/20211102/6691005690874cb194adefadde9c3bed.jpg', '2021-11-02 23:56:55', '2021-11-02 23:56:55');
INSERT INTO `file_data` VALUES (16, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', 'b570f2dded1f48e6ba5e2f1b09fce180', '$(files.path.upload)/20211102/b570f2dded1f48e6ba5e2f1b09fce180.jpg', '2021-11-02 23:57:07', '2021-11-02 23:57:07');
INSERT INTO `file_data` VALUES (17, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', '26c93b96b83d4bd39d472711ceca9bea', './src/main/resources/file/20211102/26c93b96b83d4bd39d472711ceca9bea.jpg', '2021-11-02 23:57:59', '2021-11-02 23:57:59');
INSERT INTO `file_data` VALUES (18, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', 'df0fbc560a194ad0a23957836d384fe3', './src/main/resources/file/20211103/df0fbc560a194ad0a23957836d384fe3.jpg', '2021-11-03 00:02:52', '2021-11-03 00:02:52');
INSERT INTO `file_data` VALUES (19, 21486, 'image/jpeg', 'u=2218838837,1618910374&fm=26&fmt=auto.jpg', '8a7b09742c874e80856a3771d43c2fbc', './src/main/resources/file/20211103/8a7b09742c874e80856a3771d43c2fbc.jpg', '2021-11-03 00:18:32', '2021-11-03 00:18:32');
INSERT INTO `file_data` VALUES (20, 12, 'multipart/form-data', 'd:/1.txt', '826556400f2f4b0aa309b62967441a78', './src/main/resources/file/20211103/826556400f2f4b0aa309b62967441a78.txt', '2021-11-03 09:54:10', '2021-11-03 09:54:10');
INSERT INTO `file_data` VALUES (21, 12, 'multipart/form-data', '1.txt', 'a1f0cb9719594f79a167ef343bd7f071', './src/main/resources/file/20211103/a1f0cb9719594f79a167ef343bd7f071.txt', '2021-11-03 09:55:32', '2021-11-03 09:55:32');
INSERT INTO `file_data` VALUES (22, 12, 'multipart/form-data', '1.txt', 'cc92bce69ee24a969c67d2f57a93ef65', './src/main/resources/file/20211103/cc92bce69ee24a969c67d2f57a93ef65.txt', '2021-11-03 10:16:14', '2021-11-03 10:16:14');
INSERT INTO `file_data` VALUES (23, 12, 'multipart/form-data', '1.txt', '288f2463e23843fabbc2cf5e1d181fb2', './src/main/resources/file/20211103/288f2463e23843fabbc2cf5e1d181fb2.txt', '2021-11-03 10:38:06', '2021-11-03 10:38:06');
INSERT INTO `file_data` VALUES (24, 12, 'multipart/form-data', '1.txt', 'fe8031f9ad8e4cceb4d7806fd4052c80', './src/main/resources/file/20211103/fe8031f9ad8e4cceb4d7806fd4052c80.txt', '2021-11-03 10:55:59', '2021-11-03 10:55:59');

SET FOREIGN_KEY_CHECKS = 1;
