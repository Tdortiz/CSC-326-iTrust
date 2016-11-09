package edu.ncsu.csc.itrust.controller.cptcode;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.model.cptcode.CPTCode;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;

@ManagedBean(name = "cpt_code_form")
@ViewScoped
public class CPTCodeForm {
	private CPTCodeController controller;
	private CPTCode cptCode;
	private String code;
	private String description;
	
	private String search;
	private boolean displayCodes;

	public CPTCodeForm() {
		this(null);
	}
	
	public CPTCodeForm(CPTCodeController cptCodeController) {
		try {
			controller = (cptCodeController == null) ? new CPTCodeController() : controller;
			search = "";
			setDisplayCodes(false);
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPT Code Controller Error",
					"CPT Code Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	
	public void add(){
		// TODO
		System.out.println("Fake Add : " + this.code + " - " + this.description );
		controller.logTransaction(TransactionType.MEDICAL_PROCEDURE_CODE_ADD, code);
	}
	
	public void update(){
		// TODO
		System.out.println("Fake Update : " + this.code + " - " + this.description );
		controller.logTransaction(TransactionType.MEDICAL_PROCEDURE_CODE_EDIT, code);
	}
	
	public void delete(){
		// TODO
		System.out.println("Fake Delete : " + this.code + " - " + this.description );
	}
	
	public List<CPTCode> getCodesWithFilter(){
		controller.logTransaction(TransactionType.MEDICAL_PROCEDURE_CODE_VIEW, code);
		return controller.getCodesWithFilter(search);
	}
	
	/**
	 * @return HTTPRequest in FacesContext, null if no request is found
	 */
	public HttpServletRequest getHttpServletRequest() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return null;
		}
		return ctx.getExternalContext().getRequest() instanceof HttpServletRequest ? (HttpServletRequest) ctx.getExternalContext().getRequest() : null;
	}
	
	public void fillInput(String code, String description){
		this.code = code;
		this.description = description;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public CPTCode getCptCode() {
		return cptCode;
	}

	public void setCptCode(CPTCode cptCode) {
		this.cptCode = cptCode;
	}

	public boolean getDisplayCodes() {
		return displayCodes;
	}

	public void setDisplayCodes(boolean displayCodes) {
		this.displayCodes = displayCodes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
