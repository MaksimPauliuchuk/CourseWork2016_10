-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: fp_db
-- ------------------------------------------------------
-- Server version	5.5.47-0+deb7u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP SCHEMA IF EXISTS `fp_db`;
CREATE SCHEMA IF NOT EXISTS `fp_db` DEFAULT CHARACTER SET utf8 ;

USE `fp_db`;

--
-- Table structure for table `Bus`
--

DROP TABLE IF EXISTS `Bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bus` (
  `id` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `direction_id` int(11) DEFAULT NULL,
  `route_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fx8yqvicaab1r0y48ueghkqbc` (`direction_id`),
  KEY `FK_b6bvwr3klaknjfcbeu7aw7pjs` (`route_id`),
  KEY `FK_4yhtu53bm2mb85pkjejrvklpe` (`schedule_id`),
  CONSTRAINT `FK_4yhtu53bm2mb85pkjejrvklpe` FOREIGN KEY (`schedule_id`) REFERENCES `Schedule` (`id`),
  CONSTRAINT `FK_b6bvwr3klaknjfcbeu7aw7pjs` FOREIGN KEY (`route_id`) REFERENCES `Route` (`id`),
  CONSTRAINT `FK_fx8yqvicaab1r0y48ueghkqbc` FOREIGN KEY (`direction_id`) REFERENCES `Direction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Direction`
--

DROP TABLE IF EXISTS `Direction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Direction` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Route`
--

DROP TABLE IF EXISTS `Route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Route` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Route_Stop`
--

DROP TABLE IF EXISTS `Route_Stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Route_Stop` (
  `Route_id` int(11) NOT NULL,
  `stops_id` int(11) NOT NULL,
  KEY `FK_5khgmf3ep16rsfdfuwl0unck9` (`stops_id`),
  KEY `FK_8s4bv55m5w7as93s1p41y15hu` (`Route_id`),
  CONSTRAINT `FK_8s4bv55m5w7as93s1p41y15hu` FOREIGN KEY (`Route_id`) REFERENCES `Route` (`id`),
  CONSTRAINT `FK_5khgmf3ep16rsfdfuwl0unck9` FOREIGN KEY (`stops_id`) REFERENCES `Stop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Schedule`
--

DROP TABLE IF EXISTS `Schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Schedule` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Schedule_Trip`
--

DROP TABLE IF EXISTS `Schedule_Trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Schedule_Trip` (
  `Schedule_id` int(11) NOT NULL,
  `wednesday_id` int(11) DEFAULT NULL,
  `tuesday_id` int(11) DEFAULT NULL,
  `thursday_id` int(11) DEFAULT NULL,
  `sunday_id` int(11) DEFAULT NULL,
  `saturday_id` int(11) DEFAULT NULL,
  `monday_id` int(11) DEFAULT NULL,
  `friday_id` int(11) DEFAULT NULL,
  KEY `FK_4efy4u4xm81s1os72xs7auohn` (`wednesday_id`),
  KEY `FK_6n7qqe3ckuwmgih5pat1s5evn` (`Schedule_id`),
  KEY `FK_e7gxw1ikyi0q6shnqjxaxg3d` (`tuesday_id`),
  KEY `FK_53lm7l9l6erirknbvfqio2097` (`thursday_id`),
  KEY `FK_q64ew9tm4fqcw2g3aob0mjdpn` (`sunday_id`),
  KEY `FK_644kk2oc6q8nm72x45cr41uvk` (`saturday_id`),
  KEY `FK_3hjng8n6yuj2o3txumlbl0xun` (`monday_id`),
  KEY `FK_4xqnkv6xgm1jsmc9d8mjb93eo` (`friday_id`),
  CONSTRAINT `FK_4xqnkv6xgm1jsmc9d8mjb93eo` FOREIGN KEY (`friday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_3hjng8n6yuj2o3txumlbl0xun` FOREIGN KEY (`monday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_4efy4u4xm81s1os72xs7auohn` FOREIGN KEY (`wednesday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_53lm7l9l6erirknbvfqio2097` FOREIGN KEY (`thursday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_644kk2oc6q8nm72x45cr41uvk` FOREIGN KEY (`saturday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_e7gxw1ikyi0q6shnqjxaxg3d` FOREIGN KEY (`tuesday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_q64ew9tm4fqcw2g3aob0mjdpn` FOREIGN KEY (`sunday_id`) REFERENCES `Trip` (`id`),
  CONSTRAINT `FK_6n7qqe3ckuwmgih5pat1s5evn` FOREIGN KEY (`Schedule_id`) REFERENCES `Schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Stop`
--

DROP TABLE IF EXISTS `Stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stop` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Trip`
--

DROP TABLE IF EXISTS `Trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trip` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Trip_time`
--

DROP TABLE IF EXISTS `Trip_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trip_time` (
  `Trip_id` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  KEY `FK_dcbln2f4boy2mojfkhb445he2` (`Trip_id`),
  CONSTRAINT `FK_dcbln2f4boy2mojfkhb445he2` FOREIGN KEY (`Trip_id`) REFERENCES `Trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-27 16:46:30
