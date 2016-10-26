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
 /*password: pw*/

INSERT INTO icdCode (code, name, is_chronic)
VALUES ('TES.T000', 'Test Not Chronic ICD Code', 0);
INSERT INTO icdCode (code, name, is_chronic)
VALUES ('TES.T001', 'Test Chronic ICD Code', 1);

INSERT INTO officevisit (
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID) 
VALUES (201, "2000-10-28 00:00:00", 1, 1);

set @ov_id = LAST_INSERT_ID();

INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id, 'TES.T000');
INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id, 'TES.T001');

INSERT INTO officevisit (
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID) 
VALUES (201, "2016-10-23 00:00:00", 1, 1);

set @ov_id = LAST_INSERT_ID();

INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id, 'TES.T000');
INSERT INTO diagnosis (visitId, icdCode)
VALUES (@ov_id, 'TES.T001');
