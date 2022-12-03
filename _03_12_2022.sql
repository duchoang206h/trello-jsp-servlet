-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: trello
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Current Database: `trello`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `trello` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `trello`;

--
-- Table structure for table `boards`
--

DROP TABLE IF EXISTS `boards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) NOT NULL,
  `ownerId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `boards_id_uindex` (`id`),
  KEY `boards_users_id_fk` (`ownerId`),
  CONSTRAINT `boards_users_id_fk` FOREIGN KEY (`ownerId`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=528 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boards`
--

LOCK TABLES `boards` WRITE;
/*!40000 ALTER TABLE `boards` DISABLE KEYS */;
INSERT INTO `boards` VALUES (5,'aaaeeeeeeeeeee',2),(6,'aaa',2),(7,'aaa',2),(8,'aaa',2),(9,'aaa',2),(10,'aaa',2),(11,'aaa',2),(12,'aaa',2),(13,'aaa',2),(14,'aaa',2),(15,'aaa',2),(16,'aaa',2),(17,'aaa',2),(18,'aaa',2),(19,'aaa',2),(20,'aaa',2),(21,'aaa',2),(22,'aaa',2),(23,'aaa',2),(24,'aaa',2),(25,'aaa',2),(26,'aaa',2),(27,'aaa',2),(28,'aaa',2),(29,'aaa',2),(30,'aaa',2),(31,'aaa',2),(32,'aaa',2),(33,'aaa',2),(34,'aaa',2),(35,'aaa',2),(36,'aaa',2),(37,'aaa',2),(38,'aaa',2),(39,'aaa',2),(40,'aaa',2),(41,'aaa',2),(42,'aaa',2),(43,'aaa',2),(44,'aaa',2),(45,'aaa',2),(46,'aaa',2),(47,'aaa',2),(48,'aaa',2),(49,'aaa',2),(50,'aaa',2),(51,'aaa',2),(52,'aaa',2),(53,'aaa',2),(54,'aaa',2),(55,'aaa',2),(56,'aaa',2),(57,'aaa',2),(58,'aaa',2),(59,'aaa',2),(60,'aaa',2),(61,'aaa',2),(62,'aaa',2),(63,'aaa',2),(64,'aaa',2),(65,'aaa',2),(66,'aaa',2),(67,'aaa',2),(68,'aaa',2),(69,'aaa',2),(70,'aaa',2),(71,'aaa',2),(72,'aaa',2),(73,'aaa',2),(74,'aaa',2),(75,'aaa',2),(76,'aaa',2),(77,'aaa',2),(78,'aaa',2),(79,'aaa',2),(80,'aaa',2),(81,'aaa',2),(82,'aaa',2),(83,'aaa',2),(84,'aaa',2),(85,'aaa',2),(86,'aaa',2),(87,'aaa',2),(88,'aaa',2),(89,'aaa',2),(90,'aaa',2),(91,'aaa',2),(92,'aaa',2),(93,'aaa',2),(94,'aaa',2),(95,'aaa',2),(96,'aaa',2),(97,'aaa',2),(98,'aaa',2),(99,'aaa',2),(100,'aaa',2),(101,'aaa',2),(102,'aaa',2),(103,'aaa',2),(104,'aaa',2),(105,'aaa',2),(106,'aaa',2),(107,'aaa',2),(108,'aaa',2),(109,'aaa',2),(110,'aaa',2),(111,'aaa',2),(112,'aaa',2),(113,'aaa',2),(114,'aaa',2),(115,'aaa',2),(116,'aaa',2),(117,'aaa',2),(118,'aaa',2),(119,'aaa',2),(120,'aaa',2),(121,'aaa',2),(122,'aaa',2),(123,'aaa',2),(124,'aaa',2),(125,'aaa',2),(126,'aaa',2),(127,'aaa',2),(128,'aaa',2),(129,'aaa',2),(130,'aaa',2),(131,'aaa',2),(132,'aaa',2),(133,'aaa',2),(134,'aaa',2),(135,'aaa',2),(136,'aaa',2),(137,'aaa',2),(138,'aaa',2),(139,'aaa',2),(140,'aaa',2),(141,'aaa',2),(142,'aaa',2),(143,'aaa',2),(144,'aaa',2),(145,'aaa',2),(146,'aaa',2),(147,'aaa',2),(148,'aaa',2),(149,'aaa',2),(150,'aaa',2),(151,'aaa',2),(152,'aaa',2),(153,'aaa',2),(154,'aaa',2),(155,'aaa',2),(156,'aaa',2),(157,'aaa',2),(158,'aaa',2),(159,'aaa',2),(160,'aaa',2),(161,'aaa',2),(162,'aaa',2),(163,'aaa',2),(164,'aaa',2),(165,'aaa',2),(166,'aaa',2),(167,'aaa',2),(168,'aaa',2),(169,'aaa',2),(170,'aaa',2),(171,'aaa',2),(172,'aaa',2),(173,'aaa',2),(174,'aaa',2),(175,'aaa',2),(176,'aaa',2),(177,'aaa',2),(178,'aaa',2),(179,'aaa',2),(180,'aaa',2),(181,'aaa',2),(182,'aaa',2),(183,'aaa',2),(184,'aaa',2),(185,'aaa',2),(186,'aaa',2),(187,'aaa',2),(188,'aaa',2),(189,'aaa',2),(190,'aaa',2),(191,'aaa',2),(192,'aaa',2),(193,'aaa',2),(194,'aaa',2),(195,'aaa',2),(196,'aaa',2),(197,'aaa',2),(198,'aaa',2),(199,'aaa',2),(200,'aaa',2),(201,'aaa',2),(202,'aaa',2),(203,'aaa',2),(204,'aaa',2),(205,'aaa',2),(206,'aaa',2),(207,'aaa',2),(208,'aaa',2),(209,'aaa',2),(210,'aaa',2),(211,'aaa',2),(212,'aaa',2),(213,'aaa',2),(214,'aaa',2),(215,'aaa',2),(216,'aaa',2),(217,'aaa',2),(218,'aaa',2),(219,'aaa',2),(220,'aaa',2),(221,'aaa',2),(222,'aaa',2),(223,'aaa',2),(224,'aaa',2),(225,'aaa',2),(226,'aaa',2),(227,'aaa',2),(228,'aaa',2),(229,'aaa',2),(230,'aaa',2),(231,'aaa',2),(232,'aaa',2),(233,'aaa',2),(234,'aaa',2),(235,'aaa',2),(236,'aaa',2),(237,'aaa',2),(238,'aaa',2),(239,'aaa',2),(240,'aaa',2),(241,'aaa',2),(242,'aaa',2),(243,'aaa',2),(244,'aaa',2),(245,'aaa',2),(246,'aaa',2),(247,'aaa',2),(248,'aaa',2),(249,'aaa',2),(250,'aaa',2),(251,'aaa',2),(252,'aaa',2),(253,'aaa',2),(254,'aaa',2),(255,'aaa',2),(256,'aaa',2),(257,'aaa',2),(258,'aaa',2),(259,'aaa',2),(260,'aaa',2),(261,'aaa',2),(262,'aaa',2),(263,'aaa',2),(264,'aaa',2),(265,'aaa',2),(266,'aaa',2),(267,'aaa',2),(268,'aaa',2),(269,'aaa',2),(270,'aaa',2),(271,'aaa',2),(272,'aaa',2),(273,'aaa',2),(274,'aaa',2),(275,'aaa',2),(276,'aaa',2),(277,'aaa',2),(278,'aaa',2),(279,'aaa',2),(280,'aaa',2),(281,'aaa',2),(282,'aaa',2),(283,'aaa',2),(284,'aaa',2),(285,'aaa',2),(286,'aaa',2),(287,'aaa',2),(288,'aaa',2),(289,'aaa',2),(290,'aaa',2),(291,'aaa',2),(292,'aaa',2),(293,'aaa',2),(294,'aaa',2),(295,'aaa',2),(296,'aaa',2),(297,'aaa',2),(298,'aaa',2),(299,'aaa',2),(300,'aaa',2),(301,'aaa',2),(302,'aaa',2),(303,'aaa',2),(304,'aaa',2),(305,'aaa',2),(306,'aaa',2),(307,'aaa',2),(308,'aaa',2),(309,'aaa',2),(310,'aaa',2),(311,'aaa',2),(312,'aaa',2),(313,'aaa',2),(314,'aaa',2),(315,'aaa',2),(316,'aaa',2),(317,'aaa',2),(318,'aaa',2),(319,'aaa',2),(320,'aaa',2),(321,'aaa',2),(322,'aaa',2),(323,'aaa',2),(324,'aaa',2),(325,'aaa',2),(326,'aaa',2),(327,'aaa',2),(328,'aaa',2),(329,'aaa',2),(330,'aaa',2),(331,'aaa',2),(332,'aaa',2),(333,'aaa',2),(334,'aaa',2),(335,'aaa',2),(336,'aaa',2),(337,'aaa',2),(338,'aaa',2),(339,'aaa',2),(340,'aaa',2),(341,'aaa',2),(342,'aaa',2),(343,'aaa',2),(344,'aaa',2),(345,'aaa',2),(346,'aaa',2),(347,'aaa',2),(348,'aaa',2),(349,'aaa',2),(350,'aaa',2),(351,'aaa',2),(352,'aaa',2),(353,'aaa',2),(354,'aaa',2),(355,'aaa',2),(356,'aaa',2),(357,'aaa',2),(358,'aaa',2),(359,'aaa',2),(360,'aaa',2),(361,'aaa',2),(362,'aaa',2),(363,'aaa',2),(364,'aaa',2),(365,'aaa',2),(366,'aaa',2),(367,'aaa',2),(368,'aaa',2),(369,'aaa',2),(370,'aaa',2),(371,'aaa',2),(372,'aaa',2),(373,'aaa',2),(374,'aaa',2),(375,'aaa',2),(376,'aaa',2),(377,'aaa',2),(378,'aaa',2),(379,'aaa',2),(380,'aaa',2),(381,'aaa',2),(382,'aaa',2),(383,'aaa',2),(384,'aaa',2),(385,'aaa',2),(386,'aaa',2),(387,'aaa',2),(388,'aaa',2),(389,'aaa',2),(390,'aaa',2),(391,'aaa',2),(392,'aaa',2),(393,'aaa',2),(394,'aaa',2),(395,'aaa',2),(396,'aaa',2),(397,'aaa',2),(398,'aaa',2),(399,'aaa',2),(400,'aaa',2),(401,'aaa',2),(402,'aaa',2),(403,'aaa',2),(404,'aaa',2),(405,'aaa',2),(406,'aaa',2),(407,'aaa',2),(408,'aaa',2),(409,'aaa',2),(410,'aaa',2),(411,'aaa',2),(412,'aaa',2),(413,'aaa',2),(414,'aaa',2),(415,'aaa',2),(416,'aaa',2),(417,'aaa',2),(418,'aaa',2),(419,'aaa',2),(420,'aaa',2),(421,'aaa',2),(422,'aaa',2),(423,'aaa',2),(424,'aaa',2),(425,'aaa',2),(426,'aaa',2),(427,'aaa',2),(428,'aaa',2),(429,'aaa',2),(430,'aaa',2),(431,'aaa',2),(432,'aaa',2),(433,'aaa',2),(434,'aaa',2),(435,'aaa',2),(436,'aaa',2),(437,'aaa',2),(438,'aaa',2),(439,'aaa',2),(440,'aaa',2),(441,'aaa',2),(442,'aaa',2),(443,'aaa',2),(444,'aaa',2),(445,'aaa',2),(446,'aaa',2),(447,'aaa',2),(448,'aaa',2),(449,'aaa',2),(450,'aaa',2),(451,'aaa',2),(452,'aaa',2),(453,'aaa',2),(454,'aaa',2),(455,'aaa',2),(456,'aaa',2),(457,'aaa',2),(458,'aaa',2),(459,'aaa',2),(460,'aaa',2),(461,'aaa',2),(462,'aaa',2),(463,'aaa',2),(464,'aaa',2),(465,'aaa',2),(466,'aaa',2),(467,'aaa',2),(468,'aaa',2),(469,'aaa',2),(470,'aaa',2),(471,'aaa',2),(472,'aaa',2),(473,'aaa',2),(474,'aaa',2),(475,'aaa',2),(476,'aaa',2),(477,'aaa',2),(478,'aaa',2),(479,'aaa',2),(480,'aaa',2),(481,'aaa',2),(482,'aaa',2),(483,'aaa',2),(484,'aaa',2),(485,'aaa',2),(486,'aaa',2),(487,'aaa',2),(488,'aaa',2),(489,'aaa',2),(490,'aaa',2),(491,'aaa',2),(492,'aaa',2),(493,'aaa',2),(494,'aaa',2),(495,'aaa',2),(496,'aaa',2),(497,'aaa',2),(498,'aaa',2),(499,'aaa',2),(500,'aaa',2),(501,'aaa',2),(502,'aaa',2),(503,'aaa',2),(504,'aaa',2),(505,'aaa',2),(506,'aaa',2),(507,'aaa',2),(508,'aaa',2),(509,'aaa',2),(510,'aaa',2),(511,'aaa',2),(512,'aaa',2),(513,'aaa',2),(514,'aaa',2),(515,'aaa',2),(516,'aaa',2),(517,'aaa',2),(518,'aaa',2),(519,'aaa',2),(520,'aaa',2),(521,'aaa',2),(522,'aaa',2),(523,'vdx',2),(524,'cdcdcdcd',2),(525,'hoduchoang',2),(526,'cong viec',2),(527,'dddd',2);
/*!40000 ALTER TABLE `boards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `listId` int NOT NULL,
  `boardId` int NOT NULL,
  `order` int NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `cards_boards_id_fk` (`boardId`),
  KEY `cards_lists_id_fk` (`listId`),
  CONSTRAINT `cards_boards_id_fk` FOREIGN KEY (`boardId`) REFERENCES `boards` (`id`) ON DELETE CASCADE,
  CONSTRAINT `cards_lists_id_fk` FOREIGN KEY (`listId`) REFERENCES `lists` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lists`
--

DROP TABLE IF EXISTS `lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lists` (
  `name` varchar(191) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `order` int NOT NULL,
  `boardId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lists_boards_id_fk` (`boardId`),
  CONSTRAINT `lists_boards_id_fk` FOREIGN KEY (`boardId`) REFERENCES `boards` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lists`
--

LOCK TABLES `lists` WRITE;
/*!40000 ALTER TABLE `lists` DISABLE KEYS */;
INSERT INTO `lists` VALUES ('aaa',3,0,5),('ae',4,0,5);
/*!40000 ALTER TABLE `lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `name` varchar(191) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(191) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('afsd','fdsa@fads','$2a$10$aZZbiFxITAvE45qjdisStO2y8wbkV2Lo5t.IXgcCnWJ.ix1TIPsQK',1),('Duc Hoang','duchoang206h@gmail.com','$2a$10$UQn.cG.lKX3o94zMTIEL2.wKn/rMtjSzMtO3zc/QyEf3EyYbMFl.C',2),('acea','cae@gmail.com','$2a$10$JWmTYnCrCB5DY53xmbiKDeAlX8i4ZcccAN4/nKgFLOjRWit50CTD2',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-03  8:06:06
