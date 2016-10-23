package edu.ncsu.csc.itrust.controller.emergencyRecord;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;

/**
 * A controller class for EmergencyRecord
 * @author nmiles
 *
 */
@ManagedBean(name="emergency_record_controller")
@SessionScoped
public class EmergencyRecordController {
    private PatientDAO patientDAO;
    private EmergencyRecord record;
    
    /**
     * Constructs a new EmergencyRecordController
     */
    public EmergencyRecordController(){
        patientDAO = new PatientDAO(DAOFactory.getProductionInstance());
        record = new EmergencyRecord();
    }
    
    /**
     * Gets the Emergency Record
     * @return
     */
    public EmergencyRecord getRecord(){
        return record;
    }
    
    /**
     * Stub
     * @param mid
     * @return
     */
    public boolean loadRecord(long mid){
        return false;
    }
}
