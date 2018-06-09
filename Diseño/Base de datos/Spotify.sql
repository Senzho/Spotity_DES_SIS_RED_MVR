-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: spotify
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
-- Table structure for table `album`
--
DROP DATABASE IF EXISTS `spotify`;
CREATE DATABASE `spotify`;
USE `spotify`;

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `idAlbum` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `fechaLanzamiento` date DEFAULT NULL,
  `companiaDiscografica` varchar(100) DEFAULT NULL,
  `idArtista` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAlbum`),
  KEY `idArtista` (`idArtista`),
  CONSTRAINT `album_ibfk_1` FOREIGN KEY (`idArtista`) REFERENCES `artista` (`idArtista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artista`
--

DROP TABLE IF EXISTS `artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artista` (
  `idArtista` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idArtista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artista`
--

LOCK TABLES `artista` WRITE;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
/*!40000 ALTER TABLE `artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion` (
  `idCancion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `duracion` varchar(10) DEFAULT NULL,
  `idAlbum` int(11) DEFAULT NULL,
  `idArtista` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCancion`),
  KEY `idAlbum` (`idAlbum`),
  KEY `idArtista` (`idArtista`),
  CONSTRAINT `cancion_ibfk_1` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`),
  CONSTRAINT `cancion_ibfk_2` FOREIGN KEY (`idArtista`) REFERENCES `artista` (`idArtista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancion_lista`
--

DROP TABLE IF EXISTS `cancion_lista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion_lista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idLista` int(11) DEFAULT NULL,
  `idCancion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idLista` (`idLista`),
  KEY `idCancion` (`idCancion`),
  CONSTRAINT `cancion_lista_ibfk_1` FOREIGN KEY (`idLista`) REFERENCES `listareproduccion` (`idLista`),
  CONSTRAINT `cancion_lista_ibfk_2` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion_lista`
--

LOCK TABLES `cancion_lista` WRITE;
/*!40000 ALTER TABLE `cancion_lista` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancion_lista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancionprivada`
--

DROP TABLE IF EXISTS `cancionprivada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancionprivada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calificacion` int(11) DEFAULT NULL,
  `disponibleSnConexion` tinyint(1) DEFAULT NULL,
  `idCancion` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idCancion` (`idCancion`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `cancionprivada_ibfk_1` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`),
  CONSTRAINT `cancionprivada_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancionprivada`
--

LOCK TABLES `cancionprivada` WRITE;
/*!40000 ALTER TABLE `cancionprivada` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancionprivada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero_artista`
--

DROP TABLE IF EXISTS `genero_artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero_artista` (
  `idGenero` int(11) NOT NULL AUTO_INCREMENT,
  `genero` varchar(100) DEFAULT NULL,
  `idArtista` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGenero`),
  KEY `idArtista` (`idArtista`),
  CONSTRAINT `genero_artista_ibfk_1` FOREIGN KEY (`idArtista`) REFERENCES `artista` (`idArtista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero_artista`
--

LOCK TABLES `genero_artista` WRITE;
/*!40000 ALTER TABLE `genero_artista` DISABLE KEYS */;
/*!40000 ALTER TABLE `genero_artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historialreproduccion`
--

DROP TABLE IF EXISTS `historialreproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historialreproduccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCancion` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idCancion` (`idCancion`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `historialreproduccion_ibfk_1` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`),
  CONSTRAINT `historialreproduccion_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historialreproduccion`
--

LOCK TABLES `historialreproduccion` WRITE;
/*!40000 ALTER TABLE `historialreproduccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `historialreproduccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listareproduccion`
--

DROP TABLE IF EXISTS `listareproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listareproduccion` (
  `idLista` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLista`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `listareproduccion_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listareproduccion`
--

LOCK TABLES `listareproduccion` WRITE;
/*!40000 ALTER TABLE `listareproduccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `listareproduccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-09 12:01:42
