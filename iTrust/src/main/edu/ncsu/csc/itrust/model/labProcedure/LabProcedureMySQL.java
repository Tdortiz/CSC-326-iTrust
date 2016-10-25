package edu.ncsu.csc.itrust.model.labProcedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitSQLLoader;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitValidator;

public class LabProcedureMySQL implements LabProcedureData {

	@Resource(name = "jdbc/itrust2")
	private LabProcedureSQLLoader loader;
	private DataSource ds;
	private LabProcedureValidator validator;

	public LabProcedureMySQL() throws DBException {
		loader = new LabProcedureSQLLoader();
		try {
			Context ctx = new InitialContext();
			this.ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
		} catch (NamingException e) {
			throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
		}
		validator = new LabProcedureValidator(this.ds);
	}
	
	/**
	 * Constructor injection, intended only for unit testing purposes.
	 * 
	 * @param ds
	 *            The injected DataSource dependency
	 */
	public LabProcedureMySQL(DataSource ds) {
		loader = new LabProcedureSQLLoader();
		this.ds = ds;
		validator = new LabProcedureValidator(this.ds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LabProcedure getByID(long id) throws DBException {
		LabProcedure labProcedure = null;
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet results = null;
		List<LabProcedure> procedureList = null;
		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(LabProcedureSQLLoader.SELECT_BY_LAB_PROCEDURE);
			query.setLong(1, id);
			results = query.executeQuery();

			procedureList = loader.loadList(results);
			if (procedureList.size() > 0) {
				labProcedure = procedureList.get(0);
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
				DBUtil.closeConnection(conn, query);
			}
		}
		return labProcedure;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<LabProcedure> getAll() throws DBException {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet results = null;
		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(LabProcedureSQLLoader.SELECT_ALL);
			results = query.executeQuery();

			return loader.loadList(results);
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
				DBUtil.closeConnection(conn, query);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(LabProcedure procedure) throws DBException {
		boolean successfullyAdded = false;
		Connection conn = null;
		PreparedStatement query = null;
		try {
			validator.validate(procedure);
		} catch (FormValidationException e1) {
			throw new DBException(new SQLException(e1.getMessage()));
		}
		try {
			conn = ds.getConnection();
			query = loader.loadParameters(conn, query, procedure, true);
			int exitStatus = query.executeUpdate();
			successfullyAdded = (exitStatus > 0);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, query);
		}
		return successfullyAdded;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(LabProcedure procedure) throws DBException {
		boolean successfullyUpdated = false;
		Connection conn = null;
		PreparedStatement query = null;
		try {
			validator.validate(procedure);
		} catch (FormValidationException e1) {
			throw new DBException(new SQLException(e1.getMessage()));
		}
		try {
			conn = ds.getConnection();
			query = loader.loadParameters(conn, query, procedure, false);
			int exitStatus = query.executeUpdate();
			successfullyUpdated = (exitStatus > 0);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, query);
		}
		return successfullyUpdated;
	}

	@Override
	public List<LabProcedure> getLabProceduresForLabTechnician(Long technicianID) throws DBException {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet procedures = null;
		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(LabProcedureSQLLoader.SELECT_BY_LAB_TECHNICIAN);
			query.setLong(1, technicianID);
			procedures = query.executeQuery();

			return loader.loadList(procedures);
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			try {
				if (procedures != null) {
					procedures.close();
				}
			} catch (SQLException e) {
				throw new DBException(e);
			} finally {
				DBUtil.closeConnection(conn, query);
			}
		}		
	}
}
