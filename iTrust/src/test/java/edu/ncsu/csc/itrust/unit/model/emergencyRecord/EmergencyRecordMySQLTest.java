package edu.ncsu.csc.itrust.unit.model.emergencyRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecordMySQL;
import edu.ncsu.csc.itrust.model.prescription.Prescription;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class EmergencyRecordMySQLTest extends TestCase {
    
    private DataSource ds;
    
    private EmergencyRecordMySQL sql;
    
    @Override
    public void setUp() throws DBException, FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
        sql = new EmergencyRecordMySQL(ds);
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
    public void testLoadRecord() throws DBException{
        // loads the record for Sandy Sky
        EmergencyRecord r = sql.getEmergencyRecordForPatient(201);
        Assert.assertNotNull(r);
        
        Assert.assertEquals("Sandy Sky", r.getName());
        Assert.assertEquals(24, r.getAge());
        Assert.assertEquals("Male", r.getGender());
        Assert.assertEquals("Susan Sky-Walker", r.getContactName());
        Assert.assertEquals("444-332-4309", r.getContactPhone());
        Assert.assertEquals("O-", r.getBloodType());

        
        List<Prescription> pList = r.getPrescriptions();
        Assert.assertEquals(2, pList.size());
        Assert.assertEquals("63739291", pList.get(0).getDrugCode());
        Assert.assertEquals("483013420", pList.get(1).getDrugCode());
        
        //TODO: fix these
        //Assert.assertEquals(null, r.getAllergies());
        //Assert.assertEquals(null, r.getDiagnoses());
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
