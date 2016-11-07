package edu.ncsu.csc.itrust.controller.prescription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.old.beans.MedicationBean;
import edu.ncsu.csc.itrust.model.prescription.Prescription;
import edu.ncsu.csc.itrust.model.prescription.PrescriptionMySQL;

@ManagedBean(name = "prescription_controller")
@SessionScoped
public class PrescriptionController extends iTrustController {

	private static final String INVALID_PRESCRIPTION = "Invalid Prescription";
	private PrescriptionMySQL sql;
	
    public PrescriptionController() throws DBException{
    	super();
        sql = new PrescriptionMySQL();
    }
    

    /**
	 * Constructor injection, intended only for unit testing purposes.
	 * 
	 * @param ds
	 *            The injected DataSource dependency
	 */
    public PrescriptionController(DataSource ds) throws DBException{
    	super();
    	this.sql = new PrescriptionMySQL(ds);
    }
    
    public void add(Prescription prescription){
    	// TODO
    }
    
    public void edit(Prescription prescription){
    	// TODO
    }
    
    public void remove(){
    	// TODO
    }
    
    public List<Prescription> getPrescriptionsForCurrentPatient(){
    	String pid = this.getSessionUtils().getCurrentPatientMID();
    	return getPrescriptionsByPatientID(pid);
    }
	
	public List<Prescription> getPrescriptionsByPatientID(String patientID){
		List<Prescription> pList = new ArrayList<Prescription>();
		long pid;
		Prescription p = null;
		
        try {
        	pid = Long.parseLong(patientID);
        	for(int i = 0; i < 1000; i++){
    			MedicationBean b = new MedicationBean(i + " ", i + " ");
    			p = new Prescription();
    			p.setPatientMID( pid );
    			
    			
    			p.setDrugCode(b);
    			
    			p.setStartDate(LocalDate.now());
    			p.setEndDate(LocalDate.now());
    			p.setOfficeVisitId(1);
    			p.setId(i);
    			pList.add(p);
    		}
        	
        } catch (Exception e) {
            return null;
        }

		return pList;
	}
	
}
