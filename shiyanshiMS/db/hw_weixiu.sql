-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: hw
-- ------------------------------------------------------
-- Server version	5.7.3-m13

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

--
-- Table structure for table `weixiu`
--

DROP TABLE IF EXISTS `weixiu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weixiu` (
  `Wxid` int(11) NOT NULL AUTO_INCREMENT,
  `sbname` varchar(45) DEFAULT NULL,
  `yuanyin` varchar(45) DEFAULT NULL,
  `fuzeren` varchar(45) DEFAULT NULL,
  `wxdanwei` varchar(45) DEFAULT NULL,
  `wxdidian` varchar(45) DEFAULT NULL,
  `wxbeizhu` varchar(200) DEFAULT NULL,
  `wxdate` varchar(45) DEFAULT NULL,
  `wxshenheren` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Wxid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weixiu`
--

LOCK TABLES `weixiu` WRITE;
/*!40000 ALTER TABLE `weixiu` DISABLE KEYS */;
INSERT INTO `weixiu` VALUES (1,'音响','外壳潮湿进水','马国荣','银川市联想分公司','西夏区同心路','音箱标号5','周四 五月 5 2016','李军'),(6,'h6','h6','h6','6','h6','h6','h6','h6');
/*!40000 ALTER TABLE `weixiu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-30 16:17:07
