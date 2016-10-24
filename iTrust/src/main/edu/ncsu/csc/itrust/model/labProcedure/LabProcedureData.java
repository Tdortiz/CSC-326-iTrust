package edu.ncsu.csc.itrust.model.labProcedure;

import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

public interface LabProcedureData extends DataBean<LabProcedure> {
	
	List<LabProcedure> getLabProceduresForLabTechnician(Long technicianID) throws DBException;
}
