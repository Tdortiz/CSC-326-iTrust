package edu.ncsu.csc.itrust.unit.model.diagnosis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.diagnosis.DiagnosisMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class DiagnosisMySQLTest extends TestCase {
    
    DataSource ds;
    
    DiagnosisMySQL sql;
    
    @Spy
    DiagnosisMySQL mockSql;
    
    @Override
    public void setUp() throws FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
        sql = new DiagnosisMySQL(ds);
        mockSql = Mockito.spy(new DiagnosisMySQL(ds));
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
    public void testGetAllEmergencyDiagnosis() throws SQLException{
        // load Sandy Sky's diagnoses
        List<Diagnosis> dList = sql.getAllEmergencyDiagnosis(201);
        Assert.assertEquals(2, dList.size());
        Assert.assertEquals("J00", dList.get(0).getCode());
        Assert.assertEquals("J45", dList.get(1).getCode());
    }
    
    // TODO actually do this, this is just for coverage
    @Test
    public void testGetAll() throws DBException{
        Assert.assertNull(sql.getAll());
    }
    
    // TODO actually do this, this is just for coverage
    @Test
    public void testGetByID() throws DBException{
        Assert.assertNull(sql.getByID(-1));
    }
    
    // TODO actually do this, this is just for coverage
    @Test
    public void testAdd() throws DBException{
        Assert.assertFalse(sql.add(new Diagnosis(0, 0, null)));
    }
    
    // TODO actually do this, this is just for coverage
    @Test
    public void testUpdate() throws DBException{
        Assert.assertFalse(sql.update(new Diagnosis(0, 0, null)));
    }
    
    @Test
    public void testProdConstructor(){
        try {
            new DiagnosisMySQL();
            Assert.fail();
        } catch (DBException e) {
            // yay, we passed
        }
    }
    
    public class TestDiagnosisMySQL extends DiagnosisMySQL{
		public TestDiagnosisMySQL() throws DBException {
			super();
		}
		
		public TestDiagnosisMySQL(DataSource ds) {
			super(ds);
		}
		
    	@Override
    	public DataSource getDataSource() {
    		return ds;
    	}
    }
    
    @Test
    public void testMockDataSource() throws Exception {
    	DiagnosisMySQL mysql = new TestDiagnosisMySQL();
    	Assert.assertNotNull(mysql);
    }
}
