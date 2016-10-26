package edu.ncsu.csc.itrust.unit.model.prescription;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

import edu.ncsu.csc.itrust.model.prescription.Prescription;

public class PrescriptionTest extends TestCase {
    
    @Test
    public void testEverything(){
        Prescription p = new Prescription();
        
        p.setDrugCode("test1");
        Assert.assertEquals("test1", p.getDrugCode());
        
        LocalDate now = LocalDate.now();
        p.setEndDate(now);
        Assert.assertEquals(now, p.getEndDate());
        
        now = LocalDate.of(2012, 12, 12);
        p.setStartDate(now);
        Assert.assertEquals(now, p.getStartDate());
        
        p.setOfficeVisitId(155);
        Assert.assertEquals(155, p.getOfficeVisitId());
        
        p.setPatientMID(100);
        Assert.assertEquals(100, p.getPatientMID());
    }
}
