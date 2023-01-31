package com.api.api.treatment;

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

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/treatment")
public class TreatmentController {
    
    @Autowired
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping(value = "/chimio") 
    public ResponseEntity<String> getChimioTreatment(@RequestBody Map<String, String> payload) {
        try {
            List<Object[]> protocoles = treatmentService.getChimioTreatment(payload);
            JSONObject response = new JSONObject();
            response.put("protocole", protocoles.toArray());
            return ResponseEntity.ok().body(response.toString());

        } catch (TreatmentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "getProtocole")
    public ResponseEntity<String> getProtocoleDetails(@RequestBody Map<String,String> payload) {
       try {
        List<Object[]> protocoleDetails = treatmentService.getProtocoleDetails(payload);
        JSONObject response = new JSONObject();
        response.put("intercure", protocoleDetails.get(0));
        response.put("molecule", protocoleDetails.get(1));
        response.put("dose", protocoleDetails.get(2));
        response.put("unite", protocoleDetails.get(3));
        response.put("jour_prod", protocoleDetails.get(4));
        response.put("nb_cures", protocoleDetails.get(5));

        return ResponseEntity.ok().body(response.toString());
        
       } catch (TreatmentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

}
