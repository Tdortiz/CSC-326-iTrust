/*Insert patient Sandy Sky*/
INSERT INTO patients
(MID, 
firstName,
lastName, 
email,
address1,
address2,
city,
state,
zip,
phone,
eName,
ePhone,
iCName,
iCAddress1,
iCAddress2,
iCCity, 
ICState,
iCZip,
iCPhone,
iCID,
DateOfBirth,
DateOfDeath,
CauseOfDeath,
MotherMID,
FatherMID,
BloodType,
Ethnicity,
Gender,
TopicalNotes
)
VALUES (
201,
'Sandy',
'Sky',
'sandy.sky@gmail.com',
'123 Sky Street',
'',
'Raleigh',
'NC',
'27607',
'123-456-7890',
'Susan Sky-Walker',
'444-332-4309',
'IC',
'Street1',
'Street2',
'City',
'PA',
'12345-6789',
'555-555-5555',
'1',
DATE(NOW()-INTERVAL 24 YEAR),
NULL,
'',
0,
0,
'O-',
'Caucasian',
'Male',
'Will save the universe, please protect'
)  ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
			VALUES (201, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', '2+2?', '4')
 ON DUPLICATE KEY UPDATE MID = MID;
 
 
 




INSERT INTO  personalallergies(PatientID, Allergy)
VALUES (201, 'Pollen');
INSERT INTO  personalallergies(PatientID, Allergy)
VALUES (201, 'Penicillin');

 
INSERT INTO officevisit (
	patientMID,
	visitDate,
	locationID,
	apptTypeID,
	notes,
	height,
	weight,
	blood_pressure,
	hdl,
	triglyceride,
	ldl,
	household_smoking_status,
	patient_smoking_status)
VALUES (201, "2015-11-25 00:00:00", 9191919191, 6, "finishing blood tests", 70, 180, '100/70', 40, 105, 81, 1, 4);

set @ov_id = LAST_INSERT_ID();

INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id, 'J45');

INSERT INTO immunization (visitId, cptCode)
VALUES (@ov_id, '90715');


INSERT INTO officevisit (
	patientMID,
	visitDate,
	locationID,
	apptTypeID,
	notes,
	height,
	weight,
	blood_pressure,
	hdl,
	triglyceride,
	ldl,
	household_smoking_status,
	patient_smoking_status)
VALUES (201, "2015-07-25 00:00:00", 9191919191, 6, "Patient had trouble breathing", 70, 170, '105/68', 45, 105, 81, 1, 4  );

set @ov_id2 = LAST_INSERT_ID();

INSERT INTO prescription (patientMID, drugCode, startDate, endDate, officeVisitId)
VALUES (201, 483013420, "2015-07-25", "2015-05-25", @ov_id2);

INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id2, 'J12');

INSERT INTO prescription (patientMID, drugCode, startDate, endDate, officeVisitId)
VALUES (201, 05730150, "2015-07-25", "2015-08-25", @ov_id2);
	


INSERT INTO officevisit (
	patientMID,
	visitDate,
	locationID,
	apptTypeID,
	notes,
	height,
	weight,
	blood_pressure,
	hdl,
	triglyceride,
	ldl,
	household_smoking_status,
	patient_smoking_status)
VALUES (201, "2016-11-18 00:00:00", 9191919191, 1, "Patient had trouble breathing", 70, 170, '100/68', 45, 105, 81, 1, 4  );

set @ov_id3 = LAST_INSERT_ID();

INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id3, 'J00');

INSERT INTO prescription (patientMID, drugCode, startDate, endDate, officeVisitId)
VALUES (201, 63739291, "2016-11-18", "2016-11-30", @ov_id3);








INSERT INTO patients
(MID, 
firstName,
lastName, 
email,
address1,
address2,
city,
state,
zip,
phone,
eName,
ePhone,
iCName,
iCAddress1,
iCAddress2,
iCCity, 
ICState,
iCZip,
iCPhone,
iCID,
DateOfBirth,
DateOfDeath,
CauseOfDeath,
MotherMID,
FatherMID,
BloodType,
Ethnicity,
Gender,
TopicalNotes
)
VALUES (
202,
'Sarah',
'Sky',
'sarah.sky@gmail.com',
'123 Sky Street',
'',
'Raleigh',
'NC',
'27607',
'123-456-7890',
'Susan Sky-Walker',
'444-332-4309',
'IC',
'Street1',
'Street2',
'City',
'PA',
'12345-6789',
'555-555-5555',
'1',
DATE(NOW()-INTERVAL 1 YEAR),
NULL,
'',
0,
0,
'O+',
'Caucasian',
'Female',
'Will save the universe, please protect'
)  ON DUPLICATE KEY UPDATE MID = MID;


INSERT INTO officevisit (
	patientMID,
	visitDate,
	locationID,
	apptTypeID,
	notes,
	length,
	weight,
	household_smoking_status)
VALUES (202, "2015-10-18 00:00:00", 9191919191, 6, "Patient had trouble breathing", 20, 10, 1);

set @ov_id4 = LAST_INSERT_ID();

INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id4, 'J00');