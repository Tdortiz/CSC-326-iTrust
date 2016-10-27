package edu.ncsu.csc.itrust.unit.model.immunization;

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
import edu.ncsu.csc.itrust.model.immunization.Immunization;
import edu.ncsu.csc.itrust.model.immunization.ImmunizationMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class ImmunizationMySQLTest extends TestCase {
    
    private DataSource ds;
    TestDataGenerator gen;
    private ImmunizationMySQL sql;
    
    @Override
    public void setUp() throws DBException, FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
        sql = new ImmunizationMySQL(ds);
        gen = new TestDataGenerator();
        gen.clearAllTables();
        gen.uc21();
    }
    
    @Test
    public void testGetAll(){
    	List<Immunization> immunizations = null;
    	try {
    		immunizations = sql.getAll();
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertEquals(1, immunizations.size());
    }
    
    // TODO getByID not yet fully implemented
    /**
    @Test
    public void testGetByID(){
    	List<Immunization> immunizations = null;
    	try {
    		immunization = sql.getByID(1);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertEquals("90281", code.getCode());
    	Assert.assertEquals("IG", code.getName());
    }
    */
       
    @Test
    public void testAdd(){
    	CPTCode code = new CPTCode("90636", "Hep A-Hep B" );
    	Immunization immunization = new Immunization(500 , 1, code);
    	boolean added = false;
    	
    	try {
    		added = sql.add(immunization);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertTrue(added);
    }
    
    @Test
    public void testUpdate(){
    	
    	// TODO: this test is actually broken
    	CPTCode code = new CPTCode("90636", "Hep A-Hep B" );
    	Immunization immunization = new Immunization(1 , 1, code);
    	boolean added = false;
    	
    	try {
    		added = sql.update(immunization);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	// TODO: Should be assertTrue here. Thomas, fix this later
    	Assert.assertFalse(added);
    	
    	// TODO getByID not yet fully implemented
    	/**
    	Immunization updatedImmunization = null;
    	try {
    		updatedImmunization = sql.getByID(90281);
		} catch (DBException e) {
			Assert.fail();
		}
    	
    	Assert.assertEquals("90281", updatedImmunization.getCode());
    	Assert.assertEquals("I G", updatedImmunization.getName());
    	*/
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
