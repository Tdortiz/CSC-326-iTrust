package edu.ncsu.csc.itrust.unit.model.ndcode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.ndcode.NDCCode;
import edu.ncsu.csc.itrust.model.ndcode.NDCCodeMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import junit.framework.TestCase;

public class NDCCodeMySQLTest extends TestCase {
    
    private DataSource ds;
    
    @Override
    public void setUp() throws FileNotFoundException, SQLException, IOException{
        ds = ConverterDAO.getDataSource();
    }
    
    public void testNDCodeMySQL() throws FileNotFoundException, SQLException, IOException, FormValidationException{
        TestDataGenerator gen = new TestDataGenerator();
        gen.clearAllTables();
        NDCCodeMySQL sql = new NDCCodeMySQL(ds);
        
        // ensure there are no records to start
        Assert.assertEquals(0, sql.getAll().size());
        
        // test adding a valid record
        NDCCode newCode = new NDCCode();
        newCode.setCode("123");
        newCode.setDescription("test");
        Assert.assertTrue(sql.add(newCode));
        List<NDCCode> ndList = sql.getAll();
        Assert.assertEquals(1, ndList.size());
        Assert.assertEquals("123", ndList.get(0).getCode());
        Assert.assertEquals("test", ndList.get(0).getDescription());
        
        // test adding a record with bad code
        newCode.setCode("a");
        newCode.setDescription("test2");
        try {
            sql.add(newCode);
            fail("Record should not have been added");
        } catch (FormValidationException e){
            List<String> errors = e.getErrorList();
            Assert.assertEquals(1, errors.size());
            Assert.assertEquals("NDCCode: Up to nine digit integer", errors.get(0));
        }
        Assert.assertEquals(1, sql.getAll().size());
        
        // test adding a record with too long description
        newCode.setCode("321");
        newCode.setDescription("test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3test3");
        try {
            sql.add(newCode);
            fail("Record should not have been added");
        } catch (FormValidationException e){
            List<String> errors = e.getErrorList();
            Assert.assertEquals(1, errors.size());
            Assert.assertEquals("Description: Up to 100 characters, letters, numbers, and a space", errors.get(0));
        }
        Assert.assertEquals(1, sql.getAll().size());
        
        // test deleting a record that doesn't exist
        newCode.setCode("4");
        newCode.setDescription("test4");
        Assert.assertFalse(sql.delete(newCode));
        Assert.assertEquals(1, sql.getAll().size());
        
        // test deleting a record that does exist
        newCode.setCode("123");
        newCode.setDescription("test1");
        Assert.assertTrue(sql.delete(newCode));
        Assert.assertEquals(0, sql.getAll().size());
        
    }
    
    public void testProdConstructor(){
        try {
            new NDCCodeMySQL();
            fail();
        } catch (DBException e) {
            // yay, we passed
        }
    }
}
