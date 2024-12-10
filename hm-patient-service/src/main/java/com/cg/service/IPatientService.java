package com.cg.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cg.dto.PatientDTO;
import com.cg.model.Patient;



public interface IPatientService {

	List<PatientDTO> findAllPatients();

	Optional<PatientDTO> findPatientById(int did);

	PatientDTO createPatient(Patient d);

	boolean deletePatient(int did);

	Patient updatePatient(Patient d, int id);

}