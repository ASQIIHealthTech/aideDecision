package com.api.api.patient;

import com.api.api.consultation.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import java.util.List;

@Table
@Entity 
public class Patient {

    @Id
    @SequenceGenerator(
        name = "patient_sequence",
        sequenceName = "patient_sequence",
        allocationSize = 1

    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "patient_sequence"
    )
    private Long id;
    private String nom;
    private String prenom;
    private String dmi;
    @Column(name = "dateNaissance")
    private String dateNaissance;
    private String sexe;
    @Column(name = "cancerType")
    private String cancerType;

    @OneToMany(targetEntity = Consultation.class, mappedBy = "patient")
    @Embedded
    private List<Consultation> consultations;

    public Patient() {

    }

    public Patient(String nom, String prenom, String dmi, String dateNaissance, String sexe,
            String cancerType, List<Consultation> consultations) {
        this.nom = nom;
        this.prenom = prenom;
        this.dmi = dmi;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.cancerType = cancerType;
        this.consultations = consultations;
    }

    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
        consultation.setPatient(this);
    }

    public void removeConsultation(Consultation consultation) {
        consultations.remove(consultation);
        consultation.setPatient(null);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenon) {
        this.prenom = prenon;
    }

    public String getDMI() {
        return dmi;
    }

    public void setDMI(String dMI) {
        this.dmi = dMI;
    }

    public String getDataNaissance() {
        return dateNaissance;
    }

    public void setDataNaissance(String dataNaissance) {
        this.dateNaissance = dataNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCancerType() {
        return cancerType;
    }

    public void setCancerType(String cancerType) {
        this.cancerType = cancerType;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return "Patient [Nom=" + nom + ", Prenom=" + prenom + ", DMI=" + dmi + ", dataNaissance=" + dateNaissance
                + ", sexe=" + sexe + ", CancerType=" + cancerType + ", consultations=" + consultations + "]";
    }

}
