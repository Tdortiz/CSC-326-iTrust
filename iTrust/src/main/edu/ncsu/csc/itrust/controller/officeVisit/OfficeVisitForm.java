package edu.ncsu.csc.itrust.controller.officeVisit;

import java.time.LocalDateTime;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;

@ManagedBean(name = "office_visit_form")
@ViewScoped
public class OfficeVisitForm {
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

	// Begin added fields for UC 51
	private long dateOfBirth;
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

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public Boolean getSendBill() {
		return sendBill;
	}

	public void setSendBill(Boolean sendBill) {
		this.sendBill = sendBill;
	}

	// Begin getters and setters for UC 51 data
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
			// Begin data for UC 51

			dateOfBirth = patientDAO.getPatient(patientMID).getDateOfBirth().getTime();
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
			// End data for UC 51

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
			// UC 51 fields created here
//			float ht = -1;
//			float wt = -1;
//			float hc = -1;
//			String bp = null;
//			int cholH = -1;
//			int cholT = -1;
//			int cholL = -1;
//			int hss = 0;
//			int pss = 0;

			FacesContext ctx = FacesContext.getCurrentInstance();

			String patientID = "";
			// UC 51 field Strings created here
//			String height = "";
			String weight = "";
//			String headCircumference = "";
//			String bloodPressure = "";
//			String hdl = "";
//			String triglyceride = "";
//			String ldl = "";
//			String householdSmoking = "";
//			String patientSmoking = "";

			if (ctx.getExternalContext().getRequest() instanceof HttpServletRequest) {
				HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
				HttpSession httpSession = req.getSession(false);
				patientID = (String) httpSession.getAttribute("pid");
				// UC 51 set String fields here
				weight = (String) httpSession.getAttribute("basic_ov_form:ovweight");

			}
			if (ValidationFormat.NPMID.getRegex().matcher(patientID).matches()) {
				pid = Long.parseLong(patientID);
			}
			// UC 51 fields validated here
//			if (ValidationFormat.HEIGHT_OV.getRegex().matcher(height).matches()) {
//				ht = Long.parseLong(height);
//			}
//			if (ValidationFormat.WEIGHT_OV.getRegex().matcher(weight).matches()) {
//				wt = Long.parseLong(weight);
//			}
//			if (ValidationFormat.HEAD_CIRCUMFERENCE_OV.getRegex().matcher(headCircumference).matches()) {
//				hc = Long.parseLong(headCircumference);
//			}
//			if (ValidationFormat.BLOOD_PRESSURE_OV.getRegex().matcher(bloodPressure).matches()) {
//				bp = bloodPressure;
//			}
//			if (ValidationFormat.HDL_OV.getRegex().matcher(hdl).matches()) {
//				cholH = Integer.parseInt(hdl);
//			}
//			if (ValidationFormat.TRIGLYCERIDE_OV.getRegex().matcher(triglyceride).matches()) {
//				cholT = Integer.parseInt(triglyceride);
//			}
//			if (ValidationFormat.LDL_OV.getRegex().matcher(ldl).matches()) {
//				cholL = Integer.parseInt(ldl);
//			}
//			if (ValidationFormat.HSS_OV.getRegex().matcher(householdSmoking).matches()) {
//				hss = Integer.parseInt(householdSmoking);
//			}
//			if (ValidationFormat.PSS_OV.getRegex().matcher(patientSmoking).matches()) {
//				pss = Integer.parseInt(patientSmoking);
//			}
			
			ov.setPatientMID(pid);
			// UC 51 fields set here
//			ov.setHeight(ht);
//			ov.setWeight(wt);
//			ov.setHeadCircumference(hc);
//			ov.setBloodPressure(bp);
//			ov.setHDL(cholH);
//			ov.setTriglyceride(cholT);
//			ov.setLDL(cholL);
//			ov.setHouseholdSmokingStatus(hss);
//			ov.setPatientSmokingStatus(pss);
			
			ov.setVisitID(null);

			controller.add(ov);

		}
	}

}
