package edu.ncsu.csc.itrust.unit.model.prescription;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.prescription.Prescription;
import edu.ncsu.csc.itrust.model.prescription.PrescriptionMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class PrescriptionMySQLTest extends TestCase {
    PrescriptionMySQL sql;
    @Spy
    PrescriptionMySQL mockSql;
    private DataSource ds;
    
    @Override
    public void setUp() throws DBException, FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
        sql = new PrescriptionMySQL(ds);
        mockSql = Mockito.spy(new PrescriptionMySQL(ds));
        TestDataGenerator gen = new TestDataGenerator();
        gen.clearAllTables();
        gen.ndCodes();
        gen.ndCodes1();
        gen.ndCodes100();
        gen.ndCodes2();
        gen.ndCodes3();
        gen.ndCodes4();
        gen.uc21();
    }
    
    @Test
    public void testGetPrescriptionsForPatientEndingAfter() throws DBException{
        List<Prescription> pList = sql.getPrescriptionsForPatientEndingAfter(201, LocalDate.now().minusDays(91));
        Assert.assertEquals(2, pList.size());
        Assert.assertEquals("63739291", pList.get(0).getCode());
        Assert.assertEquals("483013420", pList.get(1).getCode());
    }

    
    @Test
    public void testNoDataSource(){
        try {
            new PrescriptionMySQL();
            Assert.fail();
        } catch (DBException e){
            // yay, we passed
        }
    }

	class TestPrescriptionMySQL extends PrescriptionMySQL {
		public TestPrescriptionMySQL() throws DBException {
			super();
		}

		@Override
		public DataSource getDataSource() {
			return ds;
		}
	}
	
	@Test
	public void testMockDataSource() throws Exception {
		TestPrescriptionMySQL mysql = new TestPrescriptionMySQL();
		Assert.assertNotNull(mysql);
	}
	
	@Test
	public void testMockGetPrescriptionsForPatientEndingAfter() throws Exception {
		Mockito.doThrow(SQLException.class).when(mockSql).loadRecords(Mockito.any());
		try {
			mockSql.getPrescriptionsForPatientEndingAfter(1L, LocalDate.now());
			fail();
		} catch (DBException e) {
			// Do nothing
		}
	}
}
