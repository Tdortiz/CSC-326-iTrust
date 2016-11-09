package edu.ncsu.csc.itrust.controller.cptcode;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import edu.ncsu.csc.itrust.model.cptcode.CPTCode;

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
		controller = (cptCodeController == null) ? new CPTCodeController() : cptCodeController;
		search = "";
		setDisplayCodes(false);
	}
	
	public void add(){
	    setCptCode(new CPTCode(code, description));
        controller.add(cptCode);
	}
	
	public void update(){
        setCptCode(new CPTCode(code, description));
		controller.edit(cptCode);
	}
	
	public void delete(){
        setCptCode(new CPTCode(code, description));
		controller.remove(code);
	}
	
	public List<CPTCode> getCodesWithFilter(){
		return controller.getCodesWithFilter(search);
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
