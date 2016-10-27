package edu.ncsu.csc.itrust.unit.model.icdcode;

import org.junit.Test;

import edu.ncsu.csc.itrust.model.icdcode.ICDCodeMySQL;
import edu.ncsu.csc.itrust.model.icdcode.ICDCodeSQLLoader;
import edu.ncsu.csc.itrust.model.icdcode.ICDCodeValidator;
import junit.framework.TestCase;

public class ICDCodeMySQLTest extends TestCase {
	
	private ICDCodeMySQL mysql;
	
	@Override
	public void setUp() {
		//TODO: this will need to be changed
		mysql = new ICDCodeMySQL();
		new ICDCodeSQLLoader();
		new ICDCodeValidator();
	}
	
	@Test
	public void testGetAll() throws Exception {
		// TODO actually test this
		assertNull(mysql.getAll());
	}

	@Test
	public void testGetByID() throws Exception {
		assertNull(mysql.getByID(0));
	}

	@Test
	public void testAdd() throws Exception {
		assertFalse(mysql.add(null));
	}

	@Test
	public void testUpdate() throws Exception {
		assertFalse(mysql.update(null));
	}
}
