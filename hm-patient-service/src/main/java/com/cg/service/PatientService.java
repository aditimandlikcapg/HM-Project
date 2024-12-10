package com.cg.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.PatientDTO;
import com.cg.model.Patient;
import com.cg.repository.PatientRepository;

import jakarta.validation.Valid;

@Service
public class PatientService implements IPatientService{
	
	@Autowired
	PatientRepository patientRepository;
	
	
	@Override
	public List<PatientDTO> findAllPatients() {
		List<PatientDTO> ls = patientRepository.findAll().stream().map(PatientDTO::fromEntity).collect(Collectors.toList());
		return ls;
	}

	@Override
	public Optional<PatientDTO> findPatientById(int did) {
		
		return Optional.ofNullable(PatientDTO.fromEntity(patientRepository.findById(did).get()));
	}

	@Override
	public PatientDTO createPatient(Patient d) {
		return PatientDTO.fromEntity(patientRepository.save(d));
	}

	@Override
	public boolean deletePatient(int did) {
		patientRepository.deleteById(did);
		return true;
	
	}

	@Override
	public Patient updatePatient(Patient d, int id) {
		
		PatientDTO pt = findPatientById(id).get();
		pt.setPname(d.getPname());
		pt.setPhone(d.getPhone());
		pt.setAddress(d.getAddress());
		
		return patientRepository.save(pt.toEntity());
	}

}

