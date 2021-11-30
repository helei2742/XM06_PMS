/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : pms

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 30/11/2021 21:48:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `id` int NULL DEFAULT NULL,
  `task_name` int NULL DEFAULT NULL,
  `creator_id` int NULL DEFAULT NULL,
  `group_id` int NULL DEFAULT NULL,
  `description` int NULL DEFAULT NULL,
  `deadline` int NULL DEFAULT NULL,
  `create_date` int NULL DEFAULT NULL,
  `is_valid` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_task
-- ----------------------------

-- ----------------------------
-- Table structure for xm06_t_conference
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_conference`;
CREATE TABLE `xm06_t_conference`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `conference_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `conference_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `conference_date` datetime NOT NULL,
  `hour_long` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator_id` int NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `group_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_conference
-- ----------------------------
INSERT INTO `xm06_t_conference` VALUES (1, '小组团会', '商量团支部组建事项', '2021-11-23 23:45:52', '1h', '二基楼', NULL, NULL, 16);
INSERT INTO `xm06_t_conference` VALUES (4, 'test会议', '我们要test以下会议添加', '2021-11-30 22:53:00', '2h', '二基楼', 27, '2021-11-30 14:53:26', 16);
INSERT INTO `xm06_t_conference` VALUES (9, '1111', '我们要test以下会议添加', '2021-11-30 20:33:00', '2h', '二基楼', 27, '2021-11-30 20:33:50', 16);

-- ----------------------------
-- Table structure for xm06_t_group
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_group`;
CREATE TABLE `xm06_t_group`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `described` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager_id` int NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_group
-- ----------------------------
INSERT INTO `xm06_t_group` VALUES (1, 'group1', '\'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\'', 2, NULL, NULL, NULL);
INSERT INTO `xm06_t_group` VALUES (3, 'group2', NULL, 2, b'1', '2021-09-22 21:21:13', '2021-09-22 21:21:13');
INSERT INTO `xm06_t_group` VALUES (4, 'group3', '123', 2, b'1', '2021-09-24 16:05:17', '2021-09-24 16:05:17');
INSERT INTO `xm06_t_group` VALUES (5, 'group4', NULL, 2, b'1', '2021-09-24 16:08:11', '2021-09-24 16:08:11');
INSERT INTO `xm06_t_group` VALUES (6, 'group11', NULL, 2, b'1', '2021-09-24 16:10:27', '2021-09-24 16:10:27');
INSERT INTO `xm06_t_group` VALUES (8, 'group200', NULL, 2, b'1', '2021-09-24 17:30:16', '2021-09-24 17:30:16');
INSERT INTO `xm06_t_group` VALUES (9, 'group201', NULL, 5, b'1', '2021-09-24 17:35:01', '2021-09-24 17:35:01');
INSERT INTO `xm06_t_group` VALUES (12, 'group206', NULL, 5, b'1', '2021-09-24 17:43:59', '2021-09-24 17:43:59');
INSERT INTO `xm06_t_group` VALUES (16, 'test3', NULL, 7, b'1', '2021-09-26 12:30:11', '2021-09-26 12:30:11');
INSERT INTO `xm06_t_group` VALUES (17, 'qwerty的队伍', '123421', 16, b'1', '2021-09-29 09:52:21', '2021-09-29 09:52:21');
INSERT INTO `xm06_t_group` VALUES (18, 'qwertty', 'hahah', 7, b'1', '2021-09-29 13:52:34', '2021-09-29 13:52:34');
INSERT INTO `xm06_t_group` VALUES (19, 'qwertty3', 'hahah', 7, b'1', '2021-09-29 13:52:38', '2021-09-29 13:52:38');
INSERT INTO `xm06_t_group` VALUES (21, '你好', '1', 18, b'1', '2021-09-29 14:32:18', '2021-09-29 14:32:18');
INSERT INTO `xm06_t_group` VALUES (22, 'qwertyu', 'chesi', 7, b'1', '2021-10-01 15:58:10', '2021-10-01 15:58:10');
INSERT INTO `xm06_t_group` VALUES (23, 'dthovghIOAGvh', 'hahahhahah', 7, b'1', '2021-10-12 15:27:00', '2021-10-12 15:27:00');
INSERT INTO `xm06_t_group` VALUES (24, '123', NULL, 12, b'1', '2021-10-15 20:35:48', '2021-10-15 20:35:48');
INSERT INTO `xm06_t_group` VALUES (25, 'dfghjk', 'adaw', 7, b'1', '2021-11-14 17:38:30', '2021-11-14 17:38:30');
INSERT INTO `xm06_t_group` VALUES (26, '测试小组创建校验', NULL, 7, b'1', '2021-11-16 14:43:40', '2021-11-16 14:43:40');
INSERT INTO `xm06_t_group` VALUES (27, 'helei2742的小组', 'helei2742的小组\nhelei2742的小组\nhelei2742的小组', 26, b'1', '2021-11-20 14:54:36', '2021-11-20 14:54:36');
INSERT INTO `xm06_t_group` VALUES (28, 'dawdsadwsdsd', 'sd', 7, b'1', '2021-11-20 19:32:31', '2021-11-20 19:32:31');

-- ----------------------------
-- Table structure for xm06_t_inform
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_inform`;
CREATE TABLE `xm06_t_inform`  (
  `id` bigint NOT NULL,
  `group_id` int NULL DEFAULT NULL,
  `send_user_id` int NULL DEFAULT NULL,
  `send_date` datetime NULL DEFAULT NULL,
  `message` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_inform
-- ----------------------------
INSERT INTO `xm06_t_inform` VALUES (1637582807369, 16, 7, '2021-11-22 20:06:47', 'lkmqslDSK');
INSERT INTO `xm06_t_inform` VALUES (1637582856750, 16, 26, '2021-11-22 20:07:36', 'WADWADWADAWD');
INSERT INTO `xm06_t_inform` VALUES (1637582887635, 16, 7, '2021-11-22 20:08:07', 'SFWSD');
INSERT INTO `xm06_t_inform` VALUES (1637582902602, 16, 26, '2021-11-22 20:08:22', 'AWDWAS');
INSERT INTO `xm06_t_inform` VALUES (1637583795301, 16, 2, '2021-11-22 20:23:15', 'awjbdnkjwabnj');
INSERT INTO `xm06_t_inform` VALUES (1637583952212, 16, 7, '2021-11-22 20:25:52', 'dkajndlawa');
INSERT INTO `xm06_t_inform` VALUES (1637584161586, 16, 7, '2021-11-22 20:29:21', 'bhだkwhbdhかwbddc');
INSERT INTO `xm06_t_inform` VALUES (1637584205337, 16, 2, '2021-11-22 20:30:05', 'wadsa');
INSERT INTO `xm06_t_inform` VALUES (1637584323983, 16, 7, '2021-11-22 20:32:03', 'jkbkjｊｋ');
INSERT INTO `xm06_t_inform` VALUES (1637584377174, 16, 7, '2021-11-22 20:32:57', 'm、mン');
INSERT INTO `xm06_t_inform` VALUES (1637584433583, 16, 2, '2021-11-22 20:33:53', 'ｋんｌｋんｋｌ');
INSERT INTO `xm06_t_inform` VALUES (1637584451220, 16, 2, '2021-11-22 20:34:11', 'ｊｋｂんｊｋ');
INSERT INTO `xm06_t_inform` VALUES (1637584457159, 16, 7, '2021-11-22 20:34:17', 'ｊｋんｋｊｋ');
INSERT INTO `xm06_t_inform` VALUES (1637584564980, 16, 7, '2021-11-22 20:36:04', 'ｋｍｌｋｍｌｋ4');
INSERT INTO `xm06_t_inform` VALUES (1637584581755, 16, 2, '2021-11-22 20:36:21', 'ｌｋｍｌｋんｌｋ');
INSERT INTO `xm06_t_inform` VALUES (1637584630853, 16, 2, '2021-11-22 20:37:10', 'ｌｋんｌ');
INSERT INTO `xm06_t_inform` VALUES (1637584743385, 16, 2, '2021-11-22 20:39:03', 'awdawda');
INSERT INTO `xm06_t_inform` VALUES (1637584754297, 16, 2, '2021-11-22 20:39:14', '????????');
INSERT INTO `xm06_t_inform` VALUES (1637584778913, 16, 7, '2021-11-22 20:39:38', '????????????');
INSERT INTO `xm06_t_inform` VALUES (1637585381073, 16, 7, '2021-11-22 20:49:41', 'wdad');
INSERT INTO `xm06_t_inform` VALUES (1637585408113, 16, 2, '2021-11-22 20:50:08', 'wddwd');
INSERT INTO `xm06_t_inform` VALUES (1637585415423, 16, 7, '2021-11-22 20:50:15', 'wdwadwad');
INSERT INTO `xm06_t_inform` VALUES (1637585428252, 16, 2, '2021-11-22 20:50:28', 'wwwwwwwww');
INSERT INTO `xm06_t_inform` VALUES (1637585443752, 16, 7, '2021-11-22 20:50:43', '>>>>>>>>>>>>>>>>>>>>');
INSERT INTO `xm06_t_inform` VALUES (1637585472635, 16, 2, '2021-11-22 20:51:12', 'wdwd');
INSERT INTO `xm06_t_inform` VALUES (1637585541341, 16, 2, '2021-11-22 20:52:21', 'wdwd');
INSERT INTO `xm06_t_inform` VALUES (1637585915273, 16, 7, '2021-11-22 20:58:35', 'hjvbjkhbc s');
INSERT INTO `xm06_t_inform` VALUES (1637585937569, 16, 2, '2021-11-22 20:58:57', 'awdawd');
INSERT INTO `xm06_t_inform` VALUES (1637585964274, 16, 2, '2021-11-22 20:59:24', 'dwadw');
INSERT INTO `xm06_t_inform` VALUES (1637586206235, 16, 7, '2021-11-22 21:03:26', 'lkmkl');
INSERT INTO `xm06_t_inform` VALUES (1637586238303, 16, 2, '2021-11-22 21:03:58', 'mkml');
INSERT INTO `xm06_t_inform` VALUES (1637586304737, 16, 7, '2021-11-22 21:05:04', 'l,;,;');
INSERT INTO `xm06_t_inform` VALUES (1637586327517, 16, 2, '2021-11-22 21:05:27', 'kmlkmk');
INSERT INTO `xm06_t_inform` VALUES (1637586360388, 16, 2, '2021-11-22 21:06:00', 'l,mlk');
INSERT INTO `xm06_t_inform` VALUES (1637586852242, 16, 2, '2021-11-22 21:14:12', 'jkbnkhbk');
INSERT INTO `xm06_t_inform` VALUES (1637586867211, 16, 7, '2021-11-22 21:14:27', 'bjknjk');
INSERT INTO `xm06_t_inform` VALUES (1637586871465, 16, 7, '2021-11-22 21:14:31', 'jbjkbkj');
INSERT INTO `xm06_t_inform` VALUES (1637586877820, 16, 7, '2021-11-22 21:14:37', 'wocaonima d ');
INSERT INTO `xm06_t_inform` VALUES (1637586886053, 16, 2, '2021-11-22 21:14:45', 'xdaw??');
INSERT INTO `xm06_t_inform` VALUES (1637586889202, 16, 2, '2021-11-22 21:14:49', 'jadnwk');
INSERT INTO `xm06_t_inform` VALUES (1637586894039, 16, 7, '2021-11-22 21:14:53', 'jkawndkawjd');
INSERT INTO `xm06_t_inform` VALUES (1637586918497, 16, 2, '2021-11-22 21:15:18', 'chishiba');
INSERT INTO `xm06_t_inform` VALUES (1637633766304, 16, 7, '2021-11-23 10:16:06', 'awdawdwa');
INSERT INTO `xm06_t_inform` VALUES (1637633771574, 16, 7, '2021-11-23 10:16:11', 'Njwdkjasnjkwnjk');
INSERT INTO `xm06_t_inform` VALUES (1637633775398, 16, 7, '2021-11-23 10:16:15', 'dwadawsd');
INSERT INTO `xm06_t_inform` VALUES (1637633781818, 16, 7, '2021-11-23 10:16:21', 'lovepower\n');
INSERT INTO `xm06_t_inform` VALUES (1637633786004, 16, 7, '2021-11-23 10:16:25', 'sjmalnmdwiok');
INSERT INTO `xm06_t_inform` VALUES (1637633804838, 16, 7, '2021-11-23 10:16:44', '及喀麦隆昆明、\n');
INSERT INTO `xm06_t_inform` VALUES (1637633894695, 16, 7, '2021-11-23 10:18:14', 'kjuhbfoawjws\n');
INSERT INTO `xm06_t_inform` VALUES (1637633900394, 16, 7, '2021-11-23 10:18:20', 'law,d;lwmd');
INSERT INTO `xm06_t_inform` VALUES (1637633952251, 16, 26, '2021-11-23 10:19:12', 'wo该尼玛的\n');
INSERT INTO `xm06_t_inform` VALUES (1637633958655, 16, 26, '2021-11-23 10:19:18', '真的傻逼');
INSERT INTO `xm06_t_inform` VALUES (1637633963254, 16, 26, '2021-11-23 10:19:23', 'cnm');
INSERT INTO `xm06_t_inform` VALUES (1637633999662, 16, 26, '2021-11-23 10:19:59', 'jnjjknkj');
INSERT INTO `xm06_t_inform` VALUES (1637634020974, 16, 26, '2021-11-23 10:20:20', 'kjbndakjbdwn');
INSERT INTO `xm06_t_inform` VALUES (1637635911472, 16, 7, '2021-11-23 10:51:51', 'dwad');
INSERT INTO `xm06_t_inform` VALUES (1637635923469, 16, 7, '2021-11-23 10:52:03', 'wadwadwad');
INSERT INTO `xm06_t_inform` VALUES (1637635923961, 16, 7, '2021-11-23 10:52:03', NULL);
INSERT INTO `xm06_t_inform` VALUES (1637635946474, 16, 7, '2021-11-23 10:52:26', 'jkfnkajndjaw');
INSERT INTO `xm06_t_inform` VALUES (1637635947358, 16, 7, '2021-11-23 10:52:27', NULL);
INSERT INTO `xm06_t_inform` VALUES (1637635948954, 16, 7, '2021-11-23 10:52:28', 'awdaw');
INSERT INTO `xm06_t_inform` VALUES (1637635950264, 16, 7, '2021-11-23 10:52:30', 'wad');
INSERT INTO `xm06_t_inform` VALUES (1637638977683, 16, 7, '2021-11-23 11:42:57', 'hjvbjh');
INSERT INTO `xm06_t_inform` VALUES (1637639028062, 16, 7, '2021-11-23 11:43:47', 'mjbjk');
INSERT INTO `xm06_t_inform` VALUES (1637639056807, 16, 7, '2021-11-23 11:44:16', 'mn ');
INSERT INTO `xm06_t_inform` VALUES (1637639178507, 16, 7, '2021-11-23 11:46:18', 'klnjk');
INSERT INTO `xm06_t_inform` VALUES (1637639237004, 16, 7, '2021-11-23 11:47:16', 'wada');
INSERT INTO `xm06_t_inform` VALUES (1637639239923, 16, 7, '2021-11-23 11:47:19', 'awdwadawdaw');
INSERT INTO `xm06_t_inform` VALUES (1637639244757, 16, 7, '2021-11-23 11:47:24', 'wad');
INSERT INTO `xm06_t_inform` VALUES (1637639247184, 16, 7, '2021-11-23 11:47:27', 'wadwad');
INSERT INTO `xm06_t_inform` VALUES (1637639335956, 16, 7, '2021-11-23 11:48:55', 'awdw');
INSERT INTO `xm06_t_inform` VALUES (1637639528856, 16, 7, '2021-11-23 11:52:08', 'awdwa');
INSERT INTO `xm06_t_inform` VALUES (1637639562356, 16, 7, '2021-11-23 11:52:42', 'awdaw');
INSERT INTO `xm06_t_inform` VALUES (1637640483736, 16, 7, '2021-11-23 12:08:03', 'kkm lk');
INSERT INTO `xm06_t_inform` VALUES (1637640487382, 16, 7, '2021-11-23 12:08:07', ',, ml');
INSERT INTO `xm06_t_inform` VALUES (1637640548513, 16, 7, '2021-11-23 12:09:08', 'wadw');
INSERT INTO `xm06_t_inform` VALUES (1637640934493, 16, 7, '2021-11-23 12:15:34', '>>');
INSERT INTO `xm06_t_inform` VALUES (1637649828637, 16, 7, '2021-11-23 14:43:48', 'kwdnajkdn');
INSERT INTO `xm06_t_inform` VALUES (1637649848774, 16, 7, '2021-11-23 14:44:08', '???');
INSERT INTO `xm06_t_inform` VALUES (1637649857443, 16, 7, '2021-11-23 14:44:17', 's');
INSERT INTO `xm06_t_inform` VALUES (1637650160756, 16, 7, '2021-11-23 14:49:20', '我是真 的cao了 你ma');
INSERT INTO `xm06_t_inform` VALUES (1637650442178, 16, 2, '2021-11-23 14:54:02', '干你码的');
INSERT INTO `xm06_t_inform` VALUES (1637650456696, 16, 7, '2021-11-23 14:54:16', '？？');
INSERT INTO `xm06_t_inform` VALUES (1637650461983, 16, 2, '2021-11-23 14:54:21', 'dawd');
INSERT INTO `xm06_t_inform` VALUES (1637650485416, 16, 2, '2021-11-23 14:54:45', 'wddd');
INSERT INTO `xm06_t_inform` VALUES (1637650585679, 16, 2, '2021-11-23 14:56:25', 'dawd');
INSERT INTO `xm06_t_inform` VALUES (1637650768829, 16, 7, '2021-11-23 14:59:28', '我草你妈妈的');
INSERT INTO `xm06_t_inform` VALUES (1637650784171, 16, 2, '2021-11-23 14:59:44', '??你敢pi我瓜');
INSERT INTO `xm06_t_inform` VALUES (1637650810153, 16, 7, '2021-11-23 15:00:10', '我就pi了咋的');
INSERT INTO `xm06_t_inform` VALUES (1637650827333, 16, 7, '2021-11-23 15:00:27', 'shabi4\n\n我日你 萘胺');
INSERT INTO `xm06_t_inform` VALUES (1637651144906, 3, 7, '2021-11-23 15:05:44', '》》》》');
INSERT INTO `xm06_t_inform` VALUES (1637651184797, 28, 7, '2021-11-23 15:06:24', '？？');
INSERT INTO `xm06_t_inform` VALUES (1637651172147, 3, 2, '2021-11-23 15:06:12', '》》》');
INSERT INTO `xm06_t_inform` VALUES (1637651282028, 3, 2, '2021-11-23 15:08:01', 'ni ma maidwn');
INSERT INTO `xm06_t_inform` VALUES (1637652779938, 16, 7, '2021-11-23 15:32:59', '??????????????WAdwadsljndklaw');
INSERT INTO `xm06_t_inform` VALUES (1637652780779, 16, 7, '2021-11-23 15:33:00', NULL);
INSERT INTO `xm06_t_inform` VALUES (1637652782686, 16, 7, '2021-11-23 15:33:02', 'wadwadwa');
INSERT INTO `xm06_t_inform` VALUES (1637652994964, 16, 2, '2021-11-23 15:36:34', 'awdawddwad');
INSERT INTO `xm06_t_inform` VALUES (1637654995172, 16, 7, '2021-11-23 16:09:55', 'no');
INSERT INTO `xm06_t_inform` VALUES (1637655027530, 16, 7, '2021-11-23 16:10:27', 'wo cao nima de a');
INSERT INTO `xm06_t_inform` VALUES (1637655122597, 16, 7, '2021-11-23 16:12:02', 'awdwadwadaw');
INSERT INTO `xm06_t_inform` VALUES (1637655122643, 16, 7, '2021-11-23 16:12:02', NULL);
INSERT INTO `xm06_t_inform` VALUES (1637655125600, 16, 7, '2021-11-23 16:12:05', 'awdklmwald');
INSERT INTO `xm06_t_inform` VALUES (1637655130006, 16, 7, '2021-11-23 16:12:09', 'iaojdoawoidawoidno');

-- ----------------------------
-- Table structure for xm06_t_memo
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_memo`;
CREATE TABLE `xm06_t_memo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `memo` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stale_date` datetime NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `is_finish` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_memo
-- ----------------------------
INSERT INTO `xm06_t_memo` VALUES (3, 7, '完成测试3', '2003-12-28 08:12:09', '2001-12-28 08:12:09', b'1');
INSERT INTO `xm06_t_memo` VALUES (4, 7, '完成测试3', '2004-12-28 08:12:09', '2001-12-28 08:12:09', b'1');
INSERT INTO `xm06_t_memo` VALUES (6, 7, '完成测试3', '2006-12-28 08:12:09', '2001-12-28 08:12:09', b'1');
INSERT INTO `xm06_t_memo` VALUES (7, 7, '完成测试3', '2006-12-28 08:12:09', '2001-12-28 08:12:09', b'1');
INSERT INTO `xm06_t_memo` VALUES (10, 7, '完成测试3', '2006-12-28 08:12:09', '2001-12-28 08:12:09', b'1');
INSERT INTO `xm06_t_memo` VALUES (12, 7, 'test2', '2021-10-22 15:09:22', '2021-09-30 15:09:27', b'1');
INSERT INTO `xm06_t_memo` VALUES (13, 7, 'hahhahah', '2021-10-09 00:00:00', '2021-09-30 15:30:41', b'1');
INSERT INTO `xm06_t_memo` VALUES (15, 7, 'ceshi', '2021-10-30 00:00:00', '2021-10-01 18:53:22', b'1');
INSERT INTO `xm06_t_memo` VALUES (17, 26, '测试添加便签', '2021-11-27 00:00:00', '2021-11-20 14:58:28', b'0');
INSERT INTO `xm06_t_memo` VALUES (19, 7, 'caonima', '2021-11-30 00:00:00', '2021-11-24 15:00:54', b'1');
INSERT INTO `xm06_t_memo` VALUES (20, 7, '？？？？', '2021-11-30 00:00:00', '2021-11-24 15:03:57', b'1');
INSERT INTO `xm06_t_memo` VALUES (21, 7, 'dwadaW', '2021-11-19 00:00:00', '2021-11-24 15:36:08', b'1');
INSERT INTO `xm06_t_memo` VALUES (22, 7, 'wdawd', '2021-11-10 00:00:00', '2021-11-24 15:39:34', b'0');
INSERT INTO `xm06_t_memo` VALUES (23, 7, 'awdwad', '2021-12-24 00:00:00', '2021-11-24 15:40:14', b'0');

-- ----------------------------
-- Table structure for xm06_t_not_read_inform
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_not_read_inform`;
CREATE TABLE `xm06_t_not_read_inform`  (
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  `inform_id` bigint NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_not_read_inform
-- ----------------------------
INSERT INTO `xm06_t_not_read_inform` VALUES (1, 1, 1);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637582807369);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637582807369);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637582856750);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637582856750);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637582887635);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637582902602);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637582887635);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637582902602);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637583795301);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637583795301);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637583952212);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637583952212);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584161586);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584161586);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584205337);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584205337);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584323983);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584377174);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584323983);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584377174);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584433583);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584433583);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584451220);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584457159);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584451220);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584457159);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584564980);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584581755);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584564980);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584581755);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584630853);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584630853);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584743385);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584754297);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637584778913);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584743385);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584754297);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637584778913);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585381073);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585381073);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585408113);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585415423);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585428252);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585443752);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585408113);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585415423);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585428252);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585443752);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585472635);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585472635);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585541341);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585541341);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585915273);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585937569);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585915273);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585937569);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637585964274);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637585964274);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586206235);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586238303);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586206235);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586238303);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586304737);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586327517);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586304737);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586327517);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586360388);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586360388);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586852242);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586867211);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586871465);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586877820);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586886053);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586889202);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586894039);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586852242);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586867211);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586871465);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586877820);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586886053);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586889202);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586894039);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637586918497);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637586918497);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633766304);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633771574);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633775398);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633781818);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633786004);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633804838);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633766304);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633771574);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633775398);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633781818);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633786004);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633804838);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633894695);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633900394);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633894695);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633900394);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633952251);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633958655);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633963254);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637633999662);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633952251);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633958655);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633963254);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637633999662);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637634020974);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637634020974);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635911472);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635911472);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635911472);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635923469);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635923961);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635946474);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635947358);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635948954);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637635950264);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635923469);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635923961);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635946474);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635947358);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635948954);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637635950264);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635923469);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635923961);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635946474);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635947358);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635948954);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637635950264);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637638977683);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637638977683);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637638977683);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639028062);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639028062);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639028062);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639056807);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639056807);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639056807);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639178507);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639178507);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639178507);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639237004);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639239923);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639244757);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639247184);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639237004);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639239923);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639244757);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639247184);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639237004);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639239923);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639244757);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639247184);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639335956);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639335956);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639335956);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639528856);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637639562356);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639528856);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637639562356);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639528856);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637639562356);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637640483736);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637640487382);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637640483736);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637640487382);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637640483736);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637640487382);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637640548513);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637640548513);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637640548513);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637640934493);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637640934493);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637640934493);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637649828637);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637649828637);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637649828637);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637649848774);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637649857443);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637649848774);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637649857443);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637649848774);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637649857443);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650160756);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650160756);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650160756);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650442178);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650456696);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650461983);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650485416);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650442178);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650456696);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650461983);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650485416);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650442178);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650456696);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650461983);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650485416);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650585679);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650585679);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650585679);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650768829);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650784171);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650768829);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650784171);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650768829);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650784171);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650810153);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637650827333);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650810153);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637650827333);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650810153);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637650827333);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637652779938);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637652779938);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637652779938);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637652780779);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637652782686);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637652780779);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637652782686);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637652780779);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637652782686);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637652994964);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637652994964);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637652994964);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637654995172);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637654995172);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637654995172);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637655027530);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637655027530);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637655027530);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637655122597);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637655122643);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637655125600);
INSERT INTO `xm06_t_not_read_inform` VALUES (6, 16, 1637655130006);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637655122597);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637655122643);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637655125600);
INSERT INTO `xm06_t_not_read_inform` VALUES (26, 16, 1637655130006);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637655122597);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637655122643);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637655125600);
INSERT INTO `xm06_t_not_read_inform` VALUES (4, 16, 1637655130006);

-- ----------------------------
-- Table structure for xm06_t_project
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_project`;
CREATE TABLE `xm06_t_project`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_desc` varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator_id` int NULL DEFAULT NULL,
  `group_id` int NULL DEFAULT NULL,
  `completion_degree` double NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  `is_public` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_project
-- ----------------------------
INSERT INTO `xm06_t_project` VALUES (1, '测试创建项目', NULL, 7, 16, 0, '2021-10-27 21:27:14', '2021-10-27 21:27:14', b'1', NULL);
INSERT INTO `xm06_t_project` VALUES (2, '测试创建项目是否重名', NULL, 7, 16, 0, '2021-10-27 21:41:19', '2021-10-27 21:41:19', b'1', NULL);
INSERT INTO `xm06_t_project` VALUES (3, 'dawda', 'd', 7, NULL, 0, '2021-11-03 10:44:11', '2021-11-03 10:44:11', b'1', b'0');
INSERT INTO `xm06_t_project` VALUES (5, 'dawdadwad', 'd', 7, NULL, 0, '2021-11-03 10:49:12', '2021-11-03 10:49:12', b'1', b'0');
INSERT INTO `xm06_t_project` VALUES (6, 'dawdadw', 'd', 7, NULL, 0, '2021-11-03 10:55:34', '2021-11-03 10:55:34', b'1', b'1');
INSERT INTO `xm06_t_project` VALUES (7, 'dawd', 'd', 7, NULL, 0, '2021-11-03 11:00:05', '2021-11-03 11:00:05', b'1', b'1');
INSERT INTO `xm06_t_project` VALUES (8, '很长描述的项目', '很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目', 7, NULL, 0.8800000000745, '2021-11-03 17:16:30', '2021-11-03 17:16:30', b'1', b'1');
INSERT INTO `xm06_t_project` VALUES (9, 'dwadA', '测试换行\n测试换行\n测试换行', 7, NULL, 0.6999999862164259, '2021-11-05 10:46:17', '2021-11-05 10:46:17', b'1', b'0');
INSERT INTO `xm06_t_project` VALUES (10, '测试创建项目2', '测试创建项目\n测试创建项目', 7, NULL, 0, '2021-11-20 15:08:15', '2021-11-20 15:08:15', b'1', b'1');
INSERT INTO `xm06_t_project` VALUES (11, 'awdawd', 'ww', 7, NULL, 0, '2021-11-20 19:08:43', '2021-11-20 19:08:43', b'1', b'0');

-- ----------------------------
-- Table structure for xm06_t_project_group
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_project_group`;
CREATE TABLE `xm06_t_project_group`  (
  `project_id` int NOT NULL,
  `group_id` int NOT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_project_group
-- ----------------------------
INSERT INTO `xm06_t_project_group` VALUES (2, 1, '2021-11-02 19:42:32', b'1');
INSERT INTO `xm06_t_project_group` VALUES (2, 16, NULL, NULL);
INSERT INTO `xm06_t_project_group` VALUES (2, 7, NULL, NULL);
INSERT INTO `xm06_t_project_group` VALUES (1, 16, NULL, NULL);
INSERT INTO `xm06_t_project_group` VALUES (1, 13, '2021-11-03 10:44:11', b'1');
INSERT INTO `xm06_t_project_group` VALUES (1, 23, '2021-11-03 10:44:11', b'1');
INSERT INTO `xm06_t_project_group` VALUES (5, 13, '2021-11-03 10:49:12', b'1');
INSERT INTO `xm06_t_project_group` VALUES (5, 23, '2021-11-03 10:49:12', b'1');
INSERT INTO `xm06_t_project_group` VALUES (6, 13, '2021-11-03 10:55:34', b'1');
INSERT INTO `xm06_t_project_group` VALUES (6, 23, '2021-11-03 10:55:34', b'1');
INSERT INTO `xm06_t_project_group` VALUES (7, 19, '2021-11-03 11:00:05', b'1');
INSERT INTO `xm06_t_project_group` VALUES (7, 22, '2021-11-03 11:00:05', b'1');
INSERT INTO `xm06_t_project_group` VALUES (8, 13, '2021-11-03 17:16:30', b'1');
INSERT INTO `xm06_t_project_group` VALUES (8, 16, '2021-11-03 17:16:30', b'1');
INSERT INTO `xm06_t_project_group` VALUES (8, 22, '2021-11-03 17:16:30', b'1');
INSERT INTO `xm06_t_project_group` VALUES (8, 23, '2021-11-03 17:16:30', b'1');
INSERT INTO `xm06_t_project_group` VALUES (9, 13, '2021-11-05 10:46:17', b'1');
INSERT INTO `xm06_t_project_group` VALUES (10, 16, '2021-11-20 15:08:15', b'1');
INSERT INTO `xm06_t_project_group` VALUES (11, 16, '2021-11-20 19:08:43', b'1');

-- ----------------------------
-- Table structure for xm06_t_project_update_record
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_project_update_record`;
CREATE TABLE `xm06_t_project_update_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `project_id` int NULL DEFAULT NULL,
  `submit_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `submit_degree` float(5, 2) NULL DEFAULT NULL,
  `upload_file_src` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_project_update_record
-- ----------------------------
INSERT INTO `xm06_t_project_update_record` VALUES (1, 7, 8, '', 100.00, 'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\project_8\\10163714122981596.11-2019141460044-何磊.docx', '2021-11-17 17:27:09', '2021-11-17 17:27:09', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (2, 7, 9, '测试提交项目更新', 0.51, 'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\project_9\\XM06_项目管理子项目周报_第6周163720200255733.doc', '2021-11-18 10:20:02', '2021-11-18 10:20:02', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (3, 7, 9, '测试换行\n测试\nces', 0.06, NULL, '2021-11-18 21:06:36', '2021-11-18 21:06:36', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (4, 7, 9, '啦啦啦啦啦', 0.03, NULL, '2021-11-18 22:03:05', '2021-11-18 22:03:05', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (5, 7, 9, 'dasdwa', 0.01, NULL, '2021-11-18 22:04:54', '2021-11-18 22:04:54', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (6, 7, 9, 'awdwadsa', 0.03, NULL, '2021-11-18 22:05:25', '2021-11-18 22:05:25', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (7, 7, 9, 'awdsadwa', 0.06, NULL, '2021-11-18 22:05:39', '2021-11-18 22:05:39', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (8, 26, 8, '我提交了一个文件', 0.05, 'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\helei2742\\project_8\\高级语言程序设计II成果报告书16373918435224.doc', '2021-11-20 15:04:03', '2021-11-20 15:04:03', b'1');
INSERT INTO `xm06_t_project_update_record` VALUES (9, 7, 8, '', 0.03, NULL, '2021-11-23 17:54:27', '2021-11-23 17:54:27', b'1');

-- ----------------------------
-- Table structure for xm06_t_role
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_role`;
CREATE TABLE `xm06_t_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_remarker` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_role
-- ----------------------------

-- ----------------------------
-- Table structure for xm06_t_task
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_task`;
CREATE TABLE `xm06_t_task`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator_id` int NULL DEFAULT NULL,
  `group_id` int NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deadline` datetime NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_task
-- ----------------------------
INSERT INTO `xm06_t_task` VALUES (1, '测试发部任务', 7, 16, NULL, '2021-10-02 11:27:54', '2021-10-02 11:29:49', b'1');
INSERT INTO `xm06_t_task` VALUES (2, '测试发部任务', 7, 16, NULL, '2021-10-02 11:27:54', '2021-10-02 11:32:25', b'1');
INSERT INTO `xm06_t_task` VALUES (3, '测试发部任务', 7, 16, NULL, '2021-10-02 11:27:54', '2021-10-02 11:32:26', b'1');
INSERT INTO `xm06_t_task` VALUES (4, '测试发部任务', 7, 16, NULL, '2021-10-02 11:27:54', '2021-10-02 11:32:27', b'1');
INSERT INTO `xm06_t_task` VALUES (5, '测试发部任务', 7, 16, NULL, '2021-10-02 11:27:54', '2021-10-02 11:32:28', b'1');
INSERT INTO `xm06_t_task` VALUES (6, '测试发部任务', 7, 16, NULL, '2021-10-02 11:27:54', '2021-10-02 11:32:29', b'1');
INSERT INTO `xm06_t_task` VALUES (7, '测试发部任务', 7, 16, '这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述', '2021-10-02 11:27:54', '2021-10-02 11:34:12', b'1');
INSERT INTO `xm06_t_task` VALUES (8, 'cvbn', 7, 16, 'cvbnm,', '2021-10-03 12:00:00', '2021-10-03 14:45:13', b'1');
INSERT INTO `xm06_t_task` VALUES (9, '测试任务描述换行', 7, 13, 'WADAD\nWADWAD\nAWDAWD\nWADAWDAWD\n        WADAWDAWDAWD\nWD', '2021-10-04 12:00:00', '2021-10-04 10:45:46', b'1');
INSERT INTO `xm06_t_task` VALUES (10, '测试日期', 7, 13, 'awd', '2022-10-07 12:00:00', '2021-10-04 11:28:48', b'1');
INSERT INTO `xm06_t_task` VALUES (11, 'hhhhhhhhhhhhhhhhh', 7, 13, '测试测试', '2021-11-19 12:00:00', '2021-10-27 14:32:25', b'1');
INSERT INTO `xm06_t_task` VALUES (12, '测试发布任务', 7, 16, '测试发布任务\n测试发布任务测试发布任务\n测试发布任务\n测试发布任务', '2021-11-30 12:00:00', '2021-11-20 15:01:20', b'1');
INSERT INTO `xm06_t_task` VALUES (13, 'dawd', 7, 13, 'wdaw', '2021-11-06 12:00:00', '2021-11-20 19:08:17', b'1');
INSERT INTO `xm06_t_task` VALUES (14, 'wadwa', 7, 16, 'asdwas', '2021-11-23 12:00:00', '2021-11-20 19:11:28', b'1');

-- ----------------------------
-- Table structure for xm06_t_task_record
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_task_record`;
CREATE TABLE `xm06_t_task_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `submit_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_task_record
-- ----------------------------
INSERT INTO `xm06_t_task_record` VALUES (1, 1, 7, '哈哈哈', 'D:\\test\\##QMxQzM##wNxEzN2gDMxQzMzYTM\\项目建议书163341086711980.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (2, 10, 7, 'vfcytc', 'D:\\test\\##wNxQzM##gN2EjM5kzMxQzMzYTM\\项目计划书（研发）163341399216788.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (3, 10, 7, 'xdfcgvhbj', 'D:\\test\\##wNxQzM##QNxAjN4ADNxQzMzYTM\\项目预算表163341408601584.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (4, 10, 7, 'awda', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目建议书163341422485290.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (5, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项评审表163341425152956.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (6, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341567079527.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (7, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341588502290.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (8, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163341600469260.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (9, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341611054987.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (10, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163341664124663.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (11, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (12, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (13, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (14, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (15, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (16, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (17, 10, 7, 'xsdcfvgbhnj', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (18, 10, 7, 'daw', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (19, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341825658032.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (20, 10, 7, '测试有上传文件', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163341830178679.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (21, 10, 7, '', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (22, 10, 7, '再次测试上传文件', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目建议书163341924983237.doc', NULL);
INSERT INTO `xm06_t_task_record` VALUES (23, 10, 7, '再次测试不上传文件', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (24, 9, 7, 'hasda', NULL, NULL);
INSERT INTO `xm06_t_task_record` VALUES (25, 7, 7, '测试显示提交的内容\n测试显示提交的内容\n测试显示提交的内容\n测试显示提交的内容\n测试显示提交的内容\n   \n   测试显示提交的内容', NULL, '2021-10-06 13:26:46');
INSERT INTO `xm06_t_task_record` VALUES (26, 7, 7, '测试文件上传后下载', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\top163350143232323.png', '2021-10-06 14:23:52');
INSERT INTO `xm06_t_task_record` VALUES (27, 7, 7, 'hahahahahah', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\功能清单163350364602684.docx', '2021-10-06 15:00:46');
INSERT INTO `xm06_t_task_record` VALUES (28, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\sql163358798831719.txt', '2021-10-07 14:26:28');
INSERT INTO `xm06_t_task_record` VALUES (29, 7, 7, 'adawD', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\实验4-个人报告-何磊-2019141460044163358804598373.docx', '2021-10-07 14:27:26');
INSERT INTO `xm06_t_task_record` VALUES (30, 7, 7, '1111111111111111', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第5个实验163358864510254.doc', '2021-10-07 14:37:25');
INSERT INTO `xm06_t_task_record` VALUES (31, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第14个实验163358918720184.doc', '2021-10-07 14:46:27');
INSERT INTO `xm06_t_task_record` VALUES (32, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第1个实验163358932510856.doc', '2021-10-07 14:48:45');
INSERT INTO `xm06_t_task_record` VALUES (33, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第3个实验163359044582723.docx', '2021-10-07 15:07:25');
INSERT INTO `xm06_t_task_record` VALUES (34, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第16个实验16335907501961.doc', '2021-10-07 15:12:30');
INSERT INTO `xm06_t_task_record` VALUES (35, 7, 7, '', NULL, '2021-10-07 15:20:31');
INSERT INTO `xm06_t_task_record` VALUES (36, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第8个实验163359163125871.doc', '2021-10-07 15:27:11');
INSERT INTO `xm06_t_task_record` VALUES (37, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第8个实验163359176637692.doc', '2021-10-07 15:29:26');
INSERT INTO `xm06_t_task_record` VALUES (38, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第2个实验163359178763790.docx', '2021-10-07 15:29:47');
INSERT INTO `xm06_t_task_record` VALUES (39, 7, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第14个实验163359210568913.doc', '2021-10-07 15:35:05');
INSERT INTO `xm06_t_task_record` VALUES (40, 7, 7, '测试不带文件', NULL, '2021-10-08 09:09:07');
INSERT INTO `xm06_t_task_record` VALUES (41, 7, 7, '测试提交日期', NULL, '2021-10-08 09:13:31');
INSERT INTO `xm06_t_task_record` VALUES (42, 8, 7, '测试日期', NULL, '2021-10-08 09:19:45');
INSERT INTO `xm06_t_task_record` VALUES (43, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第9个实验163365823820345.doc', '2021-10-08 09:57:18');
INSERT INTO `xm06_t_task_record` VALUES (44, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第8个实验163365863006061.doc', '2021-10-08 10:03:50');
INSERT INTO `xm06_t_task_record` VALUES (45, 9, 7, '测试上传进度', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第4个实验163365954885220.dot', '2021-10-08 10:19:08');
INSERT INTO `xm06_t_task_record` VALUES (46, 9, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第9个实验163365974562297.doc', '2021-10-08 10:22:25');
INSERT INTO `xm06_t_task_record` VALUES (47, 10, 7, '', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第9个实验163365982562846.doc', '2021-10-08 10:23:45');
INSERT INTO `xm06_t_task_record` VALUES (48, 8, 7, 'ce', 'D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163429037551960.doc', '2021-10-15 17:32:55');
INSERT INTO `xm06_t_task_record` VALUES (49, 1, 2, '', 'D:\\test\\fljWO2AZfOtVocSHmJo3IA==\\top163471284686857.png', '2021-10-20 14:54:06');
INSERT INTO `xm06_t_task_record` VALUES (50, 11, 7, '', 'D:\\yjykfsj2021_xw_XM06\\heleidage666\\uploadfile\\要整理的算法单163659982978912.txt', '2021-11-11 11:03:49');
INSERT INTO `xm06_t_task_record` VALUES (51, 11, 7, '', 'D:\\yjykfsj2021_xw_XM06\\uploadfile\\heleidage666\\task\\20211111_173633163662893485554.mp4', '2021-11-11 19:08:54');
INSERT INTO `xm06_t_task_record` VALUES (52, 10, 7, 'lalal', 'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\task\\10163732025376770.11-2019141460044-何磊.docx', '2021-11-19 19:10:53');
INSERT INTO `xm06_t_task_record` VALUES (53, 12, 26, '发了个图片', 'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\helei2742\\task\\yjtp16373917142452.png', '2021-11-20 15:01:54');
INSERT INTO `xm06_t_task_record` VALUES (54, 12, 7, 'awdw', NULL, '2021-11-20 19:11:53');
INSERT INTO `xm06_t_task_record` VALUES (55, 12, 7, '', 'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\task\\Snipaste_2021-11-16_15-30-37163772375464927.png', '2021-11-24 11:15:54');

-- ----------------------------
-- Table structure for xm06_t_user
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_user`;
CREATE TABLE `xm06_t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_user
-- ----------------------------
INSERT INTO `xm06_t_user` VALUES (2, 'lisi123', '4QrcOUm6Wau+VuBX8g+IPg==', '李四', NULL, NULL, b'1', '2021-09-22 20:39:23', '2021-09-22 20:40:32');
INSERT INTO `xm06_t_user` VALUES (3, 'test1', '4QrcOUm6Wau+VuBX8g+IPg==', '', '', '19141273392', b'1', '2021-09-24 08:46:39', '2021-09-24 08:46:39');
INSERT INTO `xm06_t_user` VALUES (4, 'test2', 'itP6xsazUoSZ00fZJEQ6uw==', 'hhh', 'wada', '2', b'1', '2021-09-24 08:54:07', '2021-09-24 08:54:07');
INSERT INTO `xm06_t_user` VALUES (5, 'test3', '4QrcOUm6Wau+VuBX8g+IPg==', '', '', '', b'1', '2021-09-24 08:57:40', '2021-09-24 08:57:40');
INSERT INTO `xm06_t_user` VALUES (6, 'zhangsan123', '4QrcOUm6Wau+VuBX8g+IPg==', '', '', '', b'1', '2021-09-24 09:10:07', '2021-09-24 09:10:07');
INSERT INTO `xm06_t_user` VALUES (7, 'heleidage666', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '124214', b'1', '2021-09-25 17:37:57', '2021-09-29 14:25:50');
INSERT INTO `xm06_t_user` VALUES (8, 'helei1234', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 18:06:26', '2021-09-28 18:06:26');
INSERT INTO `xm06_t_user` VALUES (9, 'helei12345', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 18:12:41', '2021-09-28 18:12:41');
INSERT INTO `xm06_t_user` VALUES (10, 'heleidage1234', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 18:16:53', '2021-09-28 18:16:53');
INSERT INTO `xm06_t_user` VALUES (11, 'test888', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 18:21:42', '2021-09-28 18:21:42');
INSERT INTO `xm06_t_user` VALUES (12, 'heleidage090', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 18:23:25', '2021-09-28 18:23:25');
INSERT INTO `xm06_t_user` VALUES (13, 'heleidage999', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 18:23:32', '2021-09-28 18:23:32');
INSERT INTO `xm06_t_user` VALUES (14, 'qwer', 'gdyb21LQTcIANtvYMT7QVQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 19:37:41', '2021-09-28 19:37:41');
INSERT INTO `xm06_t_user` VALUES (15, 'qwert', 'gdyb21LQTcIANtvYMT7QVQ==', '何磊', '914577981@qq.com', '', b'0', '2021-09-28 19:54:10', '2021-09-28 19:54:10');
INSERT INTO `xm06_t_user` VALUES (16, 'qwerty', 'gdyb21LQTcIANtvYMT7QVQ==', '何磊', '914577981@qq.com', '', b'1', '2021-09-28 19:54:52', '2021-09-28 19:54:52');
INSERT INTO `xm06_t_user` VALUES (18, 'wojiaohelei', '4QrcOUm6Wau+VuBX8g+IPg==', '1', '914577981@qq.com', '123445', b'1', '2021-09-29 14:29:28', '2021-09-29 14:29:28');
INSERT INTO `xm06_t_user` VALUES (19, 'deplo', '4QrcOUm6Wau+VuBX8g+IPg==', '何磊', '914577981@qq.com', '', b'1', '2021-10-15 17:26:28', '2021-10-15 17:26:28');
INSERT INTO `xm06_t_user` VALUES (24, 'erctvbyuhnij', 'EKIjsIq9qLMGgQzOYYqh3g==', '遵义市', '914577981@qq.com', '', b'0', '2021-11-14 17:20:17', '2021-11-14 17:20:17');
INSERT INTO `xm06_t_user` VALUES (26, 'helei2742', 'xgkUKIhdWWIXaBdgiCk9lQ==', '何磊', '914577981@qq.com', '', b'1', '2021-11-20 14:50:15', '2021-11-20 14:50:15');
INSERT INTO `xm06_t_user` VALUES (27, '月华似练只待迢迢江水', 'rQsIC5O9koeIVX57B2T6oA==', '吴茂俊', '1226398122@qq.com', '18789513633', b'1', '2021-11-24 19:20:58', '2021-11-24 19:20:58');

-- ----------------------------
-- Table structure for xm06_t_user_faceinfo
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_user_faceinfo`;
CREATE TABLE `xm06_t_user_faceinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `face_yml_src` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `face_img_folder_src` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `is_valid` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_user_faceinfo
-- ----------------------------
INSERT INTO `xm06_t_user_faceinfo` VALUES (4, 26, 'D:/webapps/teach/yjykfsj2021/XM06_upload\\userface\\helei2742\\model/face_model_helei2742.yml', 'D:/webapps/teach/yjykfsj2021/XM06_upload\\userface\\helei2742\\faceimg', '2021-11-20 15:11:28', '2021-11-20 18:47:18', b'1');
INSERT INTO `xm06_t_user_faceinfo` VALUES (5, 7, 'D:/webapps/teach/yjykfsj2021/XM06_upload\\userface\\heleidage666\\model/face_model_heleidage666.yml', 'D:/webapps/teach/yjykfsj2021/XM06_upload\\userface\\heleidage666\\faceimg', '2021-11-23 18:56:17', '2021-11-23 19:01:45', b'1');

-- ----------------------------
-- Table structure for xm06_t_user_group
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_user_group`;
CREATE TABLE `xm06_t_user_group`  (
  `user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_user_group
-- ----------------------------
INSERT INTO `xm06_t_user_group` VALUES ('1', '1');
INSERT INTO `xm06_t_user_group` VALUES ('1', '4');
INSERT INTO `xm06_t_user_group` VALUES ('1', '3');
INSERT INTO `xm06_t_user_group` VALUES ('2', '1');
INSERT INTO `xm06_t_user_group` VALUES ('3', '1');
INSERT INTO `xm06_t_user_group` VALUES ('4', '1');
INSERT INTO `xm06_t_user_group` VALUES ('5', '1');
INSERT INTO `xm06_t_user_group` VALUES ('2', '3');
INSERT INTO `xm06_t_user_group` VALUES ('1', '6');
INSERT INTO `xm06_t_user_group` VALUES ('1', '8');
INSERT INTO `xm06_t_user_group` VALUES ('1', '9');
INSERT INTO `xm06_t_user_group` VALUES ('1', '10');
INSERT INTO `xm06_t_user_group` VALUES ('1', '11');
INSERT INTO `xm06_t_user_group` VALUES ('1', '12');
INSERT INTO `xm06_t_user_group` VALUES ('1', '5');
INSERT INTO `xm06_t_user_group` VALUES ('2', '4');
INSERT INTO `xm06_t_user_group` VALUES ('2', '5');
INSERT INTO `xm06_t_user_group` VALUES ('2', '6');
INSERT INTO `xm06_t_user_group` VALUES ('2', '8');
INSERT INTO `xm06_t_user_group` VALUES ('5', '12');
INSERT INTO `xm06_t_user_group` VALUES ('5', '10');
INSERT INTO `xm06_t_user_group` VALUES ('5', '9');
INSERT INTO `xm06_t_user_group` VALUES ('7', '13');
INSERT INTO `xm06_t_user_group` VALUES ('7', '14');
INSERT INTO `xm06_t_user_group` VALUES ('7', '15');
INSERT INTO `xm06_t_user_group` VALUES ('7', '16');
INSERT INTO `xm06_t_user_group` VALUES ('4', '16');
INSERT INTO `xm06_t_user_group` VALUES ('1', '16');
INSERT INTO `xm06_t_user_group` VALUES ('2', '16');
INSERT INTO `xm06_t_user_group` VALUES ('6', '16');
INSERT INTO `xm06_t_user_group` VALUES ('16', '17');
INSERT INTO `xm06_t_user_group` VALUES ('7', '10');
INSERT INTO `xm06_t_user_group` VALUES ('7', '11');
INSERT INTO `xm06_t_user_group` VALUES ('7', '12');
INSERT INTO `xm06_t_user_group` VALUES ('7', '18');
INSERT INTO `xm06_t_user_group` VALUES ('7', '19');
INSERT INTO `xm06_t_user_group` VALUES ('7', '20');
INSERT INTO `xm06_t_user_group` VALUES ('18', '21');
INSERT INTO `xm06_t_user_group` VALUES ('7', '22');
INSERT INTO `xm06_t_user_group` VALUES ('7', '23');
INSERT INTO `xm06_t_user_group` VALUES ('12', '24');
INSERT INTO `xm06_t_user_group` VALUES ('7', '25');
INSERT INTO `xm06_t_user_group` VALUES ('7', '26');
INSERT INTO `xm06_t_user_group` VALUES ('26', '27');
INSERT INTO `xm06_t_user_group` VALUES ('26', '16');
INSERT INTO `xm06_t_user_group` VALUES ('7', '28');
INSERT INTO `xm06_t_user_group` VALUES ('7', '27');
INSERT INTO `xm06_t_user_group` VALUES ('7', '3');
INSERT INTO `xm06_t_user_group` VALUES ('27', '16');

-- ----------------------------
-- Table structure for xm06_t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `xm06_t_user_role`;
CREATE TABLE `xm06_t_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xm06_t_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
