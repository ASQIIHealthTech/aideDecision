package com.api.api.medecin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Medecin findByUsername(String username);

}
