package edu.ncsu.csc.itrust.model.immunization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cptcode.CPTCode;
import edu.ncsu.csc.itrust.model.SQLLoader;

public class ImmunizationSQLLoader implements SQLLoader<Immunization> { 

	@Override
	public List<Immunization> loadList(ResultSet rs) throws SQLException {
		List<Immunization> list = new LinkedList<Immunization>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	@Override
	public Immunization loadSingle(ResultSet rs) throws SQLException {
		long id = rs.getLong("id");
		long visitId = rs.getLong("visitId");
		String cptCode = rs.getString("cptCode");
		String name = rs.getString("name");
		return new Immunization(id, visitId, new CPTCode(cptCode, name));
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement ps, Immunization insertObject,
			boolean newInstance) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
