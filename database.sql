-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookstore` DEFAULT CHARACTER SET utf8 ;
USE `bookstore` ;

-- -----------------------------------------------------
-- Table `bookstore`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`authors` (
  `id_author` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id_author`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookstore`.`publishers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`publishers` (
  `id_publisher` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_publisher`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookstore`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`books` (
  `id_book` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `release_date` DATE NOT NULL,
  `id_author` INT NOT NULL,
  `id_publisher` INT NOT NULL,
  PRIMARY KEY (`id_book`),
  INDEX `fk_books_authors_idx` (`id_author` ASC) VISIBLE,
  INDEX `fk_books_publishers_idx` (`id_publisher` ASC) VISIBLE,
  CONSTRAINT `fk_books_authors`
    FOREIGN KEY (`id_author`)
    REFERENCES `bookstore`.`authors` (`id_author`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_books_publishers`
    FOREIGN KEY (`id_publisher`)
    REFERENCES `bookstore`.`publishers` (`id_publisher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
