package com.api.api.medecin;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class LoginDTOMapper implements Function<Medecin, LoginDTO> {

    @Override
    public LoginDTO apply(Medecin medecin) {
        return new LoginDTO(medecin.getUsername(), medecin.getPassword());
    }
}
