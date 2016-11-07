package edu.ncsu.csc.itrust.model.prescriptionRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.controller.prescription.PrescriptionController;
import edu.ncsu.csc.itrust.model.prescription.Prescription;


public class PrescriptionRecord {
	
	private Prescription prescription;
	private PrescriptionController prescriptionController;

	
	
	public List<Prescription> getPrescriptionsForCurrentPatient(){
    	return prescriptionController.getPrescriptionsForCurrentPatient();
    }
	
	public List<Prescription> getPrescriptionsByPatientID(String patientID){
		return prescriptionController.getPrescriptionsByPatientID(patientID);
	}
	
	public List<Object> getListOfRepresentees(String loggedInID){
		return prescriptionController.getListOfRepresentees(loggedInID);
	}
	
}
