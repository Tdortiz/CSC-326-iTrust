#Author: mmackre
# add incorrect fields for New Patients?
#
#
Feature: Manage lab procs 
As an HCP
I want to be able to manage lab procedures
So I can change them as needed 

Scenario: Update Add Lab Procedures HCP
Given UC26sql has been loaded
And Kelly Doctor is an HCP with MID: 9000000000
And Patient 0000000026 exists in the system
And MID: 0000000026 is greater than 12 years old
And Hospital Test Hospital 9191919191 exists
And LOINC 29588-1 Component: Heavy Metals Panel, System: Blood exists 
And LOINC 34667-6 Component: Heavy Metals Panel, System: Hair exists
And LOINC 40772-6 Component: Heavy Metals, System: Urine exists
And ICD10 S60.7, Multiple superficial injuries of wrist and hand exists
And Office visit on 2015-10-24 exists for 0000000026
And Location for 2015-10-24's office visit is 9191919191
And Appointment Type for 2015-10-24's office visit is 1
And Notes for 2015-10-24's office visit is Odd symptoms. Concerned about environmental hazards at work.
And 2015-10-24's office visit has the option to send the Patient a billing statement selected
And 2015-10-24's office visit for 26 has the following Basic Health Metrics: height: 69 in, weight: 163 lbs, blood pressure: 110/70, LDL: 81, HDL: 60, Triglycerides: 110, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And 2015-10-24's office visit has a Lab Procedure listed with Code 34667-6 with a current status of in transit, priority 2, and assigned Lab Technician 5000000002
And 2015-10-24's office visit has a Lab Procedure listed with Code 40772-6 with a current status of in transit, priority 1, and assigned Lab Technician 5000000002
And Neither Lab Technician 5000000002 or Lab Technician 5000000003 has additional proccedures assigned
When I login as 9000000000 using pw
And I edit the office visit for 0000000026 from 2015-10-24  
And Delete the Lab procedure with LOINC 40772-6
And Update the lab procedure with LOINC 34667-6 by setting the Lab Technician to 5000000003
And Create a new lab procedure with LOINC 29588-1 Component: Heavy Metals Panel, System: Blood, priority 2, lab technicial 5000000003
Then When I view the lab procedures for an office visit as an HCP, I can view the previously created lab procedure including lab procedure code, current lab procedure status, the date the lab was assigned, and Lab Technician name
And When I edit the lab procedures for an office visit as an HCP, I can view the specialty for each Lab Technician and the number of pending lab procedures in his/her priority queue, grouped by priority
And When I conclude the update, there is a message displayed at the top of the page: Information Successfully Updated.
And the date is 2015-10-24 
And I view the location and it is Test Hospital 9191919191
And I view the appointment type and it is consultation
And I view the notes and it is Odd symptoms. Concerned about environmental hazards at work
And I view the sendBill and it is true
And After the office visit has been created, it does include the following basic health metrics: height: 69 in, weight: 160 lbs, blood pressure: 110/70, LDL: 81, HDL: 60, Triglycerides: 110, Household Smoking Status: 1 - non-smoking household, Patient Smoking Status: 4 - Never smoker
And there is a procedure with LOINC 29588-1, priority 2, Lab Technician 5000000003
And there is a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000003
And there isnt a procedure with  LOINC 40772-6, priority 1, Lab Technician 5000000002
And no other info is stored other than logging data


Scenario: View Lab Procedures Patient
Given UC26sql has been loaded
Given Kelly Doctor is an HCP with MID: 9000000000
And Lab Technician 5000000001 exists
And Lab Technician 5000000002 exists
And Lab Technician 5000000003 exists
And Patient 0000000022 exists in the system
And MID: 0000000022 is greater than 13 years old
And LOINC 34667-6 Component: Heavy Metals Panel, System: Hair exists
And LOINC 5583-0 Component: Arsenic, System: Blood exists
And LOINC 5685-3 Component: Mercury, System: Blood exists
And LOINC 12556-7 Component: Copper, System: Blood exists
And LOINC 14807-2 Component: Lead, System: Blood exists
And LOINC 71950-0 Component: Health Assessment Questionnaire, System: Patient exists
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
And Patient 0000000022 has an office visit with a date of 2015-10-01, a Location of 1, Appointment type 1, and Notes: foo
And 2015-10-01's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 100 lbs, blood pressure: 102/72, LDL: 81, HDL: 40, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-01 includes a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000001, status completed, numerical result: 50, confidence interval: 48-52, commentary This is concerning,
And Patient 0000000022 has an office visit with a date of 2015-10-08, a Location of 1, Appointment type 6, and Notes: bar
And 2015-10-08's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 102 lbs, blood pressure: 104/76, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-08 includes a lab procedure with LOINC 5583-0, priority 1, Lab Technician 5000000002, status received, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 5685-3, priority 1, Lab Technician 5000000002, status testing, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 12556-7, priority 1, Lab Technician 5000000002, status testing, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 14807-2, priority 1, Lab Technician 5000000003, status pending, results above average, and no other information
And The office visit from 2015-10-08 has only 4 lab procedures
And Patient 0000000022 has an office visit with a date of 2015-10-24, a Location of 1, Appointment type 6, and Notes: I think Fozzie needs to use a different shampoo
And 2015-10-24's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 101 lbs, blood pressure: 108/72, LDL: 81, HDL: 50, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-24 includes a lab procedure with LOINC 71950-0, priority 2, Lab Technician 5000000001, status in transit, and no other information
And The office visit from 2015-10-24 has only 1 lab procedures
When I login as 0000000022 using pw
And Examine the lab procedures from the office visit 2015-10-01
And Examine the lab procedures from the office visit 2015-10-08 
And Examine the lab procedures from the office visit 2015-10-24 
Then When I view the office visits, they should include the visit from 2015-10-01
And When I view the office visits, they should include the visit from 2015-10-08 
And When I view the office visits, they should include the visit from 2015-10-24 
And When I view the office visit from 2015-10-01, there should be 4 lab procedure
And When I examine the lab procedures from the office visit 2015-10-01 there is a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000001, status completed, numerical result: 50, confidence interval: 48-52, commentary This is concerning
And When I view the office visit from 2015-10-08  ago, there should be 4 lab procedure
And When I examine the lab procedures from the office visit 2015-10-08 there is a procedure with LOINC 5583-0, Lab Technician 5000000002, status received, and no other information
And When I examine the lab procedures from the office visit 2015-10-08 there is a procedure with LOINC 5685-3, Lab Technician 5000000002, status testing, and no other information
And When I examine the lab procedures from the office visit 2015-10-08 there is a procedure with LOINC 12556-7, Lab Technician 5000000002, status testing, and no other information
And When I examine the lab procedures from the office visit 2015-10-08 there is a procedure with LOINC 14807-2, Lab Technician 5000000003, status pending, results above average, and no other information
And When I view the office visit from 2015-10-08, there should be 4 lab procedure
And When I examine the lab procedures from the office visit 2015-10-08 there is a procedure with LOINC 71950-0, Lab Technician 5000000001, status in-transit, and no other information


Scenario: Update Lab Procedures LT
Given Kelly Doctor is an HCP with MID: 9000000000
And Lab Technician 5000000000 exists
And Patient 0000000022 exists in the system
And MID: 0000000022 is greater than 13 years old
And LOINC 34667-6 Component: Heavy Metals Panel, System: Hair exists
And LOINC 5583-0 Component: Arsenic, System: Blood exists
And LOINC 5685-3 Component: Mercury, System: Blood exists
And LOINC 12556-7 Component: Copper, System: Blood exists
And LOINC 14807-2 Component: Lead, System: Blood exists
And LOINC 71950-0 Component: Health Assessment Questionnaire, System: Patient exists
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
And Patient 0000000022 has an office visit with a date of a 2015-10-01, a Location of Health Institute Dr. E, Appointment type General Checkup, and Notes: foo
And 2015-10-01's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 100 lbs, blood pressure: 102/72, LDL: 81, HDL: 40, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-01 includes a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000003, status completed, numerical result: 50, confidence interval: 48-52, commentary This is concerning
And Patient 0000000022 has an office visit with a date of 2015-10-08, a Location of Health Institute Dr. E, Appointment type Consultation, and Notes: bar
And 2015-10-08's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 102 lbs, blood pressure: 104/76, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-08 includes a lab procedure with LOINC 5583-0, priority 2, Lab Technician 5000000002, status in transit, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 5685-3, priority 1, Lab Technician 5000000003, status testing, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 12556-7, priority 3, Lab Technician 5000000003, status in transit, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 14807-2, priority 1, Lab Technician 5000000003, status received, and no other information
And The office visit from 2015-10-08 has only 4 lab procedures
And Patient 0000000022 has an office visit with a date of 2015-10-24, a Location of Health Institute Dr. E, Appointment type Consultation, and Notes: I think Fozzie needs to use a different shampoo
And 2015-10-24's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 101 lbs, blood pressure: 108/72, LDL: 81, HDL: 50, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-24 includes a lab procedure with LOINC 71950-0, priority 2, Lab Technician 5000000003, status in transit, and no other information
And The office visit from 2015-10-24 has only 1 lab procedures
And No other lab procedures are assigned to Cool Person
When I login as 5000000003 using pw 
And Examine the lists of lab procedures assigned and Update the two 2 lab procedures to 3
And Examine the list of received procedures
And Add numerical result: 20 and confidence interval: 19-21 to the procedure whose current status is testing 5685-3
And Update the procedures
Then When I first access and examine the lab procedures, the received queue should contain the correct lab procedure ID, lab procedure code, status, priority, HCP name, and the date the lab was assigned for the procedures with LOINC 5685-3 and 4807-2
And When I first access and examine the lab procedures, the intransit queue should contain the correct lab procedure ID, lab procedure code, status, priority, HCP name, and the date the lab was assigned for the procedures with LOINC 12556-7 and 71950-0
And When I first access and examine the lab procedures, the received queue should contain the procedure with LOINC 5685-3 first and the procedure with LOINC 4807-2 second
And When I first access and examine the lab procedures, the in transit queue should contain the procedure with LOINC 12556-7 first and the procedure with LOINC 71950-0 second
And After I update the two intransit lab procedures to received, when I examine the received queue the order should be: the procedure with LOINC 5685-3 first, the procedure with LOINC 4807-2 second, the procedure with LOINC 71950-0 third, and the procedure with LOINC 12556-7 fourth
And After I add results to the lab procedure with LOINC 5685-3 and update the procedure, its status should change to pending and it should no longer appear in the lab technician's queue
And After the lab procedure with LOINC 5685-3 has been updated, the procedure with LOINC 4807-2 should now have status testing and be first in the received queue
And After the lab procedure with LOINC 5685-3 has been updated, the procedure with LOINC 71950-0 should be second in the received queue, and the procedure with LOINC 12556-7 should be third


Scenario: Lab Pending To Completed
Given Kelly Doctor is an HCP with MID: 9000000000
And Lab Technician 5000000001 exists
And Lab Technician 5000000002 exists
And Lab Technician 5000000003 exists
And Patient 0000000022 exists in the system
And MID: 0000000022 is greater than 13 years old
And LOINC 34667-6 Component: Heavy Metals Panel, System: Hair exists
And LOINC 5583-0 Component: Arsenic, System: Blood exists
And LOINC 5685-3 Component: Mercury, System: Blood exists
And LOINC 12556-7 Component: Copper, System: Blood exists
And LOINC 14807-2 Component: Lead, System: Blood exists
And LOINC 71950-0 Component: Health Assessment Questionnaire, System: Patient exists
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
And Patient 0000000022 has an office visit with a date of a 2015-10-01, a Location of Health Institute Dr. E, Appointment type General Checkup, and Notes: foo
And last 2015-10-01's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 100 lbs, blood pressure: 102/72, LDL: 81, HDL: 40, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-01 includes a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000001, status pending, numerical result: 50, confidence interval: 48-52, commentary n/a
When I login as 5000000003 using pw 
And Examine the lab procedures from the office visit 2015-10-01
And Add commentary to the lab procedure This seems high
And Update the procedures
Then When I view the office visits, they should include the visit from 2015-10-01, the visit from 2015-10-08 , and 2015-10-24 visit
And When I view the office visit from 2015-10-01, there should be one lab procedure
When When I examine the lab procedures from the office visit 2015-10-01 there is a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000001, status pending, numerical result: 50, confidence interval: 48-52, commentary none
And its status should change to completed



Scenario: black box test hcp fields left blank when creating lab procedure
Given Kelly Doctor is an HCP with MID: 9000000000
And Lab Technician 5000000001 exists
And Lab Technician 5000000002 exists
And Lab Technician 5000000003 exists
And Patient 0000000022 exists in the system
And MID: 0000000022 is greater than 13 years old
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
When Create a new lab procedure with LOINC 29588-1 Component: Heavy Metals Panel, System: Blood, priority 2, lab technicial 5000000003
Then an error message is displayed
And the lab procedure is not created

Scenario: black box patient view but no records shown
Given Kelly Doctor is an HCP with MID: 9000000000
And Lab Technician 5000000001 exists
And Lab Technician 5000000002 exists
And Lab Technician 5000000003 exists
And Patient 0000000022 exists in the system
And MID: 0000000022 is greater than 13 years old
And LOINC 34667-6 Component: Heavy Metals Panel, System: Hair exists
And LOINC 5583-0 Component: Arsenic, System: Blood exists
And LOINC 5685-3 Component: Mercury, System: Blood exists
And LOINC 12556-7 Component: Copper, System: Blood exists
And LOINC 14807-2 Component: Lead, System: Blood exists
And LOINC 71950-0 Component: Health Assessment Questionnaire, System: Patient exists
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
And Patient 0000000022 has an office visit with a date of 2015-10-08, a Location of Health Institute Dr. E, Appointment type Consultation, and Notes: bar
And 2015-10-08's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 102 lbs, blood pressure: 104/76, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-08 has only 0 lab procedures
When I login as 0000000022 using pw
And Examine the lab procedures from the office visit 2015-10-08
Then When I view the office visit from 2015-10-08, there should be 0 lab procedure



Scenario: black box Update Lab Procedures LT invalid data
Given Kelly Doctor is an HCP with MID: 9000000000
And Lab Technician 5000000000 exists
And Patient 0000000022 exists in the system
And MID: 0000000022 is greater than 13 years old
And LOINC 34667-6 Component: Heavy Metals Panel, System: Hair exists
And LOINC 5583-0 Component: Arsenic, System: Blood exists
And LOINC 5685-3 Component: Mercury, System: Blood exists
And LOINC 12556-7 Component: Copper, System: Blood exists
And LOINC 14807-2 Component: Lead, System: Blood exists
And LOINC 71950-0 Component: Health Assessment Questionnaire, System: Patient exists
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
And Patient 0000000022 has an office visit with a date of a 2015-10-01, a Location of Health Institute Dr. E, Appointment type General Checkup, and Notes: foo
And last 2015-10-01's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 100 lbs, blood pressure: 102/72, LDL: 81, HDL: 40, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-01 includes a procedure with LOINC 34667-6, priority 2, Lab Technician 5000000003, status completed, numerical result: 50, confidence interval: 48-52, commentary This is concerning
And Patient 0000000022 has an office visit with a date of 2015-10-08, a Location of Health Institute Dr. E, Appointment type Consultation, and Notes: bar
And 2015-10-08's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 102 lbs, blood pressure: 104/76, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-08 includes a lab procedure with LOINC 5583-0, priority 2, Lab Technician 5000000002, status in transit, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 5685-3, priority 1, Lab Technician 5000000003, status testing, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 12556-7, priority 3, Lab Technician 5000000003, status in transit, and no other information
And The office visit from 2015-10-08 includes a lab procedure with LOINC 14807-2, priority 1, Lab Technician 5000000003, status received, and no other information
And The office visit from 2015-10-08 has only 4 lab procedures
And Patient 0000000022 has an office visit with a date of 2015-10-24, a Location of Health Institute Dr. E, Appointment type Consultation, and Notes: I think Fozzie needs to use a different shampoo
And 2015-10-24's office visit for 22 has the following Basic Health Metrics: height: 63 in, weight: 101 lbs, blood pressure: 108/72, LDL: 81, HDL: 50, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And The office visit from 2015-10-24 includes a lab procedure with LOINC 71950-0, priority 2, Lab Technician 5000000003, status in transit, and no other information
And The office visit from 2015-10-24 has only 1 lab procedures
And No other lab procedures are assigned to Cool Person
When I login as 5000000003 using pw
And Add numerical result: abcd and confidence interval: 19-21 to the procedure whose current status is testing (5685-3)
And Update the procedures
Then When I first access and examine the lab procedures, the received queue should contain the correct lab procedure ID, lab procedure code, status, priority, HCP name, and the date the lab was assigned for the procedures with LOINC 5685-3 and 4807-2
And When I first access and examine the lab procedures, the in transit queue should contain the correct lab procedure ID, lab procedure code, status, priority, HCP name, and the date the lab was assigned for the procedures with LOINC 12556-7 and 71950-0
And When I first access and examine the lab procedures, the received queue should contain the procedure with LOINC 5685-3 first and the procedure with LOINC 4807-2 second
And When I first access and examine the lab procedures, the in transit queue should contain the procedure with LOINC 12556-7 first and the procedure with LOINC 71950-0 second
And After I add an invalid results to the lab procedure with LOINC 5685-3 and attempt to update the procedure, it should not successfully update.


