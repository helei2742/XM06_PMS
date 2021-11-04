/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.5.56 : Database - pms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pms`;

/*Table structure for table `t_group` */

DROP TABLE IF EXISTS `t_group`;

CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) NOT NULL,
  `described` varchar(500) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `t_group` */

insert  into `t_group`(`id`,`group_name`,`described`,`manager_id`,`is_valid`,`create_date`,`update_date`) values 
(1,'group1','\'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\'',2,NULL,NULL,NULL),
(3,'group2',NULL,2,'','2021-09-22 21:21:13','2021-09-22 21:21:13'),
(4,'group3','123',2,'','2021-09-24 16:05:17','2021-09-24 16:05:17'),
(5,'group4',NULL,2,'','2021-09-24 16:08:11','2021-09-24 16:08:11'),
(6,'group11',NULL,2,'','2021-09-24 16:10:27','2021-09-24 16:10:27'),
(8,'group200',NULL,2,'','2021-09-24 17:30:16','2021-09-24 17:30:16'),
(9,'group201',NULL,5,'','2021-09-24 17:35:01','2021-09-24 17:35:01'),
(11,'group205',NULL,5,'','2021-09-24 17:42:31','2021-09-24 17:42:31'),
(12,'group206',NULL,5,'','2021-09-24 17:43:59','2021-09-24 17:43:59'),
(13,'heleigroup','哈哈哈哈，来加啊',7,'','2021-09-25 20:09:38','2021-09-25 20:09:38'),
(16,'test3',NULL,7,'','2021-09-26 12:30:11','2021-09-26 12:30:11'),
(17,'qwerty的队伍','123421',16,'','2021-09-29 09:52:21','2021-09-29 09:52:21'),
(18,'qwertty','hahah',7,'','2021-09-29 13:52:34','2021-09-29 13:52:34'),
(19,'qwertty3','hahah',7,'','2021-09-29 13:52:38','2021-09-29 13:52:38'),
(21,'你好','1',18,'','2021-09-29 14:32:18','2021-09-29 14:32:18'),
(22,'qwertyu','chesi',7,'','2021-10-01 15:58:10','2021-10-01 15:58:10'),
(23,'dthovghIOAGvh','hahahhahah',7,'','2021-10-12 15:27:00','2021-10-12 15:27:00'),
(24,'123',NULL,12,'','2021-10-15 20:35:48','2021-10-15 20:35:48');

/*Table structure for table `t_inform` */

DROP TABLE IF EXISTS `t_inform`;

CREATE TABLE `t_inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `send_user_id` int(11) DEFAULT NULL,
  `send_date` datetime DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_inform` */

/*Table structure for table `t_memo` */

DROP TABLE IF EXISTS `t_memo`;

CREATE TABLE `t_memo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `memo` varchar(40) DEFAULT NULL,
  `stale_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_finish` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `t_memo` */

insert  into `t_memo`(`id`,`user_id`,`memo`,`stale_date`,`create_date`,`is_finish`) values 
(3,7,'完成测试3','2003-12-28 08:12:09','2001-12-28 08:12:09',''),
(4,7,'完成测试3','2004-12-28 08:12:09','2001-12-28 08:12:09',''),
(6,7,'完成测试3','2006-12-28 08:12:09','2001-12-28 08:12:09',''),
(7,7,'完成测试3','2006-12-28 08:12:09','2001-12-28 08:12:09',''),
(8,7,'完成测试3','2006-12-28 08:12:09','2001-12-28 08:12:09','\0'),
(10,7,'完成测试3','2006-12-28 08:12:09','2001-12-28 08:12:09',''),
(11,7,'test','2021-10-01 15:08:18','2021-09-30 15:08:27','\0'),
(12,7,'test2','2021-10-22 15:09:22','2021-09-30 15:09:27',''),
(13,7,'hahhahah','2021-10-09 00:00:00','2021-09-30 15:30:41',''),
(15,7,'ceshi','2021-10-30 00:00:00','2021-10-01 18:53:22','\0'),
(16,7,'测试创建便签','2021-11-11 00:00:00','2021-11-02 14:02:51','\0');

/*Table structure for table `t_project` */

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `project_desc` varchar(1500) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `completion_degree` double DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  `is_public` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_project` */

insert  into `t_project`(`id`,`project_name`,`project_desc`,`creator_id`,`group_id`,`completion_degree`,`create_date`,`update_date`,`is_valid`,`is_public`) values 
(1,'测试创建项目',NULL,7,16,0,'2021-10-27 21:27:14','2021-10-27 21:27:14','',NULL),
(2,'测试创建项目是否重名',NULL,7,16,0,'2021-10-27 21:41:19','2021-10-27 21:41:19','',NULL),
(3,'dawda','d',7,NULL,0,'2021-11-03 10:44:11','2021-11-03 10:44:11','','\0'),
(5,'dawdadwad','d',7,NULL,0,'2021-11-03 10:49:12','2021-11-03 10:49:12','','\0'),
(6,'dawdadw','d',7,NULL,0,'2021-11-03 10:55:34','2021-11-03 10:55:34','',''),
(7,'dawd','d',7,NULL,0,'2021-11-03 11:00:05','2021-11-03 11:00:05','',''),
(8,'很长描述的项目','很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目很长描述的项目',7,NULL,0,'2021-11-03 17:16:30','2021-11-03 17:16:30','','');

/*Table structure for table `t_project_group` */

DROP TABLE IF EXISTS `t_project_group`;

CREATE TABLE `t_project_group` (
  `project_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_project_group` */

insert  into `t_project_group`(`project_id`,`group_id`,`create_date`,`is_valid`) values 
(2,1,'2021-11-02 19:42:32',''),
(2,16,NULL,NULL),
(2,7,NULL,NULL),
(1,16,NULL,NULL),
(1,13,'2021-11-03 10:44:11',''),
(1,23,'2021-11-03 10:44:11',''),
(5,13,'2021-11-03 10:49:12',''),
(5,23,'2021-11-03 10:49:12',''),
(6,13,'2021-11-03 10:55:34',''),
(6,23,'2021-11-03 10:55:34',''),
(7,19,'2021-11-03 11:00:05',''),
(7,22,'2021-11-03 11:00:05',''),
(8,13,'2021-11-03 17:16:30',''),
(8,16,'2021-11-03 17:16:30',''),
(8,22,'2021-11-03 17:16:30',''),
(8,23,'2021-11-03 17:16:30','');

/*Table structure for table `t_task` */

DROP TABLE IF EXISTS `t_task`;

CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(50) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_task` */

insert  into `t_task`(`id`,`task_name`,`creator_id`,`group_id`,`description`,`deadline`,`create_date`,`is_valid`) values 
(1,'测试发部任务',7,16,NULL,'2021-10-02 11:27:54','2021-10-02 11:29:49',''),
(2,'测试发部任务',7,16,NULL,'2021-10-02 11:27:54','2021-10-02 11:32:25',''),
(3,'测试发部任务',7,16,NULL,'2021-10-02 11:27:54','2021-10-02 11:32:26',''),
(4,'测试发部任务',7,16,NULL,'2021-10-02 11:27:54','2021-10-02 11:32:27',''),
(5,'测试发部任务',7,16,NULL,'2021-10-02 11:27:54','2021-10-02 11:32:28',''),
(6,'测试发部任务',7,16,NULL,'2021-10-02 11:27:54','2021-10-02 11:32:29',''),
(7,'测试发部任务',7,16,'这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述这是一段很长很长的描述','2021-10-02 11:27:54','2021-10-02 11:34:12',''),
(8,'cvbn',7,16,'cvbnm,','2021-10-03 12:00:00','2021-10-03 14:45:13',''),
(9,'测试任务描述换行',7,13,'WADAD\nWADWAD\nAWDAWD\nWADAWDAWD\n        WADAWDAWDAWD\nWD','2021-10-04 12:00:00','2021-10-04 10:45:46',''),
(10,'测试日期',7,13,'awd','2022-10-07 12:00:00','2021-10-04 11:28:48',''),
(11,'hhhhhhhhhhhhhhhhh',7,13,'测试测试','2021-11-19 12:00:00','2021-10-27 14:32:25','');

/*Table structure for table `t_task_record` */

DROP TABLE IF EXISTS `t_task_record`;

CREATE TABLE `t_task_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `file_url` varchar(200) DEFAULT NULL,
  `submit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `t_task_record` */

insert  into `t_task_record`(`id`,`task_id`,`user_id`,`description`,`file_url`,`submit_date`) values 
(1,1,7,'哈哈哈','D:\\test\\##QMxQzM##wNxEzN2gDMxQzMzYTM\\项目建议书163341086711980.doc',NULL),
(2,10,7,'vfcytc','D:\\test\\##wNxQzM##gN2EjM5kzMxQzMzYTM\\项目计划书（研发）163341399216788.doc',NULL),
(3,10,7,'xdfcgvhbj','D:\\test\\##wNxQzM##QNxAjN4ADNxQzMzYTM\\项目预算表163341408601584.doc',NULL),
(4,10,7,'awda','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目建议书163341422485290.doc',NULL),
(5,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项评审表163341425152956.doc',NULL),
(6,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341567079527.doc',NULL),
(7,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341588502290.doc',NULL),
(8,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163341600469260.doc',NULL),
(9,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341611054987.doc',NULL),
(10,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163341664124663.doc',NULL),
(11,10,7,'',NULL,NULL),
(12,10,7,'',NULL,NULL),
(13,10,7,'',NULL,NULL),
(14,10,7,'',NULL,NULL),
(15,10,7,'',NULL,NULL),
(16,10,7,'',NULL,NULL),
(17,10,7,'xsdcfvgbhnj',NULL,NULL),
(18,10,7,'daw',NULL,NULL),
(19,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目立项申报表163341825658032.doc',NULL),
(20,10,7,'测试有上传文件','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163341830178679.doc',NULL),
(21,10,7,'',NULL,NULL),
(22,10,7,'再次测试上传文件','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目建议书163341924983237.doc',NULL),
(23,10,7,'再次测试不上传文件',NULL,NULL),
(24,9,7,'hasda',NULL,NULL),
(25,7,7,'测试显示提交的内容\n测试显示提交的内容\n测试显示提交的内容\n测试显示提交的内容\n测试显示提交的内容\n   \n   测试显示提交的内容',NULL,'2021-10-06 13:26:46'),
(26,7,7,'测试文件上传后下载','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\top163350143232323.png','2021-10-06 14:23:52'),
(27,7,7,'hahahahahah','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\功能清单163350364602684.docx','2021-10-06 15:00:46'),
(28,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\sql163358798831719.txt','2021-10-07 14:26:28'),
(29,7,7,'adawD','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\实验4-个人报告-何磊-2019141460044163358804598373.docx','2021-10-07 14:27:26'),
(30,7,7,'1111111111111111','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第5个实验163358864510254.doc','2021-10-07 14:37:25'),
(31,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第14个实验163358918720184.doc','2021-10-07 14:46:27'),
(32,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第1个实验163358932510856.doc','2021-10-07 14:48:45'),
(33,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第3个实验163359044582723.docx','2021-10-07 15:07:25'),
(34,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第16个实验16335907501961.doc','2021-10-07 15:12:30'),
(35,7,7,'',NULL,'2021-10-07 15:20:31'),
(36,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第8个实验163359163125871.doc','2021-10-07 15:27:11'),
(37,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第8个实验163359176637692.doc','2021-10-07 15:29:26'),
(38,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第2个实验163359178763790.docx','2021-10-07 15:29:47'),
(39,7,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第14个实验163359210568913.doc','2021-10-07 15:35:05'),
(40,7,7,'测试不带文件',NULL,'2021-10-08 09:09:07'),
(41,7,7,'测试提交日期',NULL,'2021-10-08 09:13:31'),
(42,8,7,'测试日期',NULL,'2021-10-08 09:19:45'),
(43,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第9个实验163365823820345.doc','2021-10-08 09:57:18'),
(44,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第8个实验163365863006061.doc','2021-10-08 10:03:50'),
(45,9,7,'测试上传进度','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第4个实验163365954885220.dot','2021-10-08 10:19:08'),
(46,9,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第9个实验163365974562297.doc','2021-10-08 10:22:25'),
(47,10,7,'','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\何磊2019141460044第9个实验163365982562846.doc','2021-10-08 10:23:45'),
(48,8,7,'ce','D:\\test\\PgRp+xNJkfj3Widg5AnG7Q==\\项目预算表163429037551960.doc','2021-10-15 17:32:55'),
(49,1,2,'','D:\\test\\fljWO2AZfOtVocSHmJo3IA==\\top163471284686857.png','2021-10-20 14:54:06');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_pwd` varchar(100) DEFAULT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`user_name`,`user_pwd`,`true_name`,`email`,`phone`,`is_valid`,`create_date`,`update_date`) values 
(1,'zhangsan','123456','helei',NULL,NULL,'','2021-09-22 19:50:12','2021-09-23 20:28:21'),
(2,'lisi','4QrcOUm6Wau+VuBX8g+IPg==','李四',NULL,NULL,'','2021-09-22 20:39:23','2021-09-22 20:40:32'),
(3,'test1','4QrcOUm6Wau+VuBX8g+IPg==','','','19141273392','','2021-09-24 08:46:39','2021-09-24 08:46:39'),
(4,'test2','itP6xsazUoSZ00fZJEQ6uw==','hhh','wada','2','','2021-09-24 08:54:07','2021-09-24 08:54:07'),
(5,'test3','4QrcOUm6Wau+VuBX8g+IPg==','','','','','2021-09-24 08:57:40','2021-09-24 08:57:40'),
(6,'zhangsan123','4QrcOUm6Wau+VuBX8g+IPg==','','','','','2021-09-24 09:10:07','2021-09-24 09:10:07'),
(7,'heleidage666','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','124214','','2021-09-25 17:37:57','2021-09-29 14:25:50'),
(8,'helei1234','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','','\0','2021-09-28 18:06:26','2021-09-28 18:06:26'),
(9,'helei12345','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','','\0','2021-09-28 18:12:41','2021-09-28 18:12:41'),
(10,'heleidage1234','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','','\0','2021-09-28 18:16:53','2021-09-28 18:16:53'),
(11,'test888','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','','\0','2021-09-28 18:21:42','2021-09-28 18:21:42'),
(12,'heleidage090','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','','\0','2021-09-28 18:23:25','2021-09-28 18:23:25'),
(13,'heleidage999','xgkUKIhdWWIXaBdgiCk9lQ==','何磊','914577981@qq.com','','\0','2021-09-28 18:23:32','2021-09-28 18:23:32'),
(14,'qwer','gdyb21LQTcIANtvYMT7QVQ==','何磊','914577981@qq.com','','\0','2021-09-28 19:37:41','2021-09-28 19:37:41'),
(15,'qwert','gdyb21LQTcIANtvYMT7QVQ==','何磊','914577981@qq.com','','\0','2021-09-28 19:54:10','2021-09-28 19:54:10'),
(16,'qwerty','gdyb21LQTcIANtvYMT7QVQ==','何磊','914577981@qq.com','','','2021-09-28 19:54:52','2021-09-28 19:54:52'),
(18,'wojiaohelei','4QrcOUm6Wau+VuBX8g+IPg==','1','914577981@qq.com','123445','','2021-09-29 14:29:28','2021-09-29 14:29:28'),
(19,'deplo','4QrcOUm6Wau+VuBX8g+IPg==','何磊','914577981@qq.com','','','2021-10-15 17:26:28','2021-10-15 17:26:28');

/*Table structure for table `t_user_group` */

DROP TABLE IF EXISTS `t_user_group`;

CREATE TABLE `t_user_group` (
  `user_id` varchar(11) DEFAULT NULL,
  `group_id` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_group` */

insert  into `t_user_group`(`user_id`,`group_id`) values 
('1','1'),
('1','4'),
('1','3'),
('2','1'),
('3','1'),
('4','1'),
('5','1'),
('5','12'),
('2','3'),
('1','6'),
('1','8'),
('1','9'),
('1','10'),
('1','11'),
('1','12'),
('1','5'),
('2','4'),
('2','5'),
('2','6'),
('2','8'),
('5','12'),
('5','11'),
('5','10'),
('5','9'),
('7','13'),
('7','14'),
('7','15'),
('7','16'),
('4','16'),
('1','16'),
('2','16'),
('6','16'),
('16','17'),
('7','10'),
('7','11'),
('7','12'),
('7','18'),
('7','19'),
('7','20'),
('18','21'),
('7','22'),
('7','23'),
('12','24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
