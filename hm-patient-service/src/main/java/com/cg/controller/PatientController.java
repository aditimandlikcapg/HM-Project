package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.PatientDTO;
import com.cg.model.Patient;
import com.cg.service.IPatientService;

@RequestMapping("/patient")
@RestController
public class PatientController {
	@Autowired
	IPatientService patientService;
	
	@GetMapping("/getAll") 
	public List<PatientDTO> getPatients(){
		return patientService.findAllPatients();
	}
	
	@GetMapping(path="/getById/{id}")
	public Optional<PatientDTO>getByPatientId(@PathVariable int id)
	 {
		return patientService.findPatientById(id);
	}
	
	@PostMapping("/post")
	
	public PatientDTO createMyPatient(@RequestBody Patient d) {
		return patientService.createPatient(d);
	}
	
	@DeleteMapping(path="/delete/{id}")
	
	public boolean deleteMyPatient(@PathVariable int id) {
		return patientService.deletePatient(id);
	}
	
	@PutMapping("/update/{id}")
	public Patient updateMyPatient(@PathVariable int id, @RequestBody Patient d)
	{
		return patientService.updatePatient(d, id);
		
	}
}