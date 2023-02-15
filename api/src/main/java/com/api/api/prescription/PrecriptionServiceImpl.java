package com.api.api.prescription;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrecriptionServiceImpl implements PrescriptionService {

	@Autowired
	PrescriptionDTOMapper PrescriptionDTOMapper;

	@Autowired
	PrescriptionRepository mr;

	@Override
	public List<PrescriptionDTO> retreiveAllPrescriptions() {

		return mr.findAll()
				.stream()
				.map(PrescriptionDTOMapper)
				.collect(Collectors.toList());
	}

	@Override
	public PrescriptionDTO addPrescription(Prescription pres) {

		mr.save(pres);
		return PrescriptionDTOMapper.apply(pres);

	}

	@Override
	public void deletePrescription(Long id) {
		mr.deleteById(id);

	}

	@Override
	public PrescriptionDTO updatePrescription(Prescription pres) {
		mr.save(pres);
		return PrescriptionDTOMapper.apply(pres);

	}

	@Override
	public PrescriptionDTO retrievePrescription(Long id) throws PrescriptionException {
		PrescriptionDTO Prescription = mr.findById(id)
				.map(PrescriptionDTOMapper)
				.orElseThrow(() -> new PrescriptionException("Prescription with id [%s] is not found".formatted(id)));

		return Prescription;

	}
}