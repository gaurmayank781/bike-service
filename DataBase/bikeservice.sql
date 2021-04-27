/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - onlinebikeservice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinebikeservice` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `onlinebikeservice`;

/*Table structure for table `b_feedback` */

DROP TABLE IF EXISTS `b_feedback`;

CREATE TABLE `b_feedback` (
  `ID` bigint(20) NOT NULL,
  `userName` varchar(225) DEFAULT NULL,
  `subject` varchar(225) DEFAULT NULL,
  `message` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `b_feedback` */

insert  into `b_feedback`(`ID`,`userName`,`subject`,`message`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Hari123','fb','dvsdv','root','root','2019-03-04 20:07:32','2019-03-04 20:07:32');

/*Table structure for table `b_invoice` */

DROP TABLE IF EXISTS `b_invoice`;

CREATE TABLE `b_invoice` (
  `ID` bigint(20) NOT NULL,
  `invoiceId` bigint(20) DEFAULT NULL,
  `userName` varchar(225) DEFAULT NULL,
  `partName` varchar(225) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `b_invoice` */

insert  into `b_invoice`(`ID`,`invoiceId`,`userName`,`partName`,`price`,`quantity`,`date`,`amount`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,1,'Hari123','df',1200,2,'2019-03-05',2400,'Hari123','Hari123','2019-03-05 18:16:57','2019-03-05 18:16:57'),(2,2,'Hari123','sac',1300,1,'2019-03-05',1300,'Hari123','Hari123','2019-03-05 20:11:51','2019-03-05 20:11:51'),(3,3,'Hari123','sedv',1300,1,'2019-03-05',1300,'Hari123','Hari123','2019-03-05 20:13:34','2019-03-05 20:13:34'),(4,4,'Hari123','f gjh',16,6,'2019-03-05',96,'Hari123','Hari123','2019-03-05 20:15:35','2019-03-05 20:15:35'),(5,5,'Hari123','ss',6,2,'2019-03-05',12,'Hari123','Hari123','2019-03-05 20:16:14','2019-03-05 20:16:14'),(6,10010,'Hari123','dvs',5,3,'2019-03-05',15,'Hari123','Hari123','2019-03-05 20:18:28','2019-03-05 20:17:32'),(7,10011,'dsv','vd',15,25,'2019-03-05',375,'Hari123','Hari123','2019-03-05 20:18:41','2019-03-05 20:18:41'),(8,10012,'1','1',16,6,'2019-03-06',96,'Hari123','Hari123','2019-03-06 09:25:38','2019-03-06 09:25:38');

/*Table structure for table `b_parts` */

DROP TABLE IF EXISTS `b_parts`;

CREATE TABLE `b_parts` (
  `ID` bigint(20) NOT NULL,
  `company` varchar(225) DEFAULT NULL,
  `partName` varchar(225) DEFAULT NULL,
  `frequency` varchar(225) DEFAULT NULL,
  `price` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `b_parts` */

insert  into `b_parts`(`ID`,`company`,`partName`,`frequency`,`price`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'cc','rg','111','1300','Hari123','Hari123','2019-03-05 13:53:41','2019-03-05 14:10:09');

/*Table structure for table `b_service` */

DROP TABLE IF EXISTS `b_service`;

CREATE TABLE `b_service` (
  `ID` bigint(20) NOT NULL,
  `company` varchar(225) DEFAULT NULL,
  `userName` varchar(225) DEFAULT NULL,
  `modelNo` varchar(225) DEFAULT NULL,
  `vehicleNo` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `b_service` */

insert  into `b_service`(`ID`,`company`,`userName`,`modelNo`,`vehicleNo`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'cc','Hari123','135fcs533','Mp2135','root','root','2019-03-04 18:59:48','2019-03-04 18:59:48');

/*Table structure for table `b_user` */

DROP TABLE IF EXISTS `b_user`;

CREATE TABLE `b_user` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `pinCode` varchar(225) DEFAULT NULL,
  `city` varchar(225) DEFAULT NULL,
  `state` varchar(225) DEFAULT NULL,
  `serviceType` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `b_user` */

insert  into `b_user`(`ID`,`firstName`,`lastName`,`login`,`password`,`mobileNo`,`address`,`pinCode`,`city`,`state`,`serviceType`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Hariom','Mukati','Hari123','321','9165415598','BDGIGIGD','653636','Indore','MP','HHH','root','root','2019-03-04 19:22:22','2019-03-04 18:58:20');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
