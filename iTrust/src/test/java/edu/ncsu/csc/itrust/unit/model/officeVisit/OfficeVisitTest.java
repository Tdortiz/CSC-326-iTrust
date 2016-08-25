package edu.ncsu.csc.itrust.unit.model.officeVisit;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Assert;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.apptType.ApptType;
import edu.ncsu.csc.itrust.model.apptType.ApptTypeData;
import edu.ncsu.csc.itrust.model.apptType.ApptTypeMySQLConverter;
import edu.ncsu.csc.itrust.model.hospital.Hospital;
import edu.ncsu.csc.itrust.model.hospital.HospitalData;
import edu.ncsu.csc.itrust.model.hospital.HospitalMySQLConverter;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;

public class OfficeVisitTest extends TestCase {
	private OfficeVisit test;
	private ApptTypeData apptData;

	@Override
	protected void setUp() throws Exception {
		test = new OfficeVisit();
		apptData = new ApptTypeMySQLConverter();
	}

	/*
	 * updated to reflect the new way addAllergy updates allergyDAO.
	 */
	public void testPatientMID() throws Exception {
		test.setPatientMID(100001L);
		Assert.assertEquals(100001L, test.getPatientMID());
		
	}
	
	public void testApptTypeID() throws DBException{
		List<ApptType> types = apptData.getAll();
		long apptTypeID = types.get((types.size()-1)).getID();
		test.setApptTypeID(apptTypeID);
		long testID = test.getApptTypeID();
		Assert.assertEquals(apptTypeID, testID);		
	}
	
	public void testDate() {
		LocalDateTime testTime = LocalDateTime.now();
		test.setDate(testTime);
		Assert.assertTrue(ChronoUnit.MINUTES.between(testTime, test.getDate())<1);
	}


}
