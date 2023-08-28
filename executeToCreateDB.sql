CREATE SCHEMA `equations` ;

DROP DATABASE IF EXISTS `equations` ;

CREATE DATABASE  IF NOT EXISTS `equations` DEFAULT CHARACTER SET utf8;
USE `equations`;

DROP TABLE IF EXISTS `equation` ;

CREATE TABLE IF NOT EXISTS `equation` (
  `id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `equation_root` ;

CREATE TABLE IF NOT EXISTS `equation_root` (
  `equation_id` VARCHAR(45) NOT NULL,
  `root` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`equation_id`),
  CONSTRAINT `fk_equation_root`
    FOREIGN KEY (`equation_id`)
    REFERENCES `equation` (`id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
  ) ENGINE = InnoDB;




