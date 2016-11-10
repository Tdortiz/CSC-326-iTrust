package edu.ncsu.csc.itrust.controller.prescription;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.prescription.Prescription;
import edu.ncsu.csc.itrust.model.prescription.PrescriptionMySQL;

@ManagedBean(name = "prescription_controller")
@SessionScoped
public class PrescriptionController extends iTrustController {

	private static final String INVALID_PRESCRIPTION = "Invalid Prescription";
	private PrescriptionMySQL sql;

	public PrescriptionController() throws DBException {
		super();
		sql = new PrescriptionMySQL();
	}

	/**
	 * Constructor injection, intended only for unit testing purposes.
	 * 
	 * @param ds
	 *            The injected DataSource dependency
	 */
	public PrescriptionController(DataSource ds) throws DBException {
		super();
		this.sql = new PrescriptionMySQL(ds);
	}

	public void add(Prescription prescription) {
		// TODO
	}

	public void edit(Prescription prescription) {
		// TODO
	}

	public void remove() {
		// TODO
	}

	public Prescription getPrescriptionByID(String prescriptionID) throws SQLException {
		Long id = null;
		try {
			id = Long.parseLong(prescriptionID);
		} catch (NumberFormatException e) {
			// Do nothing
		}
		if (id == null) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot get prescription", "Invalid prescription ID", null);
			return null;
		} else {
			return sql.get(id);
		}
	}

	public List<Prescription> getPrescriptionsForCurrentPatient() {
		String pid = getSessionUtils().getCurrentPatientMID();
		return getPrescriptionsByPatientID(pid);
	}

	public List<Prescription> getPrescriptionsByPatientID(String patientMID) {
		Long mid = null;
		List<Prescription> prescriptions = Collections.emptyList();
		
		try {
			mid = Long.parseLong(patientMID);
		} catch (NumberFormatException e) {
			// Do nothing
		}
		
		if (mid == null) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot get patient's prescriptions", "Invalid patient MID", null);
			return prescriptions;
		}
		
		try {
			prescriptions = sql.getPrescriptionsByMID(mid);
		} catch (SQLException e) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot get patient's prescriptions", e.getMessage(), null);
		}
		
		return prescriptions;
	}

	/**
	 * This should return to the logging in user a list of patients that they
	 * represent
	 * 
	 * @param loggedInID
	 *            mid of the person logged in
	 */
	public List<PatientBean> getListOfRepresentees() {
		List<PatientBean> representees = getSessionUtils().getRepresenteeList();
		
		// If there wasn't already a cached list make it and cache it for future use
		if( representees == null ){
			try {
				Long userMID = this.getSessionUtils().getSessionLoggedInMIDLong();
				representees = sql.getListOfRepresentees(userMID);
				getSessionUtils().setRepresenteeList(representees);
			} catch (SQLException e) {
				printFacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot get representees", e.getMessage(), null);
			}
		}

		return representees;
	}
	
	public String getRepParameter(){
		return this.getSessionUtils().getRequestParameter("rep");
	}

}
