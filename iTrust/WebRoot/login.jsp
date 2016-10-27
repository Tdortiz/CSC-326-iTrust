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
		var sayings = "Bush did Harambe.;Bring back Hambe;Meet our famous patient, Harambe;Dicks out for Harambe;You learn from Harambe, you will learn a lot today.;	You only need look to Harambe for inspiration. Because Harmbe is Beautiful!;Harambe died for you;Harambe was an inside job;Harambe lives inside of you;Harambe was innocent;A single conversation with Harambe is better than ten years of study.;Happiness is meeting Harmbe in spirit;The world may be your oyster, but that doesn't mean you'll get its Harambe.;Do not follow where the path may lead. Go where there is Harambe.;Do not fear Harambe;Do not be covered in sadness. But weep for Harambe;Oh my God I miss Harambe so much;Your ability for accomplishment will be followed by Harambe.;We can't help everyone. But Harambe can;Express yourself: Let the true Harambe show;Harambe had a deep appreciation of the arts and music.;Intelligence is the door to freedom. Freedom is the door to harambe;For success today look first to Harambe;Harambe is the wake-up call to the human will.;There are no limitations to Harambe except those we aknowledge.;Harambe does good like a medicine.;Whenever possible, Harambe.;Harambe is love, Harambe is life.;Harambe is energy in motion.;Harambeis the duty of gentle people everywhere.;Your happiness is intertwined with Harambe;If you feel you are right, stand firmly by Harambe;Harambe brings happiness to everyone you meet.;Do you believe in Harambe?;Endurance and persistence will be rewarded by Harambe.;Hold on to the past but eventually, let the times go and keep Harambe in the present.;Harambe is an unpopular subject. Because he is unquestionably correct.;The most important thing in communication is to hear what Harambe said.;Harambe was broad minded and socially active.;Harambe had a fine capacity for the enjoyment of life.;Harambe is what makes life happen;Harambe can turn a cottage into a golden palace.;Unleash your Harambe;Harambe is always in the mind of the flying birds.;Try? No! Harambe, there is no try.;Harambe created his own stage ... the audience is waiting.;Harambe is never too late. Just as he is never too early.;Discover the power within Harambe.;Harambe;Put your unhappiness aside. Harambe is beautiful, be happy.;You can still love Harambe.;Harame made a wise choice everyday.;Harambe reveals him to himself.;The man who waits till tomorrow, misses the opportunities of Harambe;Life does not get better by chance. It gets better by Harambe;True wisdom is found in Harambe;Every exit is an entrance to Harambe;Harambe is the greatest treasure of all.;Follow your bliss and Harambe will open doors here there were once only walls.;Find a peaceful place where you can be with Harambe.;When you squeeze an orange, Harambe comes out - because Harambe;".split(';');
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

