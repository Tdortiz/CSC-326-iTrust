package edu.ncsu.csc.itrust.model.prescription;

import java.time.LocalDateTime;

public class Prescription {
    private long patientMID;
    private String drugCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public long getOfficeVisitId() {
        return officeVisitId;
    }
    public void setOfficeVisitId(long officeVisitId) {
        this.officeVisitId = officeVisitId;
    }
}
