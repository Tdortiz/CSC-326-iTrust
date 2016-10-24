package edu.ncsu.csc.itrust.controller.labProcedure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedure;
import edu.ncsu.csc.itrust.model.labProcedure.LabProcedureSQLLoader;

public class LabProcedureController {
	
	private DataSource ds;
	
	public LabProcedureController() {
		
	}
	
	/**
	 * Constructor injection, intended only for unit testing purposes.
	 * @param ds The injected DataSource dependency
	 */
	public LabProcedureController(DataSource ds) {
		this.ds = ds;
	}

	public List<LabProcedure> getLabProceduresByLabTechnician(long technicianID) throws DBException {
		return new ArrayList<LabProcedure>(); // TODO
//		Connection conn = null;
//		PreparedStatement query = null;
//		ResultSet results = null;
//		if (ValidationFormat.NPMID.getRegex().matcher(Long.toString(technicianID)).matches()) {
//			try {
//				conn = ds.getConnection();
//				query = conn.prepareStatement(LabProcedureSQLLoader.SELECT_BY_LAB_TECHNICIAN);
//				query.setLong(1, technicianID);
//				results = query.executeQuery();
//
//				final List<LabProcedure> procedureList = loader.loadList(results);
//				return procedureList;
//			} catch (SQLException e) {
//				throw new DBException(e);
//			} finally {
//				try {
//					if (results != null) {
//						results.close();
//					}
//				} catch (SQLException e) {
//					throw new DBException(e);
//				} finally {
//					DBUtil.closeConnection(conn, query);
//				}
//			}
//		} else {
//			return null;
//		}
	}
	
	public List<LabProcedure> getPendingLabProcedures(long technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().equals(LabProcedureSQLLoader.STATUS_PENDING);
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getInTransitLabProcedures(long technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().equals(LabProcedureSQLLoader.STATUS_IN_TRANSIT);
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getReceivedLabProcedures(long technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().equals(LabProcedureSQLLoader.STATUS_RECEIVED);
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getTestingLabProcedures(long technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().equals(LabProcedureSQLLoader.STATUS_TESTING);
		}).collect(Collectors.toList());
	}

	public List<LabProcedure> getCompletedLabProcedures(long technicianID) throws DBException {
		return getLabProceduresByLabTechnician(technicianID).stream().filter((o) -> {
			return o.getStatus().equals(LabProcedureSQLLoader.STATUS_COMPLETED);
		}).collect(Collectors.toList());
	}
}
