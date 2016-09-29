package edu.ncsu.csc.itrust.controller.officeVisit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.NavigationController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitData;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;

@ManagedBean(name="office_visit_controller")
@SessionScoped
public class OfficeVisitController {
	private static final int PATIENT_BABY_AGE = 3;
	private static final int PATIENT_CHILD_AGE = 12;
	
	private OfficeVisitData officeVisitData;
	private PatientDAO patientDAO = DAOFactory.getProductionInstance().getPatientDAO();
	public OfficeVisitController() throws DBException{
		officeVisitData = new OfficeVisitMySQL();
	}
	/**
	 * For testing purposes
	 * @param ds DataSource
	 */
	public OfficeVisitController(DataSource ds) throws DBException{
		officeVisitData = new OfficeVisitMySQL(ds);
	}
	
	public void add(OfficeVisit ov) {
		boolean res = false;
		
		try {
			res = officeVisitData.add(ov);
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Office Visit", "Invalid Office Visit");
	      	FacesContext.getCurrentInstance().addMessage(null,throwMsg);
		}
		if(res){
			try {
				NavigationController.baseOfficeVisit();
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated");
		      	FacesContext.getCurrentInstance().addMessage(null,successMsg);
			} catch (IOException e) {
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Navigation Error", "Navigation Error");
		      	FacesContext.getCurrentInstance().addMessage(null,successMsg);
			}
		}
	}
	
	public List<OfficeVisit> getOfficeVisitsForPatient(String pid){
		List<OfficeVisit> ret = null;
		long mid = -1;
		if(ValidationFormat.NPMID.getRegex().matcher(pid).matches()){
			mid = Long.parseLong(pid);
			try {
				ret = officeVisitData.getVisitsForPatient(mid);
			} catch (Exception e) {
		      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Office Visits", "Unable to Retrieve Office Visits");
		      	FacesContext.getCurrentInstance().addMessage(null,throwMsg);
			}
		}
		return ret;
	}
	
	public List<OfficeVisit> getOfficeVisitsForCurrentPatient(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		String patientID = "";
				
		if(ctx.getExternalContext().getRequest() instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
			HttpSession httpSession = req.getSession(false);
			patientID = (String) httpSession.getAttribute("pid");
		}
		if((patientID != null) && (ValidationFormat.NPMID.getRegex().matcher(patientID).matches())){
			return getOfficeVisitsForPatient(patientID);
		}
		return null;

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
			visitID = req.getParameter("visitID");
		}
	
		try{
			return getVisitByID(visitID);
		}
		catch(Exception e){
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Office Visit", "Unable to Retrieve Office Visit");
	        ctx.addMessage(null,throwMsg);
	        return null;

			
		}

	}
	public boolean CurrentPatientHasVisited(){
		boolean ret = false;
		FacesContext ctx = FacesContext.getCurrentInstance();
		String patientID = "";
				
		if(ctx.getExternalContext().getRequest() instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
			HttpSession httpSession = req.getSession(false);
			patientID = (String) httpSession.getAttribute("pid");
		}
		if((patientID != null) && (ValidationFormat.NPMID.getRegex().matcher(patientID).matches())){
			if(getOfficeVisitsForPatient(patientID).size()>0){
				ret = true;
			}

		}
		return ret;
		
	}
	public void edit(OfficeVisit ov) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean res = false;
		
		try {
			res = officeVisitData.update(ov);
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Office Visit", "Invalid Office Visit");
	        ctx.addMessage(null,throwMsg);
		}
		if(res){
			try {
				NavigationController.baseOfficeVisit();
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated");
		        ctx.addMessage(null,successMsg);

			} catch (IOException e) {
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Navigation Error", "Navigation Error");
		      	FacesContext.getCurrentInstance().addMessage(null,successMsg);
			}
		}
	}
	
	private Long calculatePatientAge(final Long patientMID, final LocalDateTime officeVisitDate) throws DBException {
		Long ret = -1L;
		if (officeVisitDate == null) {
			return ret;
		}
		
		PatientBean patient = patientDAO.getPatient(patientMID);
		if (patient == null) {
			return ret;
		}
		
		Date patientDOB = patient.getDateOfBirth();
		if (patientDOB == null) {
			return ret;
		}
		
		LocalDateTime patientDOBDate = patientDOB.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		if (officeVisitDate.isBefore(patientDOBDate)) {
			return ret;
		}
		
		return ChronoUnit.YEARS.between(patientDOBDate, officeVisitDate);
	}
	
	public boolean isPatientABaby(final Long patientMID, final LocalDateTime officeVisitDate) throws DBException {
		Long age = calculatePatientAge(patientMID, officeVisitDate);
		if (age == null || age < 0) {
			return false;
		}
		return age < PATIENT_BABY_AGE;
	}


	public boolean isPatientAChild(final Long patientMID, final LocalDateTime officeVisitDate) throws DBException {
		Long age = calculatePatientAge(patientMID, officeVisitDate);
		if (age == null) {
			return false;
		}
		return age < PATIENT_CHILD_AGE && age >= PATIENT_BABY_AGE;
	}
	
	public boolean isPatientAnAdult(final Long patientMID, final LocalDateTime officeVisitDate) throws DBException {
		Long age = calculatePatientAge(patientMID, officeVisitDate);
		if (age == null) {
			return false;
		}
		return age >= PATIENT_CHILD_AGE;
	}

}
	
	
	

