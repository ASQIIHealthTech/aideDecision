package com.api.api.medecin;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class MedecinDTOMapper implements Function<Medecin, MedecinDTO> {

    @Override
    public MedecinDTO apply(Medecin medecin) {
        return new MedecinDTO(medecin.getNom(), medecin.getPrenom(), medecin.getUsername());
    }

}
