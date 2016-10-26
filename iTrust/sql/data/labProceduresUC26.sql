INSERT INTO officevisit (
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID, 
	notes,
	weight, 
	height,
	blood_pressure,
	household_smoking_status,
	patient_smoking_status,
	hdl,
	ldl,
	triglyceride) 
VALUES (26, "2015-10-24 00:00:00", 9191919191, 1, "Odd symptoms. Concerned about environmental hazards at work.", 163, 69, '110/70', 1, 4, 60, 81, 110);

INSERT INTO officevisit (
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID, 
	notes,
	weight, 
	height,
	blood_pressure,
	household_smoking_status,
	patient_smoking_status,
	hdl,
	ldl,
	triglyceride) 
VALUES (22, "2015-10-01 00:00:00", 1, 1, "foo", 100, 63, '102/72', 1, 4, 40, 81, 105);

INSERT INTO officevisit (
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID, 
	notes,
	weight, 
	height,
	blood_pressure,
	household_smoking_status,
	patient_smoking_status,
	hdl,
	ldl,
	triglyceride) 
VALUES (22, "2015-10-08 00:00:00", 1, 6, "bar", 102, 63, '104/76', 1, 4, 45, 81, 105);

INSERT INTO officevisit (
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID, 
	notes,
	weight, 
	height,
	blood_pressure,
	household_smoking_status,
	patient_smoking_status,
	hdl,
	ldl,
	triglyceride) 
VALUES (22, "2015-10-24 00:00:00", 1, 6, "I think Fozzie needs to use a different shampoo", 101, 63, '108/72', 1, 4, 50, 81, 105);


INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000002,
	3,
	2,
	true,
	2,
	"",
	"No results yet",
	"2015-10-24 04:00:00"
);

INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000002,
	3,
	1,
	true,
	2,
	"",
	"No results yet",
	"2015-10-24 04:00:00"
);


INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000002,
	29,
	1,
	true,
	3,
	"",
	"",
	"2015-10-08 04:00:00"
);


INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000002,
	29,
	1,
	true,
	4,
	"",
	"",
	"2015-10-08 04:00:00"
);


INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000002,
	29,
	1,
	true,
	4,
	"",
	"",
	"2015-10-08 04:00:00"
);


INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000003,
	29,
	1,
	true,
	1,
	"",
	"above average",
	"2015-10-08 04:00:00"
);

INSERT INTO labProcedure (
	labTechnicianID,
	officeVisitID,
	priority,
	isRestricted,
	status,
	commentary,
	results,
	updatedDate
) VALUES (
	5000000001,
	30,
	2,
	true,
	2,
	"",
	"",
	"2015-10-24 04:00:00"
);










