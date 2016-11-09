package edu.ncsu.csc.itrust.controller.icdcode;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.ncsu.csc.itrust.model.icdcode.ICDCode;

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
		controller = (icdCodeController == null) ? new ICDCodeController() : icdCodeController;
		search = "";
		setDisplayCodes(false);
	}
	
	public void add(){
	    setIcdCode(new ICDCode(code, description, isChronic));
        controller.add(icdCode);
	}
	
	public void update(){
	    setIcdCode(new ICDCode(code, description, isChronic));
        controller.edit(icdCode);
	}
	
	public void delete(){
	    setIcdCode(new ICDCode(code, description, isChronic));
        controller.remove(code);
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

	public void setIsChronic(boolean isChronic) {
		this.isChronic = isChronic;
	}

	
	
}
