package edu.ncsu.csc.itrust.model.emergencyRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.exception.DBException;

public class EmergencyRecordMySQL {
    private DataSource ds;
    
    /**
     * Standard constructor for use in deployment
     * @throws DBException
     */
    public EmergencyRecordMySQL() throws DBException {
        try {
            Context ctx = new InitialContext();
            this.ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
        } catch (NamingException e) {
            throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
        }
    }
    
    /**
     * Constructor for testing purposes
     * @param ds The DataSource to use
     */
    public EmergencyRecordMySQL(DataSource ds) {
        this.ds = ds;
    }
    
    /**
     * Gets the EmergencyRecord for the patient with the given MID
     * @param patientMID The MID of the patient whose record you want
     * @return The retrieved EmergencyRecord
     * @throws DBException
     */
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
    
    /**
     * A utility method that uses a ResultSet to load an EmergencyRecord.
     * @param rs The ResultSet to load from
     * @return The loaded EmergencyRecord
     * @throws SQLException
     */
    private EmergencyRecord loadRecord(ResultSet rs) throws SQLException {
        EmergencyRecord newRecord = new EmergencyRecord();
        if (!rs.next()){
            return null;
        }
        newRecord.setName(rs.getString("firstName") + " " + rs.getString("lastName"));
        LocalDate now = LocalDate.now();
        LocalDate birthdate = rs.getDate("DateOfBirth").toLocalDate();
        int age = (int) ChronoUnit.YEARS.between(birthdate, now);
        newRecord.setAge(age);
        newRecord.setGender(rs.getString("Gender"));
        newRecord.setContactName(rs.getString("eName"));
        newRecord.setContactPhone(rs.getString("ePhone"));
        newRecord.setBloodType(rs.getString("BloodType"));
        
        //TODO: code for loading the allergy, diagnosis, prescription, and
        //immunization lists
        newRecord.setAllergies(null);
        newRecord.setDiagnoses(null);
        newRecord.setPrescriptions(null);
        newRecord.setImmunizations(null);
        return newRecord;
    }
}
