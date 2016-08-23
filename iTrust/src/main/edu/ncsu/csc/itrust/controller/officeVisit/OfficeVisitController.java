package edu.ncsu.csc.itrust.controller.officeVisit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitDAO;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.user.User;
import edu.ncsu.csc.itrust.model.user.UserMySQLConvBean;

@ManagedBean(name="office_visit_controller")
@SessionScoped
public class OfficeVisitController {
	private OfficeVisitDAO officeVisitData;
	private OfficeVisit officeVisitInstance;
	//private FacesContext ctx;
	public OfficeVisitController() throws DBException{
		
		officeVisitData = new OfficeVisitMySQL();
		officeVisitInstance = new OfficeVisit();
		
	}
	
	public void add(OfficeVisit ov) {
		long pid = -1;
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Map<Object, Object> temp = ctx.getAttributes();
		Map<String, Object> temp2 = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		String patientID = "";
				
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
			boolean res = officeVisitData.add(ov);
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Office Visit", "Invalid Office Visit");
	        ctx.addMessage(null,throwMsg);
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		FacesContext ctx = FacesContext.getCurrentInstance();
		long mid = -1;
		Map<String, Object> session = ctx.getExternalContext().getSessionMap();
		Object pidObj = session.get("pid");
		if(pidObj instanceof Long){
			mid = (long) pidObj;
		}
		return getOfficeVisitsForPatient(Long.toString(mid));
	}

}
	
	
	

