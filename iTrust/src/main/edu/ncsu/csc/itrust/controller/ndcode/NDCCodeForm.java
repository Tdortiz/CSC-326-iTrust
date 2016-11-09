package edu.ncsu.csc.itrust.controller.ndcode;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.ncsu.csc.itrust.model.ndcode.NDCCode;

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
		controller = (cptCodeController == null) ? new NDCCodeController() : cptCodeController;
		search = "";
		setDisplayCodes(false);
	}

	public void add() {
		setNDCCode(new NDCCode(code, description));
		controller.add(ndcCode);
		code = "";
		description = "";
	}

	public void update() {
		setNDCCode(new NDCCode(code, description));
		controller.edit(ndcCode);
		code = "";
		description = "";
	}

	public void delete() {
		setNDCCode(new NDCCode(code, description));
		controller.remove(code);
		code = "";
		description = "";
	}

	public List<NDCCode> getCodesWithFilter() {
		return controller.getCodesWithFilter(search);
	}

	public void fillInput(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public NDCCode getNDCCode() {
		return ndcCode;
	}

	public void setNDCCode(NDCCode ndcCode) {
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
