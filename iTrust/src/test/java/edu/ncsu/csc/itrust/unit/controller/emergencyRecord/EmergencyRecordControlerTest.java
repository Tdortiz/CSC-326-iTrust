package edu.ncsu.csc.itrust.unit.controller.emergencyRecord;


import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc.itrust.controller.emergencyRecord.EmergencyRecordController;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.old.enums.BloodType;
import edu.ncsu.csc.itrust.model.old.enums.Gender;
import junit.framework.TestCase;

public class EmergencyRecordControlerTest extends TestCase {
    
    EmergencyRecordController c;
    
    @Override
    public void setUp(){
        c = new EmergencyRecordController();
    }
    
    @Test
    public void testLoadRecord(){
        // loads the record for Random Person
        Assert.assertTrue(c.loadRecord(1));
        EmergencyRecord r = c.getRecord();
        
     // this is necessary for the age to be accurate in the future
        r.setAge(30);
        
        Assert.assertEquals("Random Person", r.getName());
        Assert.assertEquals(30, r.getAge());
        Assert.assertEquals(Gender.Female, r.getGender());
        Assert.assertEquals("Mommy Person", r.getContactName());
        Assert.assertEquals("704-532-2117", r.getContactPhone());
        Assert.assertEquals(null, r.getAllergies());
        Assert.assertEquals(BloodType.ABPos, r.getBloodType());
        Assert.assertEquals(null, r.getDiagnoses());
        Assert.assertEquals(null, r.getPrescriptions());
        Assert.assertEquals(null, r.getImmunizations());
    }
}
