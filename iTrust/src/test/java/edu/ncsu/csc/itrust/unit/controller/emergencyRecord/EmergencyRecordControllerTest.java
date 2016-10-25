package edu.ncsu.csc.itrust.unit.controller.emergencyRecord;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc.itrust.controller.emergencyRecord.EmergencyRecordController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class EmergencyRecordControllerTest extends TestCase {
    
    EmergencyRecordController c;
    private DataSource ds;
    
    @Override
    public void setUp() throws DBException, FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
        c = new EmergencyRecordController(ds);
        TestDataGenerator gen = new TestDataGenerator();
        gen.clearAllTables();
        gen.uc21();
    }
    
    @Test
    public void testLoadRecord(){
        // loads the record for Sandy Sky
        Assert.assertNotNull(c.loadRecord("201"));
        EmergencyRecord r = c.getRecord();
        Assert.assertNotNull(r);
        
        Assert.assertEquals("Sandy Sky", r.getName());
        Assert.assertEquals(24, r.getAge());
        Assert.assertEquals("Male", r.getGender());
        Assert.assertEquals("Susan Sky-Walker", r.getContactName());
        Assert.assertEquals("444-332-4309", r.getContactPhone());
        Assert.assertEquals(null, r.getAllergies());
        Assert.assertEquals("O-", r.getBloodType());
        Assert.assertEquals(null, r.getDiagnoses());
        Assert.assertEquals(null, r.getPrescriptions());
        Assert.assertEquals(null, r.getImmunizations());
    }
    
    @Test
    public void testNoDataSource(){
        try {
            new EmergencyRecordController();
            Assert.fail();
        } catch (DBException e){
            // yay, we passed
        }
    }
}
