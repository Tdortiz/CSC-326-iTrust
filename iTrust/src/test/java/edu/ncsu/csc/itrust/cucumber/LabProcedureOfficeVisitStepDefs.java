 package edu.ncsu.csc.itrust.cucumber;
 
 import cucumber.api.java.en.Given;
 import cucumber.api.java.en.Then;
 import cucumber.api.java.en.When;
import cucumber.api.java.it.Date;
import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitValidator;
import edu.ncsu.csc.itrust.model.old.beans.HospitalBean;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.HospitalsDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.model.user.patient.Patient;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.apache.james.mime4j.field.datetime.DateTime;
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
        
        
        @Given("^UC26sql has been loaded$")
        public void loadUC26SQL() throws FileNotFoundException, SQLException, IOException{
        	
				gen.uc26();
			
        }
 
        @Given("^Patient (.*) exists in the system$")
        public void patientExists(String pid) {
                try {
					Assert.assertTrue(patientController.checkPatientExists(Long.parseLong(pid)));
				} catch (NumberFormatException e) {
					fail();
					e.printStackTrace();
				} catch (DBException e) {
					fail();
					e.printStackTrace();
				}
        }
 
        @Given("^Office visit on (.*) exists for (.*)$")
        public void officeVisitExists(String date, String pid) {
              
        		   LocalDate date2 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-dd"));
            	   try {
            		   List <OfficeVisit> allOfficeVisits = oVisSQL.getVisitsForPatient(Long.parseLong(pid));
            		   
            		   int found = 0;
            		   for (int i = 0; i < allOfficeVisits.size(); i++) {
            			   if (allOfficeVisits.get(i).getDate().toString().contains(date)){
            				   //office visit has been found and therefore exists
            				   Assert.assertTrue(true);
            				   found = 1;
            				   break;
            			   }
            		   }
            		   
            		   if (found == 0) {
            			   fail();
            		   }
            
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
               
        }
 
        @Given("^MID: (.*) is greater than (.+) years old$")
        public void checkAgeOfPatient(String mid, int age) {
        	 try {
				PatientBean p = patientController.getPatient(Long.parseLong(mid));
				Assert.assertTrue(p.getAge() > age);
			} catch (NumberFormatException e) {
				fail();
				e.printStackTrace();
			} catch (DBException e) {
				fail();
				e.printStackTrace();
			}
        }
 
        @Given("^Hospital (.*) exists$")
        public void hospitalExists(String hosName) throws DBException {
                List <HospitalBean> allHospitals = hospDAO.getAllHospitals();
                
                int found = 0;
                for(int i = 0; i < allHospitals.size(); i++){
                	if (allHospitals.get(i).getHospitalName().equals(hosName)){
                		//found the hospital. Assert true and break
                		found = 1;
                		Assert.assertTrue(true);
                		break;
                	}
                }
                if (found == 0){
                	fail();
                }
        }
 
        @Given("^Lab Technician (.+) exists$")
        public void labTechExists(int mid) {
               try {
				persDAO.checkPersonnelExists(mid);
			} catch (DBException e) {
				fail();
				e.printStackTrace();
			}
        }
 
        @Given("^ICD10 (.*) exists$")
        public void diseaseExists(String diseaseName) {
                /////////////////////////////////////////////////////////////////////////////////passing for now. Update later
        }
 
        @Given("^Immunization CPT (.*) exists$")
        public void immunizationExists() {
                fail();
        }
 
        @Given("^NDC (.+), (.*) exists$")
        public void medicationExists(int medCode, String medName) {
                fail();
        }
 
        @Given("^Medical Procedure CPT (.+) exists$")
        public void medProcedureExists(int medProcedureCode) {
                fail();
        }
 
        @Given("^LOINC (.*) exists$")
        public void studyProcedureExists(String studyProc) {
              ////////////////////////////////////////////////////////////////////////////////////passing for now. update later  
        }
 
        @Given("^For (.*) for yesterday's office visit is (.*)$")
        public void officeVisitDetails(String category, String specifics) {
                fail();
        }
 
        @Given("^(.*)'s office visit has the option to send the Patient a billing statement selected$")
        public void sendBillTrue(String date) {
        	List<OfficeVisit> allOfficeVisits;
        	try {
				allOfficeVisits = oVisSQL.getVisitsForPatient((long) 26);
				   int found = 0;
		 		   for (int i = 0; i < allOfficeVisits.size(); i++) {
		 			   if (allOfficeVisits.get(i).getDate().toString().contains(date)){
		 				   Assert.assertTrue(allOfficeVisits.get(i).getSendBill().equals(true));
		 				   found = 1;
		 				   break;
		 			   }
		 		   }		   
		 		   if (found == 0) {
		 			   fail();
		 		   }
			} catch (DBException e) {
				 fail();
				e.printStackTrace();
			}
        }
 
        @Given("^(.*)'s office visit for (.+) has the following Basic Health Metrics: height: (.+) in, weight: (.+) lbs, blood pressure: (.*), LDL: (.+), HDL: (.+), Triglycerides: (.+), Household Smoking Status: (.*), Patient Smoking Status: (.*)$")
        public void checkYesterdaysVisit( String date, int id, int height, int weight, String bloodPressure, int ldl, int hdl, int trigs, String hSmoke, String pSmoke ) {
        	List<OfficeVisit> allOfficeVisits;
        	try {
				allOfficeVisits = oVisSQL.getVisitsForPatient((long) id);
				   int found = 0;
		 		   for (int i = 0; i < allOfficeVisits.size(); i++) {
		 			   if (allOfficeVisits.get(i).getDate().toString().contains(date)){
		 				   Assert.assertEquals(Float.valueOf(height), allOfficeVisits.get(i).getHeight());
		 				   Assert.assertEquals(Float.valueOf(weight), allOfficeVisits.get(i).getWeight());
		 				   Assert.assertEquals(bloodPressure, allOfficeVisits.get(i).getBloodPressure());
		 				   Assert.assertEquals(Float.valueOf(ldl), Float.valueOf(allOfficeVisits.get(i).getLDL()));
		 				   Assert.assertEquals(Float.valueOf(hdl), Float.valueOf(allOfficeVisits.get(i).getHDL()));
		 				  Assert.assertEquals(Float.valueOf(trigs), Float.valueOf(allOfficeVisits.get(i).getTriglyceride()));
		 				  Assert.assertEquals(hSmoke, allOfficeVisits.get(i).getHouseholdSmokingStatusDescription() );
		 				 Assert.assertEquals(pSmoke, allOfficeVisits.get(i).getPatientSmokingStatusDescription() );
		 				 found = 1;
		 				   break;
		 			   }
		 		   }		   
		 		   if (found == 0) {
		 			   fail();
		 		   }
			} catch (DBException e) {
				 fail();
				e.printStackTrace();
			}
        }
 
        @Given("^The Diagnosis for yesterday's office visit include (.*)$")
        public void checkDiagnosis(String diagnosis) {
                fail();
        }
 
        @When("^I add a new prescription with the following information: Medication: (.+) (.*), Dosage: (.*). Dates: (.*) to (.*), Instructions: (.*)")
        public void enterDiagnosisBlank(int medCode, String medName, String dosage, String date1, String date2, String instructions) {
                //call method to add one but without any params
                fail();
        }
 
        @When("^I edit the office visit for (.+) from (.*)$")
        public void editVisitFromYesterday(int mid, String date) {
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
 
 
        @Then("^After the office visit has been updated, record is updated with a message displayed at the top of the page: (.*).$")
        public void successfulUpdate(String updateMessage) {
                fail();
        }
 
        @Then("^the date is (.*)$")
        public void checkDate(String date) {
                fail();
        }

        @Then("^After the office visit has been (.*) the (.*) saved for the office visit is (.*)$")
        public void checkIfUpdateOrCreateSuccess(String updateOrCreate, String attribute, String attributeDetails) {
                fail();
        }
 
        @Then("^After the office visit has been (.*), it does include the following basic health metrics: height: (.+) in, weight: (.+) lbs, blood pressure: (.*), LDL: (.+), HDL: (.+), Triglycerides: (.+), Household Smoking Status: (.*), Patient Smoking Status: (.*)$")
        public void updateBmetrics(String createOrUpdate, int height, int weight, String bloodPressure, int ldl, int hdl, int trigs, String hSmoke, String pSmoke ) {
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