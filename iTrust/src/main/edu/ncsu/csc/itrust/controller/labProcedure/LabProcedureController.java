package edu.ncsu.csc.itrust.controller.labProcedure;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureData;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureMySQL;


public class LabProcedureController {

	private static final String INVALID_LAB_PROCEDURE = "Invalid lab procedure";
	private LabProcedureData labProcedureData;

	public LabProcedureController() {
		try {
			labProcedureData = new LabProcedureMySQL();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor injection, intended only for unit testing purposes.
	 * 
	 * @param ds
	 *            The injected DataSource dependency
	 */
	public LabProcedureController(DataSource ds) {
		labProcedureData = new LabProcedureMySQL(ds);
	}

	public List<LabProcedure> getLabProceduresByLabTechnician(String technicianID) throws DBException {
		List<LabProcedure> procedures = Collections.emptyList();
		long mid = -1;
		if ((technicianID != null) && ValidationFormat.NPMID.getRegex().matcher(technicianID).matches()) {
			mid = Long.parseLong(technicianID);
			try {
				procedures = labProcedureData.getLabProceduresForLabTechnician(mid).stream().sorted((o1, o2) -> {
					return (o1.getPriority() == o2.getPriority()) ? o1.getUpdatedDate().compareTo(o2.getUpdatedDate())
							: o1.getPriority() - o2.getPriority();
				}).collect(Collectors.toList());
			} catch (Exception e) {
				printFacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Lab Procedures",
						"Unable to Retrieve Lab Procedures", null);
			}
		}
		return procedures;
	}

	public List<LabProcedure> getPendingLabProcedures(String technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().name().equals(LabProcedureStatus.PENDING.name());
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getInTransitLabProcedures(String technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().name().equals(LabProcedureStatus.IN_TRANSIT.name());
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getReceivedLabProcedures(String technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().name().equals(LabProcedureStatus.RECEIVED.name());
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getTestingLabProcedures(String technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().name().equals(LabProcedureStatus.TESTING.name());
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getCompletedLabProcedures(String technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().name().equals(LabProcedureStatus.COMPLETED.name());
		}).collect(Collectors.toList());
	}
	
	/**
	 * Adds a lab procedure.
	 * @param procedure The lab procedure to add
	 */
	public void add(LabProcedure procedure) {
		boolean successfullyAdded = false;
		try {
			successfullyAdded = labProcedureData.add(procedure);
		} catch (DBException e) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_LAB_PROCEDURE, e.getExtendedMessage(), null);
		} catch (Exception e) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_LAB_PROCEDURE, INVALID_LAB_PROCEDURE, null);
		}
		if(successfullyAdded){
			printFacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated", null);
		}
	}
	
	/**
	 * Updates a lab procedure.
	 * @param procedure The lab procedure to update
	 */
	public void edit(LabProcedure procedure) {
		boolean successfullyUpdated = false;
		
		try {
			successfullyUpdated = labProcedureData.update(procedure);
		} catch (DBException e) {
	      	printFacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_LAB_PROCEDURE, e.getExtendedMessage(), null);
		} catch (Exception e) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_LAB_PROCEDURE, INVALID_LAB_PROCEDURE, null);
		}
		if(successfullyUpdated){
			printFacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated", null);
		}
	}

	/**
	 * Sends a FacesMessage for FacesContext to display.
	 * 
	 * @param severity
	 *            severity of the message
	 * @param summary
	 *            localized summary message text
	 * @param detail
	 *            localized detail message text
	 * @param clientId
	 *            The client identifier with which this message is associated
	 *            (if any)
	 */
	public void printFacesMessage(Severity severity, String summary, String detail, String clientId) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return;
		}
		ctx.getExternalContext().getFlash().setKeepMessages(true);
		ctx.addMessage(clientId, new FacesMessage(severity, summary, detail));
	}
}
