SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema new-airlines
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema new-airlines
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `new-airlines` DEFAULT CHARACTER SET utf8 ;
USE `new-airlines` ;

-- -----------------------------------------------------
-- Table `new-airlines`.`airport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`airport` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(60) CHARACTER SET 'utf8' NOT NULL,
  `airport_code` VARCHAR(10) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `airport_airport_code_uindex` (`airport_code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `new-airlines`.`professions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`professions` (
  `id` INT(11) NOT NULL,
  `profession_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `profession_id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`profession_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new-airlines`.`crew_man`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`crew_man` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `profession_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Crew_man_prof_idx` (`profession_id` ASC) VISIBLE,
  CONSTRAINT `Crew_man_to_Profession`
    FOREIGN KEY (`profession_id`)
    REFERENCES `new-airlines`.`professions` (`id`)
    ON UPDATE CASCADE)
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new-airlines`.`flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`flights` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `flight_code` VARCHAR(10) NOT NULL,
  `departure_airport_id` INT(11) NOT NULL,
  `arrival_airport_id` INT(11) NOT NULL,
  `departure_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `arrival_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flight_status` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Flight_Departure_Airport_idx` (`departure_airport_id` ASC) VISIBLE,
  INDEX `FK_Flight_Arrival_Airport_idx` (`arrival_airport_id` ASC) VISIBLE,
  INDEX `FK_Flight_Flight_Statuses_idx` (`flight_status` ASC) VISIBLE,
  CONSTRAINT `Flight_to_Arrival_Airport`
    FOREIGN KEY (`arrival_airport_id`)
    REFERENCES airport (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `Flight_to_Departure_Airport`
    FOREIGN KEY (`departure_airport_id`)
    REFERENCES airport (`id`)
    ON UPDATE CASCADE)
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new-airlines`.`flight_crewman`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`flight_crewman` (
  `flight_id` INT(11) NOT NULL,
  `crewman_id` INT(11) NOT NULL,
  PRIMARY KEY (`flight_id`, `crewman_id`),
  INDEX `crewman_id_crewman_table_idx` (`crewman_id` ASC) VISIBLE,
  CONSTRAINT `crewman_id_crewman_table`
    FOREIGN KEY (`crewman_id`)
    REFERENCES `new-airlines`.`crew_man` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `flight_id_flight_table`
    FOREIGN KEY (`flight_id`)
    REFERENCES `new-airlines`.`flights` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `new-airlines`.`user_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`user_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `new-airlines`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new-airlines`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `user_type_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `user_vs_user_type_idx` (`user_type_id` ASC) VISIBLE,
  CONSTRAINT `user_vs_user_type`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `new-airlines`.`user_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
