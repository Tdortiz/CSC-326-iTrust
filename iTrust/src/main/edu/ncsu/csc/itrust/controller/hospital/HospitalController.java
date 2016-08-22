package edu.ncsu.csc.itrust.controller.hospital;

import java.util.List;

import javax.faces.bean.ManagedBean;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.hospital.Hospital;
import edu.ncsu.csc.itrust.model.hospital.HospitalData;
import edu.ncsu.csc.itrust.model.hospital.HospitalMySQLConverter;

@ManagedBean(name="hospital_controller")
public class HospitalController {
	public static HospitalData hospitalData;
	public HospitalController() throws DBException{
		if(hospitalData == null){
			HospitalController.hospitalData = new HospitalMySQLConverter();
			
		}
		
	}
	public List<Hospital> getHospitalList() throws DBException{
		return hospitalData.getAll();
	}

}
