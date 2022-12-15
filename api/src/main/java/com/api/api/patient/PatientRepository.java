package com.api.api.patient;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

    Optional<Patient> findPatientByDmi(String DMI);

    @Query("SELECT p FROM Patient WHERE p.nom = ?1 AND p.prenom = ?2")
    Optional<Patient> findPatientByNomAndPrenom(String nom, String prenom);
    
    
}
