package edu.ncsu.csc.itrust.model.apptType;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.exception.DBException;

@ManagedBean(name="appt_type")
@SessionScoped
public class ApptTypeMYSQLConvBean  implements Serializable, ApptTypeDataBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4035629854880598008L;
	@Resource(name="jdbc/itrust")
	private static DataSource ds;
	//private static DAOFactory factory;
	private static ApptTypeSQLLoader apptTypeLoader;
	//private transient final ProductionConnectionDriver driver = new ProductionConnectionDriver();
	/**
	 * @throws DBException 
	 * 
	 */

	public ApptTypeMYSQLConvBean() throws DBException{
			apptTypeLoader = new ApptTypeSQLLoader();
			try {
				Context ctx = new InitialContext();
					ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
			} catch (NamingException e) {
				throw new DBException(new SQLException("Context Lookup Naming Exception: "+e.getMessage()));
			}

	}
	
	public ApptTypeMYSQLConvBean(DataSource ds){
		apptTypeLoader = new ApptTypeSQLLoader();
		ApptTypeMYSQLConvBean.ds = ds;
	}
	
	@Override
	public Map<Long, ApptType> getApptTypeIDs(String name) throws DBException{
		Map<Long, ApptType> apptRef = null;
		Connection conn = null;
		PreparedStatement pstring = null;
		ResultSet results = null;
		try{
			apptRef = new HashMap<Long, ApptType>();
			conn=ds.getConnection();
			pstring = conn.prepareStatement("SELECT * FROM appointmenttype WHERE appt_type=?");
		
			pstring.setString(1, name);
		
			results = pstring.executeQuery();
			apptRef = apptTypeLoader.loadRefList(results);
	
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
					try{
						DBUtil.closeConnection(conn, pstring);
					}
					catch(Exception e){
						//donothing
					}
					finally{
						
						//close ds?
					}
				}
			}
		return apptRef;
	}

	@Override
	public String getApptTypeName(Long id) throws DBException{
		String apptname = null;
		Connection conn = null;
		PreparedStatement pstring = null;
		ResultSet results = null;
		try{

			conn=ds.getConnection();
			pstring = conn.prepareStatement("SELECT appt_type FROM appointmenttype WHERE apptType_id=?;");
		
			pstring.setLong(1, id);
		
			results = pstring.executeQuery();
			boolean check = results.next();
			apptname = results.getString("appt_type");
//			apptname = apptTypeLoader.loadSingle(results).getName();
	
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
		return apptname;
	}

	@Override
	public List<ApptType> getAppointmentTypes() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}



}
