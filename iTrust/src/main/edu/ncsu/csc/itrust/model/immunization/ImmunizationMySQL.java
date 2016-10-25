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

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

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
		Connection conn = null;
		PreparedStatement pstring = null;
		ResultSet results = null;
		try {
			conn = ds.getConnection();
			pstring = conn.prepareStatement("SELECT * FROM immunization");
			results = pstring.executeQuery();
			final List<Immunization> immunizationList = loader.loadList(results);
			return immunizationList;
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			try {
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				throw new DBException(e);
			} finally {
				DBUtil.closeConnection(conn, pstring);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Immunization getByID(long id) throws DBException {
		Immunization ret = null;
		Connection conn = null;
		PreparedStatement pstring = null;
		ResultSet results = null;
		List<Immunization> immunizationList = null;
		try {
			conn = ds.getConnection();
			pstring = conn.prepareStatement("SELECT * FROM immunization WHERE visitID=?");
			
			pstring.setLong(1, id);
			results = pstring.executeQuery();

			immunizationList = loader.loadList(results);
			if (immunizationList.size() > 0) {
				ret = immunizationList.get(0);
			}
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			try {
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				throw new DBException(e);
			} finally {

				DBUtil.closeConnection(conn, pstring);
			}
		}
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(Immunization addObj) throws DBException {
		boolean retval = false;
		Connection conn = null;
		PreparedStatement pstring = null;
		try {
			validator.validate(addObj);
		} catch (FormValidationException e1) {
			throw new DBException(new SQLException(e1));
		}
		int results;
		try {
			conn = ds.getConnection();
			pstring = loader.loadParameters(conn, pstring, addObj, true);
			results = pstring.executeUpdate();
			retval = (results > 0);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {

			DBUtil.closeConnection(conn, pstring);
		}
		return retval;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(Immunization updateObj) throws DBException {
		boolean retval = false;
		Connection conn = null;
		PreparedStatement pstring = null;
		try {
			validator.validate(updateObj);
		} catch (FormValidationException e1) {
			throw new DBException(new SQLException(e1.getMessage()));
		}
		int results;

		try {
			conn = ds.getConnection();
			pstring = loader.loadParameters(conn, pstring, updateObj, false);
			results = pstring.executeUpdate();
			retval = (results > 0);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, pstring);
		}
		return retval;
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