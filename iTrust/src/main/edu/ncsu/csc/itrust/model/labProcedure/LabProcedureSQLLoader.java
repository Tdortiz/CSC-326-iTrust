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
	private static final String PRIORITY = "priority";
	private static final String RESULTS = "results";
	private static final String IS_RESTRICTED = "isRestricted";
	private static final String STATUS = "status";
	
	/** Valid strings for the status enum. The ultimate source of truth for these
	 * is the lab procedure table creation statement in createTables.sql. */
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_IN_TRANSIT = "In transit";
	public static final String STATUS_RECEIVED = "Received";
	public static final String STATUS_TESTING = "Testing";
	public static final String STATUS_COMPLETED = "Completed";
	
	/** SQL statements relating to lab procedures */
	private static final String INSERT = "INSERT INTO " + LAB_PROCEDURE_TABLE_NAME + " ("
			+ COMMENTARY + ", "
			+ LAB_TECHNICIAN_ID + ", "
			+ OFFICE_VISIT_ID + ", "
			+ PRIORITY + ", "
			+ RESULTS + ", "
			+ IS_RESTRICTED + ", "
			+ STATUS
			+ " VALUES (?, ?, ?, ?, ?, ?, ?);";

	private static final String UPDATE = "UPDATE " + LAB_PROCEDURE_TABLE_NAME + " SET "
			+ COMMENTARY + "=?, "
			+ LAB_TECHNICIAN_ID + "=?, "
			+ OFFICE_VISIT_ID + "=?, "
			+ PRIORITY + "=?, "
			+ RESULTS + "=?, "
			+ IS_RESTRICTED + "=?, "
			+ STATUS + "=? "
			+ "WHERE " + LAB_PROCEDURE_ID + "=?;";
	
	public static final String SELECT_BY_LAB_PROCEDURE = "SELECT * from " + LAB_PROCEDURE_TABLE_NAME
			+ " WHERE " + LAB_PROCEDURE_ID + "=?;";
	
	public static final String SELECT_BY_LAB_TECHNICIAN= "SELECT * from " + LAB_PROCEDURE_TABLE_NAME
			+ " WHERE " + LAB_TECHNICIAN_ID + "=?;";
	
	public static final String SELECT_ALL= "SELECT * from " + LAB_PROCEDURE_TABLE_NAME + ";";

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
		labProcedure.setPriority(rs.getInt(PRIORITY));
		labProcedure.setResults(rs.getString(RESULTS));
		labProcedure.setIsRestricted(rs.getBoolean(IS_RESTRICTED));
		labProcedure.setStatus(rs.getLong(STATUS));
		
		return labProcedure;
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement ps, LabProcedure labProcedure,
			boolean newInstance) throws SQLException {
		StringBuilder query = new StringBuilder(newInstance ? INSERT : UPDATE);
		ps = conn.prepareStatement(query.toString());
		
		ps.setLong(1, labProcedure.getLabTechnicianID());
		ps.setLong(2, labProcedure.getOfficeVisitID());
		ps.setInt(3, labProcedure.getPriority());
		ps.setBoolean(4, labProcedure.isRestricted());
		ps.setLong(5, labProcedure.getStatus().getID());
		ps.setString(6, labProcedure.getCommentary());
		ps.setString(7, labProcedure.getResults());
		
		if(!newInstance) {
			ps.setLong(8, labProcedure.getLabProcedureID());
		}
		
		return ps;
	}

}
