package edu.ncsu.csc.itrust.unit.webutils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class SessionUtilsTest {
	
	private SessionUtils utils;

	@Before
	public void setUp() throws Exception {
		utils = SessionUtils.getInstance();
	}

	@Test
	public void testParseSessionVariable() {
		Assert.assertEquals("1", utils.parseString(1L));
		Assert.assertEquals("1", utils.parseString("1"));
		Assert.assertNull(utils.parseString(1));
	}
	
	/*@Test
	public void testGetSessionVariable() {
		final String TEST_VALUE = "testValue";
		final String TEST_KEY = "testKey";

		Mockito.doReturn(TEST_VALUE).when(mockHttpSession).getAttribute(TEST_KEY);
		Mockito.doReturn(mockHttpServletRequest).when(mockSessionUtils).getHttpServletRequest();
		Mockito.doReturn(mockHttpSession).when(mockSessionUtils).getSession(Mockito.anyBoolean());

		Assert.assertEquals(TEST_VALUE, ovc.getSessionVariable(TEST_KEY));
	}

	@Test
	public void testGetSessionVariableWithNullSession() {
		Mockito.doReturn(mockHttpServletRequest).when(ovc).getHttpServletRequest();
		Mockito.doReturn(null).when(mockHttpServletRequest).getSession(Mockito.anyBoolean());

		assertEquals("", ovc.getSessionVariable("literally anything"));
	}

	@Test
	public void testGetSessionVariableWithNullRequest() {
		Mockito.doReturn(null).when(ovc).getHttpServletRequest();
		Object variable = ovc.getSessionVariable("literally anything");
		Assert.assertEquals("", variable);
	}
	
	@Test
	public void testGetSessionUserRole() {
		final String TEST_VALUE = "some role";
		final String TEST_KEY = "userRole";

		Mockito.doReturn(TEST_VALUE).when(mockHttpSession).getAttribute(TEST_KEY);
		Mockito.doReturn(mockHttpServletRequest).when(ovc).getHttpServletRequest();
		Mockito.doReturn(mockHttpSession).when(mockHttpServletRequest).getSession(Mockito.anyBoolean());

		Assert.assertEquals(TEST_VALUE, ovc.getSessionUserRole());
	}

	@Test
	public void testGetSessionPID() {
		final String TEST_VALUE = "1";
		final String TEST_KEY = "pid";

		Mockito.doReturn(TEST_VALUE).when(mockHttpSession).getAttribute(TEST_KEY);
		Mockito.doReturn(mockHttpServletRequest).when(ovc).getHttpServletRequest();
		Mockito.doReturn(mockHttpSession).when(mockHttpServletRequest).getSession(Mockito.anyBoolean());

		Assert.assertEquals(TEST_VALUE, ovc.getSessionPID());
	}

	@Test
	public void testGetSessionLoggedInMID() {
		final String TEST_VALUE = "1";
		final String TEST_KEY = "loggedInMID";

		Mockito.doReturn(TEST_VALUE).when(mockHttpSession).getAttribute(TEST_KEY);
		Mockito.doReturn(mockHttpServletRequest).when(ovc).getHttpServletRequest();
		Mockito.doReturn(mockHttpSession).when(mockHttpServletRequest).getSession(Mockito.anyBoolean());

		Assert.assertEquals(TEST_VALUE, ovc.getSessionLoggedInMID());
	}

	@Test
	public void testGetCurrentPatientMID() {
		final String MID = "1";
		final String PATIENT = "patient";
		final String LOGGED_IN_MID = "loggedInMID";
		final String USER_ROLE = "userRole";

		Mockito.doReturn(PATIENT).when(mockHttpSession).getAttribute(USER_ROLE);
		Mockito.doReturn(MID).when(mockHttpSession).getAttribute(LOGGED_IN_MID);
		Mockito.doReturn(mockHttpServletRequest).when(ovc).getHttpServletRequest();
		Mockito.doReturn(mockHttpSession).when(mockHttpServletRequest).getSession(Mockito.anyBoolean());

		Assert.assertEquals(MID, ovc.getCurrentPatientMID());
	}*/
}
