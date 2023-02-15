package com.api.api.medecin;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MedecinService {

    List<MedecinDTO> retreiveAllMedecins();

    MedecinDTO addMedecin(Medecin med);

    void deleteMedecin(Long id);

    MedecinDTO updateMedecin(Medecin med);

    MedecinDTO retrieveMedecin(Long id) throws MedecinException;

    ResponseEntity<String> login(String username, String password);
}
