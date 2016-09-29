package edu.ncsu.csc.itrust.model.officeVisit;

import java.time.LocalDate;
import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

public interface OfficeVisitData extends DataBean<OfficeVisit>{
	public List<OfficeVisit> getVisitsForPatient(Long patientID) throws DBException;
	public LocalDate getPatientDOB(Long patientID);
}
