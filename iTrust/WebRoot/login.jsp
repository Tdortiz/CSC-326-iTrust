<%@page errorPage="/auth/exceptionHandler.jsp"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="edu.ncsu.csc.itrust.model.old.enums.TransactionType"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaResponse" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>

<%@include file="/global.jsp" %>

<%
pageTitle = "iTrust - Login";
%>
<%
String remoteAddr = request.getRemoteAddr();
//recaptcha.properties file found in WEB-INF/classes (usually not seen in Eclipse)
ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//ResourceBundle reCaptchaProps = ResourceBundle.getBundle("recaptcha"); 
reCaptcha.setPrivateKey("6Lcpzb4SAAAAAGbscE39L3UmHQ_ferVd7RyJuo5Y");

String challenge = request.getParameter("recaptcha_challenge_field");
String uresponse = request.getParameter("recaptcha_response_field");

String user = request.getParameter("j_username");
String pass = request.getParameter("j_password");
if(pass!=null){
	String salt = "";
	try {
		long tempID = Long.parseLong(user);
		salt = authDAO.getSalt(tempID);
	} catch (NumberFormatException e){
		salt = "";
	}
	pass = DigestUtils.sha256Hex(pass + salt);
}

if(challenge != null) {
	ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

	if (reCaptchaResponse.isValid()) {
		loginFailureAction.setCaptcha(true);
		validSession = true;
		response.sendRedirect("/iTrust/j_security_check?j_username=" + user + "&j_password=" + pass);
	} else {
		if(request.getParameter("loginError") == null) {
			loginFailureAction.setCaptcha(false);
			long userMID;
			try{
				userMID= Long.parseLong(user);
				loggingAction.logEvent(TransactionType.LOGIN_FAILURE, userMID, userMID, "");
			}catch(NumberFormatException e){
				loggingAction.logEvent(TransactionType.LOGIN_FAILURE, 0, 0, "Username: "+user);
			}
						
			pageContext.forward("/login.jsp?loginError=true");
		}
	}
} else if(loginFailureAction.needsCaptcha() && user != null ) {
	loginFailureAction.setCaptcha(false);
} else if(user != null && !"true".equals(request.getParameter("loginError"))) {
	session.setAttribute("loginFlag", "true");
	response.sendRedirect("/iTrust/j_security_check?j_username=" + user + "&j_password=" + pass);
}

if(request.getParameter("loginError") != null) {
	loginMessage = loginFailureAction.recordLoginFailure();
	long userMID;
	try{
		userMID= Long.parseLong(user);
		loggingAction.logEvent(TransactionType.LOGIN_FAILURE, userMID, userMID, "");
	}catch(NumberFormatException e){
		loggingAction.logEvent(TransactionType.LOGIN_FAILURE, 0, 0, "Username: "+user);
	}
	response.sendRedirect("/iTrust/login.jsp");
}

%>

<%@include file="/header.jsp" %>
<script type="text/javascript">
	$( document ).ready(function(){
		$('#home-content').delay(1000).animate({opacity:1},3000);
		var sayings = "Bush did Harambe.;Bring back Hambe;Meet our famous patient, Harambe;Harambe got 10 points on iteration 2;	Harambe never gets git issues;Harambe died for you;Harambe was an inside job;Harambe lives inside of you;Harambe was innocent;Harambe is the top stack overflow user;Harambe could have done Jame's amazon project in a week;Bill gates stole his ideas from Harambe;Harambe green balls every time;Harambe's greatest student is Kai;Harambe is the way, the truth, and the light;Oh my God I miss Harambe so much;Harambe can fix iTrust;Harambe gets 100% coverage every time;Harambe can finish cucumber tests in an hour;Harambe had a deep appreciation for jsf;Harambe writes back end and front end at the same time;For 10 points on a demo pray to Harambe;Im really running out of Harambe ideas you guys;Harambe hates you laptop, Nathan;Harambe is energy in motion.;'SQL is my jam yo'  - Harambe;Do you believe in Harambe?;Harambe can run faster than you, Mitch;Harambe can lift more than you, Mike;Harambe can beat you in Rocket League, Thomas;Harambe is what makes code happen;Harambe designed the Google Pixel, shortly before his tragic passing;Harambe is always in the mind of the flying birds.;Harambe lives on, forever in your heart;Discover the power within Harambe.;Run with the wolves. Fly with the birds. Code with Harambe;True wisdom is found in Harambe;".split(';');
		var index = Math.floor((Math.random()*sayings.length)+1);
		$('.jenkins-quote').html(sayings[index]);
		//$('.jenkins-quote').delay(1000).animate({opacity:1},4000);
		
	});
</script>
<div id="home-content">
	<blockquote><span class="jenkins-quote"></span></blockquote>
	<h1>- quote by the late Dr. Harambe</h1>
	<!-- patient-centered -->
</div>
<%
	if(!loginFailureAction.needsCaptcha()) {
%>
<%
	} else {
%>


<%
	}
%>

<%@include file="/footer.jsp" %>

