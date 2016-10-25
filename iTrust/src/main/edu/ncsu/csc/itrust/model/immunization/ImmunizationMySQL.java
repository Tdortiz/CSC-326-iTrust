package edu.ncsu.csc.itrust.model.immunization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;

public class ImmunizationMySQL implements ImmunizationData {

	private ImmunizationSQLLoader loader;
	private ImmunizationValidator validator;
	private DataSource ds;

	/**
	 * Constructor of ImmunizationsMySQL instance.
	 * 
	 * @throws DBException when data source cannot be created from the given context names
	 */
	public ImmunizationMySQL() throws DBException {
		loader = new ImmunizationSQLLoader();
		try {
			Context ctx = new InitialContext();
			this.ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
		} catch (NamingException e) {
			throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
		}
		validator = new ImmunizationValidator(this.ds);
	}
	
	/**
	 * Constructor for testing.
	 * 
	 * @param ds testing data source
	 */
	public ImmunizationMySQL(DataSource ds) {
		loader = new ImmunizationSQLLoader();
		this.ds = ds;
		validator = new ImmunizationValidator(this.ds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Immunization> getAll() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Immunization getByID(long id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(Immunization addObj) throws DBException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(Immunization updateObj) throws DBException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Immunization> getAllImmunizations(long mid) throws DBException {
		try (Connection conn = ds.getConnection();
			PreparedStatement statement = createImmunizationPreparedStatement(conn, mid);
			ResultSet resultSet = statement.executeQuery()) {
			return loader.loadList(resultSet);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
	
	public PreparedStatement createImmunizationPreparedStatement(Connection conn, long mid) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"SELECT i.id, i.visitId, i.cptCode, c.name FROM immunizations i, cptcode c, officevisit ov "
					 + "WHERE i.visitId = ov.visitID AND ov.patientMID = ? AND i.cptCode = c.code ");
		ps.setLong(1, mid);
		return ps;
	}
}