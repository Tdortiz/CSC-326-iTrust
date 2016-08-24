package edu.ncsu.csc.itrust.controller.officeVisit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;

@ManagedBean(name="office_visit_backing")
public class OfficeVisitFormBacking {
	OfficeVisitController controller;
	OfficeVisit ov;
	public OfficeVisitFormBacking(){
		try {
			controller = new OfficeVisitController();
			ov = controller.getSelectedVisit();
			if(ov == null){
				ov = new OfficeVisit();
			}
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Office Visit Controller Error", "Office Visit Controller Error");
	        FacesContext.getCurrentInstance().addMessage(null,throwMsg);


		}
		
		
	}

}
