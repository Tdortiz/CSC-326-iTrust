package edu.ncsu.csc.itrust.controller.loinccode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.loinccode.LOINCCode;
import edu.ncsu.csc.itrust.model.loinccode.LOINCCodeMySQL;

@ManagedBean(name = "loinccode_controller")
@SessionScoped
public class LoincCodeController {

	private static final String INVALID_NDC_CODE = "Invalid LOINC Code";
	private LOINCCodeMySQL sql;
	//private SessionUtils sessionUtils;

	public LoincCodeController() {
		try {
			sql = new LOINCCodeMySQL();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor injection, intended only for unit testing purposes.
	 * 
	 * @param ds
	 *            The injected DataSource dependency
	 */
	public LoincCodeController(DataSource ds) {
		sql = new LOINCCodeMySQL(ds);
	}

	/**
	 * Setter injection for lab procedure data. ONLY use for unit testing
	 * purposes.
	 */
	public void setLabProcedureData(LOINCCodeMySQL data) {
		this.sql = data;
	}
	
	/*
	public void setSessionUtils(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}*/

	
	public void add(LOINCCode code) {
		// TODO
	}

	public void edit(LOINCCode code) {
		// TODO
	}


	public void remove(String loincCodeID) {
		// TODO
	}

	public LOINCCode getCodeByID(String loincCodeID) {
		// TODO
		return null;
	}
	
	public List<LOINCCode> getCodesWithFilter(String filterString){		
		// TODO
		
		List<LOINCCode> codes = new ArrayList<LOINCCode>();
		LOINCCode code = null;
		
		for (int i=1; i <= 1000; i++) {
			code = new LOINCCode(Integer.toString(i) + "0000", "b", "c", "d", "e", "f", "g");
			codes.add(code);
		}
		List<LOINCCode> filterList = codes.stream().filter( c -> c.getCode().contains(filterString)).collect(Collectors.toList());
		
		return filterList;
	}
	
	/**
	 * Sends a FacesMessage for FacesContext to display.
	 * 
	 * @param severity
	 *            severity of the message
	 * @param summary
	 *            localized summary message text
	 * @param detail
	 *            localized detail message text
	 * @param clientId
	 *            The client identifier with which this message is associated
	 *            (if any)
	 */
	public void printFacesMessage(Severity severity, String summary, String detail, String clientId) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return;
		}
		ctx.getExternalContext().getFlash().setKeepMessages(true);
		ctx.addMessage(clientId, new FacesMessage(severity, summary, detail));
	}


}
