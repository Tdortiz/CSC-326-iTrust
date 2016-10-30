package edu.ncsu.csc.itrust.model.labProcedure;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;

public class LabProcedureValidator extends POJOValidator<LabProcedure> {

	private DataSource ds;

	public LabProcedureValidator() {

	}

	public LabProcedureValidator(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void validate(LabProcedure proc) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		// Commentary
		errorList.addIfNotNull(checkFormat("Commentary", proc.getCommentary(), 
				ValidationFormat.COMMENTS, true));
		
		// Confidence interval lower
		if(proc.getConfidenceIntervalLower() != null) {
			if(proc.getConfidenceIntervalLower() < 0 || proc.getConfidenceIntervalLower() > 100) {
				errorList.addIfNotNull("Confidence Interval Lower: Confidence interval lower is invalid");
			}
		} else {
			errorList.addIfNotNull("Confidence Interval Lower: Confidence interval lower cannot be null");
		}
		
		// Confidence interval upper
		if(proc.getConfidenceIntervalUpper() != null) {
			if(proc.getConfidenceIntervalUpper() < 0 || proc.getConfidenceIntervalUpper() > 100) {
				errorList.addIfNotNull("Confidence Interval Upper: Confidence interval upper is invalid");
			}
		} else {
			errorList.addIfNotNull("Confidence Interval Upper: Confidence interval upper cannot be null");
		}
		
		// Lab procedure code (LOINC code)
		errorList.addIfNotNull(checkFormat("Lab procedure code", proc.getLabProcedureCode(),
				ValidationFormat.LOINC, false));
		
		// Lab procedure ID
		if (proc.getLabProcedureID() != null) {
			if (proc.getLabProcedureID() <= 0) {
				errorList.addIfNotNull("Lab Procedure ID: Invalid Lab Procedure ID");
			}
		} else {
			errorList.addIfNotNull("Lab Procedure ID: Invalid Lab Procedure ID");
		}
		
		// Office visit ID
		if (proc.getOfficeVisitID() != null) {
			if (proc.getOfficeVisitID() <= 0) {
				errorList.addIfNotNull("Office Visit ID: Invalid Office Visit ID");
			}
		} else {
			errorList.addIfNotNull("Office Visit ID: Invalid Office Visit ID");
		}
		
		// Lab technician ID
		if (proc.getLabTechnicianID() != null) {
			if (proc.getLabTechnicianID() <= 0) {
				errorList.addIfNotNull("Lab Technician ID: Invalid Lab Technician ID");
			}
		} else {
			errorList.addIfNotNull("Lab Technician ID: Invalid Lab Technician ID");
		}
		
		// Priority
		if(proc.getPriority() == null || proc.getPriority() < 1 || proc.getPriority() > 3) {
			errorList.addIfNotNull("Priority: invalid priority (null or out of bounds");
		}
		
		// Results
		errorList.addIfNotNull(checkFormat("Results", proc.getResults(), 
				ValidationFormat.COMMENTS, true));
		
		// Status
		boolean statusIsValid = false;
		LabProcedureStatus statusToValidate = proc.getStatus();
		if(statusToValidate != null) {
			for(LabProcedureStatus status : LabProcedureStatus.values()) {
				if(status.getID() == statusToValidate.getID()) {
					statusIsValid = true;
				}
			}
		}
		if(!statusIsValid) {
			errorList.addIfNotNull("Status: Invalid status");
		}
		
		// Updated date
		if(proc.getUpdatedDate() == null) {
			errorList.addIfNotNull("Updated date: null updated date");
		}
		
		if (errorList.hasErrors()) {
			throw new FormValidationException(errorList);
		}
	}

}
