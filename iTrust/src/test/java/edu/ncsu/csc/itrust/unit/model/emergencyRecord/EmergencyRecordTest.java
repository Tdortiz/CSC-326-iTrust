package edu.ncsu.csc.itrust.unit.model.emergencyRecord;

import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.old.enums.BloodType;
import edu.ncsu.csc.itrust.model.old.enums.Gender;
import junit.framework.TestCase;

public class EmergencyRecordTest extends TestCase {
    
    EmergencyRecord r;
    
    @Override
    public void setUp(){
        r = new EmergencyRecord();
    }
    
    @Test
    public void testName(){
        r.setName("testName");
        Assert.assertEquals("testName", r.getName());
    }
    
    @Test
    public void testAge(){
        r.setAge(50);
        Assert.assertEquals(50,  r.getAge());
    }
    
    @Test
    public void testGender(){
        r.setGender("Female");
        Assert.assertEquals("Female", r.getGender());
    }
    
    @Test
    public void testContactName(){
        r.setContactName("cName");
        Assert.assertEquals("cName", r.getContactName());
    }
    
    @Test
    public void testContactPhone(){
        r.setContactPhone("999-555-1111");
        Assert.assertEquals("999-555-1111", r.getContactPhone());
    }
    
    //TODO: fix this when allergy functionality is added
    @Test
    public void testAllergies(){
        r.setAllergies(null);
        Assert.assertNull(r.getAllergies());
    }
    
    @Test
    public void testBloodType(){
        r.setBloodType("A-");
        Assert.assertEquals("A-", r.getBloodType());
    }
    
    //TODO: fix this when diagnosis functionality is added
    @Test
    public void testDiagnoses(){
        r.setDiagnoses(null);
        Assert.assertNull(r.getDiagnoses());
    }
    
    //TODO: fix this when prescription functionality is added
    @Test
    public void testPrescriptions(){
        r.setPrescriptions(null);
        Assert.assertNull(r.getPrescriptions());
    }
    
    //TODO: fix this when immunization functionality is added
    @Test
    public void testImmunizations(){
        r.setImmunizations(null);
        Assert.assertNull(r.getImmunizations());
    }
}
