package edu.ncsu.csc.itrust.model.labProcedure;

import java.sql.Timestamp;

public class LabProcedure {

	private Long labProcedureID;
	private Long labTechnicianID;
	private Long officeVisitID;
	private Integer priority;
	private String rights;
	private String status;
	private String commentary;
	private String results;
	private Timestamp updatedDate;
	
	public LabProcedure() {}

	public Long getLabProcedureID() {
		return labProcedureID;
	}
	public void setLabProcedureID(Long labProcedureID) {
		this.labProcedureID = labProcedureID;
	}
	public Long getLabTechnicianID() {
		return labTechnicianID;
	}
	public void setLabTechnicianID(Long labTechnicianID) {
		this.labTechnicianID = labTechnicianID;
	}
	public Long getOfficeVisitID() {
		return officeVisitID;
	}
	public void setOfficeVisitID(Long officeVisitID) {
		this.officeVisitID = officeVisitID;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}
