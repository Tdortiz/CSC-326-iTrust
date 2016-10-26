package edu.ncsu.csc.itrust.model.cptcode;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

public class CPTCodeValidator extends POJOValidator<CPTCode> {
	
	/** Data source for retrieving from database. */
	@SuppressWarnings("unused")
	private DataSource ds;

	/**
	 * Constructor for ImmunizationValidator. 
	 */
	public CPTCodeValidator(DataSource ds) {
		this.ds = ds;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(CPTCode obj) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		String code = obj.getCode();
		String name = obj.getName();
		
		if( code.length() == 0 || code.length() > 5 || !code.contains("[0-9]+") )
			errorList.addIfNotNull("Invalid code: code are 5 digit numbers");
	
		if( name.length() == 0 || name.length() > 30 || !name.contains("[a-zA-Z]+") )
			errorList.addIfNotNull("Invalid name length : the name should be up to 30 alpha characters");
	}
}
