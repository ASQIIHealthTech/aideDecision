package com.api.api.prescription;

import java.util.List;

public interface PrescriptionService {

    List<PrescriptionDTO> retreiveAllPrescriptions();

    PrescriptionDTO addPrescription(Prescription med);

    void deletePrescription(Long id);

    PrescriptionDTO updatePrescription(Prescription med);

    PrescriptionDTO retrievePrescription(Long id) throws PrescriptionException;
}
