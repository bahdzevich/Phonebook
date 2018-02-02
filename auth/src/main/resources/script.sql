-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `phonebook` ;

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `phonebook` DEFAULT CHARACTER SET utf8 ;
USE `phonebook` ;

-- -----------------------------------------------------
-- Table `phonebook`.`news_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`news_category` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`news_category` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `phonebook`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`role` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`user` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(255) NOT NULL,
  `skype` VARCHAR(45) NULL,
  `phone` VARCHAR(13) NOT NULL,
  `room` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`user_roles` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_has_role_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_role_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `phonebook`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `phonebook`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`password`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`password` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`password` (
  `user_id` BIGINT NOT NULL,
  `hash` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_password_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `phonebook`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`news`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`news` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`news` (
  `id` BIGINT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NULL,
  `created` DATETIME NOT NULL DEFAULT now(),
  `news_category_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `news_category_id`, `user_id`),
  INDEX `fk_news_news_category1_idx` (`news_category_id` ASC),
  INDEX `fk_news_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_news_news_category1`
    FOREIGN KEY (`news_category_id`)
    REFERENCES `phonebook`.`news_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_news_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `phonebook`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`project` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`project` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`user_projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`user_projects` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`user_projects` (
  `user_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `project_id`),
  INDEX `fk_user_has_project_project1_idx` (`project_id` ASC),
  INDEX `fk_user_has_project_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_project_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `phonebook`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_project_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `phonebook`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `phonebook`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`role` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `phonebook`.`role` (`id`, `name`) VALUES (2, 'EMPLOYEE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`user` (`id`, `name`, `lastname`, `email`, `skype`, `phone`, `room`) VALUES (1, 'Eugene', 'Bogdevich', 'bogdevich96@gmail.com', 'bogdevich96', '+375292838788', 820);

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`user_roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`user_roles` (`user_id`, `role_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`password`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`password` (`user_id`, `hash`) VALUES (1, '$2a$12$JY9kyJIIiR2FUsRvnqF/SuJZK2ge7f0o6famBYmsIBSvG4cDBX8VW');

COMMIT;

