package com.api.api.consultation;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/consultation")
public class ConsultationController {
    
    private String ctnm;

    @Autowired
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(path = "/ctnm", produces = "application/json; charset=UTF-8")
    public Map<String, String> getCTNM(@RequestBody Map<String, String> payload) {

        // deserialise the json payload
        String T = payload.get("t");
        String N = payload.get("n");
        String taille = payload.get("taille");

        // get the ctnm 

        String Tvalue = consultationService.TValue(T, taille);

        String ctnm = Tvalue + N + "MX" ;
        this.ctnm = ctnm;

        Map<String, String> result = new HashMap<>();

        result.put("ctnm", ctnm);

        
        return result;
    }

    @PostMapping(path = "/tnm", produces = "application/json; charset=UTF-8")
    public Map<String, String> getTNM(@RequestBody Map<String, String> payload) {

        // stream json values to array
        Collection<String> values = payload.values();

       String TNM = this.consultationService.MValue(values, this.ctnm); 

        Map<String, String> result = new HashMap<>();

        result.put("tnm", TNM);

        return result;  
    }
    
    @PostMapping(path = "/save", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> saveConsultation(@RequestBody String payload) {

        try {

            Consultation consultation = desirealisePayload(payload);
            consultationService.saveConsultation(consultation);
            return ResponseEntity.ok("Patient saved succesfully");

        } catch (JsonSyntaxException | IllegalArgumentException e ) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured saving the Pateint!!");
        }
         
    }

    // helper functions
    private <T> Consultation desirealisePayload(String payload) throws JsonSyntaxException{
        Gson gson = new Gson();
    
        Consultation consultation = gson.fromJson(payload, Consultation.class);

        return consultation; 
    
    }
}
