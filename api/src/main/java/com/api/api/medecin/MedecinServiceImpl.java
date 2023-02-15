package com.api.api.medecin;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MedecinServiceImpl implements MedecinService {

	@Autowired
	MedecinDTOMapper MedecinDTOMapper;

	@Autowired
	LoginDTOMapper LoginDTOMapper;

	@Autowired
	MedecinRepository mr;

	@Override
	public List<MedecinDTO> retreiveAllMedecins() {

		return mr.findAll()
				.stream()
				.map(MedecinDTOMapper)
				.collect(Collectors.toList());
	}

	@Override
	public MedecinDTO addMedecin(Medecin med) throws UserAlreadyExistsException {
		if (mr.findByUsername(med.getUsername()) != null) {
			throw new UserAlreadyExistsException("User with the username " + med.getUsername() + " already exists");
		}
		mr.save(med);
		return MedecinDTOMapper.apply(med);

	}

	@Override
	public void deleteMedecin(Long id) {
		mr.deleteById(id);

	}

	@Override
	public MedecinDTO updateMedecin(Medecin med) {
		mr.save(med);
		return MedecinDTOMapper.apply(med);

	}

	@Override
	public MedecinDTO retrieveMedecin(Long id) throws MedecinException {
		MedecinDTO medecin = mr.findById(id)
				.map(MedecinDTOMapper)
				.orElseThrow(() -> new MedecinException("Medecin with id [%s] is not found".formatted(id)));

		return medecin;

	}

	public ResponseEntity<String> login(String username, String password) {
		if (mr.findByUsername(username) != null) {
			LoginDTO logDTO = LoginDTOMapper.apply(mr.findByUsername(username));
			log.info(logDTO.username());
			log.info(logDTO.password());
			if (password.equalsIgnoreCase(logDTO.password()) == true) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}