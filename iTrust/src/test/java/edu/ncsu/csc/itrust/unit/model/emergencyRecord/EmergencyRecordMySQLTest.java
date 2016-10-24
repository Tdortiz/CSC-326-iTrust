package edu.ncsu.csc.itrust.unit.model.emergencyRecord;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecordMySQL;
import junit.framework.TestCase;

public class EmergencyRecordMySQLTest extends TestCase {
    
    private DataSource ds;
    
    private EmergencyRecordMySQL sql;
    
    @Override
    public void setUp() throws DBException{
        ds = ConverterDAO.getDataSource();
        sql = new EmergencyRecordMySQL(ds);
    }
    
    @Test
    public void testLoadRecord() throws DBException{
        // loads the record for Sandy Sky
        EmergencyRecord r = sql.getEmergencyRecordForPatient(201);
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
            new EmergencyRecordMySQL();
            Assert.fail();
        } catch (DBException e){
            // yay, we passed
        }
    }
}
