 package edu.ncsu.csc.itrust.cucumber;
 
 import cucumber.api.java.en.Given;
 import cucumber.api.java.en.Then;
 import cucumber.api.java.en.When;
 
 import static org.junit.Assert.fail;
 
 public class LabProcedureOfficeVisitStepDefs {
 
        public LabProcedureOfficeVisitStepDefs() {
 
        }
 
        @Given("^Patient (.*) exists in the system$")
        public void Patient_exists(String pid) {
                fail();
        }
 
        @Given("^Office visit on (.*) exists for (.*)$")
        public void office_visit_exists(String date, String pid) {
                fail();
        }
 
        @Given("^MID: (.*) is greater than (.+) years old$")
        public void check_age_of_patient(String mid, int age) {
                fail();
        }
 
        @Given("^Hospital (.*) exists$")
        public void hospital_exists(String hosName) {
                fail();
        }
 
        @Given("^Lab Technician (.+) exists$")
        public void lab_tech_exists(int mid) {
                fail();
        }
 
        @Given("^ICD10 (.*) exists$")
        public void disease_exists(String diseaseName) {
                fail();
        }
 
        @Given("^Immunization CPT (.*) exists$")
        public void immunization_exists() {
                fail();
        }
 
        @Given("^NDC (.+), (.*) exists$")
        public void medication_exists(int medCode, String medName) {
                fail();
        }
 
        @Given("^Medical Procedure CPT (.+) exists$")
        public void med_procedure_exists(int medProcedureCode) {
                fail();
        }
 
        @Given("^LOINC (.*) exists$")
        public void study_procedure_exists(String studyProc) {
                fail();
        }
 
        @Given("^For (.*) for yesterday's office visit is (.*)$")
        public void office_visit_details(String category, String specifics) {
                fail();
        }
 
        @Given("^(.*) office visit has the option to send the Patient a billing statement selected$")
        public void sendbill_true(String date) {
                fail();
        }
 
        @Given("^(.*)'s office visit has the following Basic Health Metrics: height: (.+) in, weight: (.+) lbs, blood pressure: (.*), LDL: (.+), HDL: (.+), Triglycerides: (.+), Household Smoking Status: (.*), Patient Smoking Status: (.*)$")
        public void check_yesterdays_visit( String date, int height, int weight, String bloodPressure, int ldl, int hdl, int trigs, String hSmoke, String pSmoke ) {
                fail();
        }
 
        @Given("^The Diagnosis for yesterday's office visit include (.*)$")
        public void check_diagnosis(String diagnosis) {
                fail();
        }
 
        @When("^I add a new prescription with the following information: Medication: (.+) (.*), Dosage: (.*). Dates: (.*) to (.*), Instructions: (.*)")
        public void enter_diagnosis_blank(int medCode, String medName, String dosage, String date1, String date2, String instructions) {
                //call method to add one but without any params
                fail();
        }
 
        @When("^I edit the office visit for (.+) from (.*)$")
        public void edit_visit_from_yesterday(int mid, String date) {
                fail();
        }
 
        @When("^I update notes to (.*), location to (.*), add CPT (.+) , (.*) to immunizations, delete (.*) from diagnosis$")
        public void update_health_info(String newNotes, String newLocation, int newCPT, String newImmunization, String deleteThis ) {
                fail();
        }
 
        @When("^Update the office visit$")
        public void update_office_visit() {
                fail();
        }
 
        @When("^For the Lab Procedures associated with the office visit, select (.*) , (.*)with priority 1 and Lab Technician (.+)$")
        public void make_lab_proc(String code, String description, int midLab) {
                fail();
        }
 
        @When("^Under (.*) add (.*)$")
        public void add_details(String type, String details) {
                fail();
        }
 
 
        @Then("^After the office visit has been updated, record is updated with a message displayed at the top of the page: (.*).$")
        public void successful_update(String updateMessage) {
                fail();
        }
 
        @Then("^After the office visit has been (.*) the (.*) saved for the office visit is (.*)$")
        public void check_if_update_or_create_success(String updateOrCreate, String attribute, String attributeDetails) {
                fail();
        }
 
        @Then("^After the office visit has been (.*), it does include the following basic health metrics: height: (.+) in, weight: (.+) lbs, blood pressure: (.*), LDL: (.+), HDL: (.+), Triglycerides: (.+), Household Smoking Status: (.*), Patient Smoking Status: (.*)$")
        public void update_bhmetrics(String createOrUpdate, int height, int weight, String bloodPressure, int ldl, int hdl, int trigs, String hSmoke, String pSmoke ) {
                fail();
        }
 
        @Then("^After the office visit has been updated, it does NOT include the following Diagnosis of (.*), (.*)$")
        public void success_deletion(String diagnosis, String diagnosisNotes) {
                fail();
        }
 
        @Then("^After the office visit has been updated, it does include the following Immunization (.+), (.*)")
        public void success_adding_immunizaiont(String immuneCode, String immuneName) {
                fail();
        }
 
        @Then("^After the office visit has been created, its prescription info is: drugname: (.*), Code: (.+), Dosage: (.+), Start Date: (.*), End Date: (.*), Special instructions: (.*)$")
        public void prescrip_exists_in_visit(String name, int code, int dosage, String dateStart, String dateEnd, String spInstruct) {
 
        }
 
        @Then("^After the office visit has been created, the (.*) associated with it include (.*)$")
        public void double_checking(String header, String details) {
                fail();
        }
 
        @Then("^After the office visit has been created, its Lab Procedures include (.*), (.*) with priority 1 and Lab Technician (.+)$")
        public void log_event(String procName, String procCode, int labMid) {
                fail();
        }
 
        @Then("^The (.*) event has been logged$")
        public void log_event(String event) {
 
        }
 
        @Then("^An error message is displayed (.*)$")
        public void error_message_displayed(String message) {
 
        }
 
 }