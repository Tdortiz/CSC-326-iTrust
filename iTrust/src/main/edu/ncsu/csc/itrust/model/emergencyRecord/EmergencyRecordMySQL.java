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
import edu.ncsu.csc.itrust.model.diagnosis.DiagnosisData;
import edu.ncsu.csc.itrust.model.diagnosis.DiagnosisMySQL;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AllergyDAO;

public class EmergencyRecordMySQL {
    private DataSource ds;
    private DiagnosisData diagnosisData;
    private AllergyDAO allergyData;
    
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
        diagnosisData = new DiagnosisMySQL(ds);
        allergyData = DAOFactory.getProductionInstance().getAllergyDAO();
    }
    
    /**
     * Constructor for testing purposes
     * @param ds The DataSource to use
     * @param allergyData the AllergyDAO to use
     */
    public EmergencyRecordMySQL(DataSource ds, AllergyDAO allergyData) {
        this.ds = ds;
        diagnosisData = new DiagnosisMySQL(ds);
        this.allergyData = allergyData;
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
     * @throws DBException 
     */
    private EmergencyRecord loadRecord(ResultSet rs) throws SQLException, DBException {
        EmergencyRecord newRecord = new EmergencyRecord();
        if (!rs.next()){
            return null;
        }
        long mid = rs.getLong("MID");
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
        newRecord.setAllergies(allergyData.getAllergies(mid));
        newRecord.setDiagnoses(diagnosisData.getAllEmergencyDiagnosis(mid));
        newRecord.setPrescriptions(null);
        newRecord.setImmunizations(null);
        return newRecord;
    }
}
