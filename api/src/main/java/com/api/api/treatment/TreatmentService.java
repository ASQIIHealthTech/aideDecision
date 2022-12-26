package com.api.api.treatment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TreatmentService {
   
    @Autowired
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Object[]> getChimioTreatment(Map<String, String> payload) throws TreatmentException {
        
        for (String key : payload.keySet()) {
            if (payload.get(key) == "") {
                payload.replace(key, null);
            }
        }
        System.out.println(payload);

        List<Object[]> Protocoles = treatmentRepository.findProtocole(
            payload.get("histo"),
            payload.get("stade"),
            payload.get("vems"),
            payload.get("paco2"),
            payload.get("type_histo"),
            payload.get("clairance"),
            payload.get("audiometrie"),
            payload.get("egfr"),
            payload.get("alk"),
            payload.get("braf"),
            payload.get("ros1"),
            payload.get("pdl1"),
            payload.get("ps"),
            payload.get("tabac")
        );
        if (Protocoles.isEmpty()) {
            System.out.println(Protocoles);
            throw new TreatmentException("No Protocole is found");
        } else {
            return Protocoles;
        }
    }
}
