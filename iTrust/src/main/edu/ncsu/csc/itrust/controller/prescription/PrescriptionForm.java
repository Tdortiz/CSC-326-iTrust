package edu.ncsu.csc.itrust.controller.prescription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.ncsu.csc.itrust.controller.labProcedure.LabProcedureController;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;
import edu.ncsu.csc.itrust.model.loinccode.LOINCCodeData;
import edu.ncsu.csc.itrust.model.loinccode.LOINCCodeMySQL;
import edu.ncsu.csc.itrust.model.prescription.Prescription;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "prescription_form")
@ViewScoped
public class PrescriptionForm {
	
	private String viewingReportForPID;
	private Prescription prescription;
	private PrescriptionController prescriptionController;
	private SessionUtils sessionUtils;
	
	public PrescriptionForm(){
		
	}
	
	public PrescriptionForm(PrescriptionController controller , SessionUtils sessionUtils) {
		
	}
	
	public List<Prescription> getPrescriptionsForCurrentPatient(){
    	return prescriptionController.getPrescriptionsForCurrentPatient();
    }
	
	public List<Prescription> getPrescriptionsByPatientID(String patientID){
		return prescriptionController.getPrescriptionsByPatientID(patientID);
	}
	
	public List<Object> getListOfRepresentees(){
		return prescriptionController.getListOfRepresentees();
	}

	public String getViewingReportForPID() {
		return viewingReportForPID;
	}

	public void setViewingReportForPID(String viewingReportForPID) {
		this.viewingReportForPID = viewingReportForPID;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
}
