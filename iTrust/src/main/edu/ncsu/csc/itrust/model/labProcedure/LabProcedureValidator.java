package edu.ncsu.csc.itrust.model.labProcedure;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

public class LabProcedureValidator extends POJOValidator<LabProcedure> {

	private DataSource ds;

	public LabProcedureValidator() {

	}

	public LabProcedureValidator(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void validate(LabProcedure obj) throws FormValidationException {
		// TODO Auto-generated method stub
		return;
	}

}
