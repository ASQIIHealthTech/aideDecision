package com.api.api.patient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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

    @GetMapping(value = "/get/dmi")
    public ResponseEntity<String> getPatientByDMI(@RequestBody Map<String, String> payload) {
        try {

            Optional<Patient> patient = patientService.getPatientByDMI(payload.get("dmi"));
            // Transfrom payload to Json data
            String patientResponse = new Gson().toJson(patient.toString());
            return ResponseEntity.ok().body(patientResponse);

        } catch (PatientExcecption e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/get/fullname")
    public ResponseEntity<String> getPatientByNomAndPrenom(@RequestBody Map<String, String> payload) {
        try {
            Optional<Patient> patient = patientService.getPatientByNomAndPrenom(payload.get("nom"), payload.get("prenom"));
            String patientResponse = new Gson().toJson(patient.toString());
            return ResponseEntity.ok().body(patientResponse);
        } catch (PatientExcecption e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> addNewPatient(@RequestBody String payload) {
        try {

            Patient patient = desirealisePayload(payload);
            patientService.savePatient(patient);
            return ResponseEntity.ok("Patient is saved");

        } catch (JsonSyntaxException | PatientExcecption e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // helper function
    private <T> Patient desirealisePayload(String payload) {

        Gson gson = new Gson();
        Patient patient = gson.fromJson(payload, Patient.class);
        return patient;

    }

}
