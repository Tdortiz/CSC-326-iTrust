package edu.ncsu.csc.itrust.model.cptcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Statement;

import edu.ncsu.csc.itrust.model.SQLLoader;
import edu.ncsu.csc.itrust.model.cptcode.CPTCode;

public class CPTCodeSQLLoader implements SQLLoader<CPTCode> { 

	@Override
	public List<CPTCode> loadList(ResultSet rs) throws SQLException {
		List<CPTCode> list = new LinkedList<CPTCode>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	@Override
	public CPTCode loadSingle(ResultSet rs) throws SQLException {
		String cptCode = rs.getString("code");
		String name = rs.getString("name");
		return new CPTCode(cptCode, name);
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement ps, CPTCode insertObject,
			boolean newInstance) throws SQLException {
		String stmt = "";
		if( newInstance ){ // IS NEW CODE
			stmt = "INSERT INTO cptcode(code, name) "
					+ "VALUES (?, ?);";
		} else { // NOT NEW
			long id = Long.parseLong( insertObject.getCode() );
			stmt = "UPDATE cptcode SET  "
					+ "code=?, "
					+ "name=? "
					+ "WHERE code=" + id + ";";
		}
		
		ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
		ps.setString( 1, insertObject.getCode() );
		ps.setString( 2, insertObject.getName() );
		return ps;
	}
}
