-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema empenofacil
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `empenofacil` ;

-- -----------------------------------------------------
-- Schema empenofacil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empenofacil` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `empenofacil` ;

-- -----------------------------------------------------
-- Table `empenofacil`.`sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`sucursal` (
  `idsucursal` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `colonia` VARCHAR(45) NULL,
  `codigopostal` VARCHAR(45) NULL,
  `ciudad` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `caja` DECIMAL(15,2) NULL,
  PRIMARY KEY (`idsucursal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`itemcatalogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`itemcatalogo` (
  `iditemcatalogo` INT NOT NULL AUTO_INCREMENT,
  `itemcatalogoSuperior` INT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`iditemcatalogo`),
  INDEX `fk_catalogo_catalogo1_idx` (`itemcatalogoSuperior` ASC),
  CONSTRAINT `fk_catalogo_catalogo1`
    FOREIGN KEY (`itemcatalogoSuperior`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`empleado` (
  `idempleado` INT NOT NULL AUTO_INCREMENT,
  `sucursal` INT NOT NULL,
  `tipoempleado` INT NOT NULL,
  `numpersonal` VARCHAR(45) NULL,
  `nombres` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `nombreusuario` VARCHAR(45) NOT NULL,
  `contrasenahash` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idempleado`),
  INDEX `fk_empleado_sucursal_idx` (`sucursal` ASC),
  INDEX `fk_empleado_catalogo1_idx` (`tipoempleado` ASC),
  UNIQUE INDEX `nombreUsuario_UNIQUE` (`nombreusuario` ASC),
  CONSTRAINT `fk_empleado_sucursal`
    FOREIGN KEY (`sucursal`)
    REFERENCES `empenofacil`.`sucursal` (`idsucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_catalogo1`
    FOREIGN KEY (`tipoempleado`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `tipoidentificacion` INT NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `rfc` VARCHAR(45) NULL,
  `curp` VARCHAR(45) NULL,
  `identificacion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcliente`),
  INDEX `fk_cliente_catalogo1_idx` (`tipoidentificacion` ASC),
  UNIQUE INDEX `identificacion_UNIQUE` (`identificacion` ASC),
  CONSTRAINT `fk_cliente_catalogo1`
    FOREIGN KEY (`tipoidentificacion`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`registrolistanegra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`registrolistanegra` (
  `idregistrolistanegra` INT NOT NULL AUTO_INCREMENT,
  `cliente` INT NOT NULL,
  `empleadoagrega` INT NOT NULL,
  `empleadoretira` INT NULL,
  `fechaagrega` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `motivoagrega` VARCHAR(45) NOT NULL,
  `fecharetira` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `motivoretira` VARCHAR(45) NULL,
  PRIMARY KEY (`idregistrolistanegra`),
  INDEX `fk_RegistroListaNegra_cliente1_idx` (`cliente` ASC),
  INDEX `fk_registrolistanegra_empleado1_idx` (`empleadoagrega` ASC),
  INDEX `fk_registrolistanegra_empleado2_idx` (`empleadoretira` ASC),
  CONSTRAINT `fk_RegistroListaNegra_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `empenofacil`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrolistanegra_empleado1`
    FOREIGN KEY (`empleadoagrega`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrolistanegra_empleado2`
    FOREIGN KEY (`empleadoretira`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`fotografia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`fotografia` (
  `idfotografia` INT NOT NULL,
  `img` LONGBLOB NOT NULL,
  `nota` VARCHAR(100) NULL,
  PRIMARY KEY (`idfotografia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`fotografiacliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`fotografiacliente` (
  `cliente` INT NOT NULL,
  `fotografia` INT NOT NULL,
  PRIMARY KEY (`cliente`, `fotografia`),
  INDEX `fk_cliente_has_fotografia_fotografia1_idx` (`fotografia` ASC),
  INDEX `fk_cliente_has_fotografia_cliente1_idx` (`cliente` ASC),
  CONSTRAINT `fk_cliente_has_fotografia_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `empenofacil`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_fotografia_fotografia1`
    FOREIGN KEY (`fotografia`)
    REFERENCES `empenofacil`.`fotografia` (`idfotografia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`transaccioncaja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`transaccioncaja` (
  `idtransaccioncaja` INT NOT NULL AUTO_INCREMENT,
  `sucursal` INT NOT NULL,
  `cancelacion` INT NULL,
  `monto` DECIMAL(15,2) NOT NULL,
  `balacecaja` DECIMAL(15,2) NOT NULL,
  `fechahora` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idtransaccioncaja`),
  INDEX `fk_transaccioncaja_sucursal1_idx` (`sucursal` ASC),
  INDEX `fk_transaccioncaja_transaccioncaja1_idx` (`cancelacion` ASC),
  CONSTRAINT `fk_transaccioncaja_sucursal1`
    FOREIGN KEY (`sucursal`)
    REFERENCES `empenofacil`.`sucursal` (`idsucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaccioncaja_transaccioncaja1`
    FOREIGN KEY (`cancelacion`)
    REFERENCES `empenofacil`.`transaccioncaja` (`idtransaccioncaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`empeno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`empeno` (
  `idempeno` INT NOT NULL,
  `empleado` INT NOT NULL,
  `cliente` INT NOT NULL,
  `transaccioncaja` INT NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechalimite` DATETIME NOT NULL,
  `fechaextendida` DATETIME NOT NULL,
  `interes` DECIMAL(15,2) NOT NULL,
  `almacenaje` DECIMAL(15,2) NOT NULL,
  `nombrecotitular` VARCHAR(100) NULL,
  PRIMARY KEY (`idempeno`),
  INDEX `fk_empeno_empleado1_idx` (`empleado` ASC),
  INDEX `fk_empeno_cliente1_idx` (`cliente` ASC),
  INDEX `fk_empeno_transaccioncaja1_idx` (`transaccioncaja` ASC),
  CONSTRAINT `fk_empeno_empleado1`
    FOREIGN KEY (`empleado`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empeno_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `empenofacil`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empeno_transaccioncaja1`
    FOREIGN KEY (`transaccioncaja`)
    REFERENCES `empenofacil`.`transaccioncaja` (`idtransaccioncaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`prenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`prenda` (
  `idprenda` INT NOT NULL,
  `empeno` INT NOT NULL,
  `tipo` INT NOT NULL,
  `categoria` INT NOT NULL,
  `avaluo` DECIMAL(15,2) NOT NULL,
  `montoprestado` DECIMAL(15,2) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `peso` DECIMAL(15,4) NULL,
  PRIMARY KEY (`idprenda`),
  INDEX `fk_prenda_empeno1_idx` (`empeno` ASC),
  INDEX `fk_prenda_itemCatalogo1_idx` (`tipo` ASC),
  INDEX `fk_prenda_itemcatalogo2_idx` (`categoria` ASC),
  CONSTRAINT `fk_prenda_empeno1`
    FOREIGN KEY (`empeno`)
    REFERENCES `empenofacil`.`empeno` (`idempeno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenda_itemCatalogo1`
    FOREIGN KEY (`tipo`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenda_itemcatalogo2`
    FOREIGN KEY (`categoria`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`fotografiaprenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`fotografiaprenda` (
  `prenda` INT NOT NULL,
  `fotografia` INT NOT NULL,
  PRIMARY KEY (`prenda`, `fotografia`),
  INDEX `fk_prenda_has_fotografia_fotografia1_idx` (`fotografia` ASC),
  INDEX `fk_prenda_has_fotografia_prenda1_idx` (`prenda` ASC),
  CONSTRAINT `fk_prenda_has_fotografia_prenda1`
    FOREIGN KEY (`prenda`)
    REFERENCES `empenofacil`.`prenda` (`idprenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenda_has_fotografia_fotografia1`
    FOREIGN KEY (`fotografia`)
    REFERENCES `empenofacil`.`fotografia` (`idfotografia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`articulo` (
  `idarticulo` INT NOT NULL,
  `prenda` INT NOT NULL,
  `empleado` INT NOT NULL,
  `comercializacion` DATETIME NOT NULL,
  `precio` DECIMAL(15,2) NOT NULL,
  `descuento` DECIMAL(15,2) NOT NULL,
  `codigodebarras` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idarticulo`),
  INDEX `fk_articulo_empleado1_idx` (`empleado` ASC),
  INDEX `fk_articulo_prenda1_idx` (`prenda` ASC),
  CONSTRAINT `fk_articulo_empleado1`
    FOREIGN KEY (`empleado`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_prenda1`
    FOREIGN KEY (`prenda`)
    REFERENCES `empenofacil`.`prenda` (`idprenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`venta` (
  `idventa` INT NOT NULL,
  `empleado` INT NOT NULL,
  `cliente` INT NOT NULL,
  `transaccioncaja` INT NOT NULL,
  PRIMARY KEY (`idventa`),
  INDEX `fk_venta_empleado1_idx` (`empleado` ASC),
  INDEX `fk_venta_cliente1_idx` (`cliente` ASC),
  INDEX `fk_venta_transaccioncaja1_idx` (`transaccioncaja` ASC),
  CONSTRAINT `fk_venta_empleado1`
    FOREIGN KEY (`empleado`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `empenofacil`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_transaccioncaja1`
    FOREIGN KEY (`transaccioncaja`)
    REFERENCES `empenofacil`.`transaccioncaja` (`idtransaccioncaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`articuloenventa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`articuloenventa` (
  `venta` INT NOT NULL,
  `articulo` INT NOT NULL,
  PRIMARY KEY (`venta`, `articulo`),
  INDEX `fk_venta_has_articulo_articulo1_idx` (`articulo` ASC),
  INDEX `fk_venta_has_articulo_venta1_idx` (`venta` ASC),
  CONSTRAINT `fk_venta_has_articulo_venta1`
    FOREIGN KEY (`venta`)
    REFERENCES `empenofacil`.`venta` (`idventa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_has_articulo_articulo1`
    FOREIGN KEY (`articulo`)
    REFERENCES `empenofacil`.`articulo` (`idarticulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`pagoempeno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`pagoempeno` (
  `idpagoempeno` INT NOT NULL,
  `empeno` INT NOT NULL,
  `transaccioncaja` INT NOT NULL,
  `tipo` INT NOT NULL,
  PRIMARY KEY (`idpagoempeno`),
  INDEX `fk_pagoempeno_empeno1_idx` (`empeno` ASC),
  INDEX `fk_pagoempeno_transaccioncaja1_idx` (`transaccioncaja` ASC),
  INDEX `fk_pagoempeno_itemcatalogo1_idx` (`tipo` ASC),
  CONSTRAINT `fk_pagoempeno_empeno1`
    FOREIGN KEY (`empeno`)
    REFERENCES `empenofacil`.`empeno` (`idempeno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagoempeno_transaccioncaja1`
    FOREIGN KEY (`transaccioncaja`)
    REFERENCES `empenofacil`.`transaccioncaja` (`idtransaccioncaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagoempeno_itemcatalogo1`
    FOREIGN KEY (`tipo`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`apartado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`apartado` (
  `idapartado` INT NOT NULL,
  `empleado` INT NOT NULL,
  `cliente` INT NOT NULL,
  `fechalimite` DATETIME NOT NULL,
  PRIMARY KEY (`idapartado`),
  INDEX `fk_apartado_empleado1_idx` (`empleado` ASC),
  INDEX `fk_apartado_cliente1_idx` (`cliente` ASC),
  CONSTRAINT `fk_apartado_empleado1`
    FOREIGN KEY (`empleado`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartado_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `empenofacil`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`articuloenapartado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`articuloenapartado` (
  `apartado` INT NOT NULL,
  `articulo` INT NOT NULL,
  PRIMARY KEY (`apartado`, `articulo`),
  INDEX `fk_apartado_has_articulo_articulo1_idx` (`articulo` ASC),
  INDEX `fk_apartado_has_articulo_apartado1_idx` (`apartado` ASC),
  CONSTRAINT `fk_apartado_has_articulo_apartado1`
    FOREIGN KEY (`apartado`)
    REFERENCES `empenofacil`.`apartado` (`idapartado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartado_has_articulo_articulo1`
    FOREIGN KEY (`articulo`)
    REFERENCES `empenofacil`.`articulo` (`idarticulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`pagoapartado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`pagoapartado` (
  `idpagoapartado` INT NOT NULL,
  `apartado` INT NOT NULL,
  `transaccioncaja` INT NOT NULL,
  PRIMARY KEY (`idpagoapartado`),
  INDEX `fk_pagoapartado_apartado1_idx` (`apartado` ASC),
  INDEX `fk_pagoapartado_transaccioncaja1_idx` (`transaccioncaja` ASC),
  CONSTRAINT `fk_pagoapartado_apartado1`
    FOREIGN KEY (`apartado`)
    REFERENCES `empenofacil`.`apartado` (`idapartado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagoapartado_transaccioncaja1`
    FOREIGN KEY (`transaccioncaja`)
    REFERENCES `empenofacil`.`transaccioncaja` (`idtransaccioncaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empenofacil`.`operacionexterna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`operacionexterna` (
  `idoperacionexterna` INT NOT NULL,
  `empleado` INT NOT NULL,
  `tipo` INT NOT NULL,
  `transaccioncaja` INT NOT NULL,
  `nota` VARCHAR(100) NULL,
  PRIMARY KEY (`idoperacionexterna`),
  INDEX `fk_operacionexterna_empleado1_idx` (`empleado` ASC),
  INDEX `fk_operacionexterna_itemcatalogo1_idx` (`tipo` ASC),
  INDEX `fk_operacionexterna_transaccioncaja1_idx` (`transaccioncaja` ASC),
  CONSTRAINT `fk_operacionexterna_empleado1`
    FOREIGN KEY (`empleado`)
    REFERENCES `empenofacil`.`empleado` (`idempleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_operacionexterna_itemcatalogo1`
    FOREIGN KEY (`tipo`)
    REFERENCES `empenofacil`.`itemcatalogo` (`iditemcatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_operacionexterna_transaccioncaja1`
    FOREIGN KEY (`transaccioncaja`)
    REFERENCES `empenofacil`.`transaccioncaja` (`idtransaccioncaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `empenofacil` ;

-- -----------------------------------------------------
-- Placeholder table for view `empenofacil`.`clieteview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`clieteview` (`idcliente` INT, `tipoidentificacion` INT, `nombres` INT, `apellidos` INT, `rfc` INT, `curp` INT, `identificacion` INT, `enlistanegra` INT);

-- -----------------------------------------------------
-- Placeholder table for view `empenofacil`.`empenoview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`empenoview` (`idempeno` INT, `empleado` INT, `cliente` INT, `transaccioncaja` INT, `fecha` INT, `fechalimite` INT, `fechaextendida` INT, `interes` INT, `almacenaje` INT, `nombrecotitular` INT, `extendido` INT, `vencido` INT, `cancelado` INT, `comercializado` INT);

-- -----------------------------------------------------
-- Placeholder table for view `empenofacil`.`prendaview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empenofacil`.`prendaview` (`idprenda` INT, `empeno` INT, `tipo` INT, `categoria` INT, `avaluo` INT, `montoprestado` INT, `nombre` INT, `descripcion` INT, `peso` INT, `comercializado` INT);

-- -----------------------------------------------------
-- View `empenofacil`.`clieteview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empenofacil`.`clieteview`;
USE `empenofacil`;
CREATE  OR REPLACE VIEW `clieteview` AS
SELECT *,
(SELECT COUNT(1) > 0 FROM empenofacil.registrolistanegra WHERE cliente = idCliente AND fechaRetira IS NULL) AS enlistanegra
FROM empenofacil.cliente;

-- -----------------------------------------------------
-- View `empenofacil`.`empenoview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empenofacil`.`empenoview`;
USE `empenofacil`;
CREATE  OR REPLACE VIEW `empenoview` AS
SELECT *,
fechalimite != fechaextendida AS extendido,
fechaextendida < NOW() AS vencido,
(SELECT cancelacion IS NOT NULL FROM empenofacil.transaccioncaja WHERE idtransaccioncaja = transaccioncaja) AS cancelado,
(SELECT COUNT(comercializado) = SUM(comercializado) FROM empenofacil.prendaview WHERE empeno = idempeno) AS comercializado
FROM empenofacil.empeno;

-- -----------------------------------------------------
-- View `empenofacil`.`prendaview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empenofacil`.`prendaview`;
USE `empenofacil`;
CREATE  OR REPLACE VIEW `prendaview` AS
SELECT *,
(SELECT COUNT(1) > 0 FROM empenofacil.articulo WHERE prenda = idprenda) AS comercializado
FROM empenofacil.prenda;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `empenofacil`.`sucursal`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`sucursal` (`idsucursal`, `nombre`, `direccion`, `colonia`, `codigopostal`, `ciudad`, `estado`, `caja`) VALUES (1, 'Empeño fácil Xalapa Centro', 'Calle Revolución 30', 'Zona Centro', '91000', 'Xalapa-Enríquez', 'Veracruz', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`itemcatalogo`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (1, NULL, 'Tipo empleado');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (2, NULL, 'Tipo identificación oficial');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (3, NULL, 'Tipo/categoría de prenda');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (6, NULL, 'Tipo de pago de empeño');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (7, NULL, 'Tipo de operación externa');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (8, 1, 'Gerente');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (9, 1, 'Cajero');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (10, 2, 'Pasaporte');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (11, 2, 'Credencial para votar');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (12, 2, 'Cédula profesional');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (13, 2, 'Cartilla del Servicio Militar Nacional');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (14, 3, 'Joyería');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (15, 3, 'Dispositivos electrónicos');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (16, 7, 'Deposito');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (17, 7, 'Retiro');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (18, 15, 'Consola de videojuegos');
INSERT INTO `empenofacil`.`itemcatalogo` (`iditemcatalogo`, `itemcatalogoSuperior`, `nombre`) VALUES (19, 14, 'Reloj de oro');

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`empleado`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`empleado` (`idempleado`, `sucursal`, `tipoempleado`, `numpersonal`, `nombres`, `apellidos`, `nombreusuario`, `contrasenahash`) VALUES (1, 1, 8, 'EM-XA-001', 'Francisco', 'Anaya Zambrano', 'user1', 'user1');
INSERT INTO `empenofacil`.`empleado` (`idempleado`, `sucursal`, `tipoempleado`, `numpersonal`, `nombres`, `apellidos`, `nombreusuario`, `contrasenahash`) VALUES (2, 1, 9, 'EM-XA-002', 'Gustavo', 'Camacho Macías', 'user2', 'user2');

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`cliente`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (1, 11, 'Silvia', 'Hernádez Casas', 'HECS8109143S1', 'HECS810914MOCRSI06', '3808914621717');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (2, 11, 'Luis', 'Naranjo Cervántez', 'NACL580323TT7', 'NACL580323HCSRRI08', '6949928218830');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (3, 11, 'Ángel', 'Rangel Garza', 'RAGA920904RQ2', 'RAGA920904HDFNRN011', '5689808950985');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (4, 11, 'Adriel', 'Escalante Betancourt', 'EABA530107133', 'EABA530107HDFSTD01', '1351153214133');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (5, 11, 'Ricardo', 'Franco Ledesma', 'FALR7705217T8', 'FALR770521HDFRDI05', '8197551985302');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (6, 11, 'Cristina', 'Montano Rivera', 'MORC910515I68', 'MORC910515MNLNIR07', '7631887615523');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (7, 11, 'Esperanza', 'Cisneros Fernández', 'CIFE711102G40', 'CIFE711102MJCIRS03', '8518970349511');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (8, 11, 'Verónica', 'Sánchez Ortiz', 'SEOV9409083J0', 'SEOV940908MYNNRR04', '9562757483618');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (9, 11, 'Elena', 'Alarcón López', 'AALE8803018M0', 'AALE880301MBSLPL07', '2245185731853');
INSERT INTO `empenofacil`.`cliente` (`idcliente`, `tipoidentificacion`, `nombres`, `apellidos`, `rfc`, `curp`, `identificacion`) VALUES (10, 11, 'Maria', 'Cárdenas Rocha', 'CERM850221MM0', 'CERM850221MSPRCR06', '5320261019800');

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`registrolistanegra`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`registrolistanegra` (`idregistrolistanegra`, `cliente`, `empleadoagrega`, `empleadoretira`, `fechaagrega`, `motivoagrega`, `fecharetira`, `motivoretira`) VALUES (1, 1, 1, 1, '2019-03-11 11:20:00', 'Empeñó prenda dañada', '2019-04-01 12:00:00', 'Retirado por criterio del gerente');
INSERT INTO `empenofacil`.`registrolistanegra` (`idregistrolistanegra`, `cliente`, `empleadoagrega`, `empleadoretira`, `fechaagrega`, `motivoagrega`, `fecharetira`, `motivoretira`) VALUES (2, 2, 1, NULL, '2019-03-12 10:30:00', 'Empeñó prenda robada', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`transaccioncaja`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`transaccioncaja` (`idtransaccioncaja`, `sucursal`, `cancelacion`, `monto`, `balacecaja`, `fechahora`, `descripcion`) VALUES (1, 1, NULL, 15000.00, 15000.00, '2019-02-04 10:00:00', 'Fondo inicial');
INSERT INTO `empenofacil`.`transaccioncaja` (`idtransaccioncaja`, `sucursal`, `cancelacion`, `monto`, `balacecaja`, `fechahora`, `descripcion`) VALUES (2, 1, NULL, -500.00, 14500.00, '2019-02-04 14:00:00', 'Empeño');
INSERT INTO `empenofacil`.`transaccioncaja` (`idtransaccioncaja`, `sucursal`, `cancelacion`, `monto`, `balacecaja`, `fechahora`, `descripcion`) VALUES (3, 1, NULL, -4000.00, 10500.00, '2019-02-06 10:00:00', 'Empeño');
INSERT INTO `empenofacil`.`transaccioncaja` (`idtransaccioncaja`, `sucursal`, `cancelacion`, `monto`, `balacecaja`, `fechahora`, `descripcion`) VALUES (4, 1, NULL, -1900.00, 8600.00, '2019-04-01 10:00:00', 'Empeño');

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`empeno`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`empeno` (`idempeno`, `empleado`, `cliente`, `transaccioncaja`, `fecha`, `fechalimite`, `fechaextendida`, `interes`, `almacenaje`, `nombrecotitular`) VALUES (1, 2, 5, 2, '2019-02-04 14:00:00', '2019-03-04 23:59:59', '2019-03-04 23:59:59', 80.00, 30.00, NULL);
INSERT INTO `empenofacil`.`empeno` (`idempeno`, `empleado`, `cliente`, `transaccioncaja`, `fecha`, `fechalimite`, `fechaextendida`, `interes`, `almacenaje`, `nombrecotitular`) VALUES (2, 2, 7, 3, '2019-02-06 10:00:00', '2019-03-06 23:59:59', '2019-03-20 23:59:59', 600.00, 200.00, NULL);
INSERT INTO `empenofacil`.`empeno` (`idempeno`, `empleado`, `cliente`, `transaccioncaja`, `fecha`, `fechalimite`, `fechaextendida`, `interes`, `almacenaje`, `nombrecotitular`) VALUES (3, 2, 4, 4, '2019-04-01 10:00:00', '2019-05-01 23:59:59', '2019-05-01 23:59:59', 200.00, 50.00, 'Beatriz Arasil Mata');

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`prenda`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`prenda` (`idprenda`, `empeno`, `tipo`, `categoria`, `avaluo`, `montoprestado`, `nombre`, `descripcion`, `peso`) VALUES (1, 1, 15, 18, 1099.00, 500.00, 'Xbox 360', 'Con cables', NULL);
INSERT INTO `empenofacil`.`prenda` (`idprenda`, `empeno`, `tipo`, `categoria`, `avaluo`, `montoprestado`, `nombre`, `descripcion`, `peso`) VALUES (2, 2, 15, 18, 3000.00, 1200.00, 'Nintendo switch', 'Sin cargador', NULL);
INSERT INTO `empenofacil`.`prenda` (`idprenda`, `empeno`, `tipo`, `categoria`, `avaluo`, `montoprestado`, `nombre`, `descripcion`, `peso`) VALUES (3, 2, 14, 19, 4500.00, 2800.00, 'Reloj chapado en oro', 'Oro 10k, automatico', NULL);
INSERT INTO `empenofacil`.`prenda` (`idprenda`, `empeno`, `tipo`, `categoria`, `avaluo`, `montoprestado`, `nombre`, `descripcion`, `peso`) VALUES (4, 3, 15, 18, 5000.00, 1900.00, 'Xbox one', 'En caja con accesorios', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`articulo`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`articulo` (`idarticulo`, `prenda`, `empleado`, `comercializacion`, `precio`, `descuento`, `codigodebarras`, `nombre`, `descripcion`) VALUES (1, 1, 2, '2019-03-15 11:00:00', 1099.00, 0.0, 'AAAA-1234', 'Xbox 360', 'Con cables');

COMMIT;


-- -----------------------------------------------------
-- Data for table `empenofacil`.`operacionexterna`
-- -----------------------------------------------------
START TRANSACTION;
USE `empenofacil`;
INSERT INTO `empenofacil`.`operacionexterna` (`idoperacionexterna`, `empleado`, `tipo`, `transaccioncaja`, `nota`) VALUES (1, 1, 16, 1, 'Deposito inicial');

COMMIT;

