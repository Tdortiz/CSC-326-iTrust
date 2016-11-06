package edu.ncsu.csc.itrust.controller.icdcode;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.icdcode.ICDCode;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "icd_code_form")
@ViewScoped
public class ICDCodeForm {
	private ICDCodeController controller;
	private ICDCode icdCode;
	private String code;
	private String description;
	private boolean isChronic;
	
	private String search;
	private boolean displayCodes;

	public ICDCodeForm() {
		this(null);
	}
	
	public ICDCodeForm(ICDCodeController icdCodeController) {
		try {
			controller = (icdCodeController == null) ? new ICDCodeController() : controller;
			search = "";
			setDisplayCodes(false);
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ICD Code Controller Error",
					"ICD Code Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	
	public void add(){
		// TODO
		System.out.println("Fake Add : " + this.code + " - " + this.description + " - " + this.isChronic );
	}
	
	public void update(){
		// TODO
		System.out.println("Fake Update : " + this.code + " - " + this.description + " - " + this.isChronic  );
	}
	
	public void delete(){
		// TODO
		System.out.println("Fake Delete : " + this.code + " - " + this.description + " - " + this.isChronic  );
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
	
	public void fillInput(String code, String description, boolean isChronic){
		this.code = code;
		this.description = description;
		this.isChronic = isChronic;
	}


	public List<ICDCode> getCodesWithFilter(){
		return controller.getCodesWithFilter(search);
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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

	public ICDCode getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(ICDCode icdCode) {
		this.icdCode = icdCode;
	}

	public boolean getIsChronic() {
		return isChronic;
	}

	public void setChronic(boolean isChronic) {
		this.isChronic = isChronic;
	}

	
	
}
