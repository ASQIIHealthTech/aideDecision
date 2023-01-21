package com.api.api.consultation;


import java.util.Collection;
import java.util.List;
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
    private String stade;

    @Autowired
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;

        
    }

    /**
     * Routing for the CTNM intermediate result
     * @param payload containing the variable to determine the CTNM
     * @return ResponseBody with 200 status and CTNM value
     */
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
    
    /**
     * Routing to get the TNM intermediate result
     * @param payload containing the variables to determine CTNM
     * @return ResponseBody with 200 status and TNM value
     */
    @PostMapping(path = "/tnm", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> getTNM(@RequestBody Map<String, String> payload) {

        // stream json values to array
        Collection<String> values = payload.values();
        String TNM = consultationService.MValue(values, this.ctnm); 
        String stade = consultationService.Stade(payload.get("hist"), TNM.substring(TNM.indexOf("M"), TNM.length()), TNM);
    
        JSONObject item = new JSONObject();

        item.put("tnm", TNM);
        item.put("stade", stade);
        this.stade = stade;

        
        return ResponseEntity.ok().body(item.toString());
    }

    @PostMapping(path = "/pec", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> getPEC(@RequestBody Map<String, String> payload) {
        try {
            List<String> pec = consultationService.getPEC(payload.get("histo"), this.stade, payload.get("vems"), payload.get("paco2"), payload.get("ps"));
            JSONObject item = new JSONObject();
            
            for (int i=0; i<pec.size(); i++) {
                item.put("pec"+i, pec.get(i));
            }
            return ResponseEntity.ok().body(item.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Coudn't fetch the PEC!!");
        }
    }
    
    /**
     * Routing to save the consultation 
     * @param payload containing all the values defined in the front
     * @return ResponseBody with 200 status and log message 
     * @throws JsonSyntaxException If consultation paylaod coudn't be desirealised
     * @throws IllegalArgumentException If the save function fails
     */
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
    /**
     * Routing to determine values of "Bilan-preoperatoire"
     * @param payload containing the values needed to determine the output
     * @return ResponseBody with 200 status and the values as a json body
     */
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
