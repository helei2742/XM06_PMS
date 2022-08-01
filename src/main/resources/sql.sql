-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (arm64)
--
-- Host: 127.0.0.1    Database: pms
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `xm06_t_announce`
--

DROP TABLE IF EXISTS `xm06_t_announce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_announce` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '创建用户id',
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `main_body` varchar(10000) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_announce`
--

LOCK TABLES `xm06_t_announce` WRITE;
/*!40000 ALTER TABLE `xm06_t_announce` DISABLE KEYS */;
INSERT INTO `xm06_t_announce` VALUES (65,32,'qsdq','mainbody:mainbo:asdasdasdasdsadadasasasasasasasasasasasasasasasasasasasasasasasasasas',NULL,NULL,NULL),(67,27,'发布公告','mainbody:hsjsjsj','2022-01-15 22:41:57','2022-01-15 22:41:57',_binary '');
/*!40000 ALTER TABLE `xm06_t_announce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_conference`
--

DROP TABLE IF EXISTS `xm06_t_conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_conference` (
  `id` int NOT NULL AUTO_INCREMENT,
  `conference_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `conference_info` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `conference_date` datetime NOT NULL,
  `hour_long` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_conference`
--

LOCK TABLES `xm06_t_conference` WRITE;
/*!40000 ALTER TABLE `xm06_t_conference` DISABLE KEYS */;
INSERT INTO `xm06_t_conference` VALUES (4,'test会议','我们要test一下会议修改','2021-11-30 22:53:00','2h','二基楼',28,'2021-12-17 17:02:01',33),(18,'测试会议','我们要test一下会议添加','2021-12-01 20:42:00','2h','二基楼',28,'2021-12-01 19:09:43',33),(19,'初始化会议','我们要test一下会议添加','2021-12-01 22:52:00','2h','二基楼',28,'2021-12-01 18:52:37',33),(28,'test会议1','我们要test一下会议添加','2022-01-14 16:00:00','0h','二基楼',27,'2021-12-17 15:45:01',33),(29,'野 兽 先 辈 玉 音 大 放 送','让我们来倾听野兽先辈の真言','2022-01-16 11:45:00','114514h','下北泽',28,'2022-01-05 11:55:19',33);
/*!40000 ALTER TABLE `xm06_t_conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_device`
--

DROP TABLE IF EXISTS `xm06_t_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_device` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `device_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `creator_id` int NOT NULL,
  `create_date` datetime NOT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_device`
--

LOCK TABLES `xm06_t_device` WRITE;
/*!40000 ALTER TABLE `xm06_t_device` DISABLE KEYS */;
INSERT INTO `xm06_t_device` VALUES (1,'114514','测试设备',27,'2021-12-16 08:50:29',33),(2,'114514','野兽先辈',28,'2021-12-18 11:38:27',33),(4,'114514','下北泽黑色高级轿车探测器',28,'2021-12-18 11:14:57',33),(5,'114514','下北泽广播',28,'2021-12-18 11:38:00',33);
/*!40000 ALTER TABLE `xm06_t_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_group`
--

DROP TABLE IF EXISTS `xm06_t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '小组名',
  `described` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `manager_id` int DEFAULT NULL COMMENT '创建者id，对应用户表id',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_group`
--

LOCK TABLES `xm06_t_group` WRITE;
/*!40000 ALTER TABLE `xm06_t_group` DISABLE KEYS */;
INSERT INTO `xm06_t_group` VALUES (33,'测试创建小组','测试创建一个小组\n\n小组邀请码   uDEwcS+RDjnGrmjB7YGAgQ==',27,_binary '','2021-12-09 21:47:19','2021-12-27 01:06:16'),(35,'pjj',NULL,31,_binary '','2021-12-11 17:55:51','2021-12-11 17:55:51'),(37,'wwww','123',34,_binary '','2022-01-14 21:15:44','2022-01-14 21:15:44'),(38,'sss','awd',34,_binary '','2022-01-14 21:18:04','2022-01-14 21:18:04'),(39,'test',NULL,39,_binary '','2022-01-20 22:02:05','2022-01-20 22:02:05'),(41,'test007',NULL,44,_binary '','2022-01-20 23:00:11','2022-01-20 23:00:11'),(42,'testtest','w',46,_binary '','2022-01-21 10:32:03','2022-01-21 10:32:03'),(43,'PM100',NULL,47,_binary '','2022-01-21 11:04:20','2022-01-21 11:04:20'),(44,'PM005',NULL,47,_binary '','2022-01-21 11:23:38','2022-01-21 11:23:38');
/*!40000 ALTER TABLE `xm06_t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_inform`
--

DROP TABLE IF EXISTS `xm06_t_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_inform` (
  `id` bigint NOT NULL COMMENT '主键id',
  `group_id` int DEFAULT NULL COMMENT '小组id',
  `send_user_id` int DEFAULT NULL COMMENT '用户id',
  `send_date` datetime DEFAULT NULL COMMENT '发送时间',
  `message` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_inform`
--

LOCK TABLES `xm06_t_inform` WRITE;
/*!40000 ALTER TABLE `xm06_t_inform` DISABLE KEYS */;
INSERT INTO `xm06_t_inform` VALUES (1645419093060,33,27,'2022-02-21 12:51:33','wda');
/*!40000 ALTER TABLE `xm06_t_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_memo`
--

DROP TABLE IF EXISTS `xm06_t_memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_memo` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `memo` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '便签内容',
  `stale_date` datetime DEFAULT NULL COMMENT '截止时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_finish` bit(1) DEFAULT NULL COMMENT '是否完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_memo`
--

LOCK TABLES `xm06_t_memo` WRITE;
/*!40000 ALTER TABLE `xm06_t_memo` DISABLE KEYS */;
/*!40000 ALTER TABLE `xm06_t_memo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_not_read_inform`
--

DROP TABLE IF EXISTS `xm06_t_not_read_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_not_read_inform` (
  `user_id` int NOT NULL COMMENT '用户id',
  `group_id` int NOT NULL COMMENT '小组id',
  `inform_id` bigint NOT NULL COMMENT '通知消息id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_not_read_inform`
--

LOCK TABLES `xm06_t_not_read_inform` WRITE;
/*!40000 ALTER TABLE `xm06_t_not_read_inform` DISABLE KEYS */;
/*!40000 ALTER TABLE `xm06_t_not_read_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_project`
--

DROP TABLE IF EXISTS `xm06_t_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_project` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目名称',
  `project_desc` varchar(1500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目描述',
  `creator_id` int DEFAULT NULL COMMENT '创建用户id',
  `group_id` int DEFAULT NULL COMMENT '小组id',
  `completion_degree` double DEFAULT NULL COMMENT '完成度',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  `is_public` bit(1) DEFAULT NULL COMMENT '是否公开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_project`
--

LOCK TABLES `xm06_t_project` WRITE;
/*!40000 ALTER TABLE `xm06_t_project` DISABLE KEYS */;
INSERT INTO `xm06_t_project` VALUES (16,'创建了个项目','测试创建项目',27,NULL,0.300000004470348,'2021-12-09 21:50:26','2021-12-09 21:50:26',_binary '',_binary ''),(18,'helei222创建的项目','wada',34,NULL,0.100000001490116,'2022-01-14 21:17:50','2022-01-14 21:18:15',_binary '',_binary '');
/*!40000 ALTER TABLE `xm06_t_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_project_group`
--

DROP TABLE IF EXISTS `xm06_t_project_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_project_group` (
  `project_id` int NOT NULL COMMENT '项目id',
  `group_id` int NOT NULL COMMENT '小组id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_project_group`
--

LOCK TABLES `xm06_t_project_group` WRITE;
/*!40000 ALTER TABLE `xm06_t_project_group` DISABLE KEYS */;
INSERT INTO `xm06_t_project_group` VALUES (16,33,'2021-12-09 21:50:26',_binary ''),(18,37,'2022-01-14 21:18:15',_binary ''),(18,38,'2022-01-14 21:18:15',_binary '');
/*!40000 ALTER TABLE `xm06_t_project_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_project_update_record`
--

DROP TABLE IF EXISTS `xm06_t_project_update_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_project_update_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `project_id` int DEFAULT NULL COMMENT '项目id',
  `submit_desc` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提交描述',
  `submit_degree` float(5,2) DEFAULT NULL COMMENT '提交进度',
  `total_degree` float(5,2) DEFAULT NULL COMMENT '提交后项目总进度',
  `upload_file_src` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上传文件资源路径',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_project_update_record`
--

LOCK TABLES `xm06_t_project_update_record` WRITE;
/*!40000 ALTER TABLE `xm06_t_project_update_record` DISABLE KEYS */;
INSERT INTO `xm06_t_project_update_record` VALUES (17,27,16,'提交一点进度，交了个图片',0.10,0.10,'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\project_16\\head_default163905787448858.png','2021-12-09 21:51:14','2021-12-09 21:51:14',_binary ''),(18,28,16,'11111',0.10,0.20,NULL,'2021-12-10 23:22:03','2021-12-10 23:22:03',_binary ''),(19,28,16,'111111',0.10,0.30,'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\zhangsan\\project_16\\1558670787322163914983634821.jpeg','2021-12-10 23:23:56','2021-12-10 23:23:56',_binary ''),(20,34,18,'helei222提交进度',0.10,0.10,'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\helei222\\project_18\\welcome164216633773438.jpg','2022-01-14 21:18:57','2022-01-14 21:18:57',_binary ''),(21,47,19,'PM006',0.12,0.12,NULL,'2022-01-21 11:30:45','2022-01-21 11:30:45',_binary ''),(22,47,19,'PM007',0.11,0.23,'D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\test789\\project_19\\XM06项目管理用户手册164273610932826.pdf','2022-01-21 11:35:09','2022-01-21 11:35:09',_binary ''),(23,47,19,'1',0.03,0.26,NULL,'2022-01-21 11:36:04','2022-01-21 11:36:04',_binary '');
/*!40000 ALTER TABLE `xm06_t_project_update_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_role`
--

DROP TABLE IF EXISTS `xm06_t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `role_remarker` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_role`
--

LOCK TABLES `xm06_t_role` WRITE;
/*!40000 ALTER TABLE `xm06_t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `xm06_t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_system_record`
--

DROP TABLE IF EXISTS `xm06_t_system_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_system_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_count` int DEFAULT '0' COMMENT '单天登录数量',
  `register_count` int DEFAULT '0' COMMENT '单天注册数量',
  `task_submit_count` int DEFAULT '0' COMMENT '单天提交任务数量',
  `task_create_count` int DEFAULT '0' COMMENT '单天创建任务数量',
  `group_create_count` int DEFAULT '0' COMMENT '单天创建小组数量',
  `project_create_count` int DEFAULT '0' COMMENT '单挑创建项目数量',
  `project_update_count` int DEFAULT '0' COMMENT '单挑跟新项目进度数量',
  `record_date` datetime DEFAULT NULL COMMENT '记录日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_system_record`
--

LOCK TABLES `xm06_t_system_record` WRITE;
/*!40000 ALTER TABLE `xm06_t_system_record` DISABLE KEYS */;
INSERT INTO `xm06_t_system_record` VALUES (14,0,0,0,0,0,0,0,'2021-12-03 00:00:00'),(15,0,0,0,0,0,0,0,'2021-12-04 00:00:00'),(16,0,0,0,0,0,0,0,'2021-12-05 10:10:46'),(17,0,0,0,0,0,0,0,'2021-12-06 10:10:53'),(18,0,0,0,0,0,0,0,'2021-12-07 10:10:59'),(19,0,0,0,0,0,0,0,'2021-12-08 10:11:03'),(20,0,0,0,0,0,0,0,'2021-12-09 10:11:08'),(21,0,0,0,0,0,0,0,'2021-12-10 00:00:00'),(22,2,0,0,0,0,0,0,'2021-12-13 00:00:00'),(23,2,0,0,0,0,0,0,'2021-12-14 00:00:00'),(24,0,0,0,0,0,0,0,'2021-12-15 00:00:00'),(25,1,0,0,0,0,0,0,'2021-12-16 00:00:00'),(26,1,0,0,0,0,0,0,'2021-12-17 00:00:00'),(27,1,0,0,0,0,0,0,'2021-12-18 00:00:00'),(28,3,0,0,0,0,0,0,'2021-12-19 00:00:00'),(29,1,0,0,0,0,0,0,'2021-12-20 00:00:00'),(30,1,0,0,0,0,0,0,'2021-12-21 00:00:00'),(31,1,0,0,0,0,0,0,'2021-12-22 00:00:00'),(32,0,0,0,0,0,0,0,'2021-12-23 00:00:00'),(33,0,0,0,0,0,0,0,'2021-12-24 00:00:00'),(34,1,0,0,0,0,0,0,'2021-12-25 00:00:00'),(35,1,0,0,0,0,0,0,'2021-12-26 00:00:00'),(36,1,0,0,0,0,0,0,'2021-12-27 00:00:00'),(37,1,0,0,0,0,0,0,'2021-12-28 00:00:00'),(38,0,0,0,0,0,0,0,'2021-12-29 00:00:00'),(39,0,0,0,0,0,0,0,'2021-12-30 00:00:00'),(40,0,0,0,0,0,0,0,'2021-12-31 00:00:00'),(41,0,0,0,0,0,0,0,'2022-01-01 00:00:00'),(42,2,0,1,0,0,0,0,'2022-01-02 00:00:00'),(43,0,0,0,0,0,0,0,'2022-01-03 00:00:00'),(44,0,0,0,0,0,0,0,'2022-01-04 00:00:00'),(45,1,0,0,0,0,0,0,'2022-01-05 00:00:00'),(46,0,0,0,0,0,0,0,'2022-01-06 00:00:00'),(47,0,0,0,0,0,0,0,'2022-01-07 00:00:00'),(48,0,0,0,0,0,0,0,'2022-01-08 00:00:00'),(49,1,0,0,0,0,0,0,'2022-01-09 00:00:00'),(50,0,0,0,0,0,0,0,'2022-01-10 00:00:00'),(51,1,0,0,0,0,0,0,'2022-01-11 00:00:00'),(52,1,0,0,0,0,0,0,'2022-01-12 00:00:00'),(53,0,0,0,0,0,0,0,'2022-01-13 00:00:00'),(54,2,1,1,1,3,1,1,'2022-01-14 00:00:00'),(55,1,0,0,0,0,0,0,'2022-01-15 00:00:00'),(56,0,0,0,0,0,0,0,'2022-01-16 00:00:00'),(57,1,1,0,0,0,0,0,'2022-01-17 00:00:00'),(58,1,0,0,0,0,0,0,'2022-01-18 00:00:00'),(59,5,7,0,0,2,0,0,'2022-01-20 00:00:00'),(60,0,0,0,0,0,0,0,'2022-01-21 00:00:00'),(61,1,0,0,0,0,0,0,'2022-01-22 00:00:00'),(62,0,0,0,0,0,0,0,'2022-01-23 00:00:00'),(63,0,0,0,0,0,0,0,'2022-01-24 00:00:00'),(64,0,0,0,0,0,0,0,'2022-01-25 00:00:00'),(65,0,0,0,0,0,0,0,'2022-01-26 00:00:00'),(66,0,0,0,0,0,0,0,'2022-01-27 00:00:00'),(67,0,0,0,0,0,0,0,'2022-01-28 00:00:00'),(68,0,0,0,0,0,0,0,'2022-01-29 00:00:00'),(69,1,0,0,0,0,0,0,'2022-01-30 00:00:00'),(70,0,0,0,0,0,0,0,'2022-01-31 00:00:00'),(71,0,0,0,0,0,0,0,'2022-02-01 00:00:00'),(72,0,0,0,0,0,0,0,'2022-02-02 00:00:00'),(73,0,0,0,0,0,0,0,'2022-02-03 00:00:00'),(74,0,0,0,0,0,0,0,'2022-02-04 00:00:00'),(75,0,0,0,0,0,0,0,'2022-02-05 00:00:00'),(76,0,0,0,0,0,0,0,'2022-02-06 00:00:00'),(77,0,0,0,0,0,0,0,'2022-02-07 00:00:00'),(78,0,0,0,0,0,0,0,'2022-02-08 00:00:00'),(79,0,0,0,0,0,0,0,'2022-02-09 00:00:00'),(80,0,0,0,0,0,0,0,'2022-02-10 00:00:00'),(81,0,0,0,0,0,0,0,'2022-02-11 00:00:00'),(82,0,0,0,0,0,0,0,'2022-02-12 00:00:00'),(83,0,0,0,0,0,0,0,'2022-02-13 00:00:00'),(84,0,0,0,0,0,0,0,'2022-02-14 00:00:00'),(85,0,0,0,0,0,0,0,'2022-02-15 00:00:00'),(86,0,0,0,0,0,0,0,'2022-02-16 00:00:00'),(87,0,0,0,0,0,0,0,'2022-02-17 00:00:00'),(88,0,0,0,0,0,0,0,'2022-02-18 00:00:00'),(89,0,0,0,0,0,0,0,'2022-02-19 00:00:00'),(90,0,0,0,0,0,0,0,'2022-02-20 00:00:00'),(91,1,0,0,0,0,0,0,'2022-02-21 00:00:00'),(92,0,0,0,0,0,0,0,'2022-02-22 00:00:00'),(93,0,0,0,0,0,0,0,'2022-02-23 00:00:00'),(94,0,0,0,0,0,0,0,'2022-02-24 00:00:00'),(95,0,0,0,0,0,0,0,'2022-02-25 00:00:00'),(96,0,0,0,0,0,0,0,'2022-02-26 00:00:00'),(97,0,0,0,0,0,0,0,'2022-02-27 00:00:00'),(98,1,0,0,0,0,0,0,'2022-02-28 00:00:00'),(99,0,0,0,0,0,0,0,'2022-03-01 00:00:00'),(100,0,0,0,0,0,0,0,'2022-03-02 00:00:00'),(101,0,0,0,0,0,0,0,'2022-03-03 00:00:00'),(102,0,0,0,0,0,0,0,'2022-03-04 00:00:00'),(103,0,0,0,0,0,0,0,'2022-03-05 00:00:00'),(104,0,0,0,0,0,0,0,'2022-03-06 00:00:00'),(105,0,0,0,0,0,0,0,'2022-03-07 00:00:00'),(106,0,0,0,0,0,0,0,'2022-03-08 00:00:00'),(107,0,0,0,0,0,0,0,'2022-03-09 00:00:00'),(108,0,0,0,0,0,0,0,'2022-03-10 00:00:00'),(109,0,0,0,0,0,0,0,'2022-03-11 00:00:00'),(110,0,0,0,0,0,0,0,'2022-03-12 00:00:00'),(111,0,0,0,0,0,0,0,'2022-03-13 00:00:00'),(112,0,0,0,0,0,0,0,'2022-03-14 00:00:00'),(113,0,0,0,0,0,0,0,'2022-03-15 00:00:00'),(114,0,0,0,0,0,0,0,'2022-03-16 00:00:00'),(115,0,0,0,0,0,0,0,'2022-03-17 00:00:00'),(116,0,0,0,0,0,0,0,'2022-03-18 00:00:00'),(117,0,0,0,0,0,0,0,'2022-03-19 00:00:00'),(118,0,0,0,0,0,0,0,'2022-03-20 00:00:00'),(119,0,0,0,0,0,0,0,'2022-03-21 00:00:00'),(120,0,0,0,0,0,0,0,'2022-03-22 00:00:00'),(121,0,0,0,0,0,0,0,'2022-03-23 00:00:00'),(122,0,0,0,0,0,0,0,'2022-03-24 00:00:00'),(123,0,0,0,0,0,0,0,'2022-03-25 00:00:00'),(124,0,0,0,0,0,0,0,'2022-03-26 00:00:00'),(125,0,0,0,0,0,0,0,'2022-03-27 00:00:00'),(126,0,0,0,0,0,0,0,'2022-03-28 00:00:00'),(127,0,0,0,0,0,0,0,'2022-03-29 00:00:00'),(128,0,0,0,0,0,0,0,'2022-03-30 00:00:00'),(129,0,0,0,0,0,0,0,'2022-03-31 00:00:00'),(130,0,0,0,0,0,0,0,'2022-04-01 00:00:00'),(131,0,0,0,0,0,0,0,'2022-04-02 00:00:00'),(132,0,0,0,0,0,0,0,'2022-04-03 00:00:00'),(133,0,0,0,0,0,0,0,'2022-04-04 00:00:00'),(134,1,0,0,0,0,0,0,'2022-04-05 00:00:00'),(135,0,0,0,0,0,0,0,'2022-04-06 00:00:00'),(136,0,0,0,0,0,0,0,'2022-04-07 00:00:00'),(137,0,0,0,0,0,0,0,'2022-04-08 00:00:00'),(138,0,0,0,0,0,0,0,'2022-04-09 00:00:00'),(139,0,0,0,0,0,0,0,'2022-04-10 00:00:00'),(140,0,0,0,0,0,0,0,'2022-04-11 00:00:00'),(141,0,0,0,0,0,0,0,'2022-04-12 00:00:00'),(142,0,0,0,0,0,0,0,'2022-04-13 00:00:00'),(143,0,0,0,0,0,0,0,'2022-04-14 00:00:00'),(144,0,0,0,0,0,0,0,'2022-04-15 00:00:00'),(145,0,0,0,0,0,0,0,'2022-04-16 00:00:00'),(146,0,0,0,0,0,0,0,'2022-04-17 00:00:00'),(147,0,0,0,0,0,0,0,'2022-04-18 00:00:00'),(148,0,0,0,0,0,0,0,'2022-04-19 00:00:00'),(149,0,0,0,0,0,0,0,'2022-04-20 00:00:00'),(150,0,0,0,0,0,0,0,'2022-04-21 00:00:00'),(151,0,0,0,0,0,0,0,'2022-04-22 00:00:00'),(152,0,0,0,0,0,0,0,'2022-04-23 00:00:00'),(153,0,0,0,0,0,0,0,'2022-04-24 00:00:00'),(154,0,0,0,0,0,0,0,'2022-04-25 00:00:00'),(155,0,0,0,0,0,0,0,'2022-04-26 00:00:00'),(156,0,0,0,0,0,0,0,'2022-04-27 00:00:00'),(157,0,0,0,0,0,0,0,'2022-04-28 00:00:00'),(158,0,0,0,0,0,0,0,'2022-04-29 00:00:00'),(159,0,0,0,0,0,0,0,'2022-04-30 00:00:00'),(160,0,0,0,0,0,0,0,'2022-05-01 00:00:00'),(161,0,0,0,0,0,0,0,'2022-05-02 00:00:00'),(162,0,0,0,0,0,0,0,'2022-05-03 00:00:00'),(163,0,0,0,0,0,0,0,'2022-05-04 00:00:00'),(164,0,0,0,0,0,0,0,'2022-05-05 00:00:00'),(165,0,0,0,0,0,0,0,'2022-05-06 00:00:00'),(166,0,0,0,0,0,0,0,'2022-05-07 00:00:00'),(167,0,0,0,0,0,0,0,'2022-05-08 00:00:00'),(168,0,0,0,0,0,0,0,'2022-05-09 00:00:00'),(169,0,0,0,0,0,0,0,'2022-05-10 00:00:00'),(170,0,0,0,0,0,0,0,'2022-05-11 00:00:00'),(171,0,0,0,0,0,0,0,'2022-05-12 00:00:00'),(172,0,0,0,0,0,0,0,'2022-05-13 00:00:00'),(173,0,0,0,0,0,0,0,'2022-05-14 00:00:00'),(174,0,0,0,0,0,0,0,'2022-05-16 00:00:00'),(175,0,0,0,0,0,0,0,'2022-05-17 00:00:00'),(176,0,0,0,0,0,0,0,'2022-05-18 00:00:00'),(177,0,0,0,0,0,0,0,'2022-05-19 00:00:00'),(178,0,0,0,0,0,0,0,'2022-05-20 00:00:00'),(179,0,0,0,0,0,0,0,'2022-05-21 00:00:00'),(180,0,0,0,0,0,0,0,'2022-05-22 00:00:00'),(181,0,0,0,0,0,0,0,'2022-05-23 00:00:00'),(182,0,0,0,0,0,0,0,'2022-05-24 00:00:00'),(183,0,0,0,0,0,0,0,'2022-05-25 00:00:00'),(184,0,0,0,0,0,0,0,'2022-05-26 00:00:00'),(185,0,0,0,0,0,0,0,'2022-05-27 00:00:00'),(186,0,0,0,0,0,0,0,'2022-05-28 00:00:00'),(187,0,0,0,0,0,0,0,'2022-05-29 00:00:00'),(188,0,0,0,0,0,0,0,'2022-05-30 00:00:00'),(189,0,0,0,0,0,0,0,'2022-05-31 00:00:00'),(190,0,0,0,0,0,0,0,'2022-06-01 00:00:00'),(191,0,0,0,0,0,0,0,'2022-06-02 00:00:00'),(192,0,0,0,0,0,0,0,'2022-06-03 00:00:00'),(193,0,0,0,0,0,0,0,'2022-06-04 00:00:00'),(194,0,0,0,0,0,0,0,'2022-06-05 00:00:00'),(195,0,0,0,0,0,0,0,'2022-06-06 00:00:00'),(196,0,0,0,0,0,0,0,'2022-06-07 00:00:00'),(197,0,0,0,0,0,0,0,'2022-06-08 00:00:00'),(198,0,0,0,0,0,0,0,'2022-06-09 00:00:00'),(199,0,0,0,0,0,0,0,'2022-06-10 00:00:00'),(200,0,0,0,0,0,0,0,'2022-06-11 00:00:00'),(201,0,0,0,0,0,0,0,'2022-06-12 00:00:00'),(202,0,0,0,0,0,0,0,'2022-06-13 00:00:00'),(203,0,0,0,0,0,0,0,'2022-06-14 00:00:00'),(204,0,0,0,0,0,0,0,'2022-06-15 00:00:00'),(205,0,0,0,0,0,0,0,'2022-06-16 00:00:00');
/*!40000 ALTER TABLE `xm06_t_system_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_task`
--

DROP TABLE IF EXISTS `xm06_t_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_task` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `creator_id` int DEFAULT NULL COMMENT '创建者id，对应用户表主键',
  `group_id` int DEFAULT NULL COMMENT '所属小组id，对应小组表id',
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_task`
--

LOCK TABLES `xm06_t_task` WRITE;
/*!40000 ALTER TABLE `xm06_t_task` DISABLE KEYS */;
INSERT INTO `xm06_t_task` VALUES (17,'测试发布任务2',27,33,'测试发布个任务\n\n修该了描述','2022-01-28 19:49:00','2021-12-09 21:48:57',_binary ''),(18,'pjj',31,35,'asd','2021-12-08 12:00:00','2021-12-11 17:59:19',_binary ''),(19,'helei222发布的任务',34,37,'awdawdwdaw','2022-01-22 12:00:00','2022-01-14 21:16:18',_binary ''),(20,'测试任务',46,42,'完成测试任务','2022-01-21 12:00:00','2022-01-21 10:36:19',_binary ''),(21,'测试任务',46,42,'完成测试任务','2022-01-21 12:30:00','2022-01-21 10:38:04',_binary ''),(22,'测试任务',46,42,'完成测试任务','2022-01-21 12:00:00','2022-01-21 10:39:32',_binary ''),(23,'测试任务',46,42,'啊啊','2022-01-19 12:00:00','2022-01-21 10:46:53',_binary ''),(24,'测试任务222',46,42,'啊啊‘和’','2022-01-21 12:00:00','2022-01-21 10:47:23',_binary '');
/*!40000 ALTER TABLE `xm06_t_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_task_record`
--

DROP TABLE IF EXISTS `xm06_t_task_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_task_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_id` int DEFAULT NULL COMMENT '任务id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `file_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提交文件资源路径',
  `submit_date` datetime DEFAULT NULL COMMENT '提交日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_task_record`
--

LOCK TABLES `xm06_t_task_record` WRITE;
/*!40000 ALTER TABLE `xm06_t_task_record` DISABLE KEYS */;
INSERT INTO `xm06_t_task_record` VALUES (55,17,27,'测试提交任务\n交了个图片','D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\task\\head_default163905778768863.png','2021-12-09 21:49:47'),(57,17,27,'','D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\heleidage666\\task\\head_default164112415922063.png','2022-01-02 19:49:19'),(58,17,34,'','D:\\webapps\\teach\\yjykfsj2021\\XM06_upload\\uploadfile\\helei222\\task\\head_default164216610282827.png','2022-01-14 21:15:02');
/*!40000 ALTER TABLE `xm06_t_task_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_user`
--

DROP TABLE IF EXISTS `xm06_t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_pwd` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `true_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_user`
--

LOCK TABLES `xm06_t_user` WRITE;
/*!40000 ALTER TABLE `xm06_t_user` DISABLE KEYS */;
INSERT INTO `xm06_t_user` VALUES (27,'heleidage666','xgkUKIhdWWIXaBdgiCk9lQ==','何磊666','914577981@qq.com','19141273392',_binary '','2021-12-09 21:35:53','2022-01-02 19:50:52'),(28,'zhangsan','4QrcOUm6Wau+VuBX8g+IPg==','XXX','1226398122@qq.com','1222222222',_binary '','2021-12-09 21:42:46','2021-12-09 21:42:46'),(29,'heleidage777','4QrcOUm6Wau+VuBX8g+IPg==','lei he','914577981@qq.com','',_binary '','2021-12-09 21:46:32','2021-12-09 21:46:32'),(30,'不管pjjj','4QrcOUm6Wau+VuBX8g+IPg==','pjj','850493484@qq.co','123456',_binary '\0','2021-12-11 17:27:50','2021-12-11 17:27:50'),(31,'buguanpjj','4QrcOUm6Wau+VuBX8g+IPg==','pjj','850493484@qq.com','18328638534',_binary '','2021-12-11 17:33:28','2021-12-11 17:33:28'),(32,'buguan','4QrcOUm6Wau+VuBX8g+IPg==','asd','850493484@qq.com','123456',_binary '','2021-12-11 17:51:23','2021-12-11 17:51:23'),(34,'helei222','4QrcOUm6Wau+VuBX8g+IPg==','lei heawdaw','914577981@qq.com','19141273392',_binary '','2022-01-14 21:12:12','2022-01-14 21:13:23'),(35,'123456','4QrcOUm6Wau+VuBX8g+IPg==','James','1713504552@qq.com','',_binary '','2022-01-17 17:50:37','2022-01-17 17:50:37'),(39,'testtest','4QrcOUm6Wau+VuBX8g+IPg==','123','2029772668@qq.com','',_binary '','2022-01-20 20:35:04','2022-01-20 20:35:04'),(41,'111111111111','lueSGJZetyySpUndWjMBEg==','111','111@1.com','',_binary '\0','2022-01-20 21:38:37','2022-01-20 21:38:37'),(42,'111111','4QrcOUm6Wau+VuBX8g+IPg==','1111','1111@11.com','',_binary '\0','2022-01-20 21:39:09','2022-01-20 21:39:09'),(43,'11111111111','lueSGJZetyySpUndWjMBEg==','1','111m@m.com','',_binary '\0','2022-01-20 21:40:52','2022-01-20 21:40:52'),(44,'echo5758','4QrcOUm6Wau+VuBX8g+IPg==','黎博文','1548793977@qq.com','18942895758',_binary '','2022-01-20 22:04:39','2022-01-20 22:04:39'),(45,'idealist','U91u8Xz4J5RqfKdTgexknA==','张昊钰','782237375@qq.com','13243365895',_binary '','2022-01-20 22:20:38','2022-01-20 22:20:38'),(46,'boyde2233','Czl0EWfuKe15tQ9sivqL6A==','lixin','1925776092@qq.com','18615712390',_binary '','2022-01-21 10:18:58','2022-01-21 10:18:58'),(47,'test789','4QrcOUm6Wau+VuBX8g+IPg==','test','rinw20@163.com','',_binary '','2022-01-21 10:50:43','2022-01-21 10:50:43');
/*!40000 ALTER TABLE `xm06_t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_user_faceinfo`
--

DROP TABLE IF EXISTS `xm06_t_user_faceinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_user_faceinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `face_yml_src` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `face_img_folder_src` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_user_faceinfo`
--

LOCK TABLES `xm06_t_user_faceinfo` WRITE;
/*!40000 ALTER TABLE `xm06_t_user_faceinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `xm06_t_user_faceinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_user_group`
--

DROP TABLE IF EXISTS `xm06_t_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_user_group` (
  `user_id` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户主键id',
  `group_id` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小组主键id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_user_group`
--

LOCK TABLES `xm06_t_user_group` WRITE;
/*!40000 ALTER TABLE `xm06_t_user_group` DISABLE KEYS */;
INSERT INTO `xm06_t_user_group` VALUES ('27','33'),('31','35'),('28','33'),('34','33'),('34','37'),('34','38'),('39','39'),('44','41'),('46','42'),('47','43'),('47','44');
/*!40000 ALTER TABLE `xm06_t_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xm06_t_user_role`
--

DROP TABLE IF EXISTS `xm06_t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xm06_t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xm06_t_user_role`
--

LOCK TABLES `xm06_t_user_role` WRITE;
/*!40000 ALTER TABLE `xm06_t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `xm06_t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-26 12:21:44
