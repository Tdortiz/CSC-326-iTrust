/* updated 2014-2-1 */

SET sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

CREATE TABLE hospitals(
	HospitalID   varchar(10),
	HospitalName varchar(30) NOT NULL,
	Address varchar(30),
	City varchar(15),
	State varchar(2),
	Zip varchar(10),	
	PRIMARY KEY (hospitalID)
) ENGINE=INNODB;

CREATE TABLE wards(
	wardID BIGINT unsigned AUTO_INCREMENT primary key,
	inHospital varchar(10) NOT NULL,
	requiredSpecialty varchar(128),
	FOREIGN KEY (InHospital) REFERENCES hospitals (HospitalID)
) ENGINE=INNODB;

CREATE TABLE flags(
	FID BIGINT unsigned auto_increment,
	MID BIGINT unsigned NOT NULL default '0',
	pregId BIGINT unsigned NOT NULL default '0',
	flagType enum('High Blood Pressure', 'Advanced Maternal Age', 'Maternal Allergies', 'Low-Lying Placenta',
		'Genetic Miscarriage', 'Abnormal FHR', 'Twins', 'Abnormal Weight Change', 'Negative Blood Type', 'Pregnancy relevant pre-existing conditions'),
	PRIMARY KEY (FID)
) ENGINE=INNODB;


CREATE TABLE appointmenttype (
	apptType_id			INT UNSIGNED AUTO_INCREMENT primary key,
	appt_type           VARCHAR(30) NOT NULL,
	duration			INT UNSIGNED NOT NULL,
	price				INT UNSIGNED NOT NULL default '0' /* UC60 */
) ENGINE=INNODB;

CREATE TABLE drugreactionoverridecodes(
	Code varchar(5) NOT NULL COMMENT 'Identifier for override reason',
	Description varchar(80) NOT NULL COMMENT 'Description of override reason',
	PRIMARY KEY (Code)
) ENGINE=INNODB;
	
CREATE TABLE ndcodes(
	Code varchar(10) NOT NULL, 
	Description varchar(100) NOT NULL, 
	PRIMARY KEY  (Code)
) ENGINE=INNODB;

CREATE TABLE druginteractions(
	FirstDrug varchar(9) NOT NULL,
	SecondDrug varchar(9) NOT NULL,
	Description varchar(100) NOT NULL,
	PRIMARY KEY  (FirstDrug,SecondDrug)
) ENGINE=INNODB;

CREATE TABLE globalvariables (
	Name VARCHAR(20) primary key,
	Value VARCHAR(20)
) ENGINE=INNODB;

INSERT INTO globalvariables(Name,Value) VALUES ('Timeout', '20');

CREATE TABLE fakeemail(
	ID INT(10) auto_increment primary key,
	ToAddr VARCHAR(100),
	FromAddr VARCHAR(100),
	Subject VARCHAR(500),
	Body VARCHAR(2000),
	AddedDate timestamp NOT NULL default CURRENT_TIMESTAMP
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS zipcodes (
  `zip` varchar(5) NOT NULL default '',
  `state` char(2) NOT NULL default '',
  `latitude` varchar(10) NOT NULL default '',
  `longitude` varchar(10) NOT NULL default '',
  `city` varchar(50) default NULL,
  `full_state` varchar(50) default NULL,
  PRIMARY KEY `zip` (`zip`)
) ENGINE=INNODB;

