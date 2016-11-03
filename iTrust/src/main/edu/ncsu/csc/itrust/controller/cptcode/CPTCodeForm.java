package edu.ncsu.csc.itrust.controller.cptcode;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.controller.NavigationController;
import edu.ncsu.csc.itrust.controller.labProcedure.LabProcedureController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.cptcode.CPTCode;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "cpt_code_form")
@ViewScoped
public class CPTCodeForm {
	private CPTCodeController controller;
	private CPTCode cptCode;
	private String search;

	public CPTCodeForm() {
		this(null);
	}
	
	public CPTCodeForm(LabProcedureController ovc) {
		try {
			controller = (ovc == null) ? new CPTCodeController() : controller;
			search = "";
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lab Procedure Controller Error",
					"Lab Procedure Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
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

	
	public List<CPTCode> getCodesWithFilter(){
		return controller.getCodesWithFilter(search);
	}
	
	
}
