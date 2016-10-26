package edu.ncsu.csc.itrust.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.fail;


public class EmergencyHealthRecordsStepDefs {

	
	public EmergencyHealthRecordsStepDefs(){
		
	}

	@Given("^I load uc21.sql$")
	public void maleFemale(int pid, String maleFemale){
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
