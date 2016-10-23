package edu.ncsu.csc.itrust.model.labProcedure;

import java.sql.Timestamp;

public class LabProcedure {

	private Integer labProcedureID;
	private Integer labTechnicianID;
	private Integer officeVisitID;
	private Integer priority;
	private String rights;
	private String status;
	private String commentary;
	private String results;
	private Timestamp updatedDate;
	
	public LabProcedure() {}

	public Integer getLabProcedureID() {
		return labProcedureID;
	}
	public void setLabProcedureID(Integer labProcedureID) {
		this.labProcedureID = labProcedureID;
	}
	public Integer getLabTechnicianID() {
		return labTechnicianID;
	}
	public void setLabTechnicianID(Integer labTechnicianID) {
		this.labTechnicianID = labTechnicianID;
	}
	public Integer getOfficeVisitID() {
		return officeVisitID;
	}
	public void setOfficeVisitID(Integer officeVisitID) {
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
