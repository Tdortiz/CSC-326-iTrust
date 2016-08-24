package edu.ncsu.csc.itrust.controller.officeVisit;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitDAO;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;

@ManagedBean(name="office_visit_controller")
@SessionScoped
public class OfficeVisitController {
	private OfficeVisitDAO officeVisitData;
	public OfficeVisitController() throws DBException{
		
		officeVisitData = new OfficeVisitMySQL();
			
	}
	
	public void add(OfficeVisit ov) {
		long pid = -1;
		FacesContext ctx = FacesContext.getCurrentInstance();
		String patientID = "";
		boolean res = false;
		if(ctx.getExternalContext().getRequest() instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
			HttpSession httpSession = req.getSession(false);
			patientID = (String) httpSession.getAttribute("pid");
		}
		if(ValidationFormat.NPMID.getRegex().matcher(patientID).matches()){
			pid = Long.parseLong(patientID);
		}
		ov.setPatientMID(pid);
		
		try {
			res = officeVisitData.add(ov);
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Office Visit", "Invalid Office Visit");
	        ctx.addMessage(null,throwMsg);
		}
		if(res){
	      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated");
	        ctx.addMessage(null,successMsg);
	        
			
		}
	}
	
	public List<OfficeVisit> getOfficeVisitsForPatient(String pid){
		
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		List<OfficeVisit> ret = null;
		long mid = -1;
		if(ValidationFormat.NPMID.getRegex().matcher(pid).matches()){
			mid = Long.parseLong(pid);
			try {
				ret = officeVisitData.getVisitsForPatient(mid);
			} catch (Exception e) {
		      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Office Visits", "Unable to Retrieve Office Visits");
		        ctx.addMessage(null,throwMsg);
			}
		}
		return ret;
	}
	
	public List<OfficeVisit> getOfficeVisitsForCurrentPatient(){
		long pid = -1;
		FacesContext ctx = FacesContext.getCurrentInstance();
		String patientID = "";
				
		if(ctx.getExternalContext().getRequest() instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
			HttpSession httpSession = req.getSession(false);
			patientID = (String) httpSession.getAttribute("pid");
		}
		if((patientID != null) && (ValidationFormat.NPMID.getRegex().matcher(patientID).matches())){
			pid = Long.parseLong(patientID);
			return getOfficeVisitsForPatient(Long.toString(pid));
		}
		return null;

	}
	
	public void addNewVisit(){
		
	}
	
	public OfficeVisit getVisitByID(String VisitID){
		FacesContext ctx = FacesContext.getCurrentInstance();
		long id = -1;
		try{
			id = Long.parseLong(VisitID);
		}
		catch(NumberFormatException ne){
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Office Visit", "Unable to Retrieve Office Visit");
	        ctx.addMessage(null,throwMsg);
			return null;
		}
		try {
			return officeVisitData.getByID(id);
		} catch (DBException e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Office Visit", "Unable to Retrieve Office Visit");
	        ctx.addMessage(null,throwMsg);
	        return null;
		}
	}
	
	public OfficeVisit getSelectedVisit(){
		String visitID = "";
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(ctx.getExternalContext().getRequest() instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
			HttpSession httpSession = req.getSession(false);
			visitID = req.getParameter("visitID");
			//patientID = (String) httpSession.getAttribute("pid");
		}
		//Map <String, String> temp = ctx.getExternalContext().getRequestParameterMap();
		//visitID = ctx.getExternalContext().getRequestParameterMap().get("visitID");

		try{
			return getVisitByID(visitID);
		}
		catch(Exception e){
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Office Visit", "Unable to Retrieve Office Visit");
	        ctx.addMessage(null,throwMsg);
	        return null;

			
		}

	}

}
	
	
	

