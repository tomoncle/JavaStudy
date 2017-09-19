/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.11-log : Database - aric-spring
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aric-spring` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `aric-spring`;

/*Table structure for table `t_emp` */

DROP TABLE IF EXISTS `t_emp`;

CREATE TABLE `t_emp` (
  `eid` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `deptId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_emp` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  `isDeleted` int(1) NOT NULL,
  `loginTimes` int(8) DEFAULT NULL,
  `lastLogin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`createAt`,`isDeleted`,`loginTimes`,`lastLogin`) values ('1','user01','123456','2016-01-09 23:00:01',0,0,'2016-05-05'),('a521a9a52ddf466aa76df50b629e66af','jackson','123456','2016-05-05 15:57:13',0,0,NULL),('e6b4289047c44630a1804178eab125e5','jackson','123456','2016-05-05 16:03:51',0,0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
