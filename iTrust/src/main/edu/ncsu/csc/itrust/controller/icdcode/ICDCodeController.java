package edu.ncsu.csc.itrust.controller.icdcode;

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
import edu.ncsu.csc.itrust.model.icdcode.ICDCode;
import edu.ncsu.csc.itrust.model.icdcode.ICDCodeMySQL;

@ManagedBean(name = "icdcode_controller")
@SessionScoped
public class ICDCodeController {

	private static final String INVALID_LAB_PROCEDURE = "Invalid ICD Code";
	private ICDCodeMySQL sql;
	//private SessionUtils sessionUtils;

	public ICDCodeController() {
		try {
			sql = new ICDCodeMySQL();
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
	public ICDCodeController(DataSource ds) {
		sql = new ICDCodeMySQL(ds);
	}

	/**
	 * Setter injection for lab procedure data. ONLY use for unit testing
	 * purposes.
	 */
	public void setLabProcedureData(ICDCodeMySQL data) {
		this.sql = data;
	}
	
	/*
	public void setSessionUtils(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}*/

	
	public void add(ICDCode code) {
		
	}

	public void edit(ICDCode code) {
		
	}


	public void remove(String icdCodeID) {
		
	}

	public ICDCode getCodeByID(String icdCodeID) {
		return null;
	}
	
	public List<ICDCode> getCodesWithFilter(String filterString){		
		List<ICDCode> codes = new ArrayList<ICDCode>();
		ICDCode code = null;
		
		for (int i=1; i <= 1000; i++) {
			boolean chronic = false;
			
			if( i % 3 == 0 ) chronic = true;
			
			code = new ICDCode(Integer.toString(i) + "0000", "Name" + i, chronic);
			codes.add(code);
		}
		List<ICDCode> filterList = codes.stream().filter( c -> c.getCode().contains(filterString)).collect(Collectors.toList());
		
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
