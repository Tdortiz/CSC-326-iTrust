package edu.ncsu.csc.itrust.controller.labProcedure;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.controller.NavigationController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "lab_procedure_form")
@ViewScoped
public class LabProcedureForm {
	private LabProcedureController controller;
	private LabProcedure labProcedure;

	public LabProcedureForm() {
		this(null);
	}
	
	public LabProcedureForm(LabProcedureController ovc) {
		try {
			controller = (ovc == null) ? new LabProcedureController() : ovc;
			labProcedure = getSelectedLabProcedure();
			if (labProcedure == null) {
				labProcedure = new LabProcedure();
				Long ovid = new SessionUtils().getCurrentOfficeVisitId();
				labProcedure.setOfficeVisitID(ovid);
				labProcedure.setStatus(LabProcedureStatus.IN_TRANSIT.getID());
			}
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lab Procedure Controller Error",
					"Lab Procedure Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	
	/**
	 * @return HTTPRequest in FacesContext, null if no request is found
	 */
	public HttpServletRequest getHttpServletRequest() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return null;
		}
		return ctx.getExternalContext().getRequest() instanceof HttpServletRequest ? (HttpServletRequest) ctx.getExternalContext().getRequest() : null;
	}

	
	public LabProcedure getSelectedLabProcedure(){
		String id = "";
		HttpServletRequest req = getHttpServletRequest();
		
		if (req == null) {
			return null;
		}
		
		id = req.getParameter("id");
		if (id == null) {
			return null;
		}
	
		return controller.getLabProcedureByID(id);
	}
	
	public void submitNewLabProcedure() {
		controller.add(labProcedure);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		try {
			NavigationController.officeVisitInfo();
		} catch (IOException e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to redirect",
					"Unable to redirect");
			context.addMessage(null, throwMsg);
		}
	}
	
	public void removeLabProcedure(Long id) {
		controller.remove(id.toString());
	}
	
	/**
	 * Called when user clicks on the submit button in ReassignTechnician.xhtml. Takes data from form
	 * and sends to LabProcedureControllerLLoader.java for storage and validation
	 */
	public void submitReassignment() {		
		controller.edit(labProcedure);
	}
	
	public boolean isLabProcedureCreated() {
		Long labProcedureID = labProcedure.getLabProcedureID();
		return labProcedureID != null && labProcedureID > 0;
	}
	
	public boolean isReassignable(String idStr) {
		try {
			Long.parseLong(idStr);
		} catch (NumberFormatException e) {
			return false;
		}

		LabProcedure proc = controller.getLabProcedureByID(idStr);
		
		LabProcedureStatus status = proc.getStatus();
		
		return status == LabProcedureStatus.IN_TRANSIT ||
				status == LabProcedureStatus.PENDING ||
				status == LabProcedureStatus.RECEIVED;
	}
	
	public boolean isRemovable(String idStr) {
		try {
			Long.parseLong(idStr);
		} catch (NumberFormatException e) {
			return false;
		}

		LabProcedure proc = controller.getLabProcedureByID(idStr);
		
		LabProcedureStatus status = proc.getStatus();
		
		return status == LabProcedureStatus.IN_TRANSIT ||
				status == LabProcedureStatus.RECEIVED;
	}
	
	/**
	 * Upon recording results the lab procedure is 
	 * updated to pending and  results are submitted
	 */
	public void recordResults() {
		try {
			controller.recordResults(labProcedure);
		} catch (DBException e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lab Procedure Controller Error",
					"Lab Procedure Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	
	public void updateToReceived(String labProcedureID){
		controller.setLabProcedureToReceivedStatus(labProcedureID);
	}
	
	public LabProcedure getLabProcedure() {
		return labProcedure;
	}
}
