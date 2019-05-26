CREATE DATABASE  IF NOT EXISTS `empenofacil` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `empenofacil`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: empenofacil
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `apartado`
--

DROP TABLE IF EXISTS `apartado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartado` (
  `idapartado` int(11) NOT NULL,
  `empleado` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `fechalimite` datetime NOT NULL,
  `estatusApartado` int(11) NOT NULL,
  PRIMARY KEY (`idapartado`),
  KEY `fk_apartado_empleado1_idx` (`empleado`),
  KEY `fk_apartado_cliente1_idx` (`cliente`),
  KEY `fk_apartado_itemcatalogo1_idx` (`estatusApartado`),
  CONSTRAINT `fk_apartado_cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartado_empleado1` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartado_itemcatalogo1` FOREIGN KEY (`estatusApartado`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartado`
--

LOCK TABLES `apartado` WRITE;
/*!40000 ALTER TABLE `apartado` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `idarticulo` int(11) NOT NULL AUTO_INCREMENT,
  `prenda` int(11) NOT NULL,
  `empleado` int(11) NOT NULL,
  `comercializacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `precio` decimal(15,2) NOT NULL,
  `descuento` decimal(15,2) NOT NULL,
  `codigodebarras` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `estatusArticulo` int(11) NOT NULL,
  PRIMARY KEY (`idarticulo`),
  KEY `fk_articulo_empleado1_idx` (`empleado`),
  KEY `fk_articulo_prenda1_idx` (`prenda`),
  KEY `fk_articulo_itemcatalogo1_idx` (`estatusArticulo`),
  CONSTRAINT `fk_articulo_empleado1` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_itemcatalogo1` FOREIGN KEY (`estatusArticulo`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_prenda1` FOREIGN KEY (`prenda`) REFERENCES `prenda` (`idprenda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,1,2,'2019-03-15 11:00:00',1099.00,0.00,'AAAA-1234','Xbox 360','Con cables',33);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articuloenapartado`
--

DROP TABLE IF EXISTS `articuloenapartado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articuloenapartado` (
  `apartado` int(11) NOT NULL,
  `articulo` int(11) NOT NULL,
  PRIMARY KEY (`apartado`,`articulo`),
  KEY `fk_apartado_has_articulo_articulo1_idx` (`articulo`),
  KEY `fk_apartado_has_articulo_apartado1_idx` (`apartado`),
  CONSTRAINT `fk_apartado_has_articulo_apartado1` FOREIGN KEY (`apartado`) REFERENCES `apartado` (`idapartado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartado_has_articulo_articulo1` FOREIGN KEY (`articulo`) REFERENCES `articulo` (`idarticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articuloenapartado`
--

LOCK TABLES `articuloenapartado` WRITE;
/*!40000 ALTER TABLE `articuloenapartado` DISABLE KEYS */;
/*!40000 ALTER TABLE `articuloenapartado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articuloenventa`
--

DROP TABLE IF EXISTS `articuloenventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articuloenventa` (
  `venta` int(11) NOT NULL,
  `articulo` int(11) NOT NULL,
  PRIMARY KEY (`venta`,`articulo`),
  KEY `fk_venta_has_articulo_articulo1_idx` (`articulo`),
  KEY `fk_venta_has_articulo_venta1_idx` (`venta`),
  CONSTRAINT `fk_venta_has_articulo_articulo1` FOREIGN KEY (`articulo`) REFERENCES `articulo` (`idarticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_has_articulo_venta1` FOREIGN KEY (`venta`) REFERENCES `venta` (`idventa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articuloenventa`
--

LOCK TABLES `articuloenventa` WRITE;
/*!40000 ALTER TABLE `articuloenventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `articuloenventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `tipoidentificacion` int(11) NOT NULL,
  `nombres` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `rfc` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `curp` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `identificacion` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE KEY `identificacion_UNIQUE` (`identificacion`),
  KEY `fk_cliente_catalogo1_idx` (`tipoidentificacion`),
  CONSTRAINT `fk_cliente_catalogo1` FOREIGN KEY (`tipoidentificacion`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,11,'Silvia','Hernádez Casas','HECS8109143S1','HECS810914MOCRSI06','3808914621717'),(2,11,'Luis','Naranjo Cervántez','NACL580323TT7','NACL580323HCSRRI08','6949928218830'),(3,11,'Ángel','Rangel Garza','RAGA920904RQ2','RAGA920904HDFNRN011','5689808950985'),(4,11,'Adriel','Escalante Betancourt','EABA530107133','EABA530107HDFSTD01','1351153214133'),(5,11,'Ricardo','Franco Ledesma','FALR7705217T8','FALR770521HDFRDI05','8197551985302'),(6,11,'Cristina','Montano Rivera','MORC910515I68','MORC910515MNLNIR07','7631887615523'),(7,11,'Esperanza','Cisneros Fernández','CIFE711102G40','CIFE711102MJCIRS03','8518970349511'),(8,11,'Verónica','Sánchez Ortiz','SEOV9409083J0','SEOV940908MYNNRR04','9562757483618'),(9,11,'Elena','Alarcón López','AALE8803018M0','AALE880301MBSLPL07','2245185731853'),(10,11,'Maria','Cárdenas Rocha','CERM850221MM0','CERM850221MSPRCR06','5320261019800');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `clieteview`
--

DROP TABLE IF EXISTS `clieteview`;
/*!50001 DROP VIEW IF EXISTS `clieteview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `clieteview` AS SELECT 
 1 AS `idcliente`,
 1 AS `tipoidentificacion`,
 1 AS `nombres`,
 1 AS `apellidos`,
 1 AS `rfc`,
 1 AS `curp`,
 1 AS `identificacion`,
 1 AS `enlistanegra`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `empeno`
--

DROP TABLE IF EXISTS `empeno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empeno` (
  `idempeno` int(11) NOT NULL,
  `empleado` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `transaccioncaja` int(11) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechalimite` datetime NOT NULL,
  `fechaextendida` datetime NOT NULL,
  `interes` decimal(15,2) NOT NULL,
  `almacenaje` decimal(15,2) NOT NULL,
  `nombrecotitular` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estatusEmpeno` int(11) NOT NULL,
  PRIMARY KEY (`idempeno`),
  KEY `fk_empeno_empleado1_idx` (`empleado`),
  KEY `fk_empeno_cliente1_idx` (`cliente`),
  KEY `fk_empeno_transaccioncaja1_idx` (`transaccioncaja`),
  KEY `fk_empeno_itemcatalogo1_idx` (`estatusEmpeno`),
  CONSTRAINT `fk_empeno_cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empeno_empleado1` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empeno_itemcatalogo1` FOREIGN KEY (`estatusEmpeno`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empeno_transaccioncaja1` FOREIGN KEY (`transaccioncaja`) REFERENCES `transaccioncaja` (`idtransaccioncaja`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empeno`
--

LOCK TABLES `empeno` WRITE;
/*!40000 ALTER TABLE `empeno` DISABLE KEYS */;
INSERT INTO `empeno` VALUES (1,2,5,2,'2019-02-04 14:00:00','2019-03-04 23:59:59','2019-03-04 23:59:59',80.00,30.00,NULL,22),(2,2,7,3,'2019-02-06 10:00:00','2019-03-06 23:59:59','2019-03-20 23:59:59',600.00,200.00,NULL,22),(3,2,4,4,'2019-04-01 10:00:00','2019-05-01 23:59:59','2019-05-01 23:59:59',200.00,50.00,'Beatriz Arasil Mata',22);
/*!40000 ALTER TABLE `empeno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `empenoview`
--

DROP TABLE IF EXISTS `empenoview`;
/*!50001 DROP VIEW IF EXISTS `empenoview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `empenoview` AS SELECT 
 1 AS `idempeno`,
 1 AS `empleado`,
 1 AS `cliente`,
 1 AS `transaccioncaja`,
 1 AS `fecha`,
 1 AS `fechalimite`,
 1 AS `fechaextendida`,
 1 AS `interes`,
 1 AS `almacenaje`,
 1 AS `nombrecotitular`,
 1 AS `estatusEmpeno`,
 1 AS `extendido`,
 1 AS `vencido`,
 1 AS `cancelado`,
 1 AS `comercializado`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `idempleado` int(11) NOT NULL AUTO_INCREMENT,
  `sucursal` int(11) NOT NULL,
  `tipoempleado` int(11) NOT NULL,
  `numpersonal` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nombres` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidos` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nombreusuario` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `contrasenahash` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idempleado`),
  UNIQUE KEY `nombreUsuario_UNIQUE` (`nombreusuario`),
  KEY `fk_empleado_sucursal_idx` (`sucursal`),
  KEY `fk_empleado_catalogo1_idx` (`tipoempleado`),
  CONSTRAINT `fk_empleado_catalogo1` FOREIGN KEY (`tipoempleado`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_sucursal` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`idsucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,1,8,'EM-XA-001','Francisco','Anaya Zambrano','user1','user1'),(2,1,9,'EM-XA-002','Gustavo','Camacho Macías','user2','user2');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotografia`
--

DROP TABLE IF EXISTS `fotografia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fotografia` (
  `idfotografia` int(11) NOT NULL,
  `img` longblob NOT NULL,
  `nota` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idfotografia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotografia`
--

LOCK TABLES `fotografia` WRITE;
/*!40000 ALTER TABLE `fotografia` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotografia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotografiacliente`
--

DROP TABLE IF EXISTS `fotografiacliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fotografiacliente` (
  `cliente` int(11) NOT NULL,
  `fotografia` int(11) NOT NULL,
  PRIMARY KEY (`cliente`,`fotografia`),
  KEY `fk_cliente_has_fotografia_fotografia1_idx` (`fotografia`),
  KEY `fk_cliente_has_fotografia_cliente1_idx` (`cliente`),
  CONSTRAINT `fk_cliente_has_fotografia_cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_fotografia_fotografia1` FOREIGN KEY (`fotografia`) REFERENCES `fotografia` (`idfotografia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotografiacliente`
--

LOCK TABLES `fotografiacliente` WRITE;
/*!40000 ALTER TABLE `fotografiacliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotografiacliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotografiaprenda`
--

DROP TABLE IF EXISTS `fotografiaprenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fotografiaprenda` (
  `prenda` int(11) NOT NULL,
  `fotografia` int(11) NOT NULL,
  PRIMARY KEY (`prenda`,`fotografia`),
  KEY `fk_prenda_has_fotografia_fotografia1_idx` (`fotografia`),
  KEY `fk_prenda_has_fotografia_prenda1_idx` (`prenda`),
  CONSTRAINT `fk_prenda_has_fotografia_fotografia1` FOREIGN KEY (`fotografia`) REFERENCES `fotografia` (`idfotografia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenda_has_fotografia_prenda1` FOREIGN KEY (`prenda`) REFERENCES `prenda` (`idprenda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotografiaprenda`
--

LOCK TABLES `fotografiaprenda` WRITE;
/*!40000 ALTER TABLE `fotografiaprenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotografiaprenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemcatalogo`
--

DROP TABLE IF EXISTS `itemcatalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemcatalogo` (
  `iditemcatalogo` int(11) NOT NULL AUTO_INCREMENT,
  `itemcatalogoSuperior` int(11) DEFAULT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`iditemcatalogo`),
  KEY `fk_catalogo_catalogo1_idx` (`itemcatalogoSuperior`),
  CONSTRAINT `fk_catalogo_catalogo1` FOREIGN KEY (`itemcatalogoSuperior`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemcatalogo`
--

LOCK TABLES `itemcatalogo` WRITE;
/*!40000 ALTER TABLE `itemcatalogo` DISABLE KEYS */;
INSERT INTO `itemcatalogo` VALUES (1,NULL,'Tipo empleado'),(2,NULL,'Tipo identificación oficial'),(3,NULL,'Tipo/categoría de prenda'),(6,NULL,'Tipo de pago de empeño'),(7,NULL,'Tipo de operación externa'),(8,1,'Gerente'),(9,1,'Cajero'),(10,2,'Pasaporte'),(11,2,'Credencial para votar'),(12,2,'Cédula profesional'),(13,2,'Cartilla del Servicio Militar Nacional'),(14,3,'Joyería'),(15,3,'Dispositivos electrónicos'),(16,7,'Deposito'),(17,7,'Retiro'),(18,15,'Consola de videojuegos'),(19,14,'Reloj de oro'),(20,NULL,'Estatus Empeño'),(21,NULL,'Estatus Apartado y Venta'),(22,20,'Vigente'),(23,20,'Cancelado'),(24,20,'Refrendado'),(26,21,'Completado'),(27,21,'Cancelado'),(28,21,'Vencido'),(29,21,'Liquidado'),(30,NULL,'Estus de Articulo'),(31,30,'Apartado'),(32,30,'Vendido'),(33,30,'Disponible'),(34,20,'Vencido'),(35,21,'Vigente');
/*!40000 ALTER TABLE `itemcatalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacionexterna`
--

DROP TABLE IF EXISTS `operacionexterna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operacionexterna` (
  `idoperacionexterna` int(11) NOT NULL,
  `empleado` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  `transaccioncaja` int(11) NOT NULL,
  `nota` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idoperacionexterna`),
  KEY `fk_operacionexterna_empleado1_idx` (`empleado`),
  KEY `fk_operacionexterna_itemcatalogo1_idx` (`tipo`),
  KEY `fk_operacionexterna_transaccioncaja1_idx` (`transaccioncaja`),
  CONSTRAINT `fk_operacionexterna_empleado1` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_operacionexterna_itemcatalogo1` FOREIGN KEY (`tipo`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_operacionexterna_transaccioncaja1` FOREIGN KEY (`transaccioncaja`) REFERENCES `transaccioncaja` (`idtransaccioncaja`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacionexterna`
--

LOCK TABLES `operacionexterna` WRITE;
/*!40000 ALTER TABLE `operacionexterna` DISABLE KEYS */;
INSERT INTO `operacionexterna` VALUES (1,1,16,1,'Deposito inicial');
/*!40000 ALTER TABLE `operacionexterna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoapartado`
--

DROP TABLE IF EXISTS `pagoapartado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagoapartado` (
  `idpagoapartado` int(11) NOT NULL,
  `apartado` int(11) NOT NULL,
  `transaccioncaja` int(11) NOT NULL,
  PRIMARY KEY (`idpagoapartado`),
  KEY `fk_pagoapartado_apartado1_idx` (`apartado`),
  KEY `fk_pagoapartado_transaccioncaja1_idx` (`transaccioncaja`),
  CONSTRAINT `fk_pagoapartado_apartado1` FOREIGN KEY (`apartado`) REFERENCES `apartado` (`idapartado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagoapartado_transaccioncaja1` FOREIGN KEY (`transaccioncaja`) REFERENCES `transaccioncaja` (`idtransaccioncaja`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoapartado`
--

LOCK TABLES `pagoapartado` WRITE;
/*!40000 ALTER TABLE `pagoapartado` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagoapartado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoempeno`
--

DROP TABLE IF EXISTS `pagoempeno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagoempeno` (
  `idpagoempeno` int(11) NOT NULL,
  `empeno` int(11) NOT NULL,
  `transaccioncaja` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`idpagoempeno`),
  KEY `fk_pagoempeno_empeno1_idx` (`empeno`),
  KEY `fk_pagoempeno_transaccioncaja1_idx` (`transaccioncaja`),
  KEY `fk_pagoempeno_itemcatalogo1_idx` (`tipo`),
  CONSTRAINT `fk_pagoempeno_empeno1` FOREIGN KEY (`empeno`) REFERENCES `empeno` (`idempeno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagoempeno_itemcatalogo1` FOREIGN KEY (`tipo`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagoempeno_transaccioncaja1` FOREIGN KEY (`transaccioncaja`) REFERENCES `transaccioncaja` (`idtransaccioncaja`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoempeno`
--

LOCK TABLES `pagoempeno` WRITE;
/*!40000 ALTER TABLE `pagoempeno` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagoempeno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prenda` (
  `idprenda` int(11) NOT NULL,
  `empeno` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `avaluo` decimal(15,2) NOT NULL,
  `montoprestado` decimal(15,2) NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `peso` decimal(15,4) DEFAULT NULL,
  PRIMARY KEY (`idprenda`),
  KEY `fk_prenda_empeno1_idx` (`empeno`),
  KEY `fk_prenda_itemCatalogo1_idx` (`tipo`),
  KEY `fk_prenda_itemcatalogo2_idx` (`categoria`),
  CONSTRAINT `fk_prenda_empeno1` FOREIGN KEY (`empeno`) REFERENCES `empeno` (`idempeno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenda_itemCatalogo1` FOREIGN KEY (`tipo`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenda_itemcatalogo2` FOREIGN KEY (`categoria`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenda`
--

LOCK TABLES `prenda` WRITE;
/*!40000 ALTER TABLE `prenda` DISABLE KEYS */;
INSERT INTO `prenda` VALUES (1,1,15,18,1099.00,500.00,'Xbox 360','Con cables',NULL),(2,2,15,18,3000.00,1200.00,'Nintendo switch','Sin cargador',NULL),(3,2,14,19,4500.00,2800.00,'Reloj chapado en oro','Oro 10k, automatico',NULL),(4,3,15,18,5000.00,1900.00,'Xbox one','En caja con accesorios',NULL);
/*!40000 ALTER TABLE `prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `prendaview`
--

DROP TABLE IF EXISTS `prendaview`;
/*!50001 DROP VIEW IF EXISTS `prendaview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `prendaview` AS SELECT 
 1 AS `idprenda`,
 1 AS `empeno`,
 1 AS `tipo`,
 1 AS `categoria`,
 1 AS `avaluo`,
 1 AS `montoprestado`,
 1 AS `nombre`,
 1 AS `descripcion`,
 1 AS `peso`,
 1 AS `comercializado`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `registrolistanegra`
--

DROP TABLE IF EXISTS `registrolistanegra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registrolistanegra` (
  `idregistrolistanegra` int(11) NOT NULL AUTO_INCREMENT,
  `cliente` int(11) NOT NULL,
  `empleadoagrega` int(11) NOT NULL,
  `empleadoretira` int(11) DEFAULT NULL,
  `fechaagrega` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `motivoagrega` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `fecharetira` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `motivoretira` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idregistrolistanegra`),
  KEY `fk_RegistroListaNegra_cliente1_idx` (`cliente`),
  KEY `fk_registrolistanegra_empleado1_idx` (`empleadoagrega`),
  KEY `fk_registrolistanegra_empleado2_idx` (`empleadoretira`),
  CONSTRAINT `fk_RegistroListaNegra_cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrolistanegra_empleado1` FOREIGN KEY (`empleadoagrega`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrolistanegra_empleado2` FOREIGN KEY (`empleadoretira`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrolistanegra`
--

LOCK TABLES `registrolistanegra` WRITE;
/*!40000 ALTER TABLE `registrolistanegra` DISABLE KEYS */;
INSERT INTO `registrolistanegra` VALUES (1,1,1,1,'2019-03-11 11:20:00','Empeñó prenda dañada','2019-04-01 12:00:00','Retirado por criterio del gerente'),(2,2,1,NULL,'2019-03-12 10:30:00','Empeñó prenda robada',NULL,NULL);
/*!40000 ALTER TABLE `registrolistanegra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `idsucursal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `colonia` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `codigopostal` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ciudad` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `caja` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`idsucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'Empeño fácil Xalapa Centro','Calle Revolución 30','Zona Centro','91000','Xalapa-Enríquez','Veracruz',NULL);
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccioncaja`
--

DROP TABLE IF EXISTS `transaccioncaja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccioncaja` (
  `idtransaccioncaja` int(11) NOT NULL,
  `sucursal` int(11) NOT NULL,
  `cancelacion` int(11) DEFAULT NULL,
  `monto` decimal(15,2) NOT NULL,
  `balacecaja` decimal(15,2) NOT NULL,
  `fechahora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idtransaccioncaja`),
  KEY `fk_transaccioncaja_sucursal1_idx` (`sucursal`),
  KEY `fk_transaccioncaja_transaccioncaja1_idx` (`cancelacion`),
  CONSTRAINT `fk_transaccioncaja_sucursal1` FOREIGN KEY (`sucursal`) REFERENCES `sucursal` (`idsucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaccioncaja_transaccioncaja1` FOREIGN KEY (`cancelacion`) REFERENCES `transaccioncaja` (`idtransaccioncaja`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccioncaja`
--

LOCK TABLES `transaccioncaja` WRITE;
/*!40000 ALTER TABLE `transaccioncaja` DISABLE KEYS */;
INSERT INTO `transaccioncaja` VALUES (1,1,NULL,15000.00,15000.00,'2019-02-04 10:00:00','Fondo inicial'),(2,1,NULL,-500.00,14500.00,'2019-02-04 14:00:00','Empeño'),(3,1,NULL,-4000.00,10500.00,'2019-02-06 10:00:00','Empeño'),(4,1,NULL,-1900.00,8600.00,'2019-04-01 10:00:00','Empeño');
/*!40000 ALTER TABLE `transaccioncaja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `idventa` int(11) NOT NULL,
  `empleado` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `transaccioncaja` int(11) NOT NULL,
  `estatusVenta` int(11) NOT NULL,
  PRIMARY KEY (`idventa`),
  KEY `fk_venta_empleado1_idx` (`empleado`),
  KEY `fk_venta_cliente1_idx` (`cliente`),
  KEY `fk_venta_transaccioncaja1_idx` (`transaccioncaja`),
  KEY `fk_venta_itemcatalogo1_idx` (`estatusVenta`),
  CONSTRAINT `fk_venta_cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_empleado1` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`idempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_itemcatalogo1` FOREIGN KEY (`estatusVenta`) REFERENCES `itemcatalogo` (`iditemcatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_transaccioncaja1` FOREIGN KEY (`transaccioncaja`) REFERENCES `transaccioncaja` (`idtransaccioncaja`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `clieteview`
--

/*!50001 DROP VIEW IF EXISTS `clieteview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `clieteview` AS select `cliente`.`idcliente` AS `idcliente`,`cliente`.`tipoidentificacion` AS `tipoidentificacion`,`cliente`.`nombres` AS `nombres`,`cliente`.`apellidos` AS `apellidos`,`cliente`.`rfc` AS `rfc`,`cliente`.`curp` AS `curp`,`cliente`.`identificacion` AS `identificacion`,(select (count(1) > 0) from `registrolistanegra` where ((`registrolistanegra`.`cliente` = `cliente`.`idcliente`) and isnull(`registrolistanegra`.`fecharetira`))) AS `enlistanegra` from `cliente` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `empenoview`
--

/*!50001 DROP VIEW IF EXISTS `empenoview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `empenoview` AS select `empeno`.`idempeno` AS `idempeno`,`empeno`.`empleado` AS `empleado`,`empeno`.`cliente` AS `cliente`,`empeno`.`transaccioncaja` AS `transaccioncaja`,`empeno`.`fecha` AS `fecha`,`empeno`.`fechalimite` AS `fechalimite`,`empeno`.`fechaextendida` AS `fechaextendida`,`empeno`.`interes` AS `interes`,`empeno`.`almacenaje` AS `almacenaje`,`empeno`.`nombrecotitular` AS `nombrecotitular`,`empeno`.`estatusEmpeno` AS `estatusEmpeno`,(`empeno`.`fechalimite` <> `empeno`.`fechaextendida`) AS `extendido`,(`empeno`.`fechaextendida` < now()) AS `vencido`,(select (`transaccioncaja`.`cancelacion` is not null) from `transaccioncaja` where (`transaccioncaja`.`idtransaccioncaja` = `empeno`.`transaccioncaja`)) AS `cancelado`,(select (count(`prendaview`.`comercializado`) = sum(`prendaview`.`comercializado`)) from `prendaview` where (`prendaview`.`empeno` = `empeno`.`idempeno`)) AS `comercializado` from `empeno` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `prendaview`
--

/*!50001 DROP VIEW IF EXISTS `prendaview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `prendaview` AS select `prenda`.`idprenda` AS `idprenda`,`prenda`.`empeno` AS `empeno`,`prenda`.`tipo` AS `tipo`,`prenda`.`categoria` AS `categoria`,`prenda`.`avaluo` AS `avaluo`,`prenda`.`montoprestado` AS `montoprestado`,`prenda`.`nombre` AS `nombre`,`prenda`.`descripcion` AS `descripcion`,`prenda`.`peso` AS `peso`,(select (count(1) > 0) from `articulo` where (`articulo`.`prenda` = `prenda`.`idprenda`)) AS `comercializado` from `prenda` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-25 19:03:19
