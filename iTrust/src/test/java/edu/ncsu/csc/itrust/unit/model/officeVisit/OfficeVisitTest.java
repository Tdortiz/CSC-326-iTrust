package edu.ncsu.csc.itrust.unit.model.officeVisit;


import org.junit.Assert;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;

public class OfficeVisitTest extends TestCase {
	private OfficeVisit test;

	@Override
	protected void setUp() throws Exception {
		test = new OfficeVisit();
	}

	/*
	 * updated to reflect the new way addAllergy updates allergyDAO.
	 */
	public void testPatientMID() throws Exception {
		test.setPatientMID(100001L);
		Assert.assertEquals(100001L, test.getPatientMID());
		
	}
	
	public void testApptTypeID(){
		
	}

}
