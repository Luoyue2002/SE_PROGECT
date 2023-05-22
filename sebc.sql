-- MySQL Script generated by MySQL Workbench
-- Fri Apr  7 21:34:08 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sedb` ;

-- -----------------------------------------------------
-- Schema sedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sedb` DEFAULT CHARACTER SET utf8 ;
USE `sedb` ;

-- -----------------------------------------------------
-- Table `sedb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`user` ;

CREATE TABLE IF NOT EXISTS `sedb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `realName` VARCHAR(16) NOT NULL,
  `identity` VARCHAR(18) NOT NULL,
  `school` VARCHAR(16) NULL,
  `schoolId` VARCHAR(16) NULL,
  `gender` INT NOT NULL,
  `phone` VARCHAR(16) NOT NULL,
  `image` VARCHAR(128) NOT NULL,
  `address1` VARCHAR(128) NOT NULL,
  `address2` VARCHAR(128) NULL,
  `address3` VARCHAR(128) NULL,
  `ifShop` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `sedb`.`chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`chat` ;

CREATE TABLE IF NOT EXISTS `sedb`.`chat` (
  `time` DATETIME NOT NULL,
  `content` VARCHAR(128) NOT NULL,
  `senderId` INT NOT NULL,
  `receiverId` INT NOT NULL,
  PRIMARY KEY (`time`, `senderId`, `receiverId`),
  CONSTRAINT `sender`
    FOREIGN KEY (`senderId`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `receiver`
    FOREIGN KEY (`receiverId`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`session` ;

CREATE TABLE IF NOT EXISTS `sedb`.`session` (
  `senderId` INT NOT NULL,
  `receiverId` INT NOT NULL,
  `updateTime` DATETIME NOT NULL,
  PRIMARY KEY (`senderId`, `receiverId`),
  CONSTRAINT `sender_1`
    FOREIGN KEY (`senderId`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `receiver_1`
    FOREIGN KEY (`receiverId`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`commodity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`commodity` ;

CREATE TABLE IF NOT EXISTS `sedb`.`commodity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `publisher` INT NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `dscription` VARCHAR(512) NOT NULL,
  `category` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `publisher`
    FOREIGN KEY (`publisher`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`item` ;

CREATE TABLE IF NOT EXISTS `sedb`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parentId` INT NOT NULL,
  `name` VARCHAR(256) NOT NULL,
  `number` INT NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `parent`
    FOREIGN KEY (`parentId`)
    REFERENCES `sedb`.`commodity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`cart` ;

CREATE TABLE IF NOT EXISTS `sedb`.`cart` (
  `user` INT NOT NULL,
  `item` INT NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`user`, `item`),
  CONSTRAINT `user`
    FOREIGN KEY (`user`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item`
    FOREIGN KEY (`item`)
    REFERENCES `sedb`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`favorites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`favorites` ;

CREATE TABLE IF NOT EXISTS `sedb`.`favorites` (
  `user` INT NOT NULL,
  `item` INT NOT NULL,
  PRIMARY KEY (`user`, `item`),
  CONSTRAINT `user_1`
    FOREIGN KEY (`user`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_1`
    FOREIGN KEY (`item`)
    REFERENCES `sedb`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`detail` ;

CREATE TABLE IF NOT EXISTS `sedb`.`detail` (
  `commodityId` INT NOT NULL,
  `image` VARCHAR(128) NOT NULL,
  CONSTRAINT `commodity`
    FOREIGN KEY (`commodityId`)
    REFERENCES `sedb`.`commodity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`preview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`preview` ;

CREATE TABLE IF NOT EXISTS `sedb`.`preview` (
  `commodityId` INT NOT NULL,
  `image` VARCHAR(128) NULL,
  CONSTRAINT `father`
    FOREIGN KEY (`commodityId`)
    REFERENCES `sedb`.`commodity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`order` ;

CREATE TABLE IF NOT EXISTS `sedb`.`order` (
  `id` INT NOT NULL,
  `buyer` INT NOT NULL,
  `seller` INT NOT NULL,
  `number` INT NOT NULL,
  `price` FLOAT NOT NULL,
  `address` VARCHAR(128) NOT NULL,
  `item` INT NOT NULL,
  `state` INT NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `buyer`
    FOREIGN KEY (`buyer`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `seller`
    FOREIGN KEY (`seller`)
    REFERENCES `sedb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_2`
    FOREIGN KEY (`item`)
    REFERENCES `sedb`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`comment` ;

CREATE TABLE IF NOT EXISTS `sedb`.`comment` (
  `id` INT NOT NULL,
  `content` VARCHAR(128) NULL,
  `time` DATETIME NULL,
  `review` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `order`
    FOREIGN KEY (`id`)
    REFERENCES `sedb`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sedb`.`comment_picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sedb`.`commentpicture` ;

CREATE TABLE IF NOT EXISTS `sedb`.`commentpicture` (
  `commentId` INT NOT NULL,
  `image` VARCHAR(128) NULL,
  PRIMARY KEY (`commentId`, `image`),
  CONSTRAINT `comment`
    FOREIGN KEY (`commentId`)
    REFERENCES `sedb`.`comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
