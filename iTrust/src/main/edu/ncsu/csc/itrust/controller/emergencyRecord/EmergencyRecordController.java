package edu.ncsu.csc.itrust.controller.emergencyRecord;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecordMySQL;

/**
 * A controller class for EmergencyRecord
 * @author nmiles
 *
 */
@ManagedBean(name="emergency_record_controller")
@SessionScoped
public class EmergencyRecordController {
    private EmergencyRecord record;
    private EmergencyRecordMySQL sql;
    
    /**
     * Constructs a new EmergencyRecordController
     * @throws DBException 
     */
    public EmergencyRecordController() throws DBException{
        record = new EmergencyRecord();
        sql = new EmergencyRecordMySQL();
    }
    
    public EmergencyRecordController(DataSource ds) throws DBException{
        record = new EmergencyRecord();
        sql = new EmergencyRecordMySQL(ds);
    }
    
    /**
     * Gets the Emergency Record
     * @return
     */
    public EmergencyRecord getRecord(){
        return record;
    }
    
    /**
     * Loads the appropriate data for an EmergencyRecord for the given MID
     * @param mid The mid of the patient to load the record for
     * @return true if the record was loaded successfully, false otherwise
     */
    public boolean loadRecord(String midString){
        long mid = Long.parseLong(midString);
        try {
            record = sql.getEmergencyRecordForPatient(mid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
