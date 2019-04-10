CREATE DATABASE  IF NOT EXISTS `empeniofacil` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `empeniofacil`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: empeniofacil
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
  `idApartado` int(11) NOT NULL,
  `Empleado_idEmpleado` int(11) NOT NULL,
  `montoApartado` double DEFAULT NULL,
  `abono` double DEFAULT NULL,
  `fechaApartado` date DEFAULT NULL,
  `fechaLiquidacion` date DEFAULT NULL,
  PRIMARY KEY (`idApartado`),
  KEY `fk_Apartado_Empleado1_idx` (`Empleado_idEmpleado`),
  CONSTRAINT `fk_Apartado_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `idArtículo` int(11) NOT NULL,
  `descuento` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `tipoPrenda_idCatalogo` int(11) NOT NULL,
  `tipoCategoria_idCatalogo1` int(11) NOT NULL,
  `Comercializacion_idComercializacion` int(11) NOT NULL,
  PRIMARY KEY (`idArtículo`),
  KEY `fk_Artículo_Catalogo1_idx` (`tipoPrenda_idCatalogo`),
  KEY `fk_Artículo_Catalogo2_idx` (`tipoCategoria_idCatalogo1`),
  KEY `fk_Artículo_Comercializacion1_idx` (`Comercializacion_idComercializacion`),
  CONSTRAINT `fk_Artículo_Catalogo1` FOREIGN KEY (`tipoPrenda_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_Catalogo2` FOREIGN KEY (`tipoCategoria_idCatalogo1`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_Comercializacion1` FOREIGN KEY (`Comercializacion_idComercializacion`) REFERENCES `comercializacion` (`idComercializacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo_has_apartado`
--

DROP TABLE IF EXISTS `articulo_has_apartado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo_has_apartado` (
  `Articulo_idArticulo` int(11) NOT NULL,
  `Apartado_idApartado` int(11) NOT NULL,
  PRIMARY KEY (`Articulo_idArticulo`,`Apartado_idApartado`),
  KEY `fk_Artículo_has_Apartado_Apartado1_idx` (`Apartado_idApartado`),
  KEY `fk_Artículo_has_Apartado_Artículo1_idx` (`Articulo_idArticulo`),
  CONSTRAINT `fk_Artículo_has_Apartado_Apartado1` FOREIGN KEY (`Apartado_idApartado`) REFERENCES `apartado` (`idApartado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_has_Apartado_Artículo1` FOREIGN KEY (`Articulo_idArticulo`) REFERENCES `articulo` (`idArtículo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo_has_apartado`
--

LOCK TABLES `articulo_has_apartado` WRITE;
/*!40000 ALTER TABLE `articulo_has_apartado` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo_has_apartado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo_has_venta`
--

DROP TABLE IF EXISTS `articulo_has_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo_has_venta` (
  `Articulo_idArticulo` int(11) NOT NULL,
  `Venta_idVenta` int(11) NOT NULL,
  PRIMARY KEY (`Articulo_idArticulo`,`Venta_idVenta`),
  KEY `fk_Artículo_has_Venta_Venta1_idx` (`Venta_idVenta`),
  KEY `fk_Artículo_has_Venta_Artículo1_idx` (`Articulo_idArticulo`),
  CONSTRAINT `fk_Artículo_has_Venta_Artículo1` FOREIGN KEY (`Articulo_idArticulo`) REFERENCES `articulo` (`idArtículo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_has_Venta_Venta1` FOREIGN KEY (`Venta_idVenta`) REFERENCES `venta` (`idVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo_has_venta`
--

LOCK TABLES `articulo_has_venta` WRITE;
/*!40000 ALTER TABLE `articulo_has_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo_has_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bolsa`
--

DROP TABLE IF EXISTS `bolsa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bolsa` (
  `idBolsa` int(11) NOT NULL,
  `fondoInicial` double DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `montoFinal` double DEFAULT NULL,
  PRIMARY KEY (`idBolsa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bolsa`
--

LOCK TABLES `bolsa` WRITE;
/*!40000 ALTER TABLE `bolsa` DISABLE KEYS */;
/*!40000 ALTER TABLE `bolsa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo`
--

DROP TABLE IF EXISTS `catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogo` (
  `idCatalogo` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `idTipo` int(11) DEFAULT NULL,
  `Catalogo_idCatalogo` int(11) NOT NULL,
  PRIMARY KEY (`idCatalogo`),
  KEY `fk_Catalogo_Catalogo1_idx` (`Catalogo_idCatalogo`),
  CONSTRAINT `fk_Catalogo_Catalogo1` FOREIGN KEY (`Catalogo_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo`
--

LOCK TABLES `catalogo` WRITE;
/*!40000 ALTER TABLE `catalogo` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(150) DEFAULT NULL,
  `rfc` int(11) DEFAULT NULL,
  `curp` varchar(18) DEFAULT NULL,
  `idIdentificacion` int(11) DEFAULT NULL,
  `cotitular` varchar(250) DEFAULT NULL,
  `listaNegra` tinyint(4) DEFAULT NULL,
  `tipoIdentificacion_idCatalogo` int(11) NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `fk_Cliente_Catalogo1_idx` (`tipoIdentificacion_idCatalogo`),
  CONSTRAINT `fk_Cliente_Catalogo1` FOREIGN KEY (`tipoIdentificacion_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercializacion`
--

DROP TABLE IF EXISTS `comercializacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comercializacion` (
  `idComercializacion` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `nombreEmpleado` varchar(45) DEFAULT NULL,
  `numeroArticulos` int(11) DEFAULT NULL,
  `Empleado_idEmpleado` int(11) NOT NULL,
  PRIMARY KEY (`idComercializacion`),
  KEY `fk_Comercializacion_Empleado1_idx` (`Empleado_idEmpleado`),
  CONSTRAINT `fk_Comercializacion_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercializacion`
--

LOCK TABLES `comercializacion` WRITE;
/*!40000 ALTER TABLE `comercializacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `comercializacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL,
  `Empleado_idEmpleado` int(11) NOT NULL,
  `fechaRealizacion` date DEFAULT NULL,
  `montoPrestado` double DEFAULT NULL,
  `fechaLimite` date DEFAULT NULL,
  `tiempoExtendido` int(11) DEFAULT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `tazaInteres` int(11) DEFAULT NULL,
  `almacenaje` int(11) DEFAULT NULL,
  `tipoPago_idCatalogo` int(11) NOT NULL,
  `refrendo` double DEFAULT NULL,
  PRIMARY KEY (`idContrato`),
  KEY `fk_Contrato_Empleado1_idx` (`Empleado_idEmpleado`),
  KEY `fk_Contrato_Cliente1_idx` (`Cliente_idCliente`),
  KEY `fk_Contrato_Catalogo1_idx` (`tipoPago_idCatalogo`),
  CONSTRAINT `fk_Contrato_Catalogo1` FOREIGN KEY (`tipoPago_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `idEmpleado` int(11) NOT NULL,
  `Sucursal_idSucursal` int(11) NOT NULL,
  `numeroPersonal` int(11) DEFAULT NULL,
  `nombreEmpleado` varchar(250) DEFAULT NULL,
  `contrasenia` varchar(50) DEFAULT NULL,
  `tipoEmpleado_idCatalogo` int(11) NOT NULL,
  `nombreUsuario` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `fk_Empleado_Sucursal1_idx` (`Sucursal_idSucursal`),
  KEY `fk_Empleado_Catalogo1_idx` (`tipoEmpleado_idCatalogo`),
  CONSTRAINT `fk_Empleado_Catalogo1` FOREIGN KEY (`tipoEmpleado_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleado_Sucursal1` FOREIGN KEY (`Sucursal_idSucursal`) REFERENCES `sucursal` (`idSucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_articulo`
--

DROP TABLE IF EXISTS `foto_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_articulo` (
  `idFoto_Articulo` int(11) NOT NULL,
  `Artículo_idArtículo` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFoto_Articulo`),
  KEY `fk_Foto_Articulo_Artículo1_idx` (`Artículo_idArtículo`),
  CONSTRAINT `fk_Foto_Articulo_Artículo1` FOREIGN KEY (`Artículo_idArtículo`) REFERENCES `articulo` (`idArtículo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_articulo`
--

LOCK TABLES `foto_articulo` WRITE;
/*!40000 ALTER TABLE `foto_articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_cliente`
--

DROP TABLE IF EXISTS `foto_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_cliente` (
  `idFoto_Cliente` int(11) NOT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  `tipoFoto_idCatalogo` int(11) NOT NULL,
  PRIMARY KEY (`idFoto_Cliente`),
  KEY `fk_Foto_Cliente_Cliente1_idx` (`Cliente_idCliente`),
  KEY `fk_Foto_Cliente_Catalogo1_idx` (`tipoFoto_idCatalogo`),
  CONSTRAINT `fk_Foto_Cliente_Catalogo1` FOREIGN KEY (`tipoFoto_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Foto_Cliente_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_cliente`
--

LOCK TABLES `foto_cliente` WRITE;
/*!40000 ALTER TABLE `foto_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_prenda`
--

DROP TABLE IF EXISTS `foto_prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_prenda` (
  `idFoto_Prenda` int(11) NOT NULL,
  `Prenda_idPrenda` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFoto_Prenda`),
  KEY `fk_Foto_Prenda_Prenda1_idx` (`Prenda_idPrenda`),
  CONSTRAINT `fk_Foto_Prenda_Prenda1` FOREIGN KEY (`Prenda_idPrenda`) REFERENCES `prenda` (`idPrenda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_prenda`
--

LOCK TABLES `foto_prenda` WRITE;
/*!40000 ALTER TABLE `foto_prenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametros` (
  `idParametros` int(11) NOT NULL,
  `iva` int(11) DEFAULT NULL,
  `diasExtension` int(11) DEFAULT NULL,
  `porcentajePrestamo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idParametros`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prenda` (
  `idPrenda` int(11) NOT NULL,
  `Contrato_idContrato` int(11) NOT NULL,
  `valor` double DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `tipoPrenda_idCatalogo` int(11) NOT NULL,
  `tipoCategoria_idCatalogo1` int(11) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`idPrenda`),
  KEY `fk_Prenda_Contrato1_idx` (`Contrato_idContrato`),
  KEY `fk_Prenda_Catalogo1_idx` (`tipoPrenda_idCatalogo`),
  KEY `fk_Prenda_Catalogo2_idx` (`tipoCategoria_idCatalogo1`),
  CONSTRAINT `fk_Prenda_Catalogo1` FOREIGN KEY (`tipoPrenda_idCatalogo`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prenda_Catalogo2` FOREIGN KEY (`tipoCategoria_idCatalogo1`) REFERENCES `catalogo` (`idCatalogo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prenda_Contrato1` FOREIGN KEY (`Contrato_idContrato`) REFERENCES `contrato` (`idContrato`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenda`
--

LOCK TABLES `prenda` WRITE;
/*!40000 ALTER TABLE `prenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `idSucursal` int(11) NOT NULL,
  `Parametros_idParametros` int(11) NOT NULL,
  `Bolsa_idBolsa` int(11) NOT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `fk_Sucursal_Parametros1_idx` (`Parametros_idParametros`),
  KEY `fk_Sucursal_Bolsa1_idx` (`Bolsa_idBolsa`),
  CONSTRAINT `fk_Sucursal_Bolsa1` FOREIGN KEY (`Bolsa_idBolsa`) REFERENCES `bolsa` (`idBolsa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sucursal_Parametros1` FOREIGN KEY (`Parametros_idParametros`) REFERENCES `parametros` (`idParametros`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `idVenta` int(11) NOT NULL,
  `Empleado_idEmpleado` int(11) NOT NULL,
  `montoVendido` double DEFAULT NULL,
  `fechaVenta` date DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `fk_Venta_Empleado1_idx` (`Empleado_idEmpleado`),
  CONSTRAINT `fk_Venta_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-10 11:19:09
