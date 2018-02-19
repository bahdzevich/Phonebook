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
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `phonebook` ;

-- -----------------------------------------------------
-- Table `phonebook`.`news_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`news_category` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`news_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_role_role1`
  FOREIGN KEY (`role_id`)
  REFERENCES `phonebook`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`news`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`news` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`news` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `created` DATETIME NOT NULL DEFAULT now(),
  `user_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_news_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_news_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `phonebook`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`project` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`project` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_project_project1`
  FOREIGN KEY (`project_id`)
  REFERENCES `phonebook`.`project` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`news_has_news_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`news_has_news_category` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`news_has_news_category` (
  `news_id` BIGINT NOT NULL,
  `news_category_id` BIGINT NOT NULL,
  PRIMARY KEY (`news_id`, `news_category_id`),
  INDEX `fk_news_has_news_category_news_category1_idx` (`news_category_id` ASC),
  INDEX `fk_news_has_news_category_news1_idx` (`news_id` ASC),
  CONSTRAINT `fk_news_has_news_category_news1`
  FOREIGN KEY (`news_id`)
  REFERENCES `phonebook`.`news` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_news_has_news_category_news_category1`
  FOREIGN KEY (`news_category_id`)
  REFERENCES `phonebook`.`news_category` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `phonebook`.`news_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`news_category` (`id`, `name`) VALUES (1, 'BREAKING NEWS');
INSERT INTO `phonebook`.`news_category` (`id`, `name`) VALUES (2, 'FUN');

COMMIT;


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
INSERT INTO `phonebook`.`user` (`id`, `name`, `lastname`, `email`, `skype`, `phone`, `room`) VALUES (2, 'Artem', 'Kazakov', 'artem.kazakov@fortegrp.net', 'artyom12121', '+375297179338', 901);
INSERT INTO `phonebook`.`user` (`id`, `name`, `lastname`, `email`, `skype`, `phone`, `room`) VALUES (3, 'Sergey', 'Podgaysky', 'sergey.podgaysky@fortegrp.net', 'sergeypodgayskiy', '+375447553070', 820);

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`user_roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`user_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `phonebook`.`user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `phonebook`.`user_roles` (`user_id`, `role_id`) VALUES (3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`password`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`password` (`user_id`, `hash`) VALUES (1, '$2a$12$xzykFeT9U50C/epw988EluxHdbQY/PNFh12L41SZTmDg4FNOTDWRm');
INSERT INTO `phonebook`.`password` (`user_id`, `hash`) VALUES (2, '$2a$12$uQGTE92EY/nRQgQtUMuJa.V2lRr.gamNuQPfsL1ixKYwnHrarXoUG');
INSERT INTO `phonebook`.`password` (`user_id`, `hash`) VALUES (3, '$2a$12$f98C98lqBPJoruZ2LknGvuNiDaxmD/Q99ZjCMTHWMuf0.7Bcrd58i');

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`news`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`news` (`id`, `title`, `content`, `created`, `user_id`) VALUES (1, 'In The Beginning Of Time', '<p>Welcome my son, welcome to the machine</p><p>Where have you been?</p><p>It\'s alright we know where you\'ve been...</p>', DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`project`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`project` (`id`, `name`) VALUES (1, 'R&D');
INSERT INTO `phonebook`.`project` (`id`, `name`) VALUES (2, 'OCC');
INSERT INTO `phonebook`.`project` (`id`, `name`) VALUES (3, 'ATG');

COMMIT;


-- -----------------------------------------------------
-- Data for table `phonebook`.`user_projects`
-- -----------------------------------------------------
START TRANSACTION;
USE `phonebook`;
INSERT INTO `phonebook`.`user_projects` (`user_id`, `project_id`) VALUES (1, 1);
INSERT INTO `phonebook`.`user_projects` (`user_id`, `project_id`) VALUES (1, 2);
INSERT INTO `phonebook`.`user_projects` (`user_id`, `project_id`) VALUES (2, 2);
INSERT INTO `phonebook`.`user_projects` (`user_id`, `project_id`) VALUES (3, 3);

COMMIT;