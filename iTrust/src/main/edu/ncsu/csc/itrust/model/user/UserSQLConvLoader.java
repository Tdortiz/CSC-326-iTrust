package edu.ncsu.csc.itrust.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.model.SQLLoader;
import edu.ncsu.csc.itrust.model.old.enums.Role;

public class UserSQLConvLoader implements SQLLoader<User> {


	
	
	@Override
	public List<User> loadList(ResultSet rs) throws SQLException {
		List<User> list  = new ArrayList<User>();
		while (rs.next())
			list.add(loadSingle(rs));
		return list;
	}

	@Override
	public User loadSingle(ResultSet rs) throws SQLException {
		User ret = new User();
		long mid = rs.getLong("MID");
		String roleName = rs.getString("Role");
		Role userRole = Role.parse(roleName);
		String fn = rs.getString("firstName");
		String ln = rs.getString("lastName");
		String name = "";
		//Not sure, but tester was originally nameless so we'll leave him/her that way
		if(!(userRole.equals(Role.TESTER))){
			ret.setFirstName(fn);
			ret.setLastName(ln);
		}
		ret.setRole(userRole);
		ret.setMID(mid);
		return ret;
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement ps, User insertObject,
			boolean newInstance) throws SQLException {
				throw new IllegalStateException("unimplemented");
	}
	private String getPersonnelName(long MID) throws SQLException{
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
				ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
		} catch (NamingException e) {
			throw new SQLException(("Context Lookup Naming Exception: " + e.getMessage()));
		}
		if(ds == null){
			return null;
		}

		Connection conn = null;
		PreparedStatement pstring = null;
		ResultSet results = null;
		try {
			conn=ds.getConnection();
			pstring = conn.prepareStatement("SELECT lastName, firstName FROM personnel WHERE MID = ?;");
			pstring.setString(1, Long.toString(MID));
			String temp = pstring.toString();
			results = pstring.executeQuery();
			results.next();
			String fn = results.getString("firstName");
			String ln = results.getString("lastName");
			return ln + ", "+ fn;
		}
		finally {
		
			try{
				if(results !=null){
					results.close();
				}
			} finally {
				DBUtil.closeConnection(conn, pstring);
			}
		}

		
	}

}
