package edu.ncsu.csc.itrust.controller.officeVisit;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

@ManagedBean(name="office_visit_controller")
@SessionScoped
public class OfficeVisitController {
	
	/**
	 * HttpSession variable name of the current logged in patient MID.
	 */
	private static final String LOGGED_IN_MID = "loggedInMID";

	/**
	 * HttpSession variable name of the HCP selected patient MID.
	 */
	private static final String PID = "pid";

	/**
	 * The cut off age for being considered a baby.
	 */
	private static final int PATIENT_BABY_AGE = 3;
	
	/**
	 * The cut off age for being considered a child.
	 */
	private static final int PATIENT_CHILD_AGE = 12;
	
	/**
	 * Constant for the error message to be displayed if the Office Visit is invalid.
	 */
	private static final String INVALID_OFFICE_VISIT = "Invalid Office Visit";
	
	private OfficeVisitData officeVisitData;
	public OfficeVisitController() throws DBException{
		officeVisitData = new OfficeVisitMySQL();
	}
	/**
	 * For testing purposes
	 * @param ds DataSource
	 */
	public OfficeVisitController(DataSource ds) {
		officeVisitData = new OfficeVisitMySQL(ds);
	}
	
	
	/**
	 * Adding office visit used in testing.
	 * @param ov
	 * 			Office visit
	 * @return true if successfully added, false if otherwise
	 */
	public boolean addReturnResult(OfficeVisit ov) {
		boolean res = false;
		
		try {
			res = officeVisitData.add(ov);
		} catch (Exception e) {
	      	// do nothing
		}
		
		return res;
	}
	
	public void add(OfficeVisit ov) {
		boolean res = false;
		
		try {
			res = officeVisitData.add(ov);
		} catch (DBException e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_OFFICE_VISIT, e.getExtendedMessage());
	      	FacesContext.getCurrentInstance().addMessage(null,throwMsg);
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_OFFICE_VISIT, INVALID_OFFICE_VISIT);
	      	FacesContext.getCurrentInstance().addMessage(null,throwMsg);
		}
		if(res){
			try {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated");
		      	FacesContext.getCurrentInstance().addMessage(null,successMsg);
				NavigationController.baseOfficeVisit();
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
			patientID = (String) httpSession.getAttribute(PID);
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
		
		if (visitID == null) {
			return null;
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
	
	/**
	 * Uses FacesContext to seek a HttpSession variable of a string type within the FaceContext.
	 * 
	 * @param varname
	 * 			variable name in the HTTP session 
	 * @return session variable of any type
	 */
	public Object getSessionVariable(String varname) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Object variable = "";
				
		if(ctx.getExternalContext().getRequest() instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
			HttpSession httpSession = req.getSession(false);
			variable = httpSession.getAttribute(varname);
		}
		
		return variable;
	}
	
	/**
	 * @param mid
	 * 			mid of patient in Object form, could be in String type or Long type
	 * @return
	 * 			String representation of patient MID, null if the parameter has invalid type
	 */
	public String parsePatientMID(Object mid) {
		if (mid instanceof String) {
			return (String) mid;
		} else if (mid instanceof Long) {
			return ((Long) mid).toString();
		} else {
			return null;
		}
	}
	
	/**
	 * @return MID of the patient that the HCP selected in the session. 
	 */
	public String getSessionPID() {
		return parsePatientMID(getSessionVariable(PID));
	}
	
	/**
	 * @return current logged in patient's MID
	 */
	public String getSessionLoggedInMID() {
		return parsePatientMID(getSessionVariable(LOGGED_IN_MID));
	}
	
	/**
	 * @param patientID
	 * 			Patient MID
	 * @return true if selected patient MID has at least 1 office visit, false otherwise 
	 */
	public boolean hasPatientVisited(String patientID) {
		boolean ret = false;
		if((patientID != null) && (ValidationFormat.NPMID.getRegex().matcher(patientID).matches())){
			if(getOfficeVisitsForPatient(patientID).size()>0){
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * @return true if patient selected in HCP session has at least 1 office visit,
	 * 			false if otherwise  
	 */
	public boolean CurrentPatientHasVisited(){
		String patientID = getSessionPID();
		return hasPatientVisited(patientID);
	}
	
	/**
	 * @return true if current patient logged in has at least 1 office visit, false
	 * 			if otherwise
	 */
	public boolean hasLoggedInPatientVisited() {
		String patientID = getSessionLoggedInMID();
		return hasPatientVisited(patientID);
	}
	
	public void edit(OfficeVisit ov) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean res = false;
		
		try {
			res = officeVisitData.update(ov);
		} catch (DBException e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_OFFICE_VISIT, e.getExtendedMessage());
	      	FacesContext.getCurrentInstance().addMessage(null,throwMsg);
		} catch (Exception e) {
	      	FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_OFFICE_VISIT, INVALID_OFFICE_VISIT);
	        ctx.addMessage(null,throwMsg);
		}
		if(res){
			try {
				ctx.getExternalContext().getFlash().setKeepMessages(true);
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Office Visit Successfully Updated", "Office Visit Successfully Updated");
		        ctx.addMessage(null,successMsg);
				NavigationController.baseOfficeVisit();

			} catch (IOException e) {
		      	FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Navigation Error", "Navigation Error");
		      	FacesContext.getCurrentInstance().addMessage(null,successMsg);
			}
		}
	}
	
	/**
	 * Calculate given patient's age given a day in the future.
	 * 
	 * @param patientMID
	 * 			MID of the patient
	 * @param futureDate
	 * 			Date in the future
	 * @return patient's age in calendar year, or -1 if error occurred
	 */
	private Long calculatePatientAge(final Long patientMID, final LocalDateTime futureDate) {
		Long ret = -1L;
		if (futureDate == null) {
			return ret;
		}
		
		LocalDate patientDOB = getPatientDOB(patientMID);
		if (patientDOB == null) {
			return ret;
		}
		
		if (futureDate.toLocalDate().isBefore(patientDOB)) {
			return ret;
		}
		
		return ChronoUnit.YEARS.between(patientDOB, futureDate);
	}
	
	/**
	 * Check whether patient is under 3 years of age.
	 * 
	 * @param patientMID
	 * 			MID of the patient
	 * @param officeVisitDate
	 * 			date of the office visit
	 * @return true if patient is under 3 years old at the time of the office visit, false if otherwise			
	 */
	public boolean isPatientABaby(final Long patientMID, final LocalDateTime officeVisitDate) {
		Long age = calculatePatientAge(patientMID, officeVisitDate);
		if (age == null || age < 0) {
			return false;
		}
		return age < PATIENT_BABY_AGE;
	}

	/**
	 * Check whether patient is between 3 years (inclusive) and 12 years (exclusive) old.
	 * 
	 * @param patientMID
	 * 			MID of the patient
	 * @param officeVisitDate
	 * 			date of the office visit
	 * @return true if patient is is between 3 years (inclusive) and 12 years (exclusive) old, false if otherwise			
	 */
	public boolean isPatientAChild(final Long patientMID, final LocalDateTime officeVisitDate) {
		Long age = calculatePatientAge(patientMID, officeVisitDate);
		if (age == null) {
			return false;
		}
		return age < PATIENT_CHILD_AGE && age >= PATIENT_BABY_AGE;
	}

	/**
	 * Check whether patient is between 12 years old or older.
	 * 
	 * @param patientMID
	 * 			MID of the patient
	 * @param officeVisitDate
	 * 			date of the office visit
	 * @return true if patient is is 12 years old or older, false if otherwise			
	 */
	public boolean isPatientAnAdult(final Long patientMID, final LocalDateTime officeVisitDate) {
		Long age = calculatePatientAge(patientMID, officeVisitDate);
		if (age == null) {
			return false;
		}
		return age >= PATIENT_CHILD_AGE;
	}

	/** 
	 * @param patientMID
	 * 			The patient's MID
	 * @return the patient's date of birth
	 */
	public LocalDate getPatientDOB(final Long patientMID) {
		return officeVisitData.getPatientDOB(patientMID);
	}
}
	
	
	

