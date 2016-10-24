package edu.ncsu.csc.itrust.model.emergencyRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.exception.DBException;

public class EmergencyRecordMySQL {
    private DataSource ds;
    
    public EmergencyRecordMySQL() throws DBException {
        try {
            Context ctx = new InitialContext();
            this.ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
        } catch (NamingException e) {
            throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
        }
    }
    
    public EmergencyRecordMySQL(DataSource ds){
        this.ds = ds;
    }
    
    public EmergencyRecord getEmergencyRecordForPatient(long patientMID) throws DBException{
        Connection conn = null;
        PreparedStatement pstring = null;
        ResultSet results = null;
        
        try {
            conn = ds.getConnection();
            pstring = conn.prepareStatement("SELECT * FROM patients WHERE MID=?");

            pstring.setLong(1, patientMID);

            results = pstring.executeQuery();
            return loadRecord(results);
        } catch (SQLException e){
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            try {
                if (results != null){
                    results.close();
                }
            } catch (SQLException e) {
                throw new DBException(e);
            } finally {
                DBUtil.closeConnection(conn, pstring);
            }
        }
    }
    
    public EmergencyRecord loadRecord(ResultSet rs) throws SQLException{
        EmergencyRecord ret = new EmergencyRecord();
        if (!rs.next()){
            return null;
        }
        ret.setName(rs.getString("firstName") + " " + rs.getString("lastName"));
        LocalDate now = LocalDate.now();
        LocalDate birthdate = rs.getDate("DateOfBirth").toLocalDate();
        int age = (int) ChronoUnit.YEARS.between(birthdate, now);
        ret.setAge(age);
        ret.setGender(rs.getString("Gender"));
        ret.setContactName(rs.getString("eName"));
        ret.setContactPhone(rs.getString("ePhone"));
        ret.setBloodType(rs.getString("BloodType"));
        
        //TODO: code for loading the allergy, diagnosis, prescription, and
        //immunization lists
        ret.setAllergies(null);
        ret.setDiagnoses(null);
        ret.setPrescriptions(null);
        ret.setImmunizations(null);
        return ret;
    }
}
