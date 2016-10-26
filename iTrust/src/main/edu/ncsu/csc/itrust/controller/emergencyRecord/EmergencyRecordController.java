package edu.ncsu.csc.itrust.controller.emergencyRecord;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecordMySQL;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AllergyDAO;

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
    /**
     * Constructor for testing purposes.
     * 
     * @param ds The DataSource to use
     * @param allergyData the AllergyDAO to use
     */
    public EmergencyRecordController(DataSource ds, AllergyDAO allergyData) throws DBException{
        record = new EmergencyRecord();
        sql = new EmergencyRecordMySQL(ds, allergyData);
    }
    
    /**
     * Gets the currently-loaded Emergency Record
     * 
     * Warning: loadRecord() MUST be called prior to calling this method for 
     * the first time. If loadRecord() is not called, there will be no data.
     * 
     * @return The EmergencyRecord
     */
    public EmergencyRecord getRecord(){
        return record;
    }
    
    /**
     * Loads the appropriate data for an EmergencyRecord for the given MID.
     * The loaded record is returned, but it is also stored for later retrieval
     * with getRecord(). This method MUST be called before calling getRecord().
     * 
     * @param mid The mid of the patient to load the record for
     * @return The loaded EmergencyRecord if loaded successfully, null if
     *         loading failed
     */
    public EmergencyRecord loadRecord(String midString){
        try {
        	long mid = Long.parseLong(midString);
            record = sql.getEmergencyRecordForPatient(mid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return record;
    }
}
