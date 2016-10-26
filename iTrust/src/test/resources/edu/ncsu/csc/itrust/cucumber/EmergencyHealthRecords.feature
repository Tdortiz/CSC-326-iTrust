#Author: Mike MacKrell
# 
#
#
Feature: View ER Reports



Scenario: ViewEmergencyReportER
Given I load uc21.sql
When I login as 9000000006 using pw
And Go to the Emergency Patient Report.
Then mid: 201 name: Sandy Sky, age: 24 gender: Male, emergency contact: Susan Sky-Walker 444-332-4309, allergies: Pollen, 05/05/2016 and Penicillin, 06/04/2016, blood type: O-, diagnoses: ICD10 J45 (Asthma), and 00 (Acute Nasopharyngitis), prescriptions: 483013420 (Midichlomaxene), and 63739291 (Oyster Shell Calcium with Vitamin D), immunizaitons: CPT 90715 (TDAP)



Scenario: ViewEmergencyReportHCP
tions: take once daily
When I login as 9000000000 using pw
And Go to the Emergency Patient Report.
Then mid: 201, name: Sandy Sky, age: 24 gender: Male, emergency contact: Susan Sky-Walker 444-332-4309, allergies: Pollen, 05/05/2016 and Penicillin, 06/04/2016, blood type: O-, diagnoses: ICD10 J45 (Asthma), and 00 (Acute Nasopharyngitis), prescriptions: 483013420 (Midichlomaxene), and 63739291 (Oyster Shell Calcium with Vitamin D), immunizaitons: CPT 90715 (TDAP)


Scenario: ViewEmergencyReportInfantER
Given I load uc21.sql
When I login as 9000000006 using pw
And Go to the Emergency Patient Report.
Then mid: 202, name: Sarah Sky-Walker, gender: Female, emergency contact: Susan Sky-Walker 444-332-4309, no allergies, blood type: O+, diagnosis: 00 (Acute Nasopharyngitis), prescriptions: NDC 63739291 (Oyster Shell Calcium with Vitamin D), no immunizations

Scenario: ViewEmergencyReportERNoPatient
Given I load uc21.sql
When I login as 9000000006 using pw
And Go to the Emergency Patient Report.
Then Error: The patient 2002 does not exist


Scenario: ViewEmergencyReportHCPNoPatient
Given I load uc21.sql
When I login as 9000000000 using pw
And Go to the Emergency Patient Report.
Then Error: The patient 2002 does not exist


Scenario: ViewEmergencyReportNoOfficeVisit
Given I load uc21.sql
When I login as 9000000000 using pw
And Go to the Emergency Patient Report.
Then Office visit info missing

