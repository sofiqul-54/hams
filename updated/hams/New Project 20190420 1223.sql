-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.40-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hams
--

CREATE DATABASE IF NOT EXISTS hams;
USE hams;

--
-- Definition of table `acc_head`
--

DROP TABLE IF EXISTS `acc_head`;
CREATE TABLE `acc_head` (
  `inc_id` bigint(20) NOT NULL,
  `acc_id` bigint(20) NOT NULL,
  PRIMARY KEY (`inc_id`,`acc_id`),
  KEY `FKe7flu3fchtlb6486u3foewfbl` (`acc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acc_head`
--

/*!40000 ALTER TABLE `acc_head` DISABLE KEYS */;
INSERT INTO `acc_head` (`inc_id`,`acc_id`) VALUES 
 (1,1),
 (2,1);
/*!40000 ALTER TABLE `acc_head` ENABLE KEYS */;


--
-- Definition of table `accn_head`
--

DROP TABLE IF EXISTS `accn_head`;
CREATE TABLE `accn_head` (
  `ex_id` bigint(20) NOT NULL,
  `accn_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ex_id`,`accn_id`),
  KEY `FKaomkiephex320qnnb3xnym87m` (`accn_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accn_head`
--

/*!40000 ALTER TABLE `accn_head` DISABLE KEYS */;
/*!40000 ALTER TABLE `accn_head` ENABLE KEYS */;


--
-- Definition of table `account_head`
--

DROP TABLE IF EXISTS `account_head`;
CREATE TABLE `account_head` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountname` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fbwqctueeekk9bsurtwhp7dum` (`accountname`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_head`
--

/*!40000 ALTER TABLE `account_head` DISABLE KEYS */;
INSERT INTO `account_head` (`id`,`accountname`,`description`) VALUES 
 (1,'CASH','Cash Receive'),
 (2,'CHEQUE','Receive by cheque');
/*!40000 ALTER TABLE `account_head` ENABLE KEYS */;


--
-- Definition of table `agency_info`
--

DROP TABLE IF EXISTS `agency_info`;
CREATE TABLE `agency_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `agn_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `opening_date` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pro_name` varchar(255) NOT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1k169qjhyfhs4vlsfvxls77e7` (`agn_name`),
  UNIQUE KEY `UK_i2ywpibx1phu88s9alrwxcejl` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agency_info`
--

/*!40000 ALTER TABLE `agency_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `agency_info` ENABLE KEYS */;


--
-- Definition of table `booking_summary`
--

DROP TABLE IF EXISTS `booking_summary`;
CREATE TABLE `booking_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collection_amount` double NOT NULL,
  `due_amount` double NOT NULL,
  `paid_amount` double NOT NULL,
  `total_amount` double NOT NULL,
  `booksummary_pilgrim` bigint(20) NOT NULL,
  `booksummary_package` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4vhjnx0ewq78n3h1r158iyren` (`booksummary_pilgrim`),
  KEY `FKhm5sihqsh8653a2xaq9qr2ed0` (`booksummary_package`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_summary`
--

/*!40000 ALTER TABLE `booking_summary` DISABLE KEYS */;
INSERT INTO `booking_summary` (`id`,`collection_amount`,`due_amount`,`paid_amount`,`total_amount`,`booksummary_pilgrim`,`booksummary_package`) VALUES 
 (1,0,445000,135000,580000,1,1),
 (2,0,536000,44000,580000,2,1),
 (3,0,415000,65000,480000,3,3),
 (4,0,530000,50000,580000,6,1),
 (5,0,560000,20000,580000,7,1),
 (6,0,530000,50000,580000,8,1),
 (7,0,530000,50000,580000,9,1),
 (8,0,530000,50000,580000,10,1),
 (9,0,540000,40000,580000,11,1),
 (10,0,380000,145000,525000,12,2),
 (11,0,425000,55000,480000,13,3),
 (12,0,516000,64000,580000,14,1);
/*!40000 ALTER TABLE `booking_summary` ENABLE KEYS */;


--
-- Definition of table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_title` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  `p_date` datetime DEFAULT NULL,
  `total_amount` double NOT NULL,
  `incm_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk9n4as3kq1oepptbjex4kyceu` (`incm_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expense`
--

/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;


--
-- Definition of table `group_leader`
--

DROP TABLE IF EXISTS `group_leader`;
CREATE TABLE `group_leader` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date NOT NULL,
  `commission` int(11) NOT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `exp_date` date NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `issu_date` date NOT NULL,
  `gleader_name` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `leader_nid` varchar(255) NOT NULL,
  `pass_name` varchar(255) NOT NULL,
  `regi_date` datetime DEFAULT NULL,
  `total_commission` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tqp5k423wy98seewqgqw16vdj` (`email`),
  UNIQUE KEY `UK_3va4shr5l63w5dc01dvaja309` (`leader_nid`),
  UNIQUE KEY `UK_8726lb9gegvp2o6ieqtontwmm` (`pass_name`),
  UNIQUE KEY `UK_og4q1djn046fs8lul7c93wco` (`district`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_leader`
--

/*!40000 ALTER TABLE `group_leader` DISABLE KEYS */;
INSERT INTO `group_leader` (`id`,`address`,`birth_date`,`commission`,`district`,`email`,`exp_date`,`gender`,`issu_date`,`gleader_name`,`mobile`,`leader_nid`,`pass_name`,`regi_date`,`total_commission`) VALUES 
 (1,'16/A, Shaorapara, Mirpur','1991-12-10',2,'Narayangonj','mamun@gmain.com','2022-10-07','Male','2017-10-08','Ashraful Islam','01646526667','2613869450164','BR0274123','2019-04-17 23:00:46',0),
 (2,'507, Khejurbag, South Keranigonj, Dhaka','1988-01-05',3,'Munsigonj','khurshed@gmain.com','2023-04-07','Male','2018-04-08','Sagar','01623970023','1911608450','BB0247123','2019-04-17 23:03:52',0),
 (3,NULL,'1978-09-04',4,'Barishal','mainuddin@gmail.com','2024-04-06','Male','2019-04-07','MainUddin Sikder','01712475891','2613869450166','TW0284123','2019-04-18 07:43:23',0),
 (4,'Sutrapur, Millbarac, Dhaka','2000-02-10',2,'Comilla','khadiza@gmalil.com','2024-04-21','Female','2019-04-22','Sonia Akter','01747427502','1011608454','OW0284123','2019-04-18 07:49:50',0),
 (5,NULL,'1996-10-04',2,'Chattragram','rayhan@gmail.com','2020-06-05','Male','2016-06-06','Abu Rayhan','01748497154','2613869450181','MR0284123','2019-04-20 09:34:03',0),
 (6,NULL,'1991-04-04',3,'Bagura','sajol@gmail.com','2023-06-06','Male','2018-06-07','Sajol','01435433875','1011694550','BM0284987','2019-04-20 09:41:40',0),
 (7,NULL,'1973-06-07',4,'Magura','idrish@gmail.com','2023-06-08','Male','2018-06-09','Idrish','01435436775','2694769450177','TR0284987','2019-04-20 09:47:09',0),
 (8,NULL,'1979-10-10',2,'Dhaka','rajaulislm@gmail.com','2024-04-03','Male','2019-04-04','Rajaul Islam','01846652667','2022608450','WE0514987','2019-04-20 09:54:46',0),
 (9,NULL,'2000-09-07',2,'Rangamati','ashik@gmail.com','2024-03-02','Male','2019-03-03','Ashik Hossain','01842916742','19915554664000154','ON0284123','2019-04-20 09:57:56',0),
 (10,NULL,'1964-05-05',3,'Chandpur','halim@gmail.com','2023-05-08','Male','2018-05-09','Abdul Halim','01335436775','2613867416477','LK0284123','2019-04-20 09:59:51',0);
/*!40000 ALTER TABLE `group_leader` ENABLE KEYS */;


--
-- Definition of table `group_leader_summary`
--

DROP TABLE IF EXISTS `group_leader_summary`;
CREATE TABLE `group_leader_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commission` double NOT NULL,
  `leader_name` varchar(255) DEFAULT NULL,
  `total_commission` double NOT NULL,
  `groupleader_id` bigint(20) NOT NULL,
  `pilgrim_bookingsummary` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr2t8jtkc9fo75srovkmidy7tg` (`groupleader_id`),
  KEY `FKtqhmhf860b7h10h01wlmh2hb6` (`pilgrim_bookingsummary`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_leader_summary`
--

/*!40000 ALTER TABLE `group_leader_summary` DISABLE KEYS */;
INSERT INTO `group_leader_summary` (`id`,`commission`,`leader_name`,`total_commission`,`groupleader_id`,`pilgrim_bookingsummary`) VALUES 
 (1,11600,'Ashraful Islam',23200,1,10),
 (2,15750,'Sagar',30150,2,12),
 (3,23200,'MainUddin Sikder',23200,3,14);
/*!40000 ALTER TABLE `group_leader_summary` ENABLE KEYS */;


--
-- Definition of table `income`
--

DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_title` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `c_date` datetime DEFAULT NULL,
  `pilgm_id` bigint(20) DEFAULT NULL,
  `income_package` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrs10kyjomloars9ua3mt1tl3s` (`pilgm_id`),
  KEY `FKovg3s7p0nm4a62ku409wbwfed` (`income_package`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `income`
--

/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` (`id`,`account_title`,`amount`,`c_date`,`pilgm_id`,`income_package`) VALUES 
 (1,'Hajj Installment',80000,'2019-04-20 12:17:15',1,2),
 (2,'Hajj Installment',80000,'2019-04-20 12:17:48',12,2);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;


--
-- Definition of table `income_groupleader`
--

DROP TABLE IF EXISTS `income_groupleader`;
CREATE TABLE `income_groupleader` (
  `inc_id` bigint(20) NOT NULL,
  `groupleader_id` bigint(20) NOT NULL,
  PRIMARY KEY (`inc_id`,`groupleader_id`),
  KEY `FK71nkjn4l4bn47iwo6rhojn89j` (`groupleader_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `income_groupleader`
--

/*!40000 ALTER TABLE `income_groupleader` DISABLE KEYS */;
/*!40000 ALTER TABLE `income_groupleader` ENABLE KEYS */;


--
-- Definition of table `package`
--

DROP TABLE IF EXISTS `package`;
CREATE TABLE `package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) NOT NULL,
  `package_value` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3odr7nymb4tggk96wkfiiui0y` (`package_name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `package`
--

/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` (`id`,`package_name`,`package_value`) VALUES 
 (1,'A+',580000),
 (2,'A',525000),
 (3,'B+',480000),
 (4,'B',420000),
 (5,'C',380000);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;


--
-- Definition of table `passport_info`
--

DROP TABLE IF EXISTS `passport_info`;
CREATE TABLE `passport_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dip` varchar(255) DEFAULT NULL,
  `exp_date` date DEFAULT NULL,
  `issue_date` date NOT NULL,
  `passport_no` varchar(255) DEFAULT NULL,
  `passport_pilgrim` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKheofuvxpjjwx47ivmij53had6` (`passport_pilgrim`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `passport_info`
--

/*!40000 ALTER TABLE `passport_info` DISABLE KEYS */;
INSERT INTO `passport_info` (`id`,`dip`,`exp_date`,`issue_date`,`passport_no`,`passport_pilgrim`) VALUES 
 (1,'Dhaka','2023-03-03','2018-03-04','TR0274123',12),
 (2,'Dhaka','2022-10-07','2017-10-08','WR0247123',13),
 (3,'Dhaka','2024-04-06','2019-04-07','TM0247123',14),
 (4,'Dhaka','2024-04-01','2019-04-01','BQ8174123',5);
/*!40000 ALTER TABLE `passport_info` ENABLE KEYS */;


--
-- Definition of table `payment_details`
--

DROP TABLE IF EXISTS `payment_details`;
CREATE TABLE `payment_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_amount` double NOT NULL,
  `due_amount` double NOT NULL,
  `total_amount` double NOT NULL,
  `booksummary_pilgrim` bigint(20) NOT NULL,
  `booksummary_package` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqwiojpr6o20mfhwirb7l25t1r` (`booksummary_pilgrim`),
  KEY `FKb6ybpkmw1yai95cwf8n8du3pu` (`booksummary_package`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment_details`
--

/*!40000 ALTER TABLE `payment_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_details` ENABLE KEYS */;


--
-- Definition of table `pilgrim`
--

DROP TABLE IF EXISTS `pilgrim`;
CREATE TABLE `pilgrim` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date NOT NULL,
  `booking_amount` double NOT NULL,
  `district` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) NOT NULL,
  `file_extension` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nid` varchar(255) NOT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `pilgrim_number` varchar(255) NOT NULL,
  `regi_date` datetime DEFAULT NULL,
  `spouse_name` varchar(255) DEFAULT NULL,
  `tolal_amount` double NOT NULL,
  `gl_id` bigint(20) NOT NULL,
  `pk_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hcqr6oulajqarb8u9o3blfqr0` (`pilgrim_number`),
  KEY `FK2b827o3y6xyetpdry78p3pan0` (`gl_id`),
  KEY `FK9xpw2r2mrm9x860ymkhffeb5b` (`pk_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pilgrim`
--

/*!40000 ALTER TABLE `pilgrim` DISABLE KEYS */;
INSERT INTO `pilgrim` (`id`,`address`,`birth_date`,`booking_amount`,`district`,`email`,`father_name`,`file_extension`,`file_name`,`file_path`,`file_size`,`gender`,`last_modified_date`,`marital_status`,`mother_name`,`name`,`nid`,`occupation`,`pilgrim_number`,`regi_date`,`spouse_name`,`tolal_amount`,`gl_id`,`pk_id`) VALUES 
 (1,'16/A, Shaorapara, Mirpur','1991-12-10',55000,'Narayangonj','mamun@gmain.com','Abdullah',NULL,NULL,NULL,0,'Male','2019-04-17 23:06:18',NULL,'Habiba','Abdullah Al Mamun','2613869450164','Business','2019001','2019-04-17 23:06:18','Monika',0,1,1),
 (2,'507, Khejurbag, South Keranigonj, Dhaka','1988-01-05',44000,'Munsigonj','khurshed@gmain.com','Nazrul Islam',NULL,NULL,NULL,0,'Male','2019-04-17 23:08:10',NULL,'Khaleda Begum','Md. Khurshed Alam','1911608450','Govt. Service','2019002','2019-04-17 23:08:10','',0,2,1),
 (3,'Sutrapur, Millbarac, Dhaka','2000-02-10',65000,'Comilla','khadiza@gmalil.com','Khaleq',NULL,NULL,NULL,0,'Female','2019-04-18 07:52:59',NULL,'Amena Bibi','Khadiza Begum','1011608454','House Wife','2019003','2019-04-18 07:52:59','Abdullah',0,4,3),
 (5,'Kataban','1958-02-21',49000,'Comilla','khairul@gmail.com','Oliullah',NULL,NULL,NULL,0,'Male','2019-04-18 08:02:06',NULL,'Golsan Begum','Khairul Alam','19682694073736400','Retired','2019005','2019-04-18 08:02:06','Monika',0,3,3),
 (6,'Rupgonj, Narayangonj','2019-04-01',50000,'Noakhali','sakmahbub@gmail.com','Abdullah',NULL,NULL,NULL,0,'Female','2019-04-20 10:20:38',NULL,'Umme','Rojina Akter','2613869450000','Privet Service','2019009','2019-04-20 10:20:39','Rasel',0,1,1),
 (7,'jigatola','2019-03-31',20000,'Noakhali','molgy@gmail.com','Abdullah',NULL,NULL,NULL,0,'Male','2019-04-20 10:30:59',NULL,'Umme','Rojina Akter','261386955450000','Privet Service','2019099','2019-04-20 10:30:59','Rasel',0,1,1),
 (8,'jigatola','2019-04-14',50000,'Noakhali','moffly@gmail.com','Abdullah',NULL,NULL,NULL,0,'Male','2019-04-20 10:33:14',NULL,'Umme','Rojina Akter','261381269450000','Privet Service','2019651','2019-04-20 10:33:14','rasel',0,1,1),
 (9,'jigatola','2019-04-15',50000,'Noakhali','molsdsy@gmail.com','Abdullah',NULL,NULL,NULL,0,'Male','2019-04-20 10:36:35',NULL,'Umme','Rojina Akter','2665513869450000','Privet Service','20199001','2019-04-20 10:36:35','Rasel',0,1,1),
 (10,'jigatola','2019-04-15',50000,'Noakhali','mfddoly@gmail.com','Abdullah',NULL,NULL,NULL,0,'Male','2019-04-20 10:39:33',NULL,'Umme','Rojina Akter','2613845469450147','Privet Service','2019066','2019-04-20 10:39:34','rasel',0,1,1),
 (11,'Kataban','2019-04-21',40000,'Bagura','azfgfffad@gmail.com','Hassan',NULL,NULL,NULL,0,'Male','2019-04-20 10:40:53',NULL,'Umme','Jobayer','264413869450147','Business','201900166','2019-04-20 10:40:53','rasel',0,1,1),
 (12,'Rupgonj, Narayangonj','2019-04-14',65000,'Bandorban','rojina87@gmail.com','Abdullah',NULL,NULL,NULL,0,'Female','2019-04-20 11:07:18',NULL,'Rasida','Rojina Akter','1088148450','House Wife','2001','2019-04-20 11:07:19','Rasel',0,2,2),
 (13,'Kataban','2019-04-07',55000,'Khulna','amzad@gmail.com','Azad',NULL,NULL,NULL,0,'Male','2019-04-20 11:09:43',NULL,'Mazeda','Amzad Hossain','261354750147','Business','2002','2019-04-20 11:09:43','Monika',0,2,3),
 (14,'Syampur','2001-07-07',64000,'Satkhira','ainuddin@gmail.com','Al Azad',NULL,NULL,NULL,0,'Male','2019-04-20 11:12:28',NULL,'Hosneara','Ainuddin Al Azad','2661769450177','Business','2003','2019-04-20 11:12:28','Happy',0,3,1);
/*!40000 ALTER TABLE `pilgrim` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`,`role_name`) VALUES 
 (1,'SUPERADMIN'),
 (2,'ADMIN'),
 (3,'STAFF'),
 (4,'GROUPLEADER'),
 (5,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `ticket_flight`
--

DROP TABLE IF EXISTS `ticket_flight`;
CREATE TABLE `ticket_flight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flight_date` date DEFAULT NULL,
  `flight_no` varchar(255) NOT NULL,
  `return_date` date DEFAULT NULL,
  `ticket_no` varchar(255) NOT NULL,
  `tik_agn_name` varchar(255) DEFAULT NULL,
  `pilg_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gulyqlkso1ln7cwruvujutssu` (`ticket_no`),
  KEY `FKd807a22i24xqx7eyac8om3vv5` (`pilg_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_flight`
--

/*!40000 ALTER TABLE `ticket_flight` DISABLE KEYS */;
INSERT INTO `ticket_flight` (`id`,`flight_date`,`flight_no`,`return_date`,`ticket_no`,`tik_agn_name`,`pilg_id`) VALUES 
 (1,NULL,'MEALS BOEING 747 400,2019-04-23','2019-06-15','06874788479561','Sun Shine Travels Inc',12);
/*!40000 ALTER TABLE `ticket_flight` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date NOT NULL,
  `confirmation_token` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `file_extension` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `regi_date` datetime DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`),
  UNIQUE KEY `UK_cnjwxx5favk5ycqajjt17fwy1` (`mobile`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`birth_date`,`confirmation_token`,`email`,`enabled`,`file_extension`,`file_name`,`file_path`,`file_size`,`first_name`,`gender`,`last_modified_date`,`last_name`,`mobile`,`password`,`regi_date`,`user_name`) VALUES 
 (1,'2019-04-17','142aea79-e6a3-4195-9e60-baf7d4299eea','sadmin@hams.com',0x01,NULL,NULL,NULL,0,'Sofiqul',NULL,'2019-04-17 22:31:58','Islam',NULL,'$2a$10$TQZOLVgoRKa/aMgmzyb.hOp4iEvWNYAD22a/.ycErulk4K7Kgd1XC',NULL,'saadmin'),
 (2,'1986-10-08','0b29a1be-33f6-4131-b1f5-d513ebf4731f','kabir@gmail.com',0x01,'image/jpeg','new-images4.jpg','/images/new-images4.jpg',5464,'Humaun','mail','2019-04-18 01:23:13','Kabir','01748567000','123456789','2019-04-17 22:55:19','kabir'),
 (3,'1969-09-09','3b9853d9-d98c-4b7e-83b6-06e0733ad3d0','tusar@gmail.com',0x01,'image/jpeg','new-images21.jpg','/images/new-images21.jpg',172140,'Tusar','mail','2019-04-17 22:57:59','Ahmed','01912124046','1234','2019-04-17 22:57:06','tusar'),
 (4,'1986-10-08','bd3f8d56-143c-43a5-bbbf-6a0049bec29f','madaniatravels@gmail.com',0x01,'image/jpeg','new-images5.jpg','/images/new-images5.jpg',5641,'Zakir','Male','2019-04-18 07:34:56','Hossain','01711026302','123456789','2019-04-18 07:26:58','madaniatravels'),
 (5,'1968-10-04','618ff83a-44e4-4b51-82b1-5524d9bbe463','azhar@gmail.com',0x01,'image/jpeg','new-images0.jpg','/images/new-images0.jpg',5497,'Azhar','Male','2019-04-18 07:40:59','Uddin','01711026303','$2a$10$04deuBibF5tNZc59KVrm7ummKqWYY6hy6BMJB6X.uK1vQ8QpVjNLe','2019-04-18 07:37:28','azhar');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`,`role_id`) VALUES 
 (1,1),
 (2,1),
 (2,2),
 (2,3),
 (2,4),
 (2,5),
 (3,2),
 (4,5),
 (5,1),
 (5,2),
 (5,3),
 (5,4),
 (5,5);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;


--
-- Definition of table `visa_info`
--

DROP TABLE IF EXISTS `visa_info`;
CREATE TABLE `visa_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `v_issue_date` date DEFAULT NULL,
  `v_validity_date` date DEFAULT NULL,
  `visa_no` varchar(255) NOT NULL,
  `pil_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_goskbqlfiteugmhhfsxeqmyl5` (`visa_no`),
  KEY `FKm64fga7dkr25220pb0b342cie` (`pil_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visa_info`
--

/*!40000 ALTER TABLE `visa_info` DISABLE KEYS */;
INSERT INTO `visa_info` (`id`,`apply_date`,`status`,`v_issue_date`,`v_validity_date`,`visa_no`,`pil_id`) VALUES 
 (1,'2019-04-11',NULL,'2019-04-23','2019-06-10','4011214761',12),
 (2,'2019-04-09',NULL,'2019-04-25','2019-06-15','4019177262',13),
 (3,'2019-04-08',NULL,'2019-04-16','2019-04-21','40471667261',14),
 (4,'2019-03-31',NULL,'2019-04-30','2019-06-15','4095777262',5);
/*!40000 ALTER TABLE `visa_info` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
