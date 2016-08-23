package edu.ncsu.csc.itrust.controller.officeVisit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;
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
	private FacesContext ctx;
	public OfficeVisitController() throws DBException{
		ctx = FacesContext.getCurrentInstance();
		officeVisitData = new OfficeVisitMySQL();
		officeVisitInstance = new OfficeVisit();
		
	}
	
	public void add(OfficeVisit ov) {
		long pid = 0;
		Map<String, Object> session = ctx.getExternalContext().getSessionMap();
		Object pidObj = session.get("pid");
		if(pidObj instanceof Long){
			pid = (long) pidObj;
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
	
	
	

}
