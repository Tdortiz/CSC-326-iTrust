package edu.ncsu.csc.itrust.controller.ndcode;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ndcode.NDCCode;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "ndc_code_form")
@ViewScoped
public class NDCCodeForm {
	private NDCCodeController controller;
	private NDCCode ndcCode;
	
	private String code;
	private String description;
	
	private String search;
	private boolean displayCodes;

	public NDCCodeForm() {
		this(null);
	}
	
	public NDCCodeForm(NDCCodeController cptCodeController) {
		try {
			controller = (cptCodeController == null) ? new NDCCodeController() : controller;
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
	}
	
	public void update(){
		// TODO
		System.out.println("Fake Update : " + this.code + " - " + this.description );
	}
	
	public void delete(){
		// TODO
		System.out.println("Fake Delete : " + this.code + " - " + this.description );
	}
	
	public List<NDCCode> getCodesWithFilter(){
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

	public NDCCode getCptCode() {
		return ndcCode;
	}

	public void setCptCode(NDCCode ndcCode) {
		this.ndcCode = ndcCode;
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
