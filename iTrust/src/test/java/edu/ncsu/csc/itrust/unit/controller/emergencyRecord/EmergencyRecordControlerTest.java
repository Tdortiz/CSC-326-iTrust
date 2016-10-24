package edu.ncsu.csc.itrust.unit.controller.emergencyRecord;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc.itrust.controller.emergencyRecord.EmergencyRecordController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import junit.framework.TestCase;

public class EmergencyRecordControlerTest extends TestCase {
    
    EmergencyRecordController c;
    private DataSource ds;
    
    @Override
    public void setUp() throws DBException{
        ds = ConverterDAO.getDataSource();
        c = new EmergencyRecordController(ds);
    }
    
    @Test
    public void testLoadRecord(){
        // loads the record for Random Person
        Assert.assertTrue(c.loadRecord("1"));
        EmergencyRecord r = c.getRecord();
        Assert.assertNotNull(r);
        
        // calculate what the age should be
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(1950, 10, 5);
        int age = (int) ChronoUnit.YEARS.between(birthday, now);
        
        Assert.assertEquals("Random Person", r.getName());
        Assert.assertEquals(age, r.getAge());
        Assert.assertEquals("Female", r.getGender());
        Assert.assertEquals("Mommy Person", r.getContactName());
        Assert.assertEquals("704-532-2117", r.getContactPhone());
        Assert.assertEquals(null, r.getAllergies());
        Assert.assertEquals("AB+", r.getBloodType());
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
