package edu.ncsu.csc.itrust.model.labProcedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

public class LabProcedureSQLLoader implements SQLLoader<LabProcedure> {

	/** Lab Procedure table name */
	private static final String LAB_PROCEDURE_TABLE_NAME = "labProcedure";

	/** Database column names */
	private static final String COMMENTARY = "commentary";
	private static final String LAB_PROCEDURE_ID = "labProcedureID";
	private static final String LAB_TECHNICIAN_ID = "labTechnicianID";
	private static final String OFFICE_VISIT_ID = "officeVisitID";
	private static final String LAB_PROCEDURE_CODE = "labProcedureCode";
	private static final String PRIORITY = "priority";
	private static final String RESULTS = "results";
	private static final String IS_RESTRICTED = "isRestricted";
	private static final String STATUS = "status";
	private static final String UPDATED_DATE = "updatedDate";
	private static final String CONFIDENCE_INTERVAL_LOWER = "confidenceIntervalLower";
	private static final String CONFIDENCE_INTERVAL_UPPER = "confidenceIntervalUpper";

	/** SQL statements relating to lab procedures */
	private static final String INSERT = "INSERT INTO " + LAB_PROCEDURE_TABLE_NAME + " (" 
			+ COMMENTARY + ", "
			+ LAB_TECHNICIAN_ID + ", "
			+ OFFICE_VISIT_ID + ", "
			+ LAB_PROCEDURE_CODE + ", "
			+ PRIORITY + ", "
			+ RESULTS + ", "
			+ IS_RESTRICTED + ", "
			+ STATUS + ", "
			+ UPDATED_DATE + ", "
			+ CONFIDENCE_INTERVAL_LOWER + ", "
			+ CONFIDENCE_INTERVAL_UPPER + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String UPDATE = "UPDATE " + LAB_PROCEDURE_TABLE_NAME + " SET "
			+ COMMENTARY + "=?, "
			+ LAB_TECHNICIAN_ID + "=?, "
			+ OFFICE_VISIT_ID + "=?, "
			+ LAB_PROCEDURE_CODE + ", "
			+ PRIORITY + "=?, "
			+ RESULTS + "=?, "
			+ IS_RESTRICTED + "=?, "
			+ STATUS + "=?, "
			+ UPDATED_DATE + "=?, " 
			+ CONFIDENCE_INTERVAL_LOWER + "=?, "
			+ CONFIDENCE_INTERVAL_UPPER + "=? WHERE " + LAB_PROCEDURE_ID + "=?;";

	public static final String SELECT_BY_LAB_PROCEDURE = "SELECT * from " + LAB_PROCEDURE_TABLE_NAME + " WHERE "
			+ LAB_PROCEDURE_ID + "=?;";

	public static final String SELECT_BY_LAB_TECHNICIAN = "SELECT * from " + LAB_PROCEDURE_TABLE_NAME + " WHERE "
			+ LAB_TECHNICIAN_ID + "=?;";

	public static final String SELECT_BY_OFFICE_VISIT = "SELECT * from " + LAB_PROCEDURE_TABLE_NAME + " WHERE "
			+ OFFICE_VISIT_ID + "=?;";

	public static final String SELECT_ALL = "SELECT * from " + LAB_PROCEDURE_TABLE_NAME + ";";

	public static final String REMOVE_BY_LAB_PROCEDURE = "DELETE FROM " + LAB_PROCEDURE_TABLE_NAME + " WHERE "
			+ LAB_PROCEDURE_ID + "=?;";

	@Override
	public List<LabProcedure> loadList(ResultSet rs) throws SQLException {
		List<LabProcedure> list = new ArrayList<LabProcedure>();
		while (rs.next())
			list.add(loadSingle(rs));
		return list;
	}

	@Override
	public LabProcedure loadSingle(ResultSet rs) throws SQLException {
		LabProcedure labProcedure = new LabProcedure();

		labProcedure.setLabProcedureID(rs.getLong(LAB_PROCEDURE_ID));
		labProcedure.setCommentary(rs.getString(COMMENTARY));
		labProcedure.setLabTechnicianID(rs.getLong(LAB_TECHNICIAN_ID));
		labProcedure.setOfficeVisitID(rs.getLong(OFFICE_VISIT_ID));
		labProcedure.setLabProcedureCode(rs.getString(LAB_PROCEDURE_CODE));
		labProcedure.setPriority(rs.getInt(PRIORITY));
		labProcedure.setResults(rs.getString(RESULTS));
		labProcedure.setIsRestricted(rs.getBoolean(IS_RESTRICTED));
		labProcedure.setStatus(rs.getLong(STATUS));
		labProcedure.setUpdatedDate(rs.getTimestamp(UPDATED_DATE));
		labProcedure.setConfidenceIntervalLower(rs.getInt(CONFIDENCE_INTERVAL_LOWER));
		labProcedure.setConfidenceIntervalUpper(rs.getInt(CONFIDENCE_INTERVAL_UPPER));

		return labProcedure;
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement ps, LabProcedure labProcedure,
			boolean newInstance) throws SQLException {
		StringBuilder query = new StringBuilder(newInstance ? INSERT : UPDATE);
		ps = conn.prepareStatement(query.toString());

		ps.setLong(1, labProcedure.getLabTechnicianID());
		ps.setLong(2, labProcedure.getOfficeVisitID());
		ps.setString(3, labProcedure.getLabProcedureCode());
		ps.setInt(4, labProcedure.getPriority());
		ps.setBoolean(5, labProcedure.isRestricted());
		ps.setLong(6, labProcedure.getStatus().getID());
		ps.setString(7, labProcedure.getCommentary());
		ps.setString(8, labProcedure.getResults());
		ps.setTimestamp(9, labProcedure.getUpdatedDate());
		ps.setInt(10, labProcedure.getConfidenceIntervalLower());
		ps.setInt(11, labProcedure.getConfidenceIntervalUpper());
		

		if (!newInstance) {
			ps.setLong(12, labProcedure.getLabProcedureID());
		}

		return ps;
	}

}
