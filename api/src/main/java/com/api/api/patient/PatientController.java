package com.api.api.patient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/patients")
public class PatientController {

    @Autowired
    private final PatientService patientService;

    
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    @GetMapping(value = "/get")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping(value = "/get/{patientId}")
    public Optional<Patient> getPatient(@PathVariable Long PatientId) {
        return patientService.getPatient(PatientId);
    }

    @PostMapping(value = "/new")
    public void addNewPatient(@RequestBody String payload) {

        Patient patient = desirealisePayload(payload);

        patientService.savePatient(patient);
    }

    // helper function
    private <T> Patient desirealisePayload(String payload) {

        Gson gson = new Gson();
        Patient patient = gson.fromJson(payload, Patient.class);
        System.out.println(patient);
        return patient;

    }

    

    
}
