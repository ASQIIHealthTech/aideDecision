package com.api.api.treatment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    
    public static final String FIND_PROTOCOLE = "SELECT DISTINCT Protocole FROM chimiotherapie ch WHERE ch.type_histo=?1 AND ch.clairance LIKE ?2 AND ch.audiometrie=?3 AND ch.egfr=?4 AND ch.alk=?5 AND ch.braf=?6 AND ch.ros1=?7 AND ch.pdl1=?8 AND ch.ps=?9 AND ch.tabac=?10";
    public static final String FIND_PROTOCOLE_DETAILS = "SELECT intercure, molecule, dose, unite, jour_pord, nb_cures FROM Protocole p WHERE p.protocole=?1";

    @Query(value = FIND_PROTOCOLE, nativeQuery = true)
    public List<Object[]> findProtocole(
        String histo,
        String clairance,
        String audiometrie,
        String egfr,
        String alk,
        String braf,
        String ros1,
        String pdl1,
        String ps,
        String tabac
    );

    @Query(value = FIND_PROTOCOLE_DETAILS, nativeQuery = true)
    public List<Object[]> findProtocoleDetails(
        String Protocole 
    );

   
}
