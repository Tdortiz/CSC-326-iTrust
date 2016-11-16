package edu.ncsu.csc.itrust.unit.model.diagnosis;

import javax.sql.DataSource;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.diagnosis.DiagnosisController;
import edu.ncsu.csc.itrust.controller.diagnosis.DiagnosisForm;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.webutils.SessionUtils;
import junit.framework.TestCase;

public class DiagnosisFormTest extends TestCase {

	DataSource ds;
	DiagnosisForm form;
	TestDataGenerator gen;
	
	@Mock
	SessionUtils mockSessionUtils;

	@Override
	public void setUp() throws Exception {
		ds = ConverterDAO.getDataSource();
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.testIcdCode();
		
		mockSessionUtils = Mockito.mock(SessionUtils.class);
		Mockito.doReturn(1L).when(mockSessionUtils).getCurrentOfficeVisitId();
		
		form = new DiagnosisForm(new DiagnosisController(ds), mockSessionUtils, ds);
	}
	
	@Test
	public void testDiagnosisForm() throws Exception {
		form.add();
		form.edit();
		form.remove("0");
		
		gen.uc21();
		assertEquals(1, form.getCurrentDiagnoses().size());
		
		try {
			new DiagnosisForm();
		} catch (Exception e) {
			// Do nothing
		}
	}
}
