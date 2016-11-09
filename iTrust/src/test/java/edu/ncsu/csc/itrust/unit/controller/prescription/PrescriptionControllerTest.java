package edu.ncsu.csc.itrust.unit.controller.prescription;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import edu.ncsu.csc.itrust.controller.prescription.PrescriptionController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureData;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class PrescriptionControllerTest {

	@Mock
	private SessionUtils mockSessionUtils;
	@Mock
	private LabProcedureData mockData;
	@Spy
	private PrescriptionController controller;
	@Spy
	private DataSource ds;
	private TestDataGenerator gen;
	
	@Before
	public void setUp() throws FileNotFoundException, SQLException, IOException, DBException {
		ds = Mockito.spy(ConverterDAO.getDataSource());
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.uc21();
		gen.uc19();
		controller = Mockito.spy(new PrescriptionController(ds));
		mockSessionUtils = Mockito.mock(SessionUtils.class);
		controller.setSessionUtils(mockSessionUtils);
		mockData = Mockito.mock(LabProcedureData.class);
	}

	@After
	public void tearDown() throws FileNotFoundException, SQLException, IOException {
		gen.clearAllTables();
	}
	
	@Test
	public void testGetPrescriptionByID() throws SQLException {
		assertNull(controller.getPrescriptionByID(null));
		Mockito.verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_ERROR), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		assertNull(controller.getPrescriptionByID("-1"));
		Mockito.verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_ERROR), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testGetPrescriptionsForCurrentPatient() throws Exception {
		Mockito.doReturn("201").when(mockSessionUtils).getCurrentPatientMID();
		assertEquals(3, controller.getPrescriptionsForCurrentPatient().size());
		
		Mockito.doReturn("invalid id").when(mockSessionUtils).getCurrentPatientMID();
		assertEquals(0, controller.getPrescriptionsForCurrentPatient().size());
		Mockito.verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_ERROR), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		
		Mockito.doReturn("-1").when(mockSessionUtils).getCurrentPatientMID();
		assertEquals(0, controller.getPrescriptionsForCurrentPatient().size());
		Mockito.verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_ERROR), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		
		Mockito.doThrow(SQLException.class).when(ds).getConnection();
		Mockito.doReturn("201").when(mockSessionUtils).getCurrentPatientMID();
		assertEquals(0, controller.getPrescriptionsForCurrentPatient().size());
	}
	
	@Test
	public void testGetListOfRepresentees() throws Exception {
		Mockito.doReturn(null).when(mockSessionUtils).getRepresenteeList();
		Mockito.doReturn(2L).when(mockSessionUtils).getSessionLoggedInMIDLong();
		Mockito.doNothing().when(mockSessionUtils).setRepresenteeList(Mockito.any());
		List<PatientBean> list = controller.getListOfRepresentees();
		assertNotNull(list);
	}
	
}
