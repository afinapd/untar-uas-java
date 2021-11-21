-- MySQL dump 10.13  Distrib 8.0.27, for macos11.6 (x86_64)
--
-- Host: 127.0.0.1    Database: untarjava
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` text,
  `KWP` tinyint unsigned DEFAULT NULL,
  `status_payment_id` tinyint unsigned DEFAULT '3',
  `DP` int unsigned DEFAULT NULL,
  `FP` int unsigned DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_email_uindex` (`email`),
  KEY `customer_master_status_payment_id_fk` (`status_payment_id`),
  CONSTRAINT `customer_master_status_payment_id_fk` FOREIGN KEY (`status_payment_id`) REFERENCES `master_status_payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `email`, `phone`, `address`, `KWP`, `status_payment_id`, `DP`, `FP`, `created_at`, `updated_at`) VALUES (5,'vio1@gmail.com','08123456','address1',4,2,10000,20000,'2021-11-20 19:37:03',NULL),(6,'test','192390123','jalan satu',2,2,10000,20000,'2021-11-20 19:45:44',NULL),(7,'test2@test.co','0129','address11',4,3,19901,1212121,'2021-11-20 19:47:34',NULL),(8,'databaru@databaru.com','123123','asdasd',4,3,100012,12121212,'2021-11-20 19:50:52',NULL),(10,'lastdataaa@lastdataaa.com','0120','address1',4,3,1000,2000000,'2021-11-20 20:00:18',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leads`
--

DROP TABLE IF EXISTS `leads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leads` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `area` int unsigned DEFAULT NULL,
  `bills_per_month` int unsigned DEFAULT NULL,
  `va` int unsigned DEFAULT NULL,
  `status` tinyint unsigned DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `leads_email_uindex` (`email`),
  KEY `leads_master_leads_status_id_fk` (`status`),
  CONSTRAINT `leads_master_leads_status_id_fk` FOREIGN KEY (`status`) REFERENCES `master_leads_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leads`
--

LOCK TABLES `leads` WRITE;
/*!40000 ALTER TABLE `leads` DISABLE KEYS */;
INSERT INTO `leads` (`id`, `email`, `no_hp`, `area`, `bills_per_month`, `va`, `status`, `created_at`) VALUES (1,'eric@asd.com','08123445',5,5000,2200,2,'2021-11-20 01:05:24'),(2,'vio@gmail.com','08123456',5,5000,4400,2,'2021-11-20 01:07:21'),(3,'email@email.com','08123456',100,1000,5500,2,'2021-11-20 11:47:39'),(6,'ericanthonywu89@gmail.com','081237490',500,1000,2200,2,'2021-11-20 14:18:27'),(7,'ericanthonywu89@yahoo.com','08127639',500,1000,2200,2,'2021-11-20 14:19:35'),(8,'eric@test.com','081293',100,10000,2200,2,'2021-11-20 19:36:27'),(9,'eric123@kk.com','081203',100,212,2200,1,'2021-11-20 19:41:33'),(10,'test','192390123',100123,123123,2200,2,'2021-11-20 19:45:30'),(11,'test2@test.co','0129',100,121,2200,2,'2021-11-20 19:47:27'),(12,'databaru@databaru.com','123123',123123,123123123,2200,2,'2021-11-20 19:50:44'),(13,'123@asd.com','123123',32321,123123,2200,2,'2021-11-20 19:52:30'),(14,'lastdata@gmail.com','0812381293',1000,2000,4400,1,'2021-11-20 19:58:40'),(15,'lastdataaa@lastdataaa.com','0120',1212,1212,2200,2,'2021-11-20 19:59:12'),(16,'lastdataaaa@lastdataaaa.com','121212',1000,100000,4400,1,'2021-11-20 20:00:07'),(17,'erictse123@awd.com','0120',120120,12121,2200,1,'2021-11-20 20:07:44');
/*!40000 ALTER TABLE `leads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_leads_status`
--

DROP TABLE IF EXISTS `master_leads_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_leads_status` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_leads_status`
--

LOCK TABLES `master_leads_status` WRITE;
/*!40000 ALTER TABLE `master_leads_status` DISABLE KEYS */;
INSERT INTO `master_leads_status` (`id`, `name`) VALUES (1,'New'),(2,'Customer');
/*!40000 ALTER TABLE `master_leads_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_status_payment`
--

DROP TABLE IF EXISTS `master_status_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_status_payment` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_status_payment`
--

LOCK TABLES `master_status_payment` WRITE;
/*!40000 ALTER TABLE `master_status_payment` DISABLE KEYS */;
INSERT INTO `master_status_payment` (`id`, `name`) VALUES (1,'Paid'),(2,'Reject'),(3,'Waiting DP'),(4,'Waiting FP');
/*!40000 ALTER TABLE `master_status_payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-21 13:06:19
