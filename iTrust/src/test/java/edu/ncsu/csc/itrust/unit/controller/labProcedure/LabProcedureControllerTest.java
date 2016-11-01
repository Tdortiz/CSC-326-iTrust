package edu.ncsu.csc.itrust.unit.controller.labProcedure;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.labProcedure.LabProcedureController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure.LabProcedureStatus;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureData;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * Unit tests for LabProcedureController.
 * 
 * @author mwreesjo
 */
public class LabProcedureControllerTest {

	private DataSource ds;
	private LabProcedureController controller;
	private TestDataGenerator gen;
	private LabProcedure procedure;

	@Before
	public void setUp() throws FileNotFoundException, SQLException, IOException {
		ds = ConverterDAO.getDataSource();
		gen = new TestDataGenerator();
		gen.clearAllTables();
		controller = new LabProcedureController(ds);

		procedure = new LabProcedure();
		procedure.setCommentary("commentary");
		procedure.setIsRestricted(true);
		procedure.setLabProcedureID(8L);
		procedure.setLabTechnicianID(9L);
		procedure.setOfficeVisitID(10L);
		procedure.setPriority(3);
		procedure.setResults("results!");
		procedure.setStatus(1L);
		procedure.setUpdatedDate(new Timestamp(100L));
	}

	@After
	public void tearDown() throws FileNotFoundException, SQLException, IOException {
		gen.clearAllTables();
	}

	/**
	 * Tests happy path retrieval of one lab procedure.
	 */
	@Test
	public void testGetLabProceduresByLabTechnicianSingleResult()
			throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure5();
		List<LabProcedure> procs = null;
		try {
			procs = controller.getLabProceduresByLabTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure procedure = procs.get(0);
		Assert.assertEquals("This is a lo-pri lab procedure", procedure.getCommentary());
		Assert.assertEquals("Foobar", procedure.getResults());
		Assert.assertTrue(procedure.getLabTechnicianID() == 5000000001L);
		Assert.assertTrue(procedure.getOfficeVisitID() == 4);
		Assert.assertTrue(procedure.getPriority() == 3);
		Assert.assertEquals("Pending", procedure.getStatus().getName());
	}

	/**
	 * Tests that getLabProceduresByLabTechnician() returns the correct lab
	 * procedures in the correct order
	 * 
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testGetLabProceduresByLabTechnicianMultipleResults()
			throws FileNotFoundException, SQLException, IOException {
		// Order should be lab procedure 1, 4, 3, 2, 0 based on the sort order
		// defined in S7 in UC26
		// (priority lo->hi, then date old->new)
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getLabProceduresByLabTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 5);
		Assert.assertTrue(procs.get(0).getPriority() == 1);
		Assert.assertTrue(procs.get(1).getPriority() == 2);
		Assert.assertTrue(procs.get(2).getPriority() == 3);
		Assert.assertEquals("In testing status", procs.get(2).getCommentary());
		Assert.assertEquals("In received status", procs.get(3).getCommentary());
		Assert.assertEquals("This is a lo-pri lab procedure", procs.get(4).getCommentary());
	}

	/**
	 * Tests that getLabProceduresByOfficeVisit() returns the correct lab
	 * procedures in the correct order
	 * 
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testGetLabProceduresByOfficeVisit() throws FileNotFoundException, SQLException, IOException {
		// Should return lab procedures 4, 3, 2 in that order
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getLabProceduresByOfficeVisit("3");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 3);
		Assert.assertTrue(procs.get(0).getPriority() == 2);
		Assert.assertTrue(procs.get(1).getPriority() == 3);
		Assert.assertTrue(procs.get(2).getPriority() == 3);
		Assert.assertEquals("In completed status", procs.get(0).getCommentary());
		Assert.assertEquals("In testing status", procs.get(1).getCommentary());
		Assert.assertEquals("In received status", procs.get(2).getCommentary());
	}

	@Test
	public void testGetCompletedLabProceduresByOfficeVisit() throws FileNotFoundException, SQLException, IOException {
		// Should only return labProcedure4
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getCompletedLabProceduresByOfficeVisit("3");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		Assert.assertEquals("In completed status", procs.get(0).getCommentary());
		Assert.assertEquals(new Long(5L), procs.get(0).getLabProcedureID());
	}

	@Test
	public void testGetNonCompletedLabProceduresByOfficeVisit()
			throws FileNotFoundException, SQLException, IOException {
		// Should return labProcedure{2, 3}
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getNonCompletedLabProceduresByOfficeVisit("3");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertEquals(new Integer(2), new Integer(procs.size()));
		Assert.assertEquals("In testing status", procs.get(0).getCommentary());
		Assert.assertEquals(new Long(4L), procs.get(0).getLabProcedureID());

		Assert.assertEquals("In received status", procs.get(1).getCommentary());
		Assert.assertEquals(new Long(3L), procs.get(1).getLabProcedureID());
	}

	/**
	 * Tests that getPendingLabProceduresByTechnician() returns only and all
	 * pending lab procedures.
	 */
	@Test
	public void getPendingLabProceduresByTechnician() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getPendingLabProceduresByTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure pending = procs.get(0);
		Assert.assertEquals("This is a lo-pri lab procedure", pending.getCommentary());
		Assert.assertEquals("Foobar", pending.getResults());
		Assert.assertTrue(pending.getLabTechnicianID() == 5000000001L);
		Assert.assertTrue(pending.getOfficeVisitID() == 4);
		Assert.assertTrue(pending.getPriority() == 3);
		Assert.assertEquals("Pending", pending.getStatus().getName());
	}

	/**
	 * Tests that getInTransitLabProceduresByTechnician() returns only and all
	 * in transit lab procedures.
	 */
	@Test
	public void getInTransitLabProceduresByTechnician() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getInTransitLabProceduresByTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure inTransit = procs.get(0);
		Assert.assertEquals("A hi-pri lab procedure", inTransit.getCommentary());
		Assert.assertEquals("Results are important!", inTransit.getResults());
		Assert.assertTrue(inTransit.getLabTechnicianID() == 5000000001L);
		Assert.assertTrue(inTransit.getOfficeVisitID() == 4);
		Assert.assertTrue(inTransit.getPriority() == 1);
		Assert.assertEquals("In transit", inTransit.getStatus().getName());
	}

	/**
	 * Tests that getReceivedLabProceduresByTechnician() returns only and all
	 * received lab procedures.
	 */
	@Test
	public void getReceivedLabProceduresByTechnician() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getReceivedLabProceduresByTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure received = procs.get(0);
		Assert.assertEquals("Received", received.getStatus().getName());
	}

	/**
	 * Tests that getTestingLabProceduresByTechnician() returns only and all
	 * testing lab procedures.
	 */
	@Test
	public void getTestingLabProceduresByTechnician() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getTestingLabProceduresByTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure testing = procs.get(0);
		Assert.assertEquals("Testing", testing.getStatus().getName());
	}

	/**
	 * Tests that getCompletedLabProceduresByTechnician() returns only and all
	 * completed lab procedures.
	 */
	@Test
	public void getCompletedLabProceduresByTechnician() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		List<LabProcedure> procs = null;
		try {
			procs = controller.getCompletedLabProceduresByTechnician("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure completed = procs.get(0);
		Assert.assertEquals("Completed", completed.getStatus().getName());
	}

	/**
	 * Tests that setLabProcedureToReceivedStatus() correctly sets queries,
	 * updates, and persists the lab procedure with given ID.
	 */
	@Test
	public void testSetLabProcedureToReceivedStatus() throws DBException {
		DataSource mockDS = mock(DataSource.class);
		controller = new LabProcedureController(mockDS);
		controller = spy(controller);
		procedure.setStatus(LabProcedureStatus.IN_TRANSIT.getID());
		LabProcedureData mockData = mock(LabProcedureData.class);
		controller.setLabProcedureData(mockData);
		when(mockData.getByID(Mockito.anyLong())).thenReturn(procedure);
		when(mockData.update(procedure)).thenReturn(true);
		controller.setLabProcedureToReceivedStatus("" + procedure.getLabProcedureID());
		verify(mockData, times(1)).update(procedure);
		verify(controller, times(1)).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_INFO), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString());
		Assert.assertEquals(LabProcedureStatus.RECEIVED, procedure.getStatus());
	}
	
	/**
	 * Tests that setLabProcedureToReceivedStatus() prints a faces message when
	 * a DBException occurs.
	 */
	@Test
	public void testSetLabProcedureToReceivedStatusDBException() throws DBException {
		DataSource mockDS = mock(DataSource.class);
		controller = new LabProcedureController(mockDS);
		controller = spy(controller);
		procedure.setStatus(LabProcedureStatus.IN_TRANSIT.getID());
		LabProcedureData mockData = mock(LabProcedureData.class);
		controller.setLabProcedureData(mockData);
		when(mockData.getByID(Mockito.anyLong())).thenThrow(new DBException(null));
		when(mockData.update(procedure)).thenReturn(false);
		controller.setLabProcedureToReceivedStatus("" + procedure.getLabProcedureID());
		verify(mockData, times(0)).update(procedure);
		verify(controller, times(1)).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_ERROR), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString());
		Assert.assertEquals(LabProcedureStatus.IN_TRANSIT, procedure.getStatus());
	}

	/**
	 * Tests that add() correctly adds a lab procedure.
	 */
	@Test
	public void testAdd() throws SQLException, DBException {
		DataSource mockDS = mock(DataSource.class);
		controller = new LabProcedureController(mockDS);
		controller = spy(controller);
		LabProcedureData mockData = mock(LabProcedureData.class);
		controller.setLabProcedureData(mockData);
		when(mockData.add(Mockito.any(LabProcedure.class))).thenReturn(true);
		controller.add(procedure);
		verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_INFO), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * Tests that add() catches Exceptions TODO: test this more thoroughly?
	 */
	@Test
	public void testAddWithDBException() throws SQLException, DBException {
		DataSource mockDS = mock(DataSource.class);
		controller = new LabProcedureController(mockDS);
		controller = spy(controller);
		LabProcedureData mockData = mock(LabProcedureData.class);
		controller.setLabProcedureData(mockData);
		when(mockData.add(Mockito.any(LabProcedure.class))).thenThrow(new DBException(null));
		controller.add(procedure);
		verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_ERROR), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * Tests that edit() correctly edits a lab procedure. TODO: test this more
	 * thoroughly?
	 */
	@Test
	public void testEdit() throws SQLException, DBException {
		DataSource mockDS = mock(DataSource.class);
		controller = new LabProcedureController(mockDS);
		controller = spy(controller);
		LabProcedureData mockData = mock(LabProcedureData.class);
		controller.setLabProcedureData(mockData);
		when(mockData.update(Mockito.any(LabProcedure.class))).thenReturn(true);
		controller.edit(procedure);
		verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_INFO), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * Tests that remove() correctly removes a lab procedure. TODO: test this
	 * more thoroughly?
	 */
	@Test
	public void testRemove() throws SQLException, DBException {
		DataSource mockDS = mock(DataSource.class);
		controller = new LabProcedureController(mockDS);
		controller = spy(controller);
		LabProcedureData mockData = mock(LabProcedureData.class);
		controller.setLabProcedureData(mockData);
		when(mockData.removeLabProcedure(1L)).thenReturn(true);
		controller.remove("1");
		verify(mockData, times(1)).removeLabProcedure(1L);
		verify(controller).printFacesMessage(Mockito.eq(FacesMessage.SEVERITY_INFO), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * Tests that get() correctly retrieves a lab procedure.
	 */
	@Test
	public void testGet() throws Exception {
		gen.labProcedure0();
		controller = spy(controller);
		Mockito.doNothing().when(controller).printFacesMessage(any(), any(), any(), any());
		LabProcedure proc = controller.getLabProcedureByID("1");
		Assert.assertNotNull(proc);
		Assert.assertEquals(5000000001L, proc.getLabTechnicianID().longValue());
		proc = controller.getLabProcedureByID("-1");
		Assert.assertNull(proc);
		proc = controller.getLabProcedureByID("aa");
		Assert.assertNull(proc);
		verify(controller).printFacesMessage(eq(FacesMessage.SEVERITY_ERROR), any(), any(), any());
	}

	/**
	 * Tests that remove() throws correct exception for invalid ID argument
	 */
	@Test
	public void testRemoveInvalidID() throws SQLException, FileNotFoundException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();

		DataSource mockDS = Mockito.mock(DataSource.class);
		when(mockDS.getConnection()).thenReturn(ds.getConnection());
		controller = new LabProcedureController(mockDS);
		controller.remove("foo");
		Mockito.verifyZeroInteractions(mockDS);
	}

	/**
	 * Tests controller constructor. TODO: doesn't actually test much
	 */
	@Test
	public void testLabProcedure() {
		controller = new LabProcedureController();
		Assert.assertNotNull(controller);
	}
}
