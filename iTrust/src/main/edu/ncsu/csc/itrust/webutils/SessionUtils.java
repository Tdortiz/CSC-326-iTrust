package edu.ncsu.csc.itrust.webutils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;

public class SessionUtils {

	/**
	 * Name of patient role 
	 */
	private static final String PATIENT = "patient";

	/**
	 * HttpSession variable name of the current logged in user role.
	 */
	private static final String USER_ROLE = "userRole";

	/**
	 * HttpSession variable name of the current logged in patient MID.
	 */
	private static final String LOGGED_IN_MID = "loggedInMID";

	/**
	 * HttpSession variable name of the HCP selected patient MID.
	 */
	private static final String PID = "pid";
	
	/**
	 * Uses FacesContext to seek a HttpSession variable of a string type within the FaceContext.
	 * 
	 * @param varname
	 * 			variable name in the HTTP session 
	 * @return session variable of any type
	 */
	public static Object getSessionVariable(String varname) {
		Object variable = "";
				
		HttpServletRequest req = getHttpServletRequest();
		
		if (req == null) {
			return variable;
		}
		
		HttpSession httpSession = req.getSession(false);
		
		if (httpSession == null) {
			return variable;
		}
		
		variable = httpSession.getAttribute(varname);
		
		return variable;
	}
	
	/**
	 * Returns the session variable in string form.
	 * @param variable A session variable in Object form, could be of String type or Long type
	 * @return String representation of the session variable, or null if it is neither String nor Long
	 */
	public static String parseSessionVariable(Object variable) {
		if (variable instanceof String) {
			return (String) variable;
		} else if(variable instanceof Long) {
			return ((Long) variable).toString();
		} else {
			return null;
		}
	}
	
	/**
	 * @return role of the currently logged in user
	 */
	public static String getSessionUserRole() {
		return parseSessionVariable(getSessionVariable(USER_ROLE));
	}
	
	/**
	 * @return MID of the patient that the HCP selected in the session
	 */
	public static String getSessionPID() {
		return parseSessionVariable(getSessionVariable(PID));
	}
	
	/**
	 * @return current logged in patient's MID
	 */
	public static String getSessionLoggedInMID() {
		return parseSessionVariable(getSessionVariable(LOGGED_IN_MID));
	}
	
	/**
	 * Checks whether if a patient is logged in, if so, retrieve this patient's mid,
	 * otherwise, check whether if an HCP selected a patient in his/her session.
	 * 
	 * @return MID of patient, null if no patient is logged in or selected by HCP
	 */
	public static String getCurrentPatientMID() {
		String patientMID = getSessionPID();
		String role = getSessionUserRole();
		if (role != null && role.equals(PATIENT)) {
			patientMID = getSessionLoggedInMID();
		}
		return patientMID;
	}
	
	/**
	 * @return HTTPRequest in FacesContext, null if no request is found
	 */
	private static HttpServletRequest getHttpServletRequest() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return null;
		}
		return ctx.getExternalContext().getRequest() instanceof HttpServletRequest ? (HttpServletRequest) ctx.getExternalContext().getRequest() : null;
	}
}
