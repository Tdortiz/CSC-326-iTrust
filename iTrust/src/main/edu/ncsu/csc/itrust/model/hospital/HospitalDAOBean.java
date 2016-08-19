package edu.ncsu.csc.itrust.model.hospital;

import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.hospital.Hospital;

public interface HospitalDAOBean {
	public List<Hospital> getAllHospitals() throws DBException;
	public String getHospitalName(String id) throws DBException;
}
