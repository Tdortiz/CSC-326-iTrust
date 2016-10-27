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
    private EmergencyRecordMySQL sql;
    
    /**
     * Constructs a new EmergencyRecordController
     * @throws DBException 
     */
    public EmergencyRecordController() throws DBException{
        sql = new EmergencyRecordMySQL();
    }
    /**
     * Constructor for testing purposes.
     * 
     * @param ds The DataSource to use
     * @param allergyData the AllergyDAO to use
     */
    public EmergencyRecordController(DataSource ds, AllergyDAO allergyData) throws DBException{
        sql = new EmergencyRecordMySQL(ds, allergyData);
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
            return sql.getEmergencyRecordForPatient(mid);
        } catch (Exception e) {
            return null;
        }
    }
}
