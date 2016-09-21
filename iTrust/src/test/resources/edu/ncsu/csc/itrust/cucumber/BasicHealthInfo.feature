Feature: Basic Health Information

# Student written functional blackbox test
Scenario: Test Initial Field Visibility
Given Shelly Vang is an HCP with MID: 8000000011
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Brynn McClain with MID 101
Then height field should be hidden

Scenario: Test Final Field Visibility
Given Shelly Vang is an HCP with MID: 8000000011
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Brynn McClain with MID 101
And Choose appointment type General checkup
And Chooses the date to be 10/1/2013
And Select Central Hospital for location
And enters Note “Brynn can start eating rice cereal mixed with breast milk or formula once a day”
And Creates a Basic Health History
Then height field should be visible

Scenario: Test Invalid Length
Given Shelly Vang is an HCP with MID: 8000000011
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Brynn McClain with MID 101
And Choose appointment type General checkup
And Chooses the date to be 10/1/2013
And Select Central Hospital for location
And enters Note “Brynn can start eating rice cereal mixed with breast milk or formula once a day”
And Creates a Basic Health History
And Enters 16.5 for weight
And Enters -1 for length
And Enters 16.1 for Head Circumference
And selects 1 for non-smoking for household smoking status
And submits record
Then an error message is displayed

Scenario: New Born Office Visit
Given Shelly Vang is an HCP with MID: 8000000011
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Brynn McClain with MID 101
And Choose appointment type General checkup
And Chooses the date to be 5/1/2013
And Select Central Hospital for location
And enters Note “Brynn was born today!”
And Creates a Basic Health History
And Enters 10 for weight
And Enters 8 for length
And Enters 16 for Head Circumference
And selects 1 for non-smoking for household smoking status
And submits record
Then a success message is displayed

Scenario: Invalid Cholesterol In Adult
Given Shelly Vang is an HCP with MID: 8000000011
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Brynn McClain with MID 101
And Choose appointment type General checkup
And Chooses the date to be 10/1/2026
And Select Central Hospital for location
And enters Note “Brynn is an adult now! Or at least older than 12.”
And Creates a Basic Health History
And Enters 180 for weight
And Enters 65 for height
And Enters 140/110 for blood pressure
And selects 1 for non-smoking for household smoking status
And selects 2 for former smoker for patient smoking status
And enters 89 for HDL
And enters 601 for LDL
And enters 599 for Triglycerides 
And submits record
Then an error message is displayed

# Acceptance Tests Provided by Teaching Staffs 
Scenario: Four Month Old
Given Shelly Vang is an HCP with MID: 8000000011
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Brynn McClain with MID 101
And Choose appointment type General checkup
And Chooses the date to be 10/1/2013
And Select Central Hospital for location
And enters Note “Brynn can start eating rice cereal mixed with breast milk or formula once a day”
And Creates a Basic Health History
And Enters 16.5 for weight
And Enters 22.3 for length
And Enters 16.1 for Head Circumference
And selects 1 for non-smoking for household smoking status
And submits record
Then a success message is displayed
 
 
Scenario:  Two Years Old
Given Shelly Vang is an HCP with MID: 8000000011
And Caldwell Hudson is a patient with MID 102 who is two years old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Caldwell Hudson MID 102
And Chooses the date to be 10/28/2013
And enters Note “Diagnosed with strep throat.  Avoid contact with others for the first 24 hours of antibiotics”
And Creates a Basic Health History
And Enters 30.2 for weight
And Enters 34.7 for length
And Enters 19.4 for Head Circumference
And selects 3 for indoors smokers for household smoking status
And submits record
Then a success message is displayed
 
 
Scenario:  Five Years Old
Given Shelly Vang is an HCP with MID: 8000000011
And Fulton Gray is a patient with MID 103 who is five years old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Fulton Gray MID 103
And Chooses the date to be 10/14/2013
And enters Note “Fulton has all required immunizations to start kindergarten next year.”
And Creates a Basic Health History
And Enters 37.9 for weight
And Enters 42.9 for height
And Enters 95/65 for blood pressure
And selects 2 for outdoor smokers for household smoking status
And submits record
Then a success message is displayed


Scenario:  20 Years Old
Given Shelly Vang is an HCP with MID: 8000000011
And Daria Griffin is a patient with MID 104 who is 20 years old
When Shelly Vang logs in
And Chooses to document an office visit
And selects patient Fulton Gray MID 104
And Chooses the date to be 10/25/2013
And enters Note “Patient is healthy.”
And Creates a Basic Health History
And Enters 124.3 for weight
And Enters 62.3 for height
And Enters 110/75 for blood pressure
And selects 1 for non-smoking for household smoking status
And selects 2 for former smoker for patient smoking status
And enters 65 for HDL
And enters 102 for LDL
And enters 147 for Triglycerides 
And submits record
Then a success message is displayed


Scenario:  24 Years Old
Given Shelly Vang is an HCP with MID: 8000000011
And Thane Ross is a patient with MID 105 who is 20 years old
When Shelly Vang logs in
And Chooses to document an office visit
And Chooses the date to be 10/25/2013
And selects patient Thane Ross MID 105
And enters Note “Thane should consider modifying diet and exercise to avoid future heart disease”
And Creates a Basic Health History
And Enters 210.1 for weight
And Enters 73.1 for height
And Enters 160/110 for blood pressure
And selects 1 for non-smoking for household smoking status
And selects 4 for never smoked for patient smoking status
And enters 37 for HDL
And enters 141 for LDL
And enters 162 for Triglycerides 
And submits record
Then a success message is displayed

Scenario:  No Smoking Status
Given Kelly Doctor is an HCP with MID: 9000000000
And Random Person is a patient with MID 1 
When Shelly Vang logs in
And Chooses to document an office visit
And Chooses the date to be 12/25/2013
And selects patient Random Person MID 1
And enters Note “Came in complaining of splinters.”
And Creates a Basic Health History
And Enters 500 for weight
And Enters 48.0 for height
And Enters 180/110 for blood pressure
And selects 1 for non-smoking for household smoking status
And enters 40 for HDL
And enters 190 for LDL
And enters 500 for Triglycerides 
And submits record
Then an error message is displayed


Scenario:  No High Blood Pressure 
Given Kelly Doctor is an HCP with MID: 9000000000
And Random Person is a patient with MID 1 
When Shelly Vang logs in
And Chooses to document an office visit
And Chooses the date to be 12/01/2013
And selects patient Random Person MID 1
And enters Note “It doesn't matter”
And Creates a Basic Health History
And Enters 160 for weight
And Enters 55.5 for height
And Enters /79 for blood pressure
And selects 1 for non-smoking for household smoking status
And selects 4 for new smoked for patient smoking status 
And enters 50 for HDL
And enters 100 for LDL
And enters 345 for Triglycerides 
And submits record
Then an error message is displayed


Scenario:  Invalid Characters for HDL
Given Kelly Doctor is an HCP with MID: 9000000000
And Random Person is a patient with MID 1 
When Shelly Vang logs in
And Chooses to document an office visit
And Chooses the date to be 10/31/2013
And selects patient Random Person MID 1
And enters Note “Testing invalid input”
And Creates a Basic Health History
And Enters 150 for weight
And Enters 55.5 for height
And Enters 120/79 for blood pressure
And selects 1 for non-smoking for household smoking status
And selects 4 for new smoked for patient smoking status 
And enters AA for HDL
And enters 101 for LDL
And enters 222 for Triglycerides 
And submits record
Then an error message is displayed


Scenario:  Zero Head Circumference for Under 3 Year Old
Given Kelly Doctor is an HCP with MID: 9000000000
And Brynn McClain is a patient with MID 101 who is 4 months old
When Shelly Vang logs in
And Chooses to document an office visit
And Chooses the date to be 10/26/2013
And selects patient Random Person MID 1
And enters Note “Brynn is a very healthy baby”
And Creates a Basic Health History
And Enters 90 for weight
And Enters 36.0 for height
And Enters 0 for Head Circumference
And selects 1 for non-smoking for household smoking status
And submits record
Then an error message is displayed
