package edu.ncsu.csc.itrust.model.diagnosis;

import java.sql.SQLException;
import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

public interface DiagnosisData extends DataBean<Diagnosis> {
	/**
	 * Gets a list of Diagnosis that are either chronic diagnoses or short term 
	 * diagnoses made within the last 30 days. 
	 * 
	 * @param mid
	 * 		patient MID
	 * @return a list of diagnosis shown for emergency reporting
	 * @throws DBException when error occurs when accessing database
	 */
	public List<Diagnosis> getAllEmergencyDiagnosis(long mid) throws SQLException;
}
