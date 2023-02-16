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

	@Override
	public double calcule(Prescription pres) {
		Double clcr;
		if (pres.getFormule() == "MDRD" && pres.getSexe() == "homme") {
			clcr = (double) Math
					.round(186 * Math.pow((pres.getCreatinine() / 88.4), -1.154) * Math.pow(pres.getAge(), -0.203));

		} else if (pres.getFormule() == "MDRD" && pres.getSexe() == "femme") {
			clcr = (double) Math.round(
					186 * Math.pow((pres.getCreatinine() / 88.4), -1.154) * Math.pow(pres.getAge(), -0.203) * 0.742);

		} else if (pres.getFormule() != "MDRD" && pres.getSexe() == "homme") {
			clcr = (double) Math.round(1.23 * pres.getPoids() * (140 - pres.getAge()) / pres.getCreatinine());

		} else {
			clcr = (double) Math.round(1.04 * pres.getPoids() * (140 - pres.getAge()) / pres.getCreatinine());

		}

		return clcr;

	}

	public double calculerDosageParUnite(Prescription pres) {

		float surface_cor = (float) Math.sqrt((pres.getPoids() * pres.getTaille()) / 3600);
		double surface_cor_min = Math.round(surface_cor);
		double clcr = calcule(pres);

		switch (pres.getUnite()) {
			case "mg/kg":
				return pres.getDose() * pres.getPoids();
				
			case "mg":
				return pres.getDose();
				
			case "mg/m²":
				return pres.getDose() * surface_cor_min;
				
			case "auc":
				return pres.getDose() * (clcr + 25);
				
			default:
				return pres.getDose();
				
		}
	}

}
