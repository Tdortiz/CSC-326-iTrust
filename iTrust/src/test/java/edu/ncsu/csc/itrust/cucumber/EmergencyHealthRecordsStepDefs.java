package edu.ncsu.csc.itrust.cucumber;

import javax.sql.DataSource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitValidator;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.HospitalsDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import static org.junit.Assert.fail;

public class EmergencyHealthRecordsStepDefs {

	private AuthDAO authController;
	private PatientDAO patientController;
	private PatientDataShared sharedPatient;
	private OfficeVisitValidator ovValidator;
	private DataSource ds;
	private OfficeVisitController ovController;
	private OfficeVisit sharedVisit;
	private UserDataShared sharedUser;
	private TestDataGenerator gen;
	private HospitalsDAO hospDAO;
	private PersonnelDAO persDAO;
	private OfficeVisitMySQL oVisSQL;
	private LabProcedureMySQL labPSQL;
	
	
	public EmergencyHealthRecordsStepDefs(){
		this.ds = ConverterDAO.getDataSource();
		this.ovController = new OfficeVisitController(ds);
		this.ovValidator = new OfficeVisitValidator(ds);
		this.authController = new AuthDAO(TestDAOFactory.getTestInstance());
		this.patientController = new PatientDAO(TestDAOFactory.getTestInstance());
		this.gen = new TestDataGenerator();
		this.hospDAO = new HospitalsDAO(TestDAOFactory.getTestInstance());
		this.persDAO = new PersonnelDAO(TestDAOFactory.getTestInstance());
		this.oVisSQL = new OfficeVisitMySQL(ds);
		this.labPSQL = new LabProcedureMySQL(ds);
	}
	
	@Given("^ER (.+) exists$")
	public void eRExists(int erID){
		  try {
				persDAO.checkPersonnelExists(erID);
			} catch (DBException e) {
				fail();
				e.printStackTrace();
			}
	}
	
	@Given("^(.*) (.*) exists but is not marked as chronic$")
	public void icdExists(String codeType, String icdCode){
		fail();
	}
	
	@Given("^NCD (.*) exists$")
	public void ndcExists(String ncdCode){
		fail();
	}
	
	@Given("^Patient (.+) is (.+) years old$")
	public void patientHowOld(int mid, int years){
		fail();
	}
	
	@Given("^Patient (.+) is (.*).$")
	public void maleFemale(int pid, String maleFemale){
		fail();
	}
	
	@Given("^Patient (.+) has blood type (.*)$")
	public void bloodType(int pid, String bloodType){
		fail();
	}
	
	@Given("^Patient (.+) has Emergency Contact: (.*) with phone number (.*)$")
	public void emergencyContact(int pid, String contact, String contactNumber){
		fail();
	}
	
	@Given("^Patient (.+) has the following Allergies: (.*) and (.*)$")
	public void patientAllergies(int pid, String allergy1, String allergy2){
		fail();
	}
	
	@Given("^Patient (.+) office visit from (.*) has the following diagnosis: (.*)$")
	public void officeVisitDiagnosis(int pid, String date, String diagnosis){
		fail();
	}
	
	@Given("^Patient (.+)'s office visit from (.*) has the following immunization: (.*)$")
	public void officeVisitImmunization(int pid, String date, String immunization){
		fail();
	}
	
	@Given("^Patient (.+)'s office visit from (.*) included the following prescription: (.*) with start date equal to (.*) and end date (.*), and Special Instructions: (.*)$")
	public void officeVisitWithPrescription(int pid, String date, String prescription, String startDate, String endDate, String specialInstructions){
		fail();
	}
	
	@Given("^(.*)'s office visit for (.+) has the following Basic Health Metrics: length: (.+) in, weight: (.+) lbs, blood pressure: (.*), Household Smoking Status: (.*)$")
	public void officeVisitBaby(String date, int length, int weight, String bloodPressure, String hSmoking){
		fail();
	}
	
	
	
	@When("^Go to the Emergency Patient Report.$")
	public void goToEmerReport(){
		fail();
	}
	
	
	
	@Then("^mid: (.+) name: (.*), age: (.+) gender: (.*), emergency contact: (.*), allergies: (.*), blood type: (.*), diagnoses: (.*), and (.*), prescriptions: (.*), and (.*), immunizaitons: (.*)$")
	public void checkResults(int mid, String name, int age, String gender, String contact, String allergies, String bloodType, String diagnoses1, String diagnoses2, String prescrip1, String prescrip2, String immunization ){
		fail();
	}
	
	
	
	
	@Then("^mid: (.+), name: (.*), gender: (.*), emergency contact: (.*), no allergies, blood type: (.*), diagnosis: (.*), prescriptions: (.*), no immunizations$")
	public void checkResults2(int mid, String name, String gender, String contact, String bloodType, String diagnoses1, String prescrip1){
		fail();
	}
	
	@Then("^Error: The patient (.+) does not exist$")
	public void noPatient(int mid){
		fail();
	}
	
	@Then("^Office visit info missing$")
	public void noOfficeVisitForPatient(int mid){
		fail();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
