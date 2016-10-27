package edu.ncsu.csc.itrust.unit.model.cptcode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Test;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.cptcode.CPTCode;
import edu.ncsu.csc.itrust.model.cptcode.CPTCodeMySQL;
import edu.ncsu.csc.itrust.model.immunization.ImmunizationMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class CPTCodeMySQLTest extends TestCase {
    
    private DataSource ds;
    TestDataGenerator gen;
    private CPTCodeMySQL sql;
    
    @Override
    public void setUp() throws DBException, FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
        sql = new CPTCodeMySQL(ds);
        gen = new TestDataGenerator();
        gen.clearAllTables();
        gen.cptCode();
    }
    
    @Test
    public void testGetAll(){
    	List<CPTCode> codes = null;
    	try {
			 codes = sql.getAll();
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertEquals(7, codes.size());
    }
    
    @Test
    public void testGetByID(){
    	CPTCode code = null;
    	try {
			 code = sql.getByID(90281);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertEquals("90281", code.getCode());
    	Assert.assertEquals("IG", code.getName());
    }
    
    @Test
    public void testAdd(){
    	CPTCode code = new CPTCode("90636", "Hep A-Hep B" );
    	boolean added = false;
    	
    	try {
    		added = sql.add(code);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertTrue(added);
    }
    
    @Test
    public void testUpdate(){
    	CPTCode code = new CPTCode("90281", "I G" );
    	boolean added = false;
    	
    	try {
    		added = sql.update(code);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertTrue(added);
    	
    	CPTCode updatedCode = null;
    	try {
    		updatedCode = sql.getByID(90281);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertEquals("90281", updatedCode.getCode());
    	Assert.assertEquals("I G", updatedCode.getName());
    }
    
    @Test
    public void testGetByIDDropped() throws FileNotFoundException, SQLException, IOException{
    	CPTCode code = null;
    	gen.clearAllTables();
    	
    	try {
			 code = sql.getByID(90281);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertNull(code);
    }
    
    @Test
    public void testNoDataSource(){
        try {
            new CPTCodeMySQL();
            Assert.fail();
        } catch (DBException e){
            Assert.assertTrue(true);
        }
    }
    
}
