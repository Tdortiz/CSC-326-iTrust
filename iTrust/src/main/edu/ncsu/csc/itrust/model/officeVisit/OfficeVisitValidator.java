package edu.ncsu.csc.itrust.model.officeVisit;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;
import edu.ncsu.csc.itrust.model.ValidationFormat;

public class OfficeVisitValidator extends POJOValidator<OfficeVisit> {

	
	/**
	 * Used to Validate an office visit object. If the validation does not succeed, a {@link FormValidationException} is thrown.
	 * only performs checks on the values stored in the object (e.g. Patient MID)
	 * Does NOT validate the format of the visit date and other attributes that
	 * are NOT stored in the object itself
	 * 
	 * @param obj the Office Visit to be validated
	 */
	@Override
	public void validate(OfficeVisit obj) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		errorList.addIfNotNull(checkFormat("Patient MID", obj.getPatientMID(), ValidationFormat.NPMID, false));
		errorList.addIfNotNull(checkFormat("Location ID", obj.getLocationID(), ValidationFormat.HOSPITAL_ID, false));
		if(obj.getVisitID() <=0){
			errorList.addIfNotNull("Incorrect Visit ID");
		}
		if (errorList.hasErrors())
			throw new FormValidationException(errorList);
		
	}

}
