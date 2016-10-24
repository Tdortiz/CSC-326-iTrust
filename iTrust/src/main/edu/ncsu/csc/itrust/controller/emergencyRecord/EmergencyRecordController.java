package edu.ncsu.csc.itrust.controller.emergencyRecord;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
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
        patientDAO = DAOFactory.getProductionInstance().getPatientDAO();
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
     * Loads the appropriate data for an EmergencyRecord for the given MID
     * @param mid The mid of the patient to load the record for
     * @return true if the record was loaded successfully, false otherwise
     */
    public boolean loadRecord(long mid){
        PatientBean p = null;
        try {
            p = patientDAO.getPatient(mid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        record.setName(p.getFirstName() + " " + p.getLastName());
        record.setAge(p.getAge());
        record.setGender(p.getGender());
        record.setContactName(p.getEmergencyName());
        record.setContactPhone(p.getEmergencyPhone());
        record.setBloodType(p.getBloodType());
        
        //TODO: fix these sets when the appropriate functionality is added
        record.setAllergies(null);
        record.setDiagnoses(null);
        record.setPrescriptions(null);
        record.setImmunizations(null);
        
        return true;
    }
}
