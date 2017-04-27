-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: gradebook
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `ASSIGNMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ASSIGNMENT_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ASSIGNMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (100,'Application Homework 1'),(101,'Application Homework 2'),(102,'Database Homework 1'),(103,'Database Homework 2'),(107,'Test'),(108,'Develop Database Schema'),(109,'Study for Exam'),(110,'Develop Database Schema'),(111,'Design Application'),(112,'Write Application'),(113,'Study for Quiz'),(114,'DB1'),(115,'Assignment 1'),(116,'Assignment 2'),(117,'Assignment 3'),(118,'Assignment 4'),(119,'Test'),(120,'Test of 4436 Add Assignment Link'),(121,'Assignment 1'),(122,'Assignment 2'),(123,'Assignment 3'),(124,'Assignment 4'),(125,'Assignment 5'),(126,'Assignment 6'),(127,'Assignment 7'),(128,'Assignment 8'),(129,'Assignment 9'),(130,'Assignment 10'),(131,'Assignment 11'),(132,'Assignment 12'),(133,'Final Exam');
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `COURSE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`COURSE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=414 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (284,'Database Design and Development'),(300,'Application Development and Design'),(400,'Histroy Trends and Ethics'),(401,'Algorithms 1'),(402,'Algorithms 2'),(403,'Data Structures'),(404,'Introduction to Computer Science'),(405,'Introduction to C++'),(406,'Cybersecurity'),(407,'IPv6'),(408,'Networking'),(409,'Cryptography'),(410,'Computer Ethics'),(411,'Advanced Programming Techniques'),(412,'Research'),(413,'Seminar');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gradebook`
--

DROP TABLE IF EXISTS `gradebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gradebook` (
  `GRADEBOOK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE_ID` int(11) NOT NULL,
  `SEMESTER_ID` int(11) NOT NULL,
  `INSTRUCTOR_ID` int(11) NOT NULL,
  PRIMARY KEY (`GRADEBOOK_ID`),
  KEY `GRADEBOOK_SEMESTER_FK` (`SEMESTER_ID`),
  KEY `GRADEBOOK_INSTRUCTOR_FK` (`INSTRUCTOR_ID`),
  CONSTRAINT `GRADEBOOK_INSTRUCTOR_FK` FOREIGN KEY (`INSTRUCTOR_ID`) REFERENCES `instructor` (`INSTRUCTOR_ID`),
  CONSTRAINT `GRADEBOOK_SEMESTER_FK` FOREIGN KEY (`SEMESTER_ID`) REFERENCES `semester` (`SEMESTER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4476 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradebook`
--

LOCK TABLES `gradebook` WRITE;
/*!40000 ALTER TABLE `gradebook` DISABLE KEYS */;
INSERT INTO `gradebook` VALUES (3758,300,637,2),(4375,284,521,1),(4380,284,521,1),(4381,284,521,1),(4382,284,521,1),(4383,284,521,1),(4384,284,521,1),(4385,284,521,1),(4386,284,521,1),(4387,284,521,1),(4388,284,521,1),(4389,284,521,1),(4390,284,521,1),(4391,284,521,1),(4392,284,521,1),(4393,284,521,1),(4394,284,521,1),(4395,284,521,1),(4396,284,521,1),(4397,284,521,1),(4398,284,521,1),(4399,284,521,1),(4400,284,521,1),(4401,284,521,1),(4402,284,521,1),(4403,284,521,1),(4404,284,521,1),(4405,284,521,1),(4406,284,521,1),(4407,284,521,1),(4408,284,521,1),(4409,284,521,1),(4410,284,521,1),(4411,284,521,1),(4412,284,521,1),(4413,284,521,1),(4414,284,521,1),(4415,284,521,1),(4416,284,521,1),(4417,284,521,1),(4418,284,521,1),(4419,284,521,1),(4420,284,521,1),(4421,284,521,1),(4422,284,521,1),(4423,284,521,1),(4424,284,521,1),(4425,284,521,1),(4426,284,521,1),(4427,284,521,1),(4428,284,637,3),(4429,300,835,2),(4430,284,521,1),(4431,284,521,1),(4432,284,521,1),(4433,300,835,1),(4434,300,835,1),(4435,284,521,5),(4436,400,637,5),(4437,284,637,5),(4438,284,521,5),(4439,300,835,4),(4440,300,637,5),(4441,400,637,5),(4442,300,835,8),(4443,400,637,9),(4444,300,835,9),(4445,300,835,4),(4446,400,637,8),(4447,284,835,8),(4448,300,637,5),(4449,284,637,4),(4450,400,637,4),(4451,300,637,5),(4452,400,835,5),(4453,284,637,8),(4454,300,835,5),(4455,300,835,11),(4456,400,835,8),(4457,300,637,8),(4458,284,637,9),(4459,401,521,4),(4460,402,637,4),(4461,404,521,4),(4462,405,637,4),(4463,400,521,13),(4464,284,637,13),(4465,300,521,13),(4466,401,637,13),(4467,402,521,13),(4468,403,637,13),(4469,406,521,13),(4470,407,637,13),(4471,408,521,13),(4472,409,637,13),(4473,411,521,13),(4474,412,637,13),(4475,413,521,13);
/*!40000 ALTER TABLE `gradebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gradebook_assignment`
--

DROP TABLE IF EXISTS `gradebook_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gradebook_assignment` (
  `GRADEBOOK_ASSIGNMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GRADEBOOK_ID` int(11) NOT NULL,
  `ASSIGNMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`GRADEBOOK_ASSIGNMENT_ID`),
  KEY `ASSIGNMENT_GRADEBOOK_FK` (`GRADEBOOK_ID`),
  KEY `GRADEBOOK_ASSINGNMENT_FK` (`ASSIGNMENT_ID`),
  CONSTRAINT `ASSIGNMENT_GRADEBOOK_FK` FOREIGN KEY (`GRADEBOOK_ID`) REFERENCES `gradebook` (`GRADEBOOK_ID`),
  CONSTRAINT `GRADEBOOK_ASSINGNMENT_FK` FOREIGN KEY (`ASSIGNMENT_ID`) REFERENCES `assignment` (`ASSIGNMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradebook_assignment`
--

LOCK TABLES `gradebook_assignment` WRITE;
/*!40000 ALTER TABLE `gradebook_assignment` DISABLE KEYS */;
INSERT INTO `gradebook_assignment` VALUES (1,3758,100),(3,4375,102),(4,3758,101),(5,4375,103),(8,4435,102),(9,4419,107),(10,4435,108),(11,4436,109),(12,4437,110),(13,4440,111),(14,4448,112),(15,4441,113),(16,4439,114),(17,4455,115),(18,4455,116),(19,4455,117),(20,4455,118),(21,4435,119),(22,4436,120),(23,4463,121),(24,4463,122),(25,4463,123),(26,4463,124),(27,4463,125),(28,4463,126),(29,4463,127),(30,4463,128),(31,4463,129),(32,4463,130),(33,4463,131),(34,4463,132),(35,4463,133);
/*!40000 ALTER TABLE `gradebook_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gradebook_student`
--

DROP TABLE IF EXISTS `gradebook_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gradebook_student` (
  `GRADEBOOK_STUDENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) NOT NULL,
  `GRADEBOOK_ID` int(11) NOT NULL,
  PRIMARY KEY (`GRADEBOOK_STUDENT_ID`),
  KEY `GRADEBOOK_STUDENT_FK` (`STUDENT_ID`),
  KEY `STUDENT_GRADEBOOK_FK` (`GRADEBOOK_ID`),
  CONSTRAINT `GRADEBOOK_STUDENT_FK` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`),
  CONSTRAINT `STUDENT_GRADEBOOK_FK` FOREIGN KEY (`GRADEBOOK_ID`) REFERENCES `gradebook` (`GRADEBOOK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradebook_student`
--

LOCK TABLES `gradebook_student` WRITE;
/*!40000 ALTER TABLE `gradebook_student` DISABLE KEYS */;
INSERT INTO `gradebook_student` VALUES (6,7588,4435),(7,7589,4435),(8,7590,4435),(9,7592,4439),(10,7593,4435),(11,7594,4435),(12,7595,4435),(13,7596,4435),(14,7597,4435),(15,7598,4435),(16,7599,4435),(17,7600,4435),(18,7601,4436),(19,7602,4455),(20,7603,4455),(21,7604,4455),(22,7605,4439),(23,7606,4463),(24,7607,4463),(25,7608,4463),(26,7609,4463);
/*!40000 ALTER TABLE `gradebook_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `INSTRUCTOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INSTRUCTOR_EMAIL` varchar(45) NOT NULL,
  PRIMARY KEY (`INSTRUCTOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,'MrSmith@gmail.com'),(2,'MrsGold@aol.com'),(3,'MrBig@comcast.net'),(4,'pshekhter@gmail.com'),(5,'CekloskyS@chc.edu'),(8,'pshekhterDraf@gmail.com'),(9,'Kyle@gmail.com'),(10,'Kenneth@chc.edu'),(11,'mickey.mouse@yahoo.com'),(13,'bobG@example.com'),(14,'SELECT * FROM GRADEBOOK;');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `SEMESTER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEMESTER_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`SEMESTER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=836 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (521,'Fall Semester'),(637,'Spring Semester'),(835,'Summer Semester');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_course`
--

DROP TABLE IF EXISTS `semester_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester_course` (
  `SEMESTER_COURSE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE_ID` int(11) NOT NULL,
  `SEMESTER_ID` int(11) NOT NULL,
  PRIMARY KEY (`SEMESTER_COURSE_ID`),
  KEY `SEMESTER_COURSE_FK` (`COURSE_ID`),
  KEY `COURSE_SEMESTER_FK` (`SEMESTER_ID`),
  CONSTRAINT `COURSE_SEMESTER_FK` FOREIGN KEY (`SEMESTER_ID`) REFERENCES `semester` (`SEMESTER_ID`),
  CONSTRAINT `SEMESTER_COURSE_FK` FOREIGN KEY (`COURSE_ID`) REFERENCES `course` (`COURSE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_course`
--

LOCK TABLES `semester_course` WRITE;
/*!40000 ALTER TABLE `semester_course` DISABLE KEYS */;
INSERT INTO `semester_course` VALUES (1,284,521),(2,300,521),(3,400,521),(4,284,637),(5,300,637),(6,400,637),(7,284,835),(8,300,835),(9,400,835);
/*!40000 ALTER TABLE `semester_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `STUDENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_FNAME` varchar(45) NOT NULL,
  `STUDENT_LNAME` varchar(45) NOT NULL,
  PRIMARY KEY (`STUDENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7610 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (7588,'Pavel','Shekhter'),(7589,'Allison','Heuwetter'),(7590,'Kris','Matthews'),(7592,'Thomas','Unbar'),(7593,'Yuri','Gagarin'),(7594,'Kevin','Smith'),(7595,'Gregory','Powers'),(7596,'Ichigo','Miyamoto'),(7597,'Harry','Matthews'),(7598,'Ryan','Bolton'),(7599,'Kathleen','Smith'),(7600,'Hebu','Isu'),(7601,'Ben','Urun'),(7602,'First Name 1','Last Name 1'),(7603,'First Name 2','Last Name 2'),(7604,'First Name 3','Last Name 3'),(7605,'Ulrich','Stern'),(7606,'Kristina','Marsen'),(7607,'Daniel','Marsen'),(7608,'Thomas','Ripardo'),(7609,'Orisa','Eri');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_assignment`
--

DROP TABLE IF EXISTS `student_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_assignment` (
  `STUDENT_ASSIGNMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) NOT NULL,
  `STUDENT_ASSIGNMENT_GRADE` int(11) NOT NULL,
  `GRADEBOOK_ASSIGNMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`STUDENT_ASSIGNMENT_ID`),
  KEY `STUDENT_ID_idx` (`STUDENT_ID`),
  KEY `GRADEBOOK_ASSIGNMENT_ID_idx` (`GRADEBOOK_ASSIGNMENT_ID`),
  KEY `STUDENT_ID` (`STUDENT_ID`),
  CONSTRAINT `ASSIGNMENT_STUDENT_FK` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`),
  CONSTRAINT `GRADEBOOK_ASSIGNMENT_ID` FOREIGN KEY (`GRADEBOOK_ASSIGNMENT_ID`) REFERENCES `gradebook_assignment` (`GRADEBOOK_ASSIGNMENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `STUDENT_ID` FOREIGN KEY (`STUDENT_ID`) REFERENCES `gradebook_student` (`STUDENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_assignment`
--

LOCK TABLES `student_assignment` WRITE;
/*!40000 ALTER TABLE `student_assignment` DISABLE KEYS */;
INSERT INTO `student_assignment` VALUES (29,7589,0,8),(30,7588,0,8),(32,7590,0,8),(33,7602,80,17),(34,7603,0,17),(35,7604,0,17),(36,7592,75,16),(37,7605,50,16),(38,7606,85,23),(39,7607,75,23),(40,7608,65,23),(41,7609,80,23),(50,7609,86,25),(51,7607,75,25),(52,7608,75,25),(53,7609,0,25),(54,7609,86,27),(55,7606,86,27),(56,7607,86,27),(57,7608,86,27),(58,7609,86,27),(59,7606,100,26),(60,7607,75,26),(61,7608,100,26),(62,7609,100,26),(63,7609,100,24),(64,7607,90,24),(65,7608,90,24),(66,7606,90,24);
/*!40000 ALTER TABLE `student_assignment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-27  6:34:31
