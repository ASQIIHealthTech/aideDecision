package com.api.api.patient;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByDmi(String DMI);

    List<Patient> findPatientByNomAndPrenom(String nom, String prenom);

}
