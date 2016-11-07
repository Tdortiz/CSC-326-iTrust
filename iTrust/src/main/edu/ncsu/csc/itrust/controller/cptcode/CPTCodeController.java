package edu.ncsu.csc.itrust.controller.cptcode;

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
import edu.ncsu.csc.itrust.model.cptcode.CPTCode;
import edu.ncsu.csc.itrust.model.cptcode.CPTCodeMySQL;

@ManagedBean(name = "cptcode_controller")
@SessionScoped
public class CPTCodeController {

	private static final String INVALID_CPT_CODE = "Invalid CPT Code";
	private CPTCodeMySQL sql;
	//private SessionUtils sessionUtils;

	public CPTCodeController() {
		try {
			sql = new CPTCodeMySQL();
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
	public CPTCodeController(DataSource ds) {
		sql = new CPTCodeMySQL(ds);
	}

	/**
	 * Setter injection for lab procedure data. ONLY use for unit testing
	 * purposes.
	 */
	public void setLabProcedureData(CPTCodeMySQL data) {
		this.sql = data;
	}
	
	/*
	public void setSessionUtils(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}*/

	/**
	 * Adds a cptCode.
	 * 
	 * @param code 
	 *            The code to add
	 */
	public void add(CPTCode code) {
		// TODO
	}

	/**
	 * Updates a cptCode. Prints FacesContext info message when
	 * successfully updated, error message when the update fails.
	 * 
	 * @param code 
	 *            The code to add
	 */
	public void edit(CPTCode code) {
		// TODO
	}


	public void remove(String cptCodeID) {
		// TODO
	}

	public CPTCode getCodeByID(String cptCodeID) {
		// TODO
		return null;
	}
	
	public List<CPTCode> getCodesWithFilter(String filterString){
		// TODO	
		List<CPTCode> codes = new ArrayList<CPTCode>();
		CPTCode code = null;
		
		for (int i=1; i <= 1000; i++) {
			code = new CPTCode(Integer.toString(i) + "0000", "Name" + i);
			codes.add(code);
		}
		
		List<CPTCode> filterList = codes.stream().filter( c -> c.getCode().contains(filterString)).collect(Collectors.toList());
		
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
