package com.api.api.treatment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    
    public static final String FIND_PROTOCOLE = "SELECT Protocole FROM Chimiotherapie ch WHERE ch.histo=?1 AND ch.stade LIKE ?2% AND ch.vems LIKE=?3 AND ch.paco2=?4 AND ch.type_histo=?5 AND ch.clairance=?6 AND ch.audiometrie=?7 AND ch.egfr=?8 AND ch.alk=?8 AND ch.braf=?9 AND ch.ros1=?10 AND ch.pdl1=?11 AND ch.ps=?12 AND ch.tabac=?13";


    @Query(value = FIND_PROTOCOLE, nativeQuery = true)
    public List<Object[]> findProtocole(
        String histo,
        String stade,
        String vems,
        String paco2,
        String typeHisto,
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

   
}
