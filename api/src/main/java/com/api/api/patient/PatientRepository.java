package com.api.api.patient;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

    Optional<Patient> findPatientByDmi(String DMI);

    List<Patient> findPatientByNomAndPrenom(String nom, String prenom);
    
    
    
}
