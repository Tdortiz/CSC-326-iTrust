package edu.ncsu.csc.itrust.model.hospital;

import java.io.Serializable;
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
import edu.ncsu.csc.itrust.model.hospital.Hospital;
import edu.ncsu.csc.itrust.model.old.beans.HospitalBean;
import edu.ncsu.csc.itrust.model.old.beans.loaders.HospitalBeanLoader;
import edu.ncsu.csc.itrust.model.old.dao.mysql.HospitalsDAO;

public class HospitalMYSQLConverter implements HospitalDAOBean, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 964736533764578154L;
	private static DataSource ds;
	private static HospitalsDAO oldDAO;
	private static HospitalBeanLoader hospitalLoader;

	public HospitalMYSQLConverter() throws DBException{
		hospitalLoader = new HospitalBeanLoader();
		try {
			Context ctx = new InitialContext();
				ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
		} catch (NamingException e) {
			throw new DBException(new SQLException("Context Lookup Naming Exception: "+e.getMessage()));
		}

	}
	
	public HospitalMYSQLConverter(DataSource ds){
		hospitalLoader = new HospitalBeanLoader();
		HospitalMYSQLConverter.ds = ds;
	}
	
	@Override
	public List<Hospital> getAllHospitals() throws DBException{
		return null;
	//	List<Hospital> ret = new ArrayList<Hospital>();
//		return ret;
	}

	private Hospital convertOldPOJO(HospitalBean old) {
		Hospital temp = new Hospital();
		temp.setHospitalID(old.getHospitalID());
		temp.setHospitalName(old.getHospitalName());
		temp.setHospitalAddress(old.getHospitalAddress());
		temp.setHospitalCity(old.getHospitalCity());
		temp.setHospitalState(old.getHospitalState());
		temp.setHospitalZip(old.getHospitalZip());
		return temp;
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
}
