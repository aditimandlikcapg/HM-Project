package com.cg.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.model.Appointment;



public interface IAppointmentService {

	List<AppointmentDTO> findAllAppointments();

	Optional<AppointmentDTO> findAppointmentById(int id);

	AppointmentDTO createAppointment(Appointment appointment);

	boolean deleteAppointment(int id);

	Appointment updateAppointment(Appointment appointment, int id);

	Optional<PatientDTO> getPatientDetails(int aid);

	Optional<DoctorDTO> getDoctorDetails(int aid);
	
	boolean hasPermission(String token);

}