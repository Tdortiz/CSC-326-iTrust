package edu.ncsu.csc.itrust.model.apptType;

import java.util.List;
import java.util.Map;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.apptType.ApptType;

public interface ApptTypeDataBean {
	List<ApptType> getAppointmentTypes() throws DBException;
	Map<Long, ApptType> getApptTypeIDs(String name) throws DBException;
	String getApptTypeName(Long id) throws DBException;
}
