-- MySQL Script generated by MySQL Workbench
-- Wed Mar 20 15:00:36 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema EmpenioFacil
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `EmpenioFacil` ;

-- -----------------------------------------------------
-- Schema EmpenioFacil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EmpenioFacil` DEFAULT CHARACTER SET utf8 ;
USE `EmpenioFacil` ;

-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Catalogo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Catalogo` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Catalogo` (
  `idCatalogo` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `idTipo` INT NULL,
  `Catalogo_idCatalogo` INT NOT NULL,
  PRIMARY KEY (`idCatalogo`),
  INDEX `fk_Catalogo_Catalogo1_idx` (`Catalogo_idCatalogo` ASC) VISIBLE,
  CONSTRAINT `fk_Catalogo_Catalogo1`
    FOREIGN KEY (`Catalogo_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Cliente` (
  `idCliente` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `rfc` INT NULL,
  `curp` VARCHAR(45) NULL,
  `idIdentificacion` INT NULL,
  `cotitular` VARCHAR(45) NULL,
  `listaNegra` TINYINT NULL,
  `tipoIdentificacion_idCatalogo` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Cliente_Catalogo1_idx` (`tipoIdentificacion_idCatalogo` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Catalogo1`
    FOREIGN KEY (`tipoIdentificacion_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Parametros`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Parametros` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Parametros` (
  `idParametros` INT NOT NULL,
  `iva` INT NULL,
  `diasExtension` INT NULL,
  `porcentajePrestamo` INT NULL,
  PRIMARY KEY (`idParametros`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Bolsa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Bolsa` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Bolsa` (
  `idBolsa` INT NOT NULL,
  `fondoInicial` DOUBLE NULL,
  `fecha` DATE NULL,
  `montoFinal` DOUBLE NULL,
  PRIMARY KEY (`idBolsa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Sucursal` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Sucursal` (
  `idSucursal` INT NOT NULL,
  `Parametros_idParametros` INT NOT NULL,
  `Bolsa_idBolsa` INT NOT NULL,
  PRIMARY KEY (`idSucursal`),
  INDEX `fk_Sucursal_Parametros1_idx` (`Parametros_idParametros` ASC) VISIBLE,
  INDEX `fk_Sucursal_Bolsa1_idx` (`Bolsa_idBolsa` ASC) VISIBLE,
  CONSTRAINT `fk_Sucursal_Parametros1`
    FOREIGN KEY (`Parametros_idParametros`)
    REFERENCES `EmpenioFacil`.`Parametros` (`idParametros`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sucursal_Bolsa1`
    FOREIGN KEY (`Bolsa_idBolsa`)
    REFERENCES `EmpenioFacil`.`Bolsa` (`idBolsa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Empleado` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Empleado` (
  `idEmpleado` INT NOT NULL,
  `Sucursal_idSucursal` INT NOT NULL,
  `numeroPersonal` INT NULL,
  `nombreEmpleado` VARCHAR(45) NULL,
  `contrasenia` VARCHAR(45) NULL,
  `tipoEmpleado_idCatalogo` INT NOT NULL,
  `nombreUsuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idEmpleado`),
  INDEX `fk_Empleado_Sucursal1_idx` (`Sucursal_idSucursal` ASC) VISIBLE,
  INDEX `fk_Empleado_Catalogo1_idx` (`tipoEmpleado_idCatalogo` ASC) VISIBLE,
  CONSTRAINT `fk_Empleado_Sucursal1`
    FOREIGN KEY (`Sucursal_idSucursal`)
    REFERENCES `EmpenioFacil`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleado_Catalogo1`
    FOREIGN KEY (`tipoEmpleado_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Contrato` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Contrato` (
  `idContrato` INT NOT NULL,
  `Empleado_idEmpleado` INT NOT NULL,
  `fechaRealizacion` DATE NULL,
  `montoPrestado` DOUBLE NULL,
  `fechaLimite` DATE NULL,
  `tiempoExtendido` INT NULL,
  `Cliente_idCliente` INT NOT NULL,
  `tazaInteres` INT NULL,
  `almacenaje` INT NULL,
  `tipoPago_idCatalogo` INT NOT NULL,
  `refrendo` DOUBLE NULL,
  PRIMARY KEY (`idContrato`),
  INDEX `fk_Contrato_Empleado1_idx` (`Empleado_idEmpleado` ASC) VISIBLE,
  INDEX `fk_Contrato_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  INDEX `fk_Contrato_Catalogo1_idx` (`tipoPago_idCatalogo` ASC) VISIBLE,
  CONSTRAINT `fk_Contrato_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `EmpenioFacil`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `EmpenioFacil`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Catalogo1`
    FOREIGN KEY (`tipoPago_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Prenda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Prenda` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Prenda` (
  `idPrenda` INT NOT NULL,
  `Contrato_idContrato` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `nombre` VARCHAR(45) NULL,
  `tipoPrenda_idCatalogo` INT NOT NULL,
  `tipoCategoria_idCatalogo1` INT NOT NULL,
  PRIMARY KEY (`idPrenda`),
  INDEX `fk_Prenda_Contrato1_idx` (`Contrato_idContrato` ASC) VISIBLE,
  INDEX `fk_Prenda_Catalogo1_idx` (`tipoPrenda_idCatalogo` ASC) VISIBLE,
  INDEX `fk_Prenda_Catalogo2_idx` (`tipoCategoria_idCatalogo1` ASC) VISIBLE,
  CONSTRAINT `fk_Prenda_Contrato1`
    FOREIGN KEY (`Contrato_idContrato`)
    REFERENCES `EmpenioFacil`.`Contrato` (`idContrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prenda_Catalogo1`
    FOREIGN KEY (`tipoPrenda_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prenda_Catalogo2`
    FOREIGN KEY (`tipoCategoria_idCatalogo1`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Comercializacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Comercializacion` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Comercializacion` (
  `idComercializacion` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `idEmpleado` INT NOT NULL,
  PRIMARY KEY (`idComercializacion`),
  INDEX `fk_Comercializacion_Empleado1_idx` (`idEmpleado` ASC) VISIBLE,
  CONSTRAINT `fk_Comercializacion_Empleado1`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `EmpenioFacil`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Artículo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Artículo` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Artículo` (
  `idArtículo` INT NOT NULL,
  `descuento` DOUBLE NULL,
  `precio` DOUBLE NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `tipoPrenda_idCatalogo` INT NOT NULL,
  `tipoCategoria_idCatalogo1` INT NOT NULL,
  `Comercializacion_idComercializacion` INT NOT NULL,
  PRIMARY KEY (`idArtículo`),
  INDEX `fk_Artículo_Catalogo1_idx` (`tipoPrenda_idCatalogo` ASC) VISIBLE,
  INDEX `fk_Artículo_Catalogo2_idx` (`tipoCategoria_idCatalogo1` ASC) VISIBLE,
  INDEX `fk_Artículo_Comercializacion1_idx` (`Comercializacion_idComercializacion` ASC) VISIBLE,
  CONSTRAINT `fk_Artículo_Catalogo1`
    FOREIGN KEY (`tipoPrenda_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_Catalogo2`
    FOREIGN KEY (`tipoCategoria_idCatalogo1`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_Comercializacion1`
    FOREIGN KEY (`Comercializacion_idComercializacion`)
    REFERENCES `EmpenioFacil`.`Comercializacion` (`idComercializacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Venta` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Venta` (
  `idVenta` INT NOT NULL,
  `Empleado_idEmpleado` INT NOT NULL,
  `montoVendido` DOUBLE NULL,
  `fechaVenta` DATE NULL,
  PRIMARY KEY (`idVenta`),
  INDEX `fk_Venta_Empleado1_idx` (`Empleado_idEmpleado` ASC) VISIBLE,
  CONSTRAINT `fk_Venta_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `EmpenioFacil`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Apartado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Apartado` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Apartado` (
  `idApartado` INT NOT NULL,
  `Empleado_idEmpleado` INT NOT NULL,
  `montoApartado` DOUBLE NULL,
  `abono` DOUBLE NULL,
  `fechaApartado` DATE NULL,
  `fechaLiquidacion` DATE NULL,
  PRIMARY KEY (`idApartado`),
  INDEX `fk_Apartado_Empleado1_idx` (`Empleado_idEmpleado` ASC) VISIBLE,
  CONSTRAINT `fk_Apartado_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `EmpenioFacil`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Foto_Prenda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Foto_Prenda` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Foto_Prenda` (
  `idFoto_Prenda` INT NOT NULL,
  `Prenda_idPrenda` INT NOT NULL,
  `foto` VARCHAR(45) NULL,
  PRIMARY KEY (`idFoto_Prenda`),
  INDEX `fk_Foto_Prenda_Prenda1_idx` (`Prenda_idPrenda` ASC) VISIBLE,
  CONSTRAINT `fk_Foto_Prenda_Prenda1`
    FOREIGN KEY (`Prenda_idPrenda`)
    REFERENCES `EmpenioFacil`.`Prenda` (`idPrenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Foto_Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Foto_Cliente` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Foto_Cliente` (
  `idFoto_Cliente` INT NOT NULL,
  `Cliente_idCliente` INT NOT NULL,
  `foto` VARCHAR(45) NULL,
  `tipoFoto_idCatalogo` INT NOT NULL,
  PRIMARY KEY (`idFoto_Cliente`),
  INDEX `fk_Foto_Cliente_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  INDEX `fk_Foto_Cliente_Catalogo1_idx` (`tipoFoto_idCatalogo` ASC) VISIBLE,
  CONSTRAINT `fk_Foto_Cliente_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `EmpenioFacil`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Foto_Cliente_Catalogo1`
    FOREIGN KEY (`tipoFoto_idCatalogo`)
    REFERENCES `EmpenioFacil`.`Catalogo` (`idCatalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Foto_Articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Foto_Articulo` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Foto_Articulo` (
  `idFoto_Articulo` INT NOT NULL,
  `Artículo_idArtículo` INT NOT NULL,
  `foto` VARCHAR(45) NULL,
  PRIMARY KEY (`idFoto_Articulo`),
  INDEX `fk_Foto_Articulo_Artículo1_idx` (`Artículo_idArtículo` ASC) VISIBLE,
  CONSTRAINT `fk_Foto_Articulo_Artículo1`
    FOREIGN KEY (`Artículo_idArtículo`)
    REFERENCES `EmpenioFacil`.`Artículo` (`idArtículo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Artículo_has_Venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Artículo_has_Venta` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Artículo_has_Venta` (
  `Artículo_idArtículo` INT NOT NULL,
  `Venta_idVenta` INT NOT NULL,
  PRIMARY KEY (`Artículo_idArtículo`, `Venta_idVenta`),
  INDEX `fk_Artículo_has_Venta_Venta1_idx` (`Venta_idVenta` ASC) VISIBLE,
  INDEX `fk_Artículo_has_Venta_Artículo1_idx` (`Artículo_idArtículo` ASC) VISIBLE,
  CONSTRAINT `fk_Artículo_has_Venta_Artículo1`
    FOREIGN KEY (`Artículo_idArtículo`)
    REFERENCES `EmpenioFacil`.`Artículo` (`idArtículo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_has_Venta_Venta1`
    FOREIGN KEY (`Venta_idVenta`)
    REFERENCES `EmpenioFacil`.`Venta` (`idVenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`Artículo_has_Apartado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`Artículo_has_Apartado` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`Artículo_has_Apartado` (
  `Artículo_idArtículo` INT NOT NULL,
  `Apartado_idApartado` INT NOT NULL,
  PRIMARY KEY (`Artículo_idArtículo`, `Apartado_idApartado`),
  INDEX `fk_Artículo_has_Apartado_Apartado1_idx` (`Apartado_idApartado` ASC) VISIBLE,
  INDEX `fk_Artículo_has_Apartado_Artículo1_idx` (`Artículo_idArtículo` ASC) VISIBLE,
  CONSTRAINT `fk_Artículo_has_Apartado_Artículo1`
    FOREIGN KEY (`Artículo_idArtículo`)
    REFERENCES `EmpenioFacil`.`Artículo` (`idArtículo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artículo_has_Apartado_Apartado1`
    FOREIGN KEY (`Apartado_idApartado`)
    REFERENCES `EmpenioFacil`.`Apartado` (`idApartado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EmpenioFacil`.`RegistroListaNegra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EmpenioFacil`.`RegistroListaNegra` ;

CREATE TABLE IF NOT EXISTS `EmpenioFacil`.`RegistroListaNegra` (
  `idRegistroListaNegra` INT NOT NULL,
  `idCliente` INT NOT NULL,
  `idEmpleadoAgrega` INT NOT NULL,
  `motivoAgrega` VARCHAR(100) NOT NULL,
  `fechaAgrega` DATE NOT NULL,
  `idEmpleadoRetira` INT NULL,
  `motivoRetira` VARCHAR(100) NULL,
  `fechaRetira` DATE NULL,
  PRIMARY KEY (`idRegistroListaNegra`),
  INDEX `fk_ListaNegra_Empleado1_idx` (`idEmpleadoAgrega` ASC) VISIBLE,
  INDEX `fk_ListaNegra_Empleado2_idx` (`idEmpleadoRetira` ASC) VISIBLE,
  INDEX `fk_ListaNegra_Cliente1_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_ListaNegra_Empleado1`
    FOREIGN KEY (`idEmpleadoAgrega`)
    REFERENCES `EmpenioFacil`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ListaNegra_Empleado2`
    FOREIGN KEY (`idEmpleadoRetira`)
    REFERENCES `EmpenioFacil`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ListaNegra_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `EmpenioFacil`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
