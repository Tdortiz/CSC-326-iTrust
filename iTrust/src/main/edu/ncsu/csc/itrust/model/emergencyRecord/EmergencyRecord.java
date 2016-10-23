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
}
