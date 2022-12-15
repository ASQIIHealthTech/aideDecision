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

    public void savePatient(Patient patient) throws PatientExcecption {
        Optional<Patient> patientOptional = patientRepository.findPatientByDmi(patient.getDMI());
        if (patientOptional.isPresent()) {
            throw new PatientExcecption("Patient Already Exists!!");
        }

        patientRepository.save(patient);
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientByDMI(String dmi) throws PatientExcecption {
        Optional<Patient> patientOptional = patientRepository.findPatientByDmi(dmi);
        if (patientOptional.isEmpty()) {
            throw new PatientExcecption("Patient does not exist!!");
        } else {
            return patientOptional;
        }
    }

    public Optional<Patient> getPatientByNomAndPrenom(String nom, String prenom) throws PatientExcecption{
        Optional<Patient> patientOptional = patientRepository.findPatientByNomAndPrenom(nom, prenom);
        if (patientOptional.isEmpty()) {
            throw new PatientExcecption("Patient does not exists!");
        } else {
            return patientOptional;
        }
    }

    public Optional<Patient> getPatient(Long id) {
        return patientRepository.findById(id);
    }


}
