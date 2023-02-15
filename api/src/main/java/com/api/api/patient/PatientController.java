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

    /***
     * The routing to get all the patients in the database
     * 
     * @return List of all the Patients
     */
    @GetMapping(value = "/get")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    /**
     * Filter routing to get the patient by dmi
     * 
     * @param payload containing the dmi of the patient
     * @return ResponseEntity with 200 status and patient object
     */
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

    /**
     * Filter routing to get the patient by FistName and LastName
     * 
     * @param payload containing the first and last name of the patient
     * @return ResponseEntity with 200 status and patient object
     */
    @GetMapping(value = "/get/fullname")
    public ResponseEntity<String> getPatientByNomAndPrenom(@RequestBody Map<String, String> payload) {
        try {
            List<Patient> patient = patientService.getPatientByNomAndPrenom(payload.get("nom"), payload.get("prenom"));
            String patientResponse = new Gson().toJson(patient.toString());
            return ResponseEntity.ok().body(patientResponse);
        } catch (PatientExcecption e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * The rounting to add a new patient to the database
     * 
     * @param payload containing attributes forming the patient object
     * @return ResponseEntity with 200 status and log message
     */
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
    /**
     * A helper function that desirealise the payload and convert it to a class
     * object
     * 
     * @param payload Json payload containing the attributes forming the patient
     *                object
     * @return Patient object
     */
    private <T> Patient desirealisePayload(String payload) {

        Gson gson = new Gson();
        Patient patient = gson.fromJson(payload, Patient.class);
        return patient;

    }

}
