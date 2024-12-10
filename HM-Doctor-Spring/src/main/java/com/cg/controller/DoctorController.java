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

import com.cg.dto.DoctorDTO;
import com.cg.model.Doctor;
import com.cg.service.IDoctorService;

@RequestMapping("/doctor")
@RestController
public class DoctorController {
	@Autowired
	IDoctorService doctorService;
	
	@GetMapping("/get") 
	public List<DoctorDTO> getDoctors(){
		return doctorService.findAllDoctors();
	}
	
	@GetMapping(path="/getById/{did}")
	public Optional<DoctorDTO>getByDoctorId(@PathVariable int did)
	 {
		return doctorService.findDoctorById(did);
	}
	
	@PostMapping("/postDoctors")
	public DoctorDTO createMyDoctor(@RequestBody Doctor d) {
		return doctorService.createDoctor(d);
	}
	
	@DeleteMapping(path="/deleteDoctors/{did}")
	
	public boolean deleteMyDoctor(@PathVariable int did) {
		return doctorService.deleteDoctor(did);
	}
	
	@PutMapping("/updateDoctors/{id}")
	public Doctor updateMyDoctor(@PathVariable int id, @RequestBody Doctor d)
	{
		return doctorService.updateDoctor(d, id);
		
	}
}