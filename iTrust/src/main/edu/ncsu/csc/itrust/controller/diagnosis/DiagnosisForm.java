package edu.ncsu.csc.itrust.controller.diagnosis;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "diagnosis_form")
@ViewScoped
public class DiagnosisForm {
	private Diagnosis diagnosis;
	private DiagnosisController controller;
	private SessionUtils sessionUtils;
	
	public DiagnosisForm() {
		this(null, null, null);
	}
	
	public DiagnosisForm(DiagnosisController dc, SessionUtils sessionUtils, DataSource ds) {
		this.sessionUtils = (sessionUtils == null) ? SessionUtils.getInstance() : sessionUtils;
		try {
			this.controller = (dc == null) ? new DiagnosisController() : dc;
		} catch (DBException e) {
			this.sessionUtils.printFacesMessage(FacesMessage.SEVERITY_ERROR, "Diagnosis Controller Error",
				"Diagnosis Procedure Controller Error", null);
		}
		clearFields();
	}
	
	/**
	 * @return list of diagnosis from the current office visit
	 */
	public List<Diagnosis> getCurrentDiagnoses() {
		return controller.getDiagnosesByOfficeVisit(sessionUtils.getCurrentOfficeVisitId());
	}
	
	public void add() {
		controller.add(diagnosis);
		clearFields();
	}
	
	public void edit() {
		controller.edit(diagnosis);
		clearFields();
	}
	
	public void remove(String diagnosisId) {
		controller.remove(Long.parseLong(diagnosisId));
		clearFields();
	}
	
	public void clearFields() {
		this.diagnosis = new Diagnosis();
		diagnosis.setVisitId(sessionUtils.getCurrentOfficeVisitId());
	}
}
