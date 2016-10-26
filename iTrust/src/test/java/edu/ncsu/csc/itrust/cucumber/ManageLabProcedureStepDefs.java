package edu.ncsu.csc.itrust.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitValidator;
import edu.ncsu.csc.itrust.model.old.beans.PersonnelBean;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.HospitalsDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

import static org.junit.Assert.fail;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;

public class ManageLabProcedureStepDefs {
	
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

	public ManageLabProcedureStepDefs() {
		
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
	
	@Given("^Patient (.+) has an office visit with a date of (.*), a Location of (.*), Appointment type (.*), and Notes: (.*)$")
	public void officeVisitExists(int mid, String date, String location, String appType, String notes) {
		try {
			List <OfficeVisit> oList = oVisSQL.getVisitsForPatient((long)mid);
			int found = 0;
			for(int i = 0; i < oList.size(); i++){
			
				if(oList.get(i).getDate().toString().contains(date)){
					
					Assert.assertEquals(location, oList.get(i).getLocationID());
					Assert.assertEquals(appType, oList.get(i).getApptTypeID() + "");
					Assert.assertEquals(notes, oList.get(i).getNotes());
					found = 1;
				}
			}
			if (found == 0){
				fail();
			}
		} catch (DBException e) {
			
			e.printStackTrace();
		}
	}
	
	@Given("^(.*)'s office visit has a Lab Procedure listed with Code (.*) with a current status of (.*), priority (.+), and assigned Lab Technician (.+)$")
	public void checkForLabProc(String date, String code, String status, int priority, int mid) {
		
		List<OfficeVisit> allOfficeVisits;
		List<LabProcedure> allLabProcedures;
		try {
			allOfficeVisits = oVisSQL.getVisitsForPatient((long) 26);
			 
			  int found = 0;
	 		   for (int i = 0; i < allOfficeVisits.size(); i++) {
	 			   if (allOfficeVisits.get(i).getDate().toString().contains(date)){
	 				  
	 				   allLabProcedures = labPSQL.getLabProceduresByOfficeVisit(allOfficeVisits.get(i).getVisitID());
	 				   
	 				   //at this point the test is stuck. The lab proc code is not yet implemented and thus testing can go no further/////////////////////////////////////////////////
	 				   
	 				   Assert.assertTrue(true);
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
	
	@Given("^Neither Lab Technician (.*) or Lab Technician (.*) has additional proccedures assigned$")
	public void nothingElseAssigned(String id1, String id2) {
		List<LabProcedure> allLabProcedures;
		try {
			//make sure that the only lab procedures are 2 for 50000000002 and none for 5000000003
			allLabProcedures = labPSQL.getAll();
			  int twoCount = 0;
			  int threeCount = 0;
	 		   for (int i = 0; i < allLabProcedures.size(); i++) {
	 			   if (allLabProcedures.get(i).getLabTechnicianID() == Long.parseLong(id1)){
	 				   twoCount++;
	 			   }
	 			  if (allLabProcedures.get(i).getLabTechnicianID() == Long.parseLong(id2)){
	 				   threeCount++;
	 			   }
	 		   }		   
	 		   if (twoCount > 2 || threeCount > 0) {
	 			   fail();
	 		   }
	 		   Assert.assertTrue(true);
		} catch (DBException e) {
			 fail();
			e.printStackTrace();
		}
		
	}
	
	@Given("^The office visit from (.*) includes a procedure with LOINC (.*), priority (.+), Lab Technician (.+), status (.*), numerical result: (.+), confidence interval: (.*), commentary (.*)$")
	public void checkOldOfficeVisit(String date, String code, int priority, int ltID, String status, int result, String confidence, String commentary) {
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// TODO not enough back end to test yet
	}
	
	
	@Given("^(.*) for (.*)'s office visit is (.*)$")
	public void detailsForOfficeVisit(String category, String date, String details) {
		
		if (category.equals("Location")){
			List<OfficeVisit> allOfficeVisits;
			try {
				allOfficeVisits = oVisSQL.getVisitsForPatient((long) 26);
				int found = 0;
				 
		 		   for (int i = 0; i < allOfficeVisits.size(); i++) {
		 			  if (allOfficeVisits.get(i).getDate().toString().contains(date)){
		 				 
		 				  if (allOfficeVisits.get(i).getLocationID().equals(details)){
		 				   //office visit has been found and therefore exists
		 				   Assert.assertTrue(true);
		 				   found = 1;
		 				   break;
		 				  }
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
		else if (category.equals("Appointment Type")){
			List<OfficeVisit> allOfficeVisits;
			try {
				allOfficeVisits = oVisSQL.getVisitsForPatient((long) 26);
				 int found = 0;
		 		   for (int i = 0; i < allOfficeVisits.size(); i++) {
		 			   if (allOfficeVisits.get(i).getDate().toString().contains(date)){
		 				  if (allOfficeVisits.get(i).getApptTypeID() == Long.parseLong(details)){
		 				   //office visit has been found and therefore exists
		 				   Assert.assertTrue(true);
		 				   found = 1;
		 				   break;
		 				  }
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
		else if (category.equals("Notes")){
			List<OfficeVisit> allOfficeVisits;
			try {
				allOfficeVisits = oVisSQL.getVisitsForPatient((long) 26);
				  int found = 0;
		 		   for (int i = 0; i < allOfficeVisits.size(); i++) {
		 			   if (allOfficeVisits.get(i).getDate().toString().contains(date)){
		 				  if (allOfficeVisits.get(i).getNotes().equals(details)){
		 				   //office visit has been found and therefore exists
		 				   Assert.assertTrue(true);
		 				   found = 1;
		 				   break;
		 				  }
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
	}
	
	@Given("^The office visit from (.*) includes a lab procedure with LOINC (.*), priority (.+), Lab Technician (.+), status (.*), and no other information$")
	public void checkOtherOldOfficeVisit(String date, String code, int priority, int ltID, String status ) {
		// TODO gotta inmplement this once loinc codes are up
	}
	
	@Given("^The office visit from (.*) has only (.+) lab procedures$")
	public void visitsContainLabProcs(String date, int count) {
		List<LabProcedure> allLabProcedures;
		try {
			//make sure that the only lab procedures are 2 for 50000000002 and none for 5000000003
			allLabProcedures = labPSQL.getAll();
			   int counter = 0;
	 		   for (int i = 0; i < allLabProcedures.size(); i++) {
	 			   
	 			   //if the office visit associated with this lab proc has the same date, incerment the counter
	 			   if (oVisSQL.getByID(allLabProcedures.get(i).getOfficeVisitID()).getDate().toString().contains(date)){
	 				  counter++; 
	 				  Assert.assertTrue(true);
	 			   }
	 		   }		   
	 		   if (counter != count) {
	 			   fail();
	 		   }
	 		   
		} catch (DBException e) {
			 fail();
			e.printStackTrace();
		}
		
	}
	
	@Given("^No other lab procedures are assigned to Cool Person$")
	public void noMoreLabsForCoolPerson() {
		fail();
	}
	
	@When("^Delete the Lab procedure with LOINC (.*)$")
	public void deleteLabProc(String code) {
		
		
		fail();
	}
	
	@When("^Update the lab procedure with LOINC (.*) by setting the Lab Technician to (.+)$")
	public void UpdateLabProc(String code, int ltID) {
		fail();
	}
	
	@When("^Create a new lab procedure with LOINC (.+)Component: (.*), System: (.*), priority (.+), lab technicial (.+)$")
	public void createNewLabProc(String code, String componenet, String system, int priority, int techID) {
		fail();
	}
	
	@When("^Examine the lab procedures from the office visit (.*)$")
	public void examineLabProc(String date) {
		//double check that they are still there 
		fail();
	}
	
	@When("^Examine the lists of lab procedures assigned and Update the two (.+) lab procedures to (.+)$")
	public void examineAndUpdateList(int previous, int update) {
		List<LabProcedure> allLabProcedures;
		try {
			allLabProcedures = labPSQL.getAll();
	 		   for (int i = 0; i < allLabProcedures.size(); i++) {
	 			   if (allLabProcedures.get(i).getStatus().getID() == previous){
	 				  allLabProcedures.get(i).setStatus(update);
	 			   }
	 		   }		  
		} catch (DBException e) {
			 fail();
			e.printStackTrace();
		}
	}
	
	
	@When("^Examine the list of received procedures$")
	public void examineList2() {
		fail();
	}
	
	@When("^Add numerical result: (.+) and confidence interval: (.*) to the procedure whose current status is (.*)$")
	public void addNumericalResult(int result, String confInterval, String status) {
		fail();
	}
	
	@When("^Update the procedures$")
	public void update() {
		fail();
	}
	
	@When("^Add commentary to the lab procedure (.*)$")
	public void addCommentary(String comment) {
		fail();
	}
	
	@Then("^When I view the lab procedures for an office visit as an HCP, I can view the previously created lab procedure including lab procedure code, current lab procedure status, the date the lab was assigned, and Lab Technician name$")
	public void canAccessFields() {
		//make sure all these fields can be accessed
		fail();
	}
	
	@Then("^When I edit the lab procedures for an office visit as an HCP, I can view the specialty for each Lab Technician and the number of pending lab procedures in his/her priority queue, grouped by priority$")
	public void canEditFields() {
		//make sure all these fields can be accessed
		fail();
	}
	
	@Then("^When I conclude the update, there is a message displayed at the top of the page: Information Successfully Updated.$")
	public void successMessage() {
		fail();
	}
	
	@Then("^I view the (.*) and it is(.*)$")
	public void checkFields(String category, String details) {
		fail();
	}
	
	@Then("^there (.*) a procedure with LOINC (.*), priority (.+), Lab Technician (.+)$")
	public void thereIsAProcedure(String isIsnt, String code, String priority, String labTech) {
		fail();
	}
	
	@Then("^no other info is stored other than logging data$")
	public void noOtherInfo() {
		fail();
	}
	
	@Then("^When I view the office visits, they should include the visit from (.*)$")
	public void viewAndInclude(String Date) {
		fail();
	}
	
	@Then("^When I view the office visit from (.*), there should be (.+) lab procedure$")
	public void viewWithProcs(String date, int count) {
		fail();
	}
	
	@Then("^When I examine the lab procedures from the office visit (.*) there is a procedure with LOINC (.*), priority (.+), Lab Technician (.+), status (.*), numerical result: (.+), confidence interval: (.*), commentary (.*)$")
	public void examineLabProc(String date, String code, int priority, int labTech, String status, String result, String interval, String comments) {
		fail();
	}
	
	@Then("^When I examine the lab procedures from the office visit (.*) there is a procedure with LOINC (.*), Lab Technician (.+), status (.*), and no other information$")
	public void examineProcNoOtherInfo(String date, String code, int labTech, String status) {
		fail();
	}
	
	@Then("^When I first access and examine the lab procedures, the (.*) queue should contain the correct lab procedure ID, lab procedure code, status, priority, HCP name, and the date the lab was assigned for the procedures with LOINC (.*) and (.*)$")
	public void correctInfo(String qTpe, String code1, String code2) {
		fail();
	}
	
	@Then("^After I update the two intransit lab procedures to received, when I examine the (.*) queue the order should be: the procedure with LOINC (.*) first, the procedure with LOINC (.*) second, the procedure with LOINC (.*) third, and the procedure with LOINC (.*) fourth$")
	public void afterUpdate(String qType, String code1, String code2, String code3, String code4) {
		fail();
	}
	      
	@Then("^When I first access and examine the lab procedures, the (.*) queue should contain the procedure with LOINC (.*) first and the procedure with LOINC (.*) second$")
	public void firstAccess(String qType, String code1, String code2) {
		fail();
	}
	
	@Then("^After I add results to the lab procedure with LOINC (.*) and update the procedure, its status should change to pending and it should no longer appear in the lab technician's queue$")
	public void afterAddResults(String code) {
		fail();
	}
	
	@Then("^After the lab procedure with LOINC ().* has been updated, the procedure with LOINC (.*) should now have status testing and be first in the received queue$")
	public void afterMoreUpdate(String code1, String code2) {
		fail();
	}
	
	@Then("^After the lab procedure with LOINC (.*) has been updated, the procedure with LOINC (.*) should be second in the received queue, and the procedure with LOINC (.*) should be third$")
	public void finalUpdate(String code1, String code2, String code3) {
		fail();
	}
	
	@Then("^its status should change to completed$")
	public void statusCompleted() {
		fail();
	}
	
	@Then("^an error message is displayed$")
	public void errorMessage() {
		fail();
	}
	
	@Then("^After I add an invalid results to the lab procedure with LOINC (.*) and attempt to update the procedure, it should not successfully update.$")
	public void failedToAdd(String code) {
		fail();
	}
	
	@Then("^the lab procedure is not created$")
	public void failNotCreatedMessage() {
		fail();
	}
	
}
