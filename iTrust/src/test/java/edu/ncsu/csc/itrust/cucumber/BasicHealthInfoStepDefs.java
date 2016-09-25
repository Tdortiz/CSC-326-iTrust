package edu.ncsu.csc.itrust.cucumber;

import java.util.Date;

import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.cucumber.util.SharedPatient;
import edu.ncsu.csc.itrust.cucumber.util.SharedPersonnel;
import edu.ncsu.csc.itrust.model.old.enums.Role;

public class BasicHealthInfoStepDefs {
	
	private SharedPersonnel sharedPersonnel;
	private SharedPatient sharedPatient;
	
	public BasicHealthInfoStepDefs(SharedPersonnel sharedDAO) throws Exception {
		this.sharedPersonnel = sharedDAO;
	}
	
	@Given("^(?:.*) is an HCP with MID: (\\d+)$")
	public void shelly_Vang_is_an_HCP_with_MID(long MID) throws Throwable {
	    Assert.assertEquals(Role.HCP, this.sharedPersonnel.getPersonnelDAO().getPersonnel(MID).getRole());
	}

	@Given("^(?:.*) is a patient with MID (\\d+) who is born on (\\S+)$") 
	public void brynn_McClain_is_a_patient_with_MID_who_is_months_old(long MID, Date birthday) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^(?:.*) logs in with MID: (\\d+)")
	public void shelly_Vang_logs_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Chooses to document an office visit$")
	public void chooses_to_document_an_office_visit() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^selects patient (?:.*) with MID (\\d+)$")
	public void selects_patient_Brynn_McClain_with_MID(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Choose appointment type General checkup$")
	public void choose_appointment_type_General_checkup() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Chooses the date to be (\\S+)$")
	public void chooses_the_date_to_be(Date date) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Select Central Hospital for location$")
	public void select_Central_Hospital_for_location() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^enters Note “(.+)”$")
	public void enters_Notes_Brynn_can_start_eating_rice_cereal_mixed_with_breast_milk_or_formula_once_a_day() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Creates a Basic Health History$")
	public void creates_a_Basic_Health_History() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Enters (\\S+) for weight$")
	public void enters_for_weight(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Enters (\\S+) for length$")
	public void enters_for_length(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Enters (\\S+) for Head Circumference$")
	public void enters_for_Head_Circumference(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^selects (\\d+) for non-smoking for household smoking status$")
	public void selects_for_non_smoking_for_household_smoking_status(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^submits record$")
	public void submits_record() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^a success message is displayed$")
	public void a_success_message_is_displayed() throws Throwable {
	    Assert.fail();
		// Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^selects (\\d+) for indoors smokers for household smoking status$")
	public void selects_for_indoors_smokers_for_household_smoking_status(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Enters (\\S+) for height$")
	public void enters_for_height(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Enters (\\S+) for blood pressure$")
	public void enters_for_blood_pressure(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^selects (\\d+) for outdoor smokers for household smoking status$")
	public void selects_for_outdoor_smokers_for_household_smoking_status(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


	@When("^selects (\\d+) for former smoker for patient smoking status$")
	public void selects_for_former_smoker_for_patient_smoking_status(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^enters (\\w+) for HDL$")
	public void enters_for_HDL(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^enters (\\d+) for LDL$")
	public void enters_for_LDL(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^enters (\\d+) for Triglycerides$")
	public void enters_for_Triglycerides(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^selects (\\d+) for never smoked for patient smoking status$")
	public void selects_for_never_smoked_for_patient_smoking_status(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^selects (\\d+) for new smoked for patient smoking status$")
	public void selects_for_new_smoked_for_patient_smoking_status(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^an error message is displayed$")
	public void an_error_message_is_displayed() throws Throwable {
		Assert.fail();
		// Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^height field should be hidden$")
	public void height_field_should_be_hidden() throws Throwable {
		Assert.fail();
		// Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^height field should be visible$")
	public void height_field_should_be_visible() throws Throwable {
		Assert.fail();
		// Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
