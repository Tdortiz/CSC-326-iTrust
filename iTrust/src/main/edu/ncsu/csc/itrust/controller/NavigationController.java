package edu.ncsu.csc.itrust.controller;

import java.io.IOException;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.controller.user.patient.PatientControllerBean;
import edu.ncsu.csc.itrust.exception.DBException;

@ManagedBean(name="navigation_controller")
@RequestScoped
public class NavigationController {

	private PatientControllerBean patientController;
	public NavigationController() throws DBException{
		patientController = new PatientControllerBean();
	}
	public void redirectIfInvalidPatient() throws DBException, IOException{
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		long pid = 0;
		Map<String, Object> session = ctx.getSessionMap();
		Object pidObj = session.get("pid");
		if(pidObj instanceof Long){
			pid = (long) pidObj;
		}
		if((pidObj == null) || (!(patientController.doesPatientExistWithID(Long.toString(pid))))){
			 Object req = ctx.getRequest();
			 String url = "";
			 if (req instanceof HttpServletRequest){
				 HttpServletRequest req2 = (HttpServletRequest) req;
				 url = req2.getRequestURI();

				 
			 }


			    ctx.redirect("/iTrust/auth/getPatientID.jsp?forward="+url);
			
			
		}
	}


}
