package edu.ncsu.csc.itrust.model.immunization;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * Validates instance of Immunization.
 */
public class ImmunizationValidator extends POJOValidator<Immunization> {
	
	/** Data source for retrieving from database. */
	private DataSource ds;

	/**
	 * Constructor for ImmunizationValidator. 
	 */
	public ImmunizationValidator(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Immunization obj) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		String code = obj.getCptCode().getCode();
		String name = obj.getCptCode().getName();
		
		if( code.length() == 0 || code.length() > 5 || !code.contains("[0-9]+") )
			errorList.addIfNotNull("Invalid code: code are 5 digit numbers");
	
		if( name.length() == 0 || name.length() > 30 || !name.contains("[a-zA-Z]+") )
			errorList.addIfNotNull("Invalid name length : the name should be up to 30 alpha characters");
	}
	
}
