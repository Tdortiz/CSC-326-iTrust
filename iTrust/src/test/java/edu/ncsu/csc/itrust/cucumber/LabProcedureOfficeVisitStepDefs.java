 package edu.ncsu.csc.itrust.cucumber;
 
 import cucumber.api.java.en.Given;
 import cucumber.api.java.en.Then;
 import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.beans.HospitalBean;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.mysql.HospitalsDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
 
 
 public class LabProcedureOfficeVisitStepDefs {
	 
	 
		private PatientDAO patientController;
		private DataSource ds;
		private TestDataGenerator gen;
		private HospitalsDAO hospDAO;
		private PersonnelDAO persDAO;
		private OfficeVisitMySQL oVisSQL;
 
        public LabProcedureOfficeVisitStepDefs() {
        	this.ds = ConverterDAO.getDataSource();
    		this.patientController = new PatientDAO(TestDAOFactory.getTestInstance());
    		this.gen = new TestDataGenerator();
    		this.hospDAO = new HospitalsDAO(TestDAOFactory.getTestInstance());
    		this.persDAO = new PersonnelDAO(TestDAOFactory.getTestInstance());
    		this.oVisSQL = new OfficeVisitMySQL(ds);
    		
    		
    		
        }
        
        
        
        @Given("^I load uc11.sql$")
        public void loadData(){
        	fail();
        }
			

        @When("^I add a new prescription with the following information: Medication: (.+) (.*), Dosage: (.*). Dates: (.*) to (.*), Instructions: (.*)")
        public void enterDiagnosisBlank(int medCode, String medName, String dosage, String date1, String date2, String instructions) {
                //call method to add one but without any params
                fail();
        }
 
        
 
        @When("^I update notes to (.*), location to (.*), add CPT (.+) , (.*) to immunizations, delete (.*) from diagnosis$")
        public void updateHealthInfo(String newNotes, String newLocation, int newCPT, String newImmunization, String deleteThis ) {
                fail();
        }
 
        @When("^Update the office visit$")
        public void updateOfficeVisit() {
                fail();
        }
 
        @When("^For the Lab Procedures associated with the office visit, select (.*) , (.*)with priority 1 and Lab Technician (.+)$")
        public void makeLabProc(String code, String description, int midLab) {
                fail();
        }
 
        @When("^Under (.*) add (.*)$")
        public void addDetails(String type, String details) {
                fail();
        }
        
        @When("^user enters weight: (.*), height: (.*), blood pressure (.*), ldl: (.*), hdl: (.*), Triglycerides: (.*), house smoke: (.*), patient smoke: (.*)$")
        public void officeMetrics(String weight, String height, String blood, String ldl, String hdl, String trig, String house, String patient) {
                fail();
        }
 
 
        @Then("^After the office visit has been (.*) it does include the following basic health metrics: height: (.*) in, weight: (.*) lbs, blood pressure: (.*), LDL: (.*), HDL: (.*), Triglycerides: (.*), Household Smoking Status: (.*), Patient Smoking Status: (.*)$")
        public void successfulUpdate(String createOrUpdate, String height, String weight, String blood, String ldl, String hdl, String trig, String house, String patient) {
                fail();
        }
 
       

        @Then("^After the office visit has been created the Location: (.*), notes: (.*), sendbill: (.*)$")
        public void locationNotesSendBill(String location, String notes, String sendBill) {
                fail();
        }
        
        @Then("^After the office visit has been updated the type saved for the office visit is General Checkup$")
        public void appointmentType() {
                fail();
        }
        
        @Then("^After the office visit has been updated, it does NOT include the following Diagnosis of (.*), (.*)$")
        public void successDeletion(String diagnosis, String diagnosisNotes) {
                fail();
        }
 
        @Then("^After the office visit has been updated, it does include the following Immunization (.+), (.*)")
        public void successAddingImmunizaiont(String immuneCode, String immuneName) {
                fail();
        }
 
        @Then("^After the office visit has been created, its prescription info is: drugname: (.*), Code: (.+), Dosage: (.+), Start Date: (.*), End Date: (.*), Special instructions: (.*)$")
        public void prescripExistsInVisit(String name, int code, int dosage, String dateStart, String dateEnd, String spInstruct) {
 
        }
 
        @Then("^After the office visit has been created, the (.*) associated with it include (.*)$")
        public void doubleChecking(String header, String details) {
                fail();
        }
 
        @Then("^After the office visit has been created, its Lab Procedures include (.*), (.*) with priority 1 and Lab Technician (.+)$")
        public void logEvent(String procName, String procCode, int labMid) {
                fail();
        }
 
        @Then("^The (.*) event has been logged$")
        public void logEvent(String event) {
 
        }
 
        @Then("^An error message is displayed (.*)$")
        public void errorMessageDisplayed(String message) {
 
        }
 
 }