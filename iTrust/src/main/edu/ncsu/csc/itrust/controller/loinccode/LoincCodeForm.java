package edu.ncsu.csc.itrust.controller.loinccode;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.controller.loinccode.LoincCodeController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.loinccode.LOINCCode;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "loinc_code_form")
@ViewScoped
public class LoincCodeForm {
	private LoincCodeController controller;
	private LOINCCode loincCode;
	// Input Fields
	private String code;
	private String component;
	private String kindOfProperty;
	private String timeAspect;
	private String system;
	private String scaleType;
	private String methodType;
	
	private String search;
	private boolean displayCodes;

	public LoincCodeForm() {
		this(null);
	}
	
	public LoincCodeForm(LoincCodeController loincCodeController) {
		try {
			controller = (loincCodeController == null) ? new LoincCodeController() : controller;
			search = "";
			setDisplayCodes(false);
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "LOINC Code Controller Error",
					"LOINC Code Controller Error Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	
	public void add(){
		// TODO
		System.out.println("Fake Add : " + this.code + " - "  + 
							this.component + " - " + this.kindOfProperty + " - " +
							this.timeAspect + " - " + this.system + " - " + 
							this.scaleType + " - " + this.methodType );
	}
	
	public void update(){
		// TODO
		System.out.println("Fake Update : " + this.code + " - "  + 
							this.component + " - " + this.kindOfProperty + " - " +
							this.timeAspect + " - " + this.system + " - " + 
							this.scaleType + " - " + this.methodType );
	}
	
	public void delete(){
		// TODO
		System.out.println("Fake Delete : " + this.code + " - "  + 
							this.component + " - " + this.kindOfProperty + " - " +
							this.timeAspect + " - " + this.system + " - " + 
							this.scaleType + " - " + this.methodType );
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
	
	public void fillInput(String code, String component, String kindOfProperty,
						  String timeAspect, String system, String scaleType, String methodType){
		this.code = code;
		this.component = component;
		this.kindOfProperty = kindOfProperty;
		this.timeAspect = timeAspect;
		this.system = system;
		this.scaleType = scaleType;
		this.methodType = methodType;
	}


	public List<LOINCCode> getCodesWithFilter(){
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

	public LOINCCode getLoincCode() {
		return loincCode;
	}

	public void setLoincCode(LOINCCode loincCode) {
		this.loincCode = loincCode;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getTimeAspect() {
		return timeAspect;
	}

	public void setTimeAspect(String timeAspect) {
		this.timeAspect = timeAspect;
	}

	public String getKindOfProperty() {
		return kindOfProperty;
	}

	public void setKindOfProperty(String kindOfProperty) {
		this.kindOfProperty = kindOfProperty;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}	
	
}
