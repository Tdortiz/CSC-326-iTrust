package edu.ncsu.csc.itrust.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.fail;

public class ManageLabProcedureStepDefs {

	public ManageLabProcedureStepDefs() {
		
	}
	
	@Given("^Patient (.+) has an office visit with a date of (.*), a Location of (.*), Appointment type (.*), and Notes: (.*)$")
	public void officeVisitExists(int mid, String date, String location, String appType, String notes) {
		fail();
	}
	
	@Given("^(.*) office visit has a Lab Procedure listed with Code (.*) with a current status of (.*), priority (.+), and assigned Lab Technician (.+)$")
	public void checkForLabProc(String date, String code, String status, int priority, int mid) {
		fail();
	}
	
	@Given("^Neither Lab Technician 5000000002 or Lab Technician 5000000003 has additional proccedures assigned$")
	public void nothingElseAssigned() {
		fail();
	}
	
	@Given("^The office visit from (.*) includes a procedure with LOINC (.*), priority (.+), Lab Technician (.+), status (.*), numerical result: (.+), confidence interval: (.*), commentary (.*)$")
	public void checkOldOfficeVisit(String date, String code, int priority, int ltID, String status, int result, String confidence, String commentary) {
		fail();
	}
	
	@Given("^No other information is added to the office visit from (.*)$")
	public void noOtherInfo(String date) {
		fail();
	}
	
	@Given("^The office visit from (.*) includes a lab procedure with LOINC (.*), priority (.+), Lab Technician (.+), status (.*), and no other information$")
	public void checkOtherOldOfficeVisit(String date, String code, int priority, int ltID, String status ) {
		fail();
	}
	
	@Given("^The office visit from (.*) has only (.+) lab procedures$")
	public void visitsContainLabProcs(String date, int count) {
		fail();
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
	
	@When("^Go to the (.*) functionality$")
	public void viewFunctionality(String type) {
		fail();
	}
	
	@When("^Examine the lab procedures from the office visit (.*)$")
	public void examineLabProc(String date) {
		fail();
	}
	
	@When("^Examine the lists of lab procedures assigned$")
	public void examineList() {
		fail();
	}
	
	@When("^Update the two in transit lab procedures to received$")
	public void updateTransitLabProc() {
		fail();
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
	
	@Then("^the (.*) is (.*)$")
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
	
}
