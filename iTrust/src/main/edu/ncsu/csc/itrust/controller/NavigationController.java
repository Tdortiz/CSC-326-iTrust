package edu.ncsu.csc.itrust.controller;

import java.io.IOException;

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
	public void redirectIfInvalidPatient(String mid) throws DBException, IOException{
		if(!(patientController.doesPatientExistWithID(mid))){
			 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			 Object req = context.getRequest();
			 String url = "";
			 if (req instanceof HttpServletRequest){
				 HttpServletRequest req2 = (HttpServletRequest) req;
				 url = req2.getRequestURI();

				 
			 }
			    context.redirect("/iTrust/auth/getPatientID.jsp?forward="+url);
			
			
		}
	}


}
