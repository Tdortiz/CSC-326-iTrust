package edu.ncsu.csc.itrust.unit.controller.labProcedure;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * Unit tests for LabProcedureController.
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
	public void testGetLabProceduresByLabTechnicianSingleResult() throws FileNotFoundException, SQLException, IOException {
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
	 * Tests that getLabProceduresByLabTechnician() returns the correct lab procedures in the correct order
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testGetLabProceduresByLabTechnicianMultipleResults() throws FileNotFoundException, SQLException, IOException {
		// Order should be lab procedure 1, 4, 3, 2, 0 based on the sort order defined in S7 in UC26 
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
	 * Tests that getLabProceduresByOfficeVisit() returns the correct lab procedures in the correct order
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

	/**
	 * Tests that getPendingLabProcedures() returns only and all pending lab procedures.
	 */
	@Test
	public void testGetPendingLabProcedures() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();
		
		List<LabProcedure> procs = null;
		try {
			procs = controller.getPendingLabProcedures("5000000001");
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
	 * Tests that getInTransitLabProcedures() returns only and all in transit lab procedures.
	 */
	@Test
	public void testGetInTransitLabProcedures() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();
		
		List<LabProcedure> procs = null;
		try {
			procs = controller.getInTransitLabProcedures("5000000001");
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
	 * Tests that getReceivedLabProcedures() returns only and all received lab procedures.
	 */
	@Test
	public void testGetReceivedLabProcedures() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();
		
		List<LabProcedure> procs = null;
		try {
			procs = controller.getReceivedLabProcedures("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure received = procs.get(0);
		Assert.assertEquals("Received", received.getStatus().getName());
	}

	/**
	 * Tests that getTestingLabProcedures() returns only and all testing lab procedures.
	 */
	@Test
	public void testGetTestingLabProcedures() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();
		
		List<LabProcedure> procs = null;
		try {
			procs = controller.getTestingLabProcedures("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure testing = procs.get(0);
		Assert.assertEquals("Testing", testing.getStatus().getName());
	}

	/**
	 * Tests that getCompletedLabProcedures() returns only and all completed lab procedures.
	 */
	@Test
	public void testGetCompletedLabProcedures() throws FileNotFoundException, SQLException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();
		
		List<LabProcedure> procs = null;
		try {
			procs = controller.getCompletedLabProcedures("5000000001");
		} catch (DBException e) {
			fail("Shouldn't throw exception when getting lab procedures");
		}
		Assert.assertTrue(procs.size() == 1);
		LabProcedure completed = procs.get(0);
		Assert.assertEquals("Completed", completed.getStatus().getName());
	}

	/**
	 * Tests that add() correctly adds a lab procedure.
	 * TODO: test this more thoroughly?
	 */
	@Test
	public void testAdd() throws SQLException {
		DataSource mockDS = Mockito.mock(DataSource.class);
		when(mockDS.getConnection()).thenReturn(ds.getConnection());
		controller = new LabProcedureController(mockDS);
		controller.add(procedure);
		verify(mockDS, times(1)).getConnection();
	}

	/**
	 * Tests that edit() correctly edits a lab procedure.
	 * TODO: test this more thoroughly?
	 */
	@Test
	public void testEdit() throws SQLException {
		DataSource mockDS = Mockito.mock(DataSource.class);
		when(mockDS.getConnection()).thenReturn(ds.getConnection());
		controller = new LabProcedureController(mockDS);
		controller.edit(procedure);
		verify(mockDS, times(1)).getConnection();
	}

	/**
	 * Tests that remove() correctly removes a lab procedure.
	 * TODO: test this more thoroughly?
	 */
	@Test
	public void testRemove() throws SQLException, FileNotFoundException, IOException {
		gen.labProcedure0();
		gen.labProcedure1();
		gen.labProcedure2();
		gen.labProcedure3();
		gen.labProcedure4();
		gen.labProcedure5();
		
		DataSource mockDS = Mockito.mock(DataSource.class);
		when(mockDS.getConnection()).thenReturn(ds.getConnection());
		controller = new LabProcedureController(mockDS);
		controller.remove("1");
		verify(mockDS, times(1)).getConnection();
	}
}
