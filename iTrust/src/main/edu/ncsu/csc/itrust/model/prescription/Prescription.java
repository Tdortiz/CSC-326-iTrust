package edu.ncsu.csc.itrust.model.prescription;

import java.time.LocalDate;

public class Prescription {
    private long patientMID;
    private String drugCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private long officeVisitId;
    
    public long getPatientMID() {
        return patientMID;
    }
    public void setPatientMID(long patientMID) {
        this.patientMID = patientMID;
    }
    public String getDrugCode() {
        return drugCode;
    }
    public void setDrugCode(String drugCode) {
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
}
