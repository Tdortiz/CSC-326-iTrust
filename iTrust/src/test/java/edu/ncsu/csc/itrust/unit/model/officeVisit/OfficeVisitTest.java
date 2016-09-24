package edu.ncsu.csc.itrust.unit.model.officeVisit;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.apptType.ApptType;
import edu.ncsu.csc.itrust.model.apptType.ApptTypeData;
import edu.ncsu.csc.itrust.model.apptType.ApptTypeMySQLConverter;
import edu.ncsu.csc.itrust.model.hospital.Hospital;
import edu.ncsu.csc.itrust.model.hospital.HospitalData;
import edu.ncsu.csc.itrust.model.hospital.HospitalMySQLConverter;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class OfficeVisitTest extends TestCase {
	private OfficeVisit test;
	private ApptTypeData apptData;
	private TestDataGenerator gen;
	private DataSource ds;

	@Override
	protected void setUp() throws Exception {
		ds = ConverterDAO.getDataSource();
		test = new OfficeVisit();
		apptData = new ApptTypeMySQLConverter(ds);
		gen = new TestDataGenerator();
		
	}
	@Test
	public void testApptTypeID() throws DBException, FileNotFoundException, IOException, SQLException{
		gen.appointmentType();
		List<ApptType> types = apptData.getAll();
		long apptTypeID = types.get((types.size()-1)).getID();
		test.setApptTypeID(apptTypeID);
		long testID = test.getApptTypeID();
		Assert.assertEquals(apptTypeID, testID);		
	}
	@Test
	public void testDate() {
		LocalDateTime testTime = LocalDateTime.now();
		test.setDate(testTime);
		Assert.assertTrue(ChronoUnit.MINUTES.between(testTime, test.getDate())<1);
	}
	@Test
	public void testLocationID() throws FileNotFoundException, SQLException, IOException, DBException{
		gen.hospitals();
		HospitalData hData = new HospitalMySQLConverter(ds);
		List<Hospital> all = hData.getAll();
		String id = all.get(0).getHospitalID();
		test.setLocationID(id);
		Assert.assertEquals(id, test.getLocationID());
	}
	@Test
	public void testNotes(){
		String note = "ABCDEF123><$%> ";
		test.setNotes(note);
		Assert.assertEquals(note, test.getNotes());
	}
	@Test
	public void testBill(){
		test.setSendBill(true);
		Assert.assertTrue(test.getSendBill());
		test.setSendBill(false);
		Assert.assertFalse(test.getSendBill());
	}
	@Test
	public void testPatient() throws FileNotFoundException, IOException, SQLException{
		gen.patient1();
		test.setPatientMID(1L);
		Assert.assertEquals(1L,test.getPatientMID());
	}
	@Test
	public void testID(){
		test.setVisitID(1L);
		long check = test.getVisitID();
		Assert.assertEquals(1L, check);
	}
	// Begin JUnit tests for UC 51
	@Test
	/** Tests height methods. */
	public void testHeight(){
		test.setHeight(1F);
		float check = test.getHeight();
		Assert.assertEquals(1F, check, .01);
	}
	@Test
	/** Tests weight methods. */
	public void testWeight(){
		test.setWeight(1F);
		float check = test.getWeight();
		Assert.assertEquals(1F, check, .01);
	}
	@Test
	/** Tests headCircunference methods. */
	public void testHeadCircumference(){
		test.setHeadCircumference(1F);
		float check = test.getHeadCircumference();
		Assert.assertEquals(1F, check, .01);
	}
	@Test
	/** Tests bloodPressure methods. */
	public void testBloodPressure(){
		String bp = "140/90";
		test.setBloodPressure(bp);
		Assert.assertEquals(bp, test.getBloodPressure());
	}
	@Test
	/** Tests HDL Cholesterol methods. */
	public void testHDL(){
		test.setHDL(1);
		long check = test.getHDL();
		Assert.assertEquals(1, check);
	}
	@Test
	/** Tests Triglyceride Cholesterol methods. */
	public void testTriglyceride(){
		test.setTriglyceride(1);
		long check = test.getTriglyceride();
		Assert.assertEquals(1, check);
	}
	@Test
	/** Tests LDL Cholesterol methods. */
	public void testLDL(){
		test.setLDL(1);
		long check = test.getLDL();
		Assert.assertEquals(1, check);
	}
	@Test
	/** Tests Household Smoking Status methods. */
	public void testHouseholdSmokingStatus(){
		test.setHouseholdSmokingStatus(1);
		long check = test.getHouseholdSmokingStatus();
		Assert.assertEquals(1, check);
	}
	@Test
	/** Tests Patient Smoking Status methods. */
	public void testPatientSmokingStatus(){
		test.setPatientSmokingStatus(1);
		long check = test.getPatientSmokingStatus();
		Assert.assertEquals(1, check);
	}
}