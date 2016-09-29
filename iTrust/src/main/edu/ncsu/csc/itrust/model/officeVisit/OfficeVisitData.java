package edu.ncsu.csc.itrust.model.officeVisit;

import java.time.LocalDate;
import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

public interface OfficeVisitData extends DataBean<OfficeVisit>{
	public List<OfficeVisit> getVisitsForPatient(Long patientID) throws DBException;
	
	/**
	 * Retrieves the patient's date of birth from database.
	 * 
	 * @param patientMID
	 * 			MID of the patient
	 * @return patient's date of birth
	 */
	public LocalDate getPatientDOB(Long patientID);
}
