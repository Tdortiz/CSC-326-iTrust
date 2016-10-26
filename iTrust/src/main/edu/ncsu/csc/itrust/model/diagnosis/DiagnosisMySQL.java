package edu.ncsu.csc.itrust.model.diagnosis;

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

public class DiagnosisMySQL implements DiagnosisData {

	private DiagnosisSQLLoader loader;
	private DiagnosisValidator validator;
	private DataSource ds;

	/**
	 * Constructor of DiagnosisMySQL instance.
	 * 
	 * @throws DBException when data source cannot be created from the given context names
	 */
	public DiagnosisMySQL() throws DBException {
		loader = new DiagnosisSQLLoader();
		try {
			Context ctx = new InitialContext();
			this.ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
		} catch (NamingException e) {
			throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
		}
		validator = new DiagnosisValidator(this.ds);
	}
	
	/**
	 * Constructor for testing.
	 * 
	 * @param ds
	 * 		testing data source
	 */
	public DiagnosisMySQL(DataSource ds) {
		loader = new DiagnosisSQLLoader();
		this.ds = ds;
		validator = new DiagnosisValidator(this.ds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Diagnosis> getAll() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Diagnosis getByID(long id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(Diagnosis addObj) throws DBException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(Diagnosis updateObj) throws DBException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Diagnosis> getAllEmergencyDiagnosis(long mid) throws DBException {
		try (Connection conn = ds.getConnection();
			PreparedStatement statement = createEmergencyDiagnosisPreparedStatement(conn, mid);
			ResultSet resultSet = statement.executeQuery()) {
			return loader.loadList(resultSet);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
	
	public PreparedStatement createEmergencyDiagnosisPreparedStatement(Connection conn, long mid) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"SELECT d.id, d.visitId, d.icdCode, c.name, c.is_chronic FROM diagnosis d, icdcode c, officevisit ov "
					 + "WHERE d.visitId = ov.visitID AND ov.patientMID = ? AND d.icdCode = c.code "
					 + "AND (c.is_chronic OR ov.visitDate >= DATE_SUB(NOW(), INTERVAL 30 DAY)) "
					 + "ORDER BY ov.visitDate DESC");
		ps.setLong(1, mid);
		return ps;
	}
}
