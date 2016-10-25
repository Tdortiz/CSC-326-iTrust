package edu.ncsu.csc.itrust.model.diagnosis;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * Validates instance of Diagnosis.
 */
public class DiagnosisValidator extends POJOValidator<Diagnosis> {
	/**
	 * Data source for retrieving from database.
	 */
	private DataSource ds;

	/**
	 * Constructor for DiagnosisValidator. 
	 */
	public DiagnosisValidator(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Diagnosis obj) throws FormValidationException {
		// TODO Auto-generated method stub	
	}
}
