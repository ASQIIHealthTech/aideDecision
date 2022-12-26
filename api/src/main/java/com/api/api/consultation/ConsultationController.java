package com.api.api.consultation;


import java.util.Collection;
import java.util.Map;

import org.json.JSONObject;
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
    public ResponseEntity<String> getCTNM(@RequestBody Map<String, String> payload) {

        // deserialise the json payload
        String T = payload.get("t");
        String N = payload.get("n");
        String taille = payload.get("taille");

        // get the ctnm 

        String Tvalue = consultationService.TValue(T, taille);

        String ctnm = Tvalue + N + "MX" ;
        this.ctnm = ctnm;

        JSONObject item = new JSONObject();
        item.put("ctnm", ctnm);

        return ResponseEntity.ok().body(item.toString());
    }
    // TODO: check the M subscripts
    @PostMapping(path = "/tnm", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> getTNM(@RequestBody Map<String, String> payload) {

        // stream json values to array
        System.out.println(payload);
        Collection<String> values = payload.values();
        System.out.println(payload.get("hist"));
        String TNM = consultationService.MValue(values, this.ctnm); 
        String stade = consultationService.Stade(payload.get("hist"), TNM.substring(TNM.indexOf("M"), TNM.length() - 1), TNM);
    
        JSONObject item = new JSONObject();

        item.put("tnm", TNM);
        item.put("stade", stade);

        
        return ResponseEntity.ok().body(item.toString());
    }
    
    @PostMapping(path = "/save", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> saveConsultation(@RequestBody String payload) {

        try {

            Consultation consultation = desirealisePayload(payload);
            consultationService.saveConsultation(consultation);
            return ResponseEntity.ok("Patient saved succesfully");

        } catch (JsonSyntaxException | IllegalArgumentException e ) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
         
    }

    @PostMapping(path = "/efr", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> bilanEFR(@RequestBody Map<String, String> payload) {

        Map<String, String> bilanEFR = consultationService.EFR(payload);

        JSONObject respPayload = new JSONObject(bilanEFR);

        return ResponseEntity.ok().body(respPayload.toString());
    }

    // helper functions
    private <T> Consultation desirealisePayload(String payload) throws JsonSyntaxException{
        Gson gson = new Gson();
    
        Consultation consultation = gson.fromJson(payload, Consultation.class);

        return consultation; 
    
    }
}
