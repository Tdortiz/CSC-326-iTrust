package edu.ncsu.csc.itrust.model.emergencyRecord;

import java.util.List;

import edu.ncsu.csc.itrust.model.old.enums.BloodType;
import edu.ncsu.csc.itrust.model.old.enums.Gender;

/**
 * A class for storing emergency health record data for UC21
 * @author nmiles
 *
 */
public class EmergencyRecord {
    private String name;
    private int age;
    private Gender gender;
    private String contactName;
    private String contactPhone;
    private List<Object> allergies;
    private BloodType bloodType;
    private List<Object> diagnoses;
    private List<Object> prescriptions;
    private List<Object> immunizations;
    
    /**
     * Get the patient name
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the patient name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the patient age
     * @return
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Set the patient age
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Get the patient's gender
     * @return
     */
    public Gender getGender() {
        return gender;
    }
    
    /**
     * Set the patient's gender
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    /**
     * Get the patient's emergency contact's name
     * @return
     */
    public String getContactName() {
        return contactName;
    }
    
    /**
     * Set the patient's emergency contact's name
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     * Get the patient's emergency contact's phone number
     * @return
     */
    public String getContactPhone() {
        return contactPhone;
    }
    
    /**
     * Set the patient's emergency contact's name
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    /**
     * Gets a list of all this patient's allergies.
     * WARNING: Allergy functionality has not been added to the system yet, so
     * this list will always be empty or null.
     * @return
     */
    public List<Object> getAllergies() {
        return allergies;
    }
    
    /**
     * Sets a List of all this patient's allergies
     * @param allergies
     */
    public void setAllergies(List<Object> allergies) {
        this.allergies = allergies;
    }
    
    /**
     * Gets the patient's blood type
     * @return
     */
    public BloodType getBloodType() {
        return bloodType;
    }
    
    /**
     * Sets the patient's blood type
     * @param bloodType
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
    
    /**
     * Gets a List of all the patient's diagnoses
     * WARNING: Diagnosis functionality has not been added to the system yet,
     * so this list will always be empty or null.
     * @return
     */
    public List<Object> getDiagnoses() {
        return diagnoses;
    }
    
    /**
     * Sets the List of this patient's diagnoses
     * @param diagnoses
     */
    public void setDiagnoses(List<Object> diagnoses) {
        this.diagnoses = diagnoses;
    }
    
    /**
     * Gets a List of all the patient's prescriptions
     * WARNING: Prescription functionality has not been added to the system yet,
     * so this list will always be empty or null.
     * @return
     */
    public List<Object> getPrescriptions() {
        return prescriptions;
    }
    
    /**
     * Sets a List of all the patient's prescriptions
     * @param prescriptions
     */
    public void setPrescriptions(List<Object> prescriptions) {
        this.prescriptions = prescriptions;
    }
    
    /**
     * Gets a List of all the patient's immunizations
     * WARNING: Immunization functionality has not been added to the system yet,
     * so this list will always be empty or null.
     * @return
     */
    public List<Object> getImmunizations() {
        return immunizations;
    }
    
    /**
     * Sets a List of all the patient's immunizations
     * @param immunizations
     */
    public void setImmunizations(List<Object> immunizations) {
        this.immunizations = immunizations;
    }
}
