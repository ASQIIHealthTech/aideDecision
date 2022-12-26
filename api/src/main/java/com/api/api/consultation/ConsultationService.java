package com.api.api.consultation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private Map<String, List<String>> stade = new HashMap<String, List<String>>();


    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
        stade.put("Stade IA", List.of("T1N0M0"));
        stade.put("Stade IA1", List.of("T1aN0M0"));
        stade.put("Stade IA2", List.of("T1bN0M0"));
        stade.put("Stade IA3", List.of("T1cN0M0"));
        stade.put("Stade IB", List.of("T2aN0M0"));
        stade.put("Stade IIA", List.of("T2b N0 M0"));
        stade.put("Stade IIB", List.of("T1aN1M0", "T1bN1M0", "T1cN1M0", "T2aN1M0", "T2bN1M0", "T3N0M0"));
        stade.put("Stade IIIA", List.of("T1aN2M0", "T1bN2M0", "T1cN2M0", "T2aN2M0", "T2bN2M0", "T3N1M0", "T4N0M0", "T4N1M0"));
        stade.put("Stade IIIB", List.of("T1aN3M0", "T1bN3M0", "T1cN3M0", "T2aN3M0", "T2bN3M0", "T3N2M0", "T4 N2 M0"));
        stade.put("Stade IIIC", List.of("T3N3M0", "T4N3M0"));
        stade.put("Stade IVA", List.of("T1aN0M1a", "T1bN0M1a", "T1cN0M1a", "T2aN0M1a", "T2bN0M1a", "T3N0M1a", "T4N0M1a", "T1aN1M1a", "T1bN1M1a", "T1cN1M1a", "T2aN1M1a", "T2bN1M1a", "T3N1M1a", "T4N1M1a", "T1aN2M1a", "T1bN2M1a", "T1cN2M1a", "T2aN2M1a", "T2bN2M1a", "T3N2M1a", "T4N2M1a", "T1aN3M1a", "T1bN3M1a", "T1cN3M1a", "T2aN3M1a", "T2bN3M1a", "T3N3M1a", "T4N3M1a", "T1aN0M1b", "T1bN0M1b", "T1cN0M1b", "T2aN0M1b", "T2bN0M1b", "T3N0M1b", "T4N0M1b", "T1aN1M1b", "T1bN1M1b", "T1cN1M1b", "T2aN1M1b", "T2bN1M1b", "T3N1M1b", "T4N1M1b", "T1aN2M1b", "T1bN2M1b", "T1cN2M1b", "T2aN2M1b", "T2bN2M1b", "T3N2M1b", "T4N2M1b", "T1aN3M1b", "T1bN3M1b", "T1cN3M1b", "T2aN3M1b", "T2bN3M1b", "T3N3M1b", "T4N3M1b"));
        stade.put("Stade IVB", List.of("T1aN0M1c", "T1bN0M1c", "T1cN0M1c", "T2aN0M1c", "T2bN0M1c", "T3N0M1c", "T4N0M1c", "T1aN1M1c", "T1bN1M1c", "T1cN1M1c", "T2aN1M1c", "T2bN1M1c", "T3N1M1c", "T4N1M1c", "T1aN2M1c", "T1bN2M1c", "T1cN2M1c", "T2aN2M1c", "T2bN2M1c", "T3N2M1c", "T4N2M1c", "T1aN3M1c", "T1bN3M1c", "T1cN3M1c", "T2aN3M1c", "T2bN3M1c", "T3N3M1c", "T4N3M1c"));

    }
    

    private String tailleClasse(String taille) {
        
        int tailleConverted = Integer.parseInt(taille);
        
        if (tailleConverted >= 11 && tailleConverted <= 20) {
            return "T1b";
        }

        else if (tailleConverted >= 21 && tailleConverted <= 30) {
            return "T1c";
        }

        else if (tailleConverted >= 31 && tailleConverted <= 40) {
            return "T2a";
        }

        else if (tailleConverted >= 41 && tailleConverted <= 50) {
            return "T2b";
        }
        
        else if (tailleConverted >= 51 && tailleConverted <=70) {
            return "T3";
        }

        else {
            return "T4";
        }
    }

    public String TValue(String TDesscription, String taille) {

        String T = tailleClasse(taille);

        if (Integer.parseInt(String.valueOf(T.charAt(1))) >= Integer.parseInt(String.valueOf(TDesscription.charAt(1)))) {
            return T;
        } else {
            return TDesscription;
        }
    }

    public String MValue(Collection<String> values, String ctnm) {

        String M = "";

        if (values.contains("M1")) {
             M = "M1";
        } else {
             M = "M0";
        }

        String TNM = ctnm.substring(0, ctnm.length() - 2) + M;

        return TNM;
    }

    // TODO: check the cv = 0 case
    public Map<String, String> EFR(Map<String, String> payload) {

        Map<String, String> result = new HashMap<String, String>();
        int cv = Integer.parseInt(payload.get("vri")) + Integer.parseInt(payload.get("vc")) + Integer.parseInt(payload.get("vre"));
        result.put("cv", Integer.toString(cv));
        result.put("ci", Integer.toString(Integer.parseInt(payload.get("vc")) + Integer.parseInt(payload.get("vri"))));
        result.put("crf", Integer.toString(Integer.parseInt(payload.get("vre")) + Integer.parseInt(payload.get("vr"))));
        result.put("cpt", Integer.toString(cv + Integer.parseInt(payload.get("vri"))));
        result.put("rapport", Float.toString(Float.parseFloat(payload.get("vems")) / cv));

        return result;

    }

    public String Stade(String histo, String M, String TNM) {
        String result = null;
        System.out.println(histo);
        if (histo.equals("cnpc")) {
            result = this.stade.entrySet().stream().filter(e -> e.getValue().contains(TNM)).map(Map.Entry::getKey).findFirst().get();
        } 
        if (histo.equals("cpc")) {
            if (M.equals("M0")) {  
                result = "localise";
            }
            else result = "dissimine";
        }
        
        return result;
    }

    public void saveConsultation(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    public List<Consultation> getConsultations() {
        return consultationRepository.findAll();
    }
    
    public Optional<Consultation> getConsultation(long id) {
        return consultationRepository.findById(id);
    }

    
    
}
