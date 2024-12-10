package com.cg.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cg.dto.DoctorDTO;
import com.cg.model.Doctor;



public interface IDoctorService {

	List<DoctorDTO> findAllDoctors();

	Optional<DoctorDTO> findDoctorById(int did);

	DoctorDTO createDoctor(Doctor d);

	boolean deleteDoctor(int did);

	Doctor updateDoctor(Doctor d, int id);

}