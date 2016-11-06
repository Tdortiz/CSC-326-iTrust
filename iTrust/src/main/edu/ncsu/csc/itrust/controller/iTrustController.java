package edu.ncsu.csc.itrust.controller;

import javax.faces.application.FacesMessage.Severity;

import edu.ncsu.csc.itrust.logger.TransactionLogger;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * Base class for all controllers in iTrust containing functionality universally
 * applicable to controllers.
 * 
 * @author mwreesjo
 */
public class iTrustController {
	
	protected SessionUtils sessionUtils;
	
	public iTrustController() {
		this(null);
	}
	
	public iTrustController(SessionUtils sessionUtils) {
		if(sessionUtils == null) {
			sessionUtils = new SessionUtils();
		}
		this.sessionUtils = sessionUtils;
	}
	
	public void setSessionUtils(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}
	
	/**
	 * @see {@link SessionUtils#printFacesMessage(Severity, String, String, String)}
	 */
	public void printFacesMessage(Severity severity, String summary, String detail, String clientId) {
		sessionUtils.printFacesMessage(severity, summary, detail, clientId);
	}
	
	/**
	 * @see {@link TransactionLogger#logTransaction(TransactionType, long, long, String)}
	 */
	public void logTransaction(TransactionType type, Long loggedInMID, Long secondaryMID, String addedInfo) {
		TransactionLogger.getInstance().logTransaction(type, loggedInMID, secondaryMID, addedInfo);
	}
}
