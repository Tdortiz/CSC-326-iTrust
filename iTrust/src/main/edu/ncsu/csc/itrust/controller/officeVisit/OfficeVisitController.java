package edu.ncsu.csc.itrust.controller.officeVisit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitDAO;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.user.User;
import edu.ncsu.csc.itrust.model.user.UserMySQLConvBean;

@ManagedBean(name="office_visit_controller")
public class OfficeVisitController {
	private OfficeVisitDAO officeVisitData;
	private OfficeVisit officeVisitInstance;
	public OfficeVisitController() throws DBException{
		officeVisitData = new OfficeVisitMySQL();
		officeVisitInstance = new OfficeVisit();
		
	}
	
	public void add(OfficeVisit ov) throws DBException{
		boolean res = officeVisitData.add(ov);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//		LocalDateTime ldtVisitDate= LocalDateTime.parse(date, formatter);

		//officeVisitInstance.setDate(ldtVisitDate);
	}
	
	
	

}
