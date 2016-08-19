package edu.ncsu.csc.itrust.model.officeVisit;

import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

public interface OfficeVisitDAO extends DataBean<OfficeVisit>{
	public List<OfficeVisit> getVisitsForPatient(Long patientID) throws DBException;


}
