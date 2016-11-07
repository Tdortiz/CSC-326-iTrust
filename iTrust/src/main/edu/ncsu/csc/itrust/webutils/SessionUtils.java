package edu.ncsu.csc.itrust.webutils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Singleton class containing session-related utility methods.
 * @author mwreesjo
 */
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
	 * Get the current office visit id that the user (HCP) is viewing.
	 */
	private static final String OFFICE_VISIT_ID = "officeVisitId";

	/** The singleton instance of this class. */
	private static SessionUtils singleton = null;
	
	private SessionUtils() {}
	
	/**
	 * Uses FacesContext to seek a HttpSession variable of a string type within
	 * the FaceContext.
	 * 
	 * @param varname
	 *            variable name in the HTTP session
	 * @return session variable of any type
	 */
	public Object getSessionVariable(String varname) {
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
	 * 
	 * @param variable
	 *            A session variable in Object form, could be of String type or
	 *            Long type
	 * @return String representation of the session variable, or null if it is
	 *         neither String nor Long
	 */
	public String parseString(Object variable) {
		if (variable instanceof String) {
			return (String) variable;
		} else if (variable instanceof Long) {
			return ((Long) variable).toString();
		} else {
			return null;
		}
	}

	/**
	 * Returns the session variable in Long form.
	 * 
	 * @param variable
	 *            A session variable in Object form, could be of String type or
	 *            Long type
	 * @return Long representation of the session variable, or null if it is not Long
	 */
	public Long parseLong(Object variable) {
		if (variable instanceof Long) {
			return (Long) variable;
		}
		return null;
	}

	/**
	 * @return role of the currently logged in user
	 */
	public String getSessionUserRole() {
		return parseString(getSessionVariable(USER_ROLE));
	}

	/**
	 * @return MID of the patient that the HCP selected in the session
	 */
	public String getSessionPID() {
		return parseString(getSessionVariable(PID));
	}

	/**
	 * @return current logged in patient's MID
	 */
	public String getSessionLoggedInMID() {
		return parseString(getSessionVariable(LOGGED_IN_MID));
	}

	/**
	 * Checks whether if a patient is logged in, if so, retrieve this patient's
	 * mid, otherwise, check whether if an HCP selected a patient in his/her
	 * session.
	 * 
	 * @return MID of patient, null if no patient is logged in or selected by
	 *         HCP
	 */
	public String getCurrentPatientMID() {
		String patientMID = getSessionPID();
		String role = getSessionUserRole();
		if (role != null && role.equals(PATIENT)) {
			patientMID = getSessionLoggedInMID();
		}
		return patientMID;
	}
	
	/**
	 * @return office visit that the current logged in user (HCP) selected
	 */
	public Long getCurrentOfficeVisitId() {
		return parseLong(getSessionVariable(OFFICE_VISIT_ID));
	}

	/**
	 * Returns the value of a request parameter as a String, or null if the
	 * parameter does not exist.
	 * 
	 * @param name a String specifying the name of the parameter
	 * @return a String representing the single value of the parameter
	 */
	public String getRequestParameter(String name) {
		HttpServletRequest req = getHttpServletRequest();
		return (req == null) ? null : req.getParameter(name);
	}

	/**
	 * @return HTTPRequest in FacesContext, null if no request is found
	 */
	private HttpServletRequest getHttpServletRequest() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return null;
		}
		return ctx.getExternalContext().getRequest() instanceof HttpServletRequest
				? (HttpServletRequest) ctx.getExternalContext().getRequest() : null;
	}
	
	/**
	 * Sends a FacesMessage for FacesContext to display.
	 * 
	 * @param severity
	 *            severity of the message
	 * @param summary
	 *            localized summary message text
	 * @param detail
	 *            localized detail message text
	 * @param clientId
	 *            The client identifier with which this message is associated
	 *            (if any)
	 */
	public void printFacesMessage(Severity severity, String summary, String detail, String clientId) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return;
		}
		ctx.getExternalContext().getFlash().setKeepMessages(true);
		ctx.addMessage(clientId, new FacesMessage(severity, summary, detail));
	}
	
	/**
	 * @return the current instance of FacesContext
	 */
	public FacesContext getCurrentFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public static SessionUtils getInstance() {
		if (singleton == null) 
			singleton = new SessionUtils();
		return singleton;
	}
}
