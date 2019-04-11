-- MySQL Script generated by MySQL Workbench
-- Wed Apr 10 22:28:20 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema empeniofacil
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema empeniofacil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empeniofacil` DEFAULT CHARACTER SET utf8 ;
USE `empeniofacil` ;

-- -----------------------------------------------------
-- Table `empeniofacil`.`catalogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`catalogo` (
  `idCatalogo` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `idTipo` INT(11) NULL DEFAULT NULL,
  `Catalogo_idCatalogo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idCatalogo`),
  INDEX `fk_Catalogo_Catalogo1_idx` (`Catalogo_idCatalogo` ASC),
  CONSTRAINT `fk_Catalogo_Catalogo1`
    FOREIGN KEY (`Catalogo_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`bolsa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`bolsa` (
  `idBolsa` INT(11) NOT NULL AUTO_INCREMENT,
  `fondoInicial` DOUBLE NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `montoFinal` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idBolsa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`parametros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`parametros` (
  `idParametros` INT(11) NOT NULL AUTO_INCREMENT,
  `iva` INT(11) NULL DEFAULT NULL,
  `diasExtension` INT(11) NULL DEFAULT NULL,
  `porcentajePrestamo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idParametros`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`sucursal` (
  `idSucursal` INT(11) NOT NULL AUTO_INCREMENT,
  `Parametros_idParametros` INT(11) NOT NULL,
  `Bolsa_idBolsa` INT(11) NOT NULL,
  PRIMARY KEY (`idSucursal`),
  INDEX `fk_Sucursal_Parametros1_idx` (`Parametros_idParametros` ASC),
  INDEX `fk_Sucursal_Bolsa1_idx` (`Bolsa_idBolsa` ASC),
  CONSTRAINT `fk_Sucursal_Bolsa1`
    FOREIGN KEY (`Bolsa_idBolsa`)
    REFERENCES `empeniofacil`.`bolsa` (`idBolsa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sucursal_Parametros1`
    FOREIGN KEY (`Parametros_idParametros`)
    REFERENCES `empeniofacil`.`parametros` (`idParametros`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`empleado` (
  `idEmpleado` INT(11) NOT NULL AUTO_INCREMENT,
  `Sucursal_idSucursal` INT(11) NOT NULL,
  `numeroPersonal` INT(11) NULL DEFAULT NULL,
  `nombreEmpleado` VARCHAR(250) NULL DEFAULT NULL,
  `contrasenia` VARCHAR(50) NULL DEFAULT NULL,
  `tipoEmpleado_idCatalogo` INT(11) NOT NULL,
  `nombreUsuario` VARCHAR(250) NULL DEFAULT NULL,
  INDEX `fk_Empleado_Sucursal1_idx` (`Sucursal_idSucursal` ASC),
  INDEX `fk_Empleado_Catalogo1_idx` (`tipoEmpleado_idCatalogo` ASC),
  PRIMARY KEY (`idEmpleado`),
  CONSTRAINT `fk_Empleado_Catalogo1`
    FOREIGN KEY (`tipoEmpleado_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleado_Sucursal1`
    FOREIGN KEY (`Sucursal_idSucursal`)
    REFERENCES `empeniofacil`.`sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`apartado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`apartado` (
  `idApartado` INT(11) NOT NULL AUTO_INCREMENT,
  `Empleado_idEmpleado` INT(11) NOT NULL,
  `montoApartado` DOUBLE NULL DEFAULT NULL,
  `abono` DOUBLE NULL DEFAULT NULL,
  `fechaApartado` DATE NULL DEFAULT NULL,
  `fechaLiquidacion` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idApartado`),
  INDEX `fk_Apartado_Empleado1_idx` (`Empleado_idEmpleado` ASC),
  CONSTRAINT `fk_Apartado_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `empeniofacil`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`comercializacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`comercializacion` (
  `idComercializacion` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  `nombreEmpleado` VARCHAR(45) NULL DEFAULT NULL,
  `numeroArticulos` INT(11) NULL DEFAULT NULL,
  `Empleado_idEmpleado` INT(11) NOT NULL,
  PRIMARY KEY (`idComercializacion`),
  INDEX `fk_Comercializacion_Empleado1_idx` (`Empleado_idEmpleado` ASC),
  CONSTRAINT `fk_Comercializacion_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `empeniofacil`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`articulo` (
  `idArtículo` INT(11) NOT NULL AUTO_INCREMENT,
  `descuento` VARCHAR(45) NULL DEFAULT NULL,
  `precio` DOUBLE NULL DEFAULT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `tipoPrenda_idCatalogo` INT(11) NOT NULL,
  `tipoCategoria_idCatalogo1` INT(11) NOT NULL,
  `Comercializacion_idComercializacion` INT(11) NOT NULL,
  PRIMARY KEY (`idArtículo`),
  INDEX `fk_Artículo_Catalogo1_idx` (`tipoPrenda_idCatalogo` ASC),
  INDEX `fk_Artículo_Catalogo2_idx` (`tipoCategoria_idCatalogo1` ASC),
  INDEX `fk_Artículo_Comercializacion1_idx` (`Comercializacion_idComercializacion` ASC),
  CONSTRAINT `fk_Artículo_Catalogo1`
    FOREIGN KEY (`tipoPrenda_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_Catalogo2`
    FOREIGN KEY (`tipoCategoria_idCatalogo1`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_Comercializacion1`
    FOREIGN KEY (`Comercializacion_idComercializacion`)
    REFERENCES `empeniofacil`.`comercializacion` (`idComercializacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`articulo_has_apartado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`articulo_has_apartado` (
  `Articulo_idArticulo` INT(11) NOT NULL AUTO_INCREMENT,
  `Apartado_idApartado` INT(11) NOT NULL,
  PRIMARY KEY (`Articulo_idArticulo`, `Apartado_idApartado`),
  INDEX `fk_Artículo_has_Apartado_Apartado1_idx` (`Apartado_idApartado` ASC),
  INDEX `fk_Artículo_has_Apartado_Artículo1_idx` (`Articulo_idArticulo` ASC),
  CONSTRAINT `fk_Artículo_has_Apartado_Apartado1`
    FOREIGN KEY (`Apartado_idApartado`)
    REFERENCES `empeniofacil`.`apartado` (`idApartado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_has_Apartado_Artículo1`
    FOREIGN KEY (`Articulo_idArticulo`)
    REFERENCES `empeniofacil`.`articulo` (`idArtículo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`venta` (
  `idVenta` INT(11) NOT NULL AUTO_INCREMENT,
  `Empleado_idEmpleado` INT(11) NOT NULL,
  `montoVendido` DOUBLE NULL DEFAULT NULL,
  `fechaVenta` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  INDEX `fk_Venta_Empleado1_idx` (`Empleado_idEmpleado` ASC),
  CONSTRAINT `fk_Venta_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `empeniofacil`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`articulo_has_venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`articulo_has_venta` (
  `Articulo_idArticulo` INT(11) NOT NULL,
  `Venta_idVenta` INT(11) NOT NULL,
  PRIMARY KEY (`Articulo_idArticulo`, `Venta_idVenta`),
  INDEX `fk_Artículo_has_Venta_Venta1_idx` (`Venta_idVenta` ASC),
  INDEX `fk_Artículo_has_Venta_Artículo1_idx` (`Articulo_idArticulo` ASC),
  CONSTRAINT `fk_Artículo_has_Venta_Artículo1`
    FOREIGN KEY (`Articulo_idArticulo`)
    REFERENCES `empeniofacil`.`articulo` (`idArtículo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_has_Venta_Venta1`
    FOREIGN KEY (`Venta_idVenta`)
    REFERENCES `empeniofacil`.`venta` (`idVenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`cliente` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL DEFAULT NULL,
  `apellidos` VARCHAR(150) NULL DEFAULT NULL,
  `rfc` INT(11) NULL DEFAULT NULL,
  `curp` VARCHAR(18) NULL DEFAULT NULL,
  `idIdentificacion` INT(11) NULL DEFAULT NULL,
  `cotitular` VARCHAR(250) NULL DEFAULT NULL,
  `listaNegra` TINYINT(4) NULL DEFAULT NULL,
  `tipoIdentificacion_idCatalogo` INT(11) NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Cliente_Catalogo1_idx` (`tipoIdentificacion_idCatalogo` ASC),
  CONSTRAINT `fk_Cliente_Catalogo1`
    FOREIGN KEY (`tipoIdentificacion_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`contrato` (
  `idContrato` INT(11) NOT NULL AUTO_INCREMENT,
  `Empleado_idEmpleado` INT(11) NOT NULL,
  `fechaRealizacion` DATE NULL DEFAULT NULL,
  `montoPrestado` DOUBLE NULL DEFAULT NULL,
  `fechaLimite` DATE NULL DEFAULT NULL,
  `tiempoExtendido` INT(11) NULL DEFAULT NULL,
  `Cliente_idCliente` INT(11) NOT NULL,
  `tazaInteres` INT(11) NULL DEFAULT NULL,
  `almacenaje` INT(11) NULL DEFAULT NULL,
  `tipoPago_idCatalogo` INT(11) NOT NULL,
  `refrendo` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idContrato`),
  INDEX `fk_Contrato_Empleado1_idx` (`Empleado_idEmpleado` ASC),
  INDEX `fk_Contrato_Cliente1_idx` (`Cliente_idCliente` ASC),
  INDEX `fk_Contrato_Catalogo1_idx` (`tipoPago_idCatalogo` ASC),
  CONSTRAINT `fk_Contrato_Catalogo1`
    FOREIGN KEY (`tipoPago_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `empeniofacil`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `empeniofacil`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`foto_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`foto_articulo` (
  `idFoto_Articulo` INT(11) NOT NULL AUTO_INCREMENT,
  `Artículo_idArtículo` INT(11) NOT NULL,
  `foto` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idFoto_Articulo`),
  INDEX `fk_Foto_Articulo_Artículo1_idx` (`Artículo_idArtículo` ASC),
  CONSTRAINT `fk_Foto_Articulo_Artículo1`
    FOREIGN KEY (`Artículo_idArtículo`)
    REFERENCES `empeniofacil`.`articulo` (`idArtículo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`foto_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`foto_cliente` (
  `idFoto_Cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `Cliente_idCliente` INT(11) NOT NULL,
  `foto` VARCHAR(45) NULL DEFAULT NULL,
  `tipoFoto_idCatalogo` INT(11) NOT NULL,
  PRIMARY KEY (`idFoto_Cliente`),
  INDEX `fk_Foto_Cliente_Cliente1_idx` (`Cliente_idCliente` ASC),
  INDEX `fk_Foto_Cliente_Catalogo1_idx` (`tipoFoto_idCatalogo` ASC),
  CONSTRAINT `fk_Foto_Cliente_Catalogo1`
    FOREIGN KEY (`tipoFoto_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Foto_Cliente_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `empeniofacil`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`prenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`prenda` (
  `idPrenda` INT(11) NOT NULL AUTO_INCREMENT,
  `Contrato_idContrato` INT(11) NOT NULL,
  `valor` DOUBLE NULL DEFAULT NULL,
  `nombre` VARCHAR(250) NULL DEFAULT NULL,
  `tipoPrenda_idCatalogo` INT(11) NOT NULL,
  `tipoCategoria_idCatalogo1` INT(11) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idPrenda`),
  INDEX `fk_Prenda_Contrato1_idx` (`Contrato_idContrato` ASC),
  INDEX `fk_Prenda_Catalogo1_idx` (`tipoPrenda_idCatalogo` ASC),
  INDEX `fk_Prenda_Catalogo2_idx` (`tipoCategoria_idCatalogo1` ASC),
  CONSTRAINT `fk_Prenda_Catalogo1`
    FOREIGN KEY (`tipoPrenda_idCatalogo`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prenda_Catalogo2`
    FOREIGN KEY (`tipoCategoria_idCatalogo1`)
    REFERENCES `empeniofacil`.`catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prenda_Contrato1`
    FOREIGN KEY (`Contrato_idContrato`)
    REFERENCES `empeniofacil`.`contrato` (`idContrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empeniofacil`.`foto_prenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empeniofacil`.`foto_prenda` (
  `idFoto_Prenda` INT(11) NOT NULL AUTO_INCREMENT,
  `Prenda_idPrenda` INT(11) NOT NULL,
  `foto` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idFoto_Prenda`),
  INDEX `fk_Foto_Prenda_Prenda1_idx` (`Prenda_idPrenda` ASC),
  CONSTRAINT `fk_Foto_Prenda_Prenda1`
    FOREIGN KEY (`Prenda_idPrenda`)
    REFERENCES `empeniofacil`.`prenda` (`idPrenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
