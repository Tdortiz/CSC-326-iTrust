#Author: Mike MacKrell
# 
#
#
Feature: View ER Reports



Scenario: ViewEmergencyReportER
Given ER 9000000006 exists
And ICD10 J45 (Asthma) exists but is not marked as chronic
And ICD10 J12 (Viral Pneumonia) exists but is not marked as chronic 
And J00 (Acute Nasopharyngitis) exists but is not marked as chronic 
And NCD 05730150 (Advil) exists
And NCD 483013420 (Midichlomaxene) exists
And NCD 63739291 (Oyster Shell Calcium with Vitamin D) exists
And Immunization CPT 90715, TDAP exists
And Hospital Test Hospital 9191919191 exists
And Patient 0000000201 exists in the system
And Patient 0000000201 is 24 years old.
And Patient 0000000201 is Male.
And Patient 0000000201 has blood type O-
And Patient 0000000201 has Emergency Contact: Susan Sky-Walker with phone number 444-332-4309
And Patient 0000000201 has the following Allergies: Pollen (diagnosed 05/05/2016) and Penicillin (diagnosed 06/04/2016)
And Patient 201 has an office visit with a date of 2015-11-25, a Location of 9191919191, Appointment type 6, and Notes: finishing blood tests
And 2015-10-25's office visit for 201 has the following Basic Health Metrics: height: 70 in, weight: 180 lbs, blood pressure: 100/70, LDL: 81, HDL: 40, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And Patient 201 office visit from 2015-11-25 has the following diagnosis: ICD10 J45 (Asthma)
And Patient 201's office visit from 2015-11-25 has the following immunization: CPT 90715 (TDAP)
And Patient 201 has an office visit with a date of 2015-07-25, a Location of 9191919191, Appointment type 6, and Notes: Patient had trouble breathing
And 2015-07-25's office visit for 201 has the following Basic Health Metrics: height: 70 in, weight: 178 lbs, blood pressure: 105/68, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And Patient 201's office visit from 2015-07-25 included the following prescription: NDC 483013420 (Midichlomaxene) with start date equal to 2015-07-25 and end date 2015-05-25, and Special Instructions: take daily
And Patient 201 office visit from 2015-07-25 has the following diagnosis: J12 (Viral Pneumonia)
And Patient 201's office visit from 2015-07-25 included the following prescription: NDC 05730150 (Advil) with start date equal to 2015-07-25 and end date 2015-08-25, and Special Instructions: extra-strength: take if needed
And Patient 201 has an office visit with a date of 2016-11-18, a Location of 9191919191, Appointment type 1, and Notes: Patient had trouble breathing
And 2016-11-18's office visit for 201 has the following Basic Health Metrics: height: 70 in, weight: 170 lbs, blood pressure: 100/68, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And Patient 201's office visit from 2016-11-18 has the following diagnosis: J00 (Acute Nasopharyngitis)
And Patient 201's office visit from 2016-11-18 included the following prescription: NDC 63739291 (Oyster Shell Calcium with Vitamin D) with start date equal to the 2016-11-18 and end date 2016-11-30, and Special Instructions: take once daily
When I login as 9000000006 using pw
And Go to the Emergency Patient Report.
Then mid: 201 name: Sandy Sky, age: 24 gender: Male, emergency contact: Susan Sky-Walker 444-332-4309, allergies: Pollen, 05/05/2016 and Penicillin, 06/04/2016, blood type: O-, diagnoses: ICD10 J45 (Asthma), and 00 (Acute Nasopharyngitis), prescriptions: 483013420 (Midichlomaxene), and 63739291 (Oyster Shell Calcium with Vitamin D), immunizaitons: CPT 90715 (TDAP)



Scenario: ViewEmergencyReportHCP
Given Kelly Doctor is an HCP with MID: 9000000000
And ICD10 J45 (Asthma) exists but is not marked as chronic
And ICD10 J12 (Viral Pneumonia) exists but is not marked as chronic 
And J00 (Acute Nasopharyngitis) exists but is not marked as chronic 
And NCD 05730150 (Advil) exists
And NCD 483013420 (Midichlomaxene) exists
And NCD 63739291 (Oyster Shell Calcium with Vitamin D) exists
And Immunization CPT 90715, TDAP exists
And Hospital Test Hospital 9191919191 exists
And Patient 0000000201 exists in the system
And Patient 0000000201 is 24 years old.
And Patient 0000000201 is Male
And Patient 0000000201 has blood type O-
And Patient 0000000201 has Emergency Contact: Susan Sky-Walker with phone number 444-332-4309
And Patient 0000000201 has the following Allergies: Pollen (diagnosed 05/05/2016) and Penicillin (diagnosed 06/04/2016)
And Patient 201 has an office visit with a date of 2015-11-25, a Location of 9191919191, Appointment type 6, and Notes: finishing blood tests
And 2015-10-25's office visit for 201 has the following Basic Health Metrics: height: 70 in, weight: 180 lbs, blood pressure: 100/70, LDL: 81, HDL: 40, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And Patient 201 office visit from 2015-11-25 has the following diagnosis: ICD10 J45 (Asthma)
And Patient 201's office visit from 2015-11-25 has the following immunization: CPT 90715 (TDAP)
And Patient 201 has an office visit with a date of 2015-07-25, a Location of 9191919191, Appointment type 6, and Notes: Patient had trouble breathing
And 2015-07-25's office visit for 201 has the following Basic Health Metrics: height: 70 in, weight: 178 lbs, blood pressure: 105/68, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And Patient 201's office visit from 2015-07-25 included the following prescription: NDC 483013420 (Midichlomaxene) with start date equal to the office visit and end date 60 days ago, and Special Instructions: take daily
And Patient 201's office visit from 2015-07-25 has the following diagnosis: J12 (Viral Pneumonia)
And Patient 201's office visit from 2015-07-25 included the following prescription: NDC 05730150 (Advil) with start date equal to 2015-07-25 and end date 2015-08-25, and Special Instructions: extra-strength: take if needed
And Patient 201 has an office visit with a date of 2016-11-18, a Location of 9191919191, Appointment type 1, and Notes: Patient had trouble breathing
And 2016-11-18's office visit for 201 has the following Basic Health Metrics: height: 70 in, weight: 170 lbs, blood pressure: 100/68, LDL: 81, HDL: 45, Triglycerides: 105, Household Smoking Status: 1 - Non-Smoking Household, Patient Smoking Status: 4 - Never Smoker
And Patient 201's office visit from 2016-11-18 has the following diagnosis: J00 (Acute Nasopharyngitis)
And Patient 201's office visit from 2016-11-18 included the following prescription: NDC 63739291 (Oyster Shell Calcium with Vitamin D) with start date equal to the 2016-11-18 and end date 2016-11-30, and Special Instructions: take once daily
When I login as 9000000000 using pw
And Go to the Emergency Patient Report.
Then mid: 201, name: Sandy Sky, age: 24 gender: Male, emergency contact: Susan Sky-Walker 444-332-4309, allergies: Pollen, 05/05/2016 and Penicillin, 06/04/2016, blood type: O-, diagnoses: ICD10 J45 (Asthma), and 00 (Acute Nasopharyngitis), prescriptions: 483013420 (Midichlomaxene), and 63739291 (Oyster Shell Calcium with Vitamin D), immunizaitons: CPT 90715 (TDAP)


Scenario: ViewEmergencyReportInfantER
Given ER 9000000006 exists
And ICD10 J45 (Asthma) exists but is not marked as chronic
And ICD10 J12 (Viral Pneumonia) exists but is not marked as chronic 
And J00 (Acute Nasopharyngitis) exists but is not marked as chronic 
And Patient 0000000202 exists in the system
And Patient 0000000202 is female
And Patient 0000000202 has blood type O+
And Patient 0000000202 has Emergency Contact: Susan Sky-Walker with phone number 444-332-4309
And Patient 202 has an office visit with a date of 2015-10-18, a Location of Test Hospital 9191919191, Appointment type Consultation, and Notes: Patient had trouble breathing
And 2015-10-18's office visit for 202 has the following Basic Health Metrics: length: 20 in, weight: 10 lbs, blood pressure: 80/40, Household Smoking Status: 1 - non-smoking household
And Patient 202's office visit from a week ago has the following diagnosis: J00 (Acute Nasopharyngitis)
When I login as 9000000006 using pw
And Go to the Emergency Patient Report.
Then mid: 202, name: Sarah Sky-Walker, gender: Female, emergency contact: Susan Sky-Walker 444-332-4309, no allergies, blood type: O+, diagnosis: 00 (Acute Nasopharyngitis), prescriptions: NDC 63739291 (Oyster Shell Calcium with Vitamin D), no immunizations

Scenario: ViewEmergencyReportERNoPatient
Given ER 9000000006 exists
And ICD10 J45 (Asthma) exists but is not marked as chronic
And ICD10 J12 (Viral Pneumonia) exists but is not marked as chronic 
And J00 (Acute Nasopharyngitis) exists but is not marked as chronic 
And NCD 05730150 (Advil) exists
And NCD 483013420 (Midichlomaxene) exists
And NCD 63739291 (Oyster Shell Calcium with Vitamin D) exists
And Immunization CPT 90715, TDAP exists
And Hospital Test Hospital 9191919191 exists
When I login as 9000000006 using pw
And Go to the Emergency Patient Report.
Then Error: The patient 2002 does not exist


Scenario: ViewEmergencyReportHCPNoPatient
Given ER 9000000000 exists
And ICD10 J45 (Asthma) exists but is not marked as chronic
And ICD10 J12 (Viral Pneumonia) exists but is not marked as chronic 
And J00 (Acute Nasopharyngitis) exists but is not marked as chronic 
And NCD 05730150 (Advil) exists
And NCD 483013420 (Midichlomaxene) exists
And NCD 63739291 (Oyster Shell Calcium with Vitamin D) exists
And Immunization CPT 90715, TDAP exists
And Hospital Test Hospital 9191919191 exists
When I login as 9000000000 using pw
And Go to the Emergency Patient Report.
Then Error: The patient 2002 does not exist


Scenario: ViewEmergencyReportNoOfficeVisit
Given ER 9000000000 exists
And Patient 0000000203 exists in the system
And ICD10 J45 (Asthma) exists but is not marked as chronic
And ICD10 J12 (Viral Pneumonia) exists but is not marked as chronic 
And J00 (Acute Nasopharyngitis) exists but is not marked as chronic 
And NCD 05730150 (Advil) exists
And NCD 483013420 (Midichlomaxene) exists
And NCD 63739291 (Oyster Shell Calcium with Vitamin D) exists
And Immunization CPT 90715, TDAP exists
And Hospital Test Hospital 9191919191 exists
When I login as 9000000000 using pw
And Go to the Emergency Patient Report.
Then Error: No office visits for Patient 203





























