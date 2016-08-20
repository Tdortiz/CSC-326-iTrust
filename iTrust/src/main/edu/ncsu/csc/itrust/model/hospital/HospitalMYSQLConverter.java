package edu.ncsu.csc.itrust.model.hospital;

import java.io.Serializable;
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
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.SQLLoader;
import edu.ncsu.csc.itrust.model.hospital.Hospital;
import edu.ncsu.csc.itrust.model.old.beans.HospitalBean;
import edu.ncsu.csc.itrust.model.old.beans.loaders.HospitalBeanLoader;
import edu.ncsu.csc.itrust.model.old.dao.mysql.HospitalsDAO;

public class HospitalMYSQLConverter implements HospitalDAO, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 964736533764578154L;
	private static DataSource ds;
	private static HospitalsDAO oldDAO;
	private static SQLLoader<Hospital> hospitalLoader;

	public HospitalMYSQLConverter() throws DBException{
		hospitalLoader = new HospitalMySQLLoader();
		try {
			Context ctx = new InitialContext();
				ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
		} catch (NamingException e) {
			throw new DBException(new SQLException("Context Lookup Naming Exception: "+e.getMessage()));
		}

	}
	
	public HospitalMYSQLConverter(DataSource ds){
		hospitalLoader = new HospitalMySQLLoader();
		HospitalMYSQLConverter.ds = ds;
	}
	
	@Override
	public List<Hospital> getAll() throws DBException{
		List<Hospital> ret = null;
		Connection conn = null;
		PreparedStatement pstring = null;
		ResultSet results = null;
		try{

			conn=ds.getConnection();
			pstring = conn.prepareStatement("SELECT HospitalID, HospitalName,  Address, City, State, Zip FROM hospitals;");
		
			results = pstring.executeQuery();
			ret = hospitalLoader.loadList(results);
		}
		catch(SQLException e){
			throw new DBException(e);
		}
		 finally {
				try{
					if(results !=null){
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

	
	@Override
	public String getHospitalName(String id) throws DBException{
			String hospitalname = null;
			Connection conn = null;
			PreparedStatement pstring = null;
			ResultSet results = null;
			try{

				conn=ds.getConnection();
				pstring = conn.prepareStatement("SELECT HospitalName FROM hospitals WHERE HospitalID = ?;");
			
				pstring.setString(1, id);
			
				results = pstring.executeQuery();
				boolean temp = results.next();
				
				hospitalname = results.getString("HospitalName");
		
			}
			catch(SQLException e){
				throw new DBException(e);
			}
			 finally {
					try{
						if(results !=null){
							results.close();
						}
					} catch (SQLException e) {
						throw new DBException(e);
					} finally {
							DBUtil.closeConnection(conn, pstring);
					}
				}
			return hospitalname;
	}

	@Override
	@Deprecated
	public Hospital getByID(long id) throws DBException {
		throw new IllegalStateException("HospitalID is NOT a 'long' value");

	}

	@Override
	public boolean add(Hospital hospital) throws DBException {
		boolean retval = false;
		Connection conn = null;
		PreparedStatement pstring = null;
		int results;
		try {
			conn = ds.getConnection();
			pstring = hospitalLoader.loadParameters(conn, pstring, hospital, true);
			results = pstring.executeUpdate();
			retval = (results >0);
		}
		catch(SQLException e){
			throw new DBException(e);
			
		}
		finally{

			DBUtil.closeConnection(conn, pstring);
		}
		return retval;	
	}

	@Override
	public boolean update(Hospital hospital) throws DBException {
		boolean retval = false;
		Connection conn = null;
		PreparedStatement pstring = null;
		int results;
		try {
			conn = ds.getConnection();
			pstring = hospitalLoader.loadParameters(conn, pstring, hospital, false);
			results = pstring.executeUpdate();
			retval = (results >0);
		}
		catch(SQLException e){
			throw new DBException(e);
			
		}
		finally{

			DBUtil.closeConnection(conn, pstring);
		}
		return retval;		}

}
