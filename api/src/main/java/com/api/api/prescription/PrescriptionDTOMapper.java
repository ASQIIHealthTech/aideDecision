package com.api.api.prescription;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class PrescriptionDTOMapper implements Function<Prescription, PrescriptionDTO> {

    @Override
    public PrescriptionDTO apply(Prescription Prescription) {
        return new PrescriptionDTO(Prescription.getMolecule(), Prescription.getDose());
    }

}
