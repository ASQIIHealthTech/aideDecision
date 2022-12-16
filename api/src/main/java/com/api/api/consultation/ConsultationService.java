package com.api.api.consultation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;

    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
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

        if (Integer.parseInt(String.valueOf(T.charAt(1))) > Integer.parseInt(String.valueOf(TDesscription.charAt(1)))) {
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

    public void getConsultationByLastDate(Consultation consultation) {
        
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
