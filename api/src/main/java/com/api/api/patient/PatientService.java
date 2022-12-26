package com.api.api.patient;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Method for saving a patient object to a table in the database
     * @param patient Object to be saved
     * @throws PatientExcecption If the patient already exists (dmi is the unique paramater)
     */
    public void savePatient(Patient patient) throws PatientExcecption {
        Optional<Patient> patientOptional = patientRepository.findPatientByDmi(patient.getDMI());
        if (patientOptional.isPresent()) {
            throw new PatientExcecption("Patient Already Exists!!");
        }

        patientRepository.save(patient);
    }

    /**
     * Method for getting all the patient in the database
     * @return List of patients
     */
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    /**
     * Method for getting the patient by the dmi
     * @param dmi attribute related for the object
     * @return List optional of patient (will contain 1 object at most)
     * @throws PatientExcecption If patient does not exist
     */
    public Optional<Patient> getPatientByDMI(String dmi) throws PatientExcecption {
        Optional<Patient> patientOptional = patientRepository.findPatientByDmi(dmi);
        if (patientOptional.isEmpty()) {
            throw new PatientExcecption("Patient does not exist!!");
        } else {
            return patientOptional;
        }
    }
    /**
     * Method for getting the patient by firstname and lastname
     * @param nom Name of the patient
     * @param prenom FirstName of the patient
     * @return Patient object from the database
     * @throws PatientExcecption If the patient does not exists
     */
    public List<Patient> getPatientByNomAndPrenom(String nom, String prenom) throws PatientExcecption{
        List<Patient> patientOptional = patientRepository.findPatientByNomAndPrenom(nom, prenom);
        if (patientOptional.isEmpty()) {
            throw new PatientExcecption("Patient does not exists!");
        } else {
            return patientOptional;
        }
    } 
    /**
     * Method to get patient by id
     * @param id Id of the patient
     * @return List optional containing patient object (1 at most)
     */
    public Optional<Patient> getPatient(Long id) {
        return patientRepository.findById(id);
    }


}
