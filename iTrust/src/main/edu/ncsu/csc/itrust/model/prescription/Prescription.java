package edu.ncsu.csc.itrust.model.prescription;

import java.time.LocalDate;

import edu.ncsu.csc.itrust.model.old.beans.MedicationBean;

public class Prescription {
    private long patientMID;
    private MedicationBean drugCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private long officeVisitId;
    private long hcpMID;
	private String description;
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private long id;
    
    public long getPatientMID() {
        return patientMID;
    }
    public void setPatientMID(long patientMID) {
        this.patientMID = patientMID;
    }
    public long getHcpMID() {
		return hcpMID;
	}
	public void setHcpMID(long hcpMID) {
		this.hcpMID = hcpMID;
	}
    public MedicationBean getDrugCode() {
        return drugCode;
    }
    public void setDrugCode(MedicationBean drugCode) {
        this.drugCode = drugCode;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public long getOfficeVisitId() {
        return officeVisitId;
    }
    public void setOfficeVisitId(long officeVisitId) {
        this.officeVisitId = officeVisitId;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCode() {
    	return getDrugCode().getNDCode();
    }
    public String getName() {
    	return getDrugCode().getDescription();
    }
}
