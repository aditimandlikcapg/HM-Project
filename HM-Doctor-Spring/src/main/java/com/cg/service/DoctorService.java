package com.cg.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.DoctorDTO;
import com.cg.model.Doctor;
import com.cg.repository.DoctorRepository;

import jakarta.validation.Valid;

@Service
public class DoctorService implements IDoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;
	@Override
	public List<DoctorDTO> findAllDoctors() {
		return doctorRepository.findAll().stream().map(DoctorDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Optional<DoctorDTO> findDoctorById(int did) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(DoctorDTO.fromEntity(doctorRepository.findById(did).get()));
	}

	@Override
	public DoctorDTO createDoctor(Doctor d) {
		return DoctorDTO.fromEntity(doctorRepository.save(d));
	}

	@Override
	public boolean deleteDoctor(int did) {
		doctorRepository.deleteById(did);
		return true;
	
	}

	@Override
	public Doctor updateDoctor(Doctor d, int id) {		
		DoctorDTO doc = findDoctorById(id).get();
		doc.setName(d.getDname());
		doc.setQualification(d.getQualification());
		Doctor dd = doc.toEntity();
		return doctorRepository.save(dd);
	}

}

