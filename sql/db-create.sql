-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema internet_provider
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema internet_provider
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `internet_provider` DEFAULT CHARACTER SET utf8 ;
USE `internet_provider` ;

-- -----------------------------------------------------
-- Table `internet_provider`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `internet_provider`.`account` ;

CREATE TABLE IF NOT EXISTS `internet_provider`.`account` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(64) NOT NULL,
    `password` VARCHAR(64) NULL DEFAULT NULL,
    `role` ENUM('ROLE_ADMIN', 'ROLE_SUBSCRIBER') NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email` (`email` ASC) VISIBLE,
    UNIQUE INDEX `password` (`password` ASC) VISIBLE)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `internet_provider`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `internet_provider`.`person` ;

CREATE TABLE IF NOT EXISTS `internet_provider`.`person` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `is_blocked` TINYINT NOT NULL DEFAULT '0',
    `balance` INT NOT NULL,
    `account_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `account_id_idx` (`account_id` ASC) VISIBLE,
    CONSTRAINT `person_ibfk_1`
        FOREIGN KEY (`account_id`)
            REFERENCES `internet_provider`.`account` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `internet_provider`.`service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `internet_provider`.`service` ;

CREATE TABLE IF NOT EXISTS `internet_provider`.`service` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `description` MEDIUMTEXT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `internet_provider`.`tariff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `internet_provider`.`tariff` ;

CREATE TABLE IF NOT EXISTS `internet_provider`.`tariff` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `duration` INT NOT NULL,
    `service_id` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `service_id_idx` (`service_id` ASC) VISIBLE,
    CONSTRAINT `tariff_ibfk_1`

        FOREIGN KEY (`service_id`)

            REFERENCES `internet_provider`.`service` (`id`)

            ON DELETE CASCADE)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `internet_provider`.`person_tariff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `internet_provider`.`person_tariff` ;

CREATE TABLE IF NOT EXISTS `internet_provider`.`person_tariff` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `person_id` INT NOT NULL,
    `tariff_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `person_id` (`person_id` ASC, `tariff_id` ASC) VISIBLE,
    INDEX `person_id_idx` (`person_id` ASC) VISIBLE,
    INDEX `tariff_id_idx` (`tariff_id` ASC) VISIBLE,
    CONSTRAINT `person_tariff_ibfk_1`
        FOREIGN KEY (`person_id`)
            REFERENCES `internet_provider`.`person` (`id`),
            CONSTRAINT `person_tariff_ibfk_2`
                FOREIGN KEY (`tariff_id`)
                    REFERENCES `internet_provider`.`tariff` (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
