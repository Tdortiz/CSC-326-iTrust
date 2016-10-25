#Author: mmackre
# add incorrect fields for New Patients?
#
#
Feature: Lab Procedure Office Visit
As an HCP
I want to be able to document an office visit
So I have a record of the visit for future diagnosis and billing purposes



#fig out what this cr thing is in the prescription notes
Scenario Outline: Document Office Visit All
Given Kelly Doctor is an HCP with MID: 9000000000
And Patient 0000000001 exists in the system
And MID: 0000000001 is greater than 12 years old
And Hospital Test Hospital 9191919191 exists
And Lab Technician Nice Guy (MID: 5000000002) exists
And NDC "081096", Aspirin exists
And Immunization CPT 90715, TDAP exists
And Medical Procedure CPT 99214 exists
And LOINC 18106-5, Cardiac echo study procedure exists
And ICD10 "S10.8", Superficial injury of other parts of neck exists
Given I have already selected <patient>
When I login as 9000000000 using pw
And I enter date: <visitDate>, location: <hospitalLocation>, appointment type: <appointmentType>, notes: <notes>, and select send a bill true
And user enters "160" as the weight
And user enters "69" as the height
And user enters "100/70" as the blood pressure
And enters 80 for LDL
And enters 60 for HDL
And enters 100 for Triglycerides
And selects 1 for non-smoking for household smoking status
And selects 4 for never smoker for patient smoking status
And I add a new prescription with the following information: Medication: 081096 Aspirin, Dosage: 75 mg, Dates: tomorrow to today + 365, Instructions: Number Per day: 1;(cr)Not sure why patient's heart-rate was elevated? Re-evaluate in a year.
And For the Lab Procedures associated with the office visit, select LOINC 18106-5 , Cardiac echo study procedure with priority 1 and Lab Technician Nice Guy (MID: 5000000002)
And Under Diagnosis add S10.8, Superficial injury of other parts of neck
And Under Medical Procedures add CPT 99214
And Under Immunizations add CPT 90715 , TDAP
And submits record
Then The record is saved successfully
And the date is today
And After the office visit has been created the Location saved for the office visit is Test Hospital 9191919191
And After the office visit has been created the Notes saved for the office visit is Patient reported issues: hair loss, fast heartbeat
And After the office visit has been created the sendBill saved for the office visit is true
And After the office visit has been created, it does include the following basic health metrics: height: 69 in, weight: 160 lbs, blood pressure: 100/70, LDL: 80, HDL: 60, Triglycerides: 100, Household Smoking Status: 1 - non-smoking household, Patient Smoking Status: 4 - Never smoker
And After the office visit has been created, its prescription info is: drugname: <drugName>, Code: <drugCode>, Dosage: <dosage>, Start Date: <startDate>, End Date: <endDate>, Special instructions: <spInstructions>
And After the office visit has been created, its Lab Procedures include LOINC 18106-5, Cardiac echo study procedure with priority 1 and Lab Technician 5000000002
And After the office visit has been created, the Diagnosis associated with it include S10.8, Superficial injury of other parts of neck
And After the office visit has been created, the Medical Procedures associated with it include CPT 99214
And After the office visit has been created, the Immunizations associated with it include CPT 90715, TDAP

Examples:
| patient   | visitDate        | hospitalLocation           | appointmentType | notes                                              | drugName| drugCode| dosage| startDate| endDate    | spInstructions                                                                              |
| 000000001 | today            | Test Hospital 9191919191   | Consultation    | Patient reported issues: hair loss, fast heartbeat | Asprin  | 081096  | 75    | today    | today + 365|  Number Per day: 1;(cr)Not sure why patient's heart-rate was elevated? Re-evaluate in a year.|



Scenario: Edit Office Visit
Given Kelly Doctor is an HCP with MID: 9000000000
And Patient 0000000026 exists in the system
And MID: 0000000026 is greater than 12 years old
And Hospital Test Hospital 9191919191 exists
And Hospital Health Institute Dr. E exists
And Lab Technician 5000000002 exists
And ICD10 S60.7, Multiple superficial injuries of wrist and hand exists
And Immunization CPT 90715 , Typhoid Vaccine exists
And Office visit on yesterday exists for 0000000026
And For Location for yesterday's office visit is Test Hospital 9191919191
And For Appointment Type for yesterday's office visit is General Checkup
And For Notes for yesterday's office visit is Overall Good!
And Yesterday's office visit has the option to send the Patient a billing statement selected
And Yesterday's office visit has the following Basic Health Metrics: height: 69 in, weight: 163 lbs, blood pressure: 102/60, LDL: 81, HDL: 60, Triglycerides: 110, Household Smoking Status: 1 - non-smoking household, Patient Smoking Status: 4 - Never smoker
And The Diagnosis for yesterday's office visit include S60.7, Multiple superficial injuries of wrist and hand
When I login as 9000000000 using pw
And I edit the office visit for 0000000026 from yesterday 
And I update notes to Update: Will be visiting Hyderabad, location to Health Institute Dr. E, add CPT 90714 , Typhoid Vaccine to immunizations, delete S60.7, Multiple superficial injuries of wrist and hand from diagnosis
And Update the office visit
Then After the office visit has been updated, record is updated with a message displayed at the top of the page: Information Successfully Updated.
And the date is today 
And After the office visit has been updated the Location saved for the office visit is Health Institute Dr. E
And After the office visit has been updated the type saved for the office visit is General Checkup
And After the office visit has been updated the Notes saved for the office visit is Update: Will be visiting Hyderabad
And After the office visit has been updated, it does include the following basic health metrics: height: 69 in, weight: 163 lbs, blood pressure: 102/60, LDL: 81, HDL: 60, Triglycerides: 110, Household Smoking Status: 1 - non-smoking household, Patient Smoking Status: 4 - Never smoker
And After the office visit has been updated, it does NOT include the following Diagnosis of "S60.7", Multiple superficial injuries of wrist and hand
And After the office visit has been updated, it does include the following Immunization 90714, Typhoid Vaccine

