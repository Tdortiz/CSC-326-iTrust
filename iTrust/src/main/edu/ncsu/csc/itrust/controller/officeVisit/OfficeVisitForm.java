package edu.ncsu.csc.itrust.controller.officeVisit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;

@ManagedBean(name = "office_visit_form")
@ViewScoped
public class OfficeVisitForm {
	private static final int PATIENT_BABY_AGE = 3;
	private static final int PATIENT_CHILD_AGE = 12;
	private OfficeVisitController controller;
	private PatientDAO patientDAO;
	private OfficeVisit ov;
	private Long visitID;
	private Long patientMID;
	private LocalDateTime date;
	private String locationID;
	private Long apptTypeID;
	private String notes;
	private Boolean sendBill;
	private Long age;
	private Float height;
	private Float length;
	private Float weight;
	private Float headCircumference;
	private String bloodPressure;
	private Integer hdl;
	private Integer triglyceride;
	private Integer ldl;
	private Integer householdSmokingStatus;
	private Integer patientSmokingStatus;

	public Long getVisitID() {
		return visitID;
	}

	public void setVisitID(Long visitID) {
		this.visitID = visitID;
	}

	public Long getPatientMID() {
		return patientMID;
	}

	public void setPatientMID(Long patientMID) {
		this.patientMID = patientMID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public Long getApptTypeID() {
		return apptTypeID;
	}

	public void setApptTypeID(Long apptTypeID) {
		this.apptTypeID = apptTypeID;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getSendBill() {
		return sendBill;
	}

	public void setSendBill(Boolean sendBill) {
		this.sendBill = sendBill;
	}

	/**
	 * Returns the Height recorded at the office visit.
	 * 
	 * @return the Height recorded at the office visit.
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * Sets the height recorded at the office visit.
	 * 
	 * @param height
	 *            The height recorded at the office visit
	 */
	public void setHeight(Float height) {
		this.height = height;
	}

	/**
	 * Gets the length recorded at the office visit.
	 * 
	 * @param length
	 *            The height recorded at the office visit
	 */
	public Float getLength() {
		return length;
	}

	/**
	 * Sets the length recorded at the office visit.
	 * 
	 * @param length
	 *            The height recorded at the office visit
	 */
	public void setLength(Float length) {
		this.length = length;
	}

	/**
	 * Returns the weight recorded at the office visit.
	 * 
	 * @return the weight recorded at the office visit.
	 */
	public Float getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Float weight) {
		this.weight = weight;
	}

	/**
	 * Returns the head circumference measured at the office visit.
	 * 
	 * @return the headCircumference
	 */
	public Float getHeadCircumference() {
		return headCircumference;
	}

	/**
	 * @param headCircumference
	 *            the headCircumference to set
	 */
	public void setHeadCircumference(Float headCircumference) {
		this.headCircumference = headCircumference;
	}

	/**
	 * Returns the blood pressure measured at the office visit.
	 * 
	 * @return the bloodPressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @param bloodPressure
	 *            the bloodPressure to set
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * Returns the HDL cholesterol level measured at the office visit.
	 * 
	 * @return the hdl
	 */
	public Integer getHDL() {
		return hdl;
	}

	/**
	 * @param hdl
	 *            the hdl to set
	 */
	public void setHDL(Integer hdl) {
		this.hdl = hdl;
	}

	/**
	 * Returns the triglyceride cholesterol level measured at the office visit.
	 * 
	 * @return the triglyceride
	 */
	public Integer getTriglyceride() {
		return triglyceride;
	}

	/**
	 * @param triglyceride
	 *            the triglyceride to set
	 */
	public void setTriglyceride(Integer triglyceride) {
		this.triglyceride = triglyceride;
	}

	/**
	 * Returns the LDL cholesterol level measured at the office visit.
	 * 
	 * @return the ldl
	 */
	public Integer getLDL() {
		return ldl;
	}

	/**
	 * @param ldl
	 *            the ldl to set
	 */
	public void setLDL(Integer ldl) {
		this.ldl = ldl;
	}

	/**
	 * Returns the household smoking status indicated at the office visit.
	 * 
	 * @return the householdSmokingStatus
	 */
	public Integer getHouseholdSmokingStatus() {
		return householdSmokingStatus;
	}

	/**
	 * @param householdSmokingStatus
	 *            the householdSmokingStatus to set
	 */
	public void setHouseholdSmokingStatus(Integer householdSmokingStatus) {
		this.householdSmokingStatus = householdSmokingStatus;
	}

	/**
	 * Returns the patient smoking status indicated at the office visit.
	 * 
	 * @return the patientSmokingStatus
	 */
	public Integer getPatientSmokingStatus() {
		return patientSmokingStatus;
	}

	/**
	 * @param patientSmokingStatus
	 *            the patientSmokingStatus to set
	 */
	public void setPatientSmokingStatus(Integer patientSmokingStatus) {
		this.patientSmokingStatus = patientSmokingStatus;
	}

	public OfficeVisitForm() {
		try {
			controller = new OfficeVisitController();
			patientDAO = DAOFactory.getProductionInstance().getPatientDAO();
			ov = controller.getSelectedVisit();
			if (ov == null) {
				ov = new OfficeVisit();
			}
			visitID = ov.getVisitID();
			patientMID = ov.getPatientMID();
			if (patientMID == null) {
				patientMID = Long.parseLong(
				(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pid"));
			}
			date = ov.getDate();
			locationID = ov.getLocationID();
			apptTypeID = ov.getApptTypeID();
			sendBill = ov.getSendBill();
			notes = ov.getNotes();
			
			age = calculatePatientAge(patientDAO, patientMID, date);
			height = ov.getHeight();
			length = ov.getLength();
			weight = ov.getWeight();
			headCircumference = ov.getHeadCircumference();
			bloodPressure = ov.getBloodPressure();
			hdl = ov.getHDL();
			triglyceride = ov.getTriglyceride();
			ldl = ov.getLDL();
			householdSmokingStatus = ov.getHouseholdSmokingStatus();
			patientSmokingStatus = ov.getPatientSmokingStatus();

		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Office Visit Controller Error",
					"Office Visit Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}

	public void submit() {
		ov.setApptTypeID(apptTypeID);
		ov.setDate(date);
		ov.setLocationID(locationID);
		ov.setNotes(notes);
		ov.setSendBill(sendBill);
		ov.setPatientMID(patientMID);
		
		// UC 51 Data
		ov.setBirthDate(new Long(dateOfBirth));

		ov.setHeight(height);
		ov.setLength(length);
		ov.setWeight(weight);
		ov.setHeadCircumference(headCircumference);
		ov.setBloodPressure(bloodPressure);
		ov.setHDL(hdl);
		ov.setTriglyceride(triglyceride);
		ov.setLDL(ldl);
		ov.setHouseholdSmokingStatus(householdSmokingStatus);
		ov.setPatientSmokingStatus(patientSmokingStatus);
		
		if ((visitID != null) && (visitID > 0)) {
			ov.setVisitID(visitID);
			controller.edit(ov);

		} else {
			long pid = -1;
			
			FacesContext ctx = FacesContext.getCurrentInstance();

			String patientID = "";
			

			if (ctx.getExternalContext().getRequest() instanceof HttpServletRequest) {
				HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
				HttpSession httpSession = req.getSession(false);
				patientID = (String) httpSession.getAttribute("pid");
				

			}
			if (ValidationFormat.NPMID.getRegex().matcher(patientID).matches()) {
				pid = Long.parseLong(patientID);
			}
			
			ov.setPatientMID(pid);
			
			ov.setVisitID(null);

			controller.add(ov);
		}
	}
	
	private static Long calculatePatientAge(PatientDAO patientDAO, Long patientMID, LocalDateTime officeVisitDate) throws DBException {
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

	public boolean isPatientABaby() {
		if (age == null || age < 0) {
			return false;
		}
		return age < PATIENT_BABY_AGE;
	}


	public boolean isPatientAChild() {
		if (age == null) {
			return false;
		}
		return age < PATIENT_CHILD_AGE && age >= PATIENT_BABY_AGE;
	}
	
	public boolean isPatientAnAdult() {
		if (age == null) {
			return false;
		}
		return age >= PATIENT_CHILD_AGE;
	}
}
