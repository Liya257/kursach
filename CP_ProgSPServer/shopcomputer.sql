-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema shopcomputer
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shopcomputer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shopcomputer` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `shopcomputer` ;

-- -----------------------------------------------------
-- Table `shopcomputer`.`categorytechnique`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`categorytechnique` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategory`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shopcomputer`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  `online` INT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shopcomputer`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`client` (
  `idClient` INT NOT NULL DEFAULT '-3',
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `surnameClient` VARCHAR(45) NOT NULL,
  `nameClient` VARCHAR(45) NOT NULL,
  `lastnameClient` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `user_idUser` INT NOT NULL,
  PRIMARY KEY (`idClient`, `user_idUser`),
  INDEX `fk_client_user1_idx` (`user_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_client_user1`
    FOREIGN KEY (`user_idUser`)
    REFERENCES `shopcomputer`.`user` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shopcomputer`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`employee` (
  `idEmployee` INT NOT NULL DEFAULT '-3',
  `position` VARCHAR(45) NOT NULL,
  `salary` INT NOT NULL,
  `surnameEmployee` VARCHAR(45) NOT NULL,
  `nameEmployee` VARCHAR(45) NOT NULL,
  `lastnameEmployee` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `user_idUser` INT NOT NULL,
  PRIMARY KEY (`idEmployee`, `user_idUser`),
  INDEX `fk_employee_user1_idx` (`user_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_employee_user1`
    FOREIGN KEY (`user_idUser`)
    REFERENCES `shopcomputer`.`user` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shopcomputer`.`techniquebrand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`techniquebrand` (
  `idTechniqueBrand` INT NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTechniqueBrand`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shopcomputer`.`technique`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`technique` (
  `CodeofProduct` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `dateOfRelease` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `techniquebrand_idTechniqueBrand` INT NOT NULL,
  `categorytechnique_idCategory` INT NOT NULL,
  PRIMARY KEY (`CodeofProduct`, `techniquebrand_idTechniqueBrand`, `categorytechnique_idCategory`),
  INDEX `fk_technique_techniquebrand1_idx` (`techniquebrand_idTechniqueBrand` ASC) VISIBLE,
  INDEX `fk_technique_categorytechnique1_idx` (`categorytechnique_idCategory` ASC) VISIBLE,
  CONSTRAINT `fk_technique_categorytechnique1`
    FOREIGN KEY (`categorytechnique_idCategory`)
    REFERENCES `shopcomputer`.`categorytechnique` (`idCategory`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_technique_techniquebrand1`
    FOREIGN KEY (`techniquebrand_idTechniqueBrand`)
    REFERENCES `shopcomputer`.`techniquebrand` (`idTechniqueBrand`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shopcomputer`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopcomputer`.`order` (
  `idOrder` INT NOT NULL AUTO_INCREMENT,
  `dataOfSale` VARCHAR(45) NOT NULL,
  `client_idClient` INT NOT NULL,
  `employee_idEmployee` INT NOT NULL DEFAULT '-3',
  `technique_CodeofProduct` INT NOT NULL,
  PRIMARY KEY (`idOrder`, `client_idClient`, `employee_idEmployee`, `technique_CodeofProduct`),
  INDEX `fk_order_client1_idx` (`client_idClient` ASC) VISIBLE,
  INDEX `fk_order_employee1_idx` (`employee_idEmployee` ASC) VISIBLE,
  INDEX `fk_order_technique1_idx` (`technique_CodeofProduct` ASC) VISIBLE,
  CONSTRAINT `fk_order_client1`
    FOREIGN KEY (`client_idClient`)
    REFERENCES `shopcomputer`.`client` (`idClient`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_employee1`
    FOREIGN KEY (`employee_idEmployee`)
    REFERENCES `shopcomputer`.`employee` (`idEmployee`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_technique1`
    FOREIGN KEY (`technique_CodeofProduct`)
    REFERENCES `shopcomputer`.`technique` (`CodeofProduct`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
