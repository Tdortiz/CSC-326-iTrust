package edu.ncsu.csc.itrust.controller.labProcedure;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;

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
			}
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lab Procedure Controller Error",
					"Lab Procedure Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	
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
	
	/**
	 * Upon recording results the lab procedure is 
	 * updated to pending and  results are submitted
	 */
	public void recordResults() {
		labProcedure.setStatus("Pending");
		controller.edit(labProcedure);
	}
	
	public void updateToReceived(String labProcedureID){
		controller.setLabProcedureToReceivedStatus(labProcedureID);
	}
	
	public LabProcedure getLabProcedure() {
		return labProcedure;
	}
	
}