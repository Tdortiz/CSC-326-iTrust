package edu.ncsu.csc.itrust.unit.model.ndcode;

import org.junit.Assert;

import edu.ncsu.csc.itrust.model.ndcode.NDCode;
import junit.framework.TestCase;

public class NDCodeTest extends TestCase {
    
    public void testEverything(){
        NDCode nd = new NDCode();
        nd.setCode("test");
        Assert.assertEquals("test", nd.getCode());
        
        nd.setDescription("test2");
        Assert.assertEquals("test2", nd.getDescription());
    }
}
