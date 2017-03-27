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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (100,'Homework 1'),(101,'Homework 2'),(102,'Homework 3');
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
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (284,'Database Design and Development'),(300,'Application Development and Design'),(400,'Histroy Trends and Ethics');
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
) ENGINE=InnoDB AUTO_INCREMENT=4450 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradebook`
--

LOCK TABLES `gradebook` WRITE;
/*!40000 ALTER TABLE `gradebook` DISABLE KEYS */;
INSERT INTO `gradebook` VALUES (3758,300,637,2),(4375,284,521,1),(4380,284,521,1),(4381,284,521,1),(4382,284,521,1),(4383,284,521,1),(4384,284,521,1),(4385,284,521,1),(4386,284,521,1),(4387,284,521,1),(4388,284,521,1),(4389,284,521,1),(4390,284,521,1),(4391,284,521,1),(4392,284,521,1),(4393,284,521,1),(4394,284,521,1),(4395,284,521,1),(4396,284,521,1),(4397,284,521,1),(4398,284,521,1),(4399,284,521,1),(4400,284,521,1),(4401,284,521,1),(4402,284,521,1),(4403,284,521,1),(4404,284,521,1),(4405,284,521,1),(4406,284,521,1),(4407,284,521,1),(4408,284,521,1),(4409,284,521,1),(4410,284,521,1),(4411,284,521,1),(4412,284,521,1),(4413,284,521,1),(4414,284,521,1),(4415,284,521,1),(4416,284,521,1),(4417,284,521,1),(4418,284,521,1),(4419,284,521,1),(4420,284,521,1),(4421,284,521,1),(4422,284,521,1),(4423,284,521,1),(4424,284,521,1),(4425,284,521,1),(4426,284,521,1),(4427,284,521,1),(4428,284,637,3),(4429,300,835,2),(4430,284,521,1),(4431,284,521,1),(4432,284,521,1),(4433,300,835,1),(4434,300,835,1),(4435,284,521,5),(4436,400,637,5),(4437,284,637,5),(4438,284,521,5),(4439,300,835,4),(4440,300,637,5),(4441,400,637,5),(4442,300,835,8),(4443,400,637,9),(4444,300,835,9),(4445,300,835,4),(4446,400,637,8),(4447,284,835,8),(4448,300,637,5),(4449,284,637,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradebook_assignment`
--

LOCK TABLES `gradebook_assignment` WRITE;
/*!40000 ALTER TABLE `gradebook_assignment` DISABLE KEYS */;
INSERT INTO `gradebook_assignment` VALUES (1,3758,100),(3,4375,100),(4,3758,101),(5,3758,102),(6,4375,101),(7,4375,102);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradebook_student`
--

LOCK TABLES `gradebook_student` WRITE;
/*!40000 ALTER TABLE `gradebook_student` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,'MrSmith@gmail.com'),(2,'MrsGold@aol.com'),(3,'MrBig@comcast.net'),(4,'pshekhter@gmail.com'),(5,'CekloskyS@chc.edu'),(8,'pshekhterDraf@gmail.com'),(9,'Kyle@gmail.com');
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
) ENGINE=InnoDB AUTO_INCREMENT=7533 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2385,'Allison','Heuwetter'),(4742,'Pavel','Shekhter'),(7532,'Ryan','Dawson');
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
  `ASSIGNMENT_ID` int(11) NOT NULL,
  `STUDENT_ASSIGNMENT_GRADE` int(11) DEFAULT NULL,
  PRIMARY KEY (`STUDENT_ASSIGNMENT_ID`),
  KEY `ASSIGNMENT_STUDENT_FK` (`STUDENT_ID`),
  CONSTRAINT `ASSIGNMENT_STUDENT_FK` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_assignment`
--

LOCK TABLES `student_assignment` WRITE;
/*!40000 ALTER TABLE `student_assignment` DISABLE KEYS */;
INSERT INTO `student_assignment` VALUES (1,2385,100,100),(2,2385,101,80),(3,2385,102,90),(4,4742,100,70),(5,4742,101,75),(6,4742,102,90),(7,7532,100,80),(8,7532,101,60),(9,7532,102,78);
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

-- Dump completed on 2017-03-26 18:20:06
