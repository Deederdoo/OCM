-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OCM
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OCM` ;

-- -----------------------------------------------------
-- Schema OCM
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OCM` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `OCM` ;

-- -----------------------------------------------------
-- Table `OCM`.`ORG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`ORG` (
  `OrgID` INT NOT NULL AUTO_INCREMENT,
  `OrgName` VARCHAR(45) NULL,
  PRIMARY KEY (`OrgID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `OCM`.`SURVEY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`SURVEY` (
  `SurveyID` INT NOT NULL AUTO_INCREMENT,
  `SurveyName` VARCHAR(20) NOT NULL,
  `DateCreated` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `IsLegacy` TINYINT(1) NOT NULL,
  `AvgPCM` INT NULL DEFAULT NULL,
  `ORG_OrgID` INT NULL,
  PRIMARY KEY (`SurveyID`),
  INDEX `fk_SURVEY_ORG1_idx` (`ORG_OrgID` ASC) VISIBLE,
  CONSTRAINT `fk_SURVEY_ORG1`
    FOREIGN KEY (`ORG_OrgID`)
    REFERENCES `OCM`.`ORG` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `OCM`.`FACTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`FACTOR` (
  `IDFAC` INT NOT NULL,
  `FactorID` INT NOT NULL AUTO_INCREMENT,
  `SurveyID` INT NOT NULL,
  `Details` VARCHAR(255) NULL DEFAULT NULL,
  `AvgFactorPCM` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`FactorID`),
  INDEX `fk_survey_id` (`SurveyID` ASC) VISIBLE,
  CONSTRAINT `fk_survey_id`
    FOREIGN KEY (`SurveyID`)
    REFERENCES `OCM`.`SURVEY` (`SurveyID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `OCM`.`PERSON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`PERSON` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(50) NOT NULL,
  `LastName` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Department` VARCHAR(50) NULL DEFAULT NULL,
  `Pass` VARCHAR(50) NOT NULL,
  `Access_Level` TINYINT(1) NOT NULL,
  `Pass_Flag` TINYINT(1) NOT NULL,
  `OrgID` INT NULL,
  PRIMARY KEY (`UserID`),
  INDEX `fk_PERSON_ORG1_idx` (`OrgID` ASC) VISIBLE,
  CONSTRAINT `fk_PERSON_ORG1`
    FOREIGN KEY (`OrgID`)
    REFERENCES `OCM`.`ORG` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO PERSON (FirstName, LastName, Access_Level, Pass_Flag, Email, Pass) 
VALUES ('admin','admin',1,1,'admin@root.ca','password');

-- -----------------------------------------------------
-- Table `OCM`.`PERSON_SURVEY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`PERSON_SURVEY` (
  `SurveyID` INT NULL DEFAULT NULL,
  `UserID` INT NULL DEFAULT NULL,
  `PCM` INT NULL DEFAULT NULL,
  `GENDER` VARCHAR(10) NULL DEFAULT NULL,
  `AGE_GROUP` VARCHAR(15) NULL DEFAULT NULL,
  INDEX `surveyid_fk` (`SurveyID` ASC) VISIBLE,
  INDEX `userid_fk` (`UserID` ASC) VISIBLE,
  CONSTRAINT `surveyid_fk`
    FOREIGN KEY (`SurveyID`)
    REFERENCES `OCM`.`SURVEY` (`SurveyID`)
    ON DELETE CASCADE,
  CONSTRAINT `userid_fk`
    FOREIGN KEY (`UserID`)
    REFERENCES `OCM`.`PERSON` (`UserID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `OCM`.`QUESTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`QUESTION` (
  `SurveyID` INT NOT NULL,
  `QuestionID` INT NOT NULL AUTO_INCREMENT,
  `Details` VARCHAR(255) NULL DEFAULT NULL,
  `FactorID` INT NULL,
  `IDFAC` INT NOT NULL,
  PRIMARY KEY (`QuestionID`),
  INDEX `fk_QUESTION_SURVEY1_idx` (`SurveyID` ASC) VISIBLE,
  INDEX `fk_QUESTION_FACTOR1_idx` (`FactorID` ASC) VISIBLE,
  CONSTRAINT `fk_QUESTION_SURVEY1`
    FOREIGN KEY (`SurveyID`)
    REFERENCES `OCM`.`SURVEY` (`SurveyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_QUESTION_FACTOR1`
    FOREIGN KEY (`FactorID`)
    REFERENCES `OCM`.`FACTOR` (`FactorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `OCM`.`QUESTION_ANSWER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OCM`.`QUESTION_ANSWER` (
  `QuestionAnswerID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `SurveyID` INT NOT NULL,
  `Score` DOUBLE(10,2) NULL DEFAULT NULL,
  `QuestionID` INT NULL DEFAULT NULL,
  `OrgId` INT NOT NULL,
  PRIMARY KEY (`QuestionAnswerID`),
  INDEX `user_id_fk` (`UserID` ASC) VISIBLE,
  INDEX `survey_id_fk` (`SurveyID` ASC) VISIBLE,
  INDEX `question_id_fk` (`QuestionID` ASC) VISIBLE,
  CONSTRAINT `question_id_fk`
    FOREIGN KEY (`QuestionID`)
    REFERENCES `OCM`.`QUESTION` (`QuestionID`)
    ON DELETE CASCADE,
  CONSTRAINT `survey_id_fk`
    FOREIGN KEY (`SurveyID`)
    REFERENCES `OCM`.`SURVEY` (`SurveyID`)
    ON DELETE CASCADE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`UserID`)
    REFERENCES `OCM`.`PERSON` (`UserID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;