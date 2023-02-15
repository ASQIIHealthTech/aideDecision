package com.api.api.medecin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/Medecin")
public class MedecinController {
	@Autowired
	MedecinService ms;

	// http://localhost:8081/Medecin/retrieve-all-Medecin
	@GetMapping("/retrieve-all-Medecins")
	@ResponseBody
	public List<MedecinDTO> retreiveAllMedecins() {
		return ms.retreiveAllMedecins();

	}

	// http://localhost:8081/Medecin/retrieve-Medecin/8
	@GetMapping("/retrieve-Medecin/{Medecin-id}")
	@ResponseBody
	public MedecinDTO retrieveMedecin(@PathVariable("Medecin-id") Long medID) {
		try {
			return ms.retrieveMedecin(medID);
		} catch (MedecinException e) {
			// e.printStackTrace();

		}
		return null;
	}

	// http://localhost:8081/Medecin/add-Medecin
	@PostMapping("/add-Medecin")
	@ResponseBody
	public MedecinDTO addMedecin(@RequestBody Medecin cp) {
		return ms.addMedecin(cp);

	}

	@DeleteMapping("/remove-Medecin/{Medecin-id}")
	@ResponseBody
	public void removeMedecin(@PathVariable("Medecin-id") Long medID) {
		ms.deleteMedecin(medID);
	}

	// http://localhost:8081/Medecin/modify-Medecin
	@PutMapping("/modify-Medecin")
	@ResponseBody
	public MedecinDTO modifyMedecin(@RequestBody Medecin Medecin) {
		return ms.updateMedecin(Medecin);
	}

	// http://localhost:8081/Medecin/login
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody LoginDTO login) {
		System.out.println(login);
		System.out.println("Username : " + login.username() + " Password : " + login.password() + "");
		return ms.login(login.username(), login.password());
	}
}
