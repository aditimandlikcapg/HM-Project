package com.cg.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.feignclient.*;
import com.cg.feignclient.AuthFeignApp;
import com.cg.feignclient.DoctorFeignClient;
import com.cg.feignclient.PatientFeignClient;
import com.cg.model.Appointment;
import com.cg.repository.AppointmentRepository;

import jakarta.validation.ValidationException;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    
    @Autowired
    PatientFeignClient patientFeignClient;
    
    @Autowired
    DoctorFeignClient doctorFeignClient;
    
    @Autowired (required = true)
	AuthFeignApp authFeignApp;
	
    @Override
	public boolean hasPermission(String token) {
		System.out.println("From product Service: "+ token);
		boolean flag =  authFeignApp.getTokenValidity(token);
		System.out.println(" MyFlag: " + flag);
		return flag;
	}
    

    @Override
    public List<AppointmentDTO> findAllAppointments() {
        return appointmentRepository.findAll().stream().map(AppointmentDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDTO> findAppointmentById(int aid) {
        return Optional.ofNullable(AppointmentDTO.fromEntity(appointmentRepository.findById(aid).get()));
    }

    @Override
    public AppointmentDTO createAppointment(Appointment appointment) {
    	Optional<PatientDTO> patient = patientFeignClient.getPatientById(appointment.getPid());
    	if(patient.isEmpty())
    	{
    		throw new ValidationException("patient does not exsist");
    	}
    	
    	Optional<DoctorDTO> doc = doctorFeignClient.getDoctorById(appointment.getDid());
    	if(doc.isEmpty())
    	{
    		throw new ValidationException("doctor does not exsist");
    	}
        return AppointmentDTO.fromEntity(appointmentRepository.save(appointment));
    }

    @Override
    public boolean deleteAppointment(int aid) {
        appointmentRepository.deleteById(aid);
        return true;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, int id) {
        Optional<AppointmentDTO> existingAppointment = findAppointmentById(id);
        if (existingAppointment.isPresent()) {
            AppointmentDTO updatedAppointment = existingAppointment.get();
            updatedAppointment.setDid(appointment.getDid());
            updatedAppointment.setPid(appointment.getPid());
            updatedAppointment.setDateTime(appointment.getDateTime());
            updatedAppointment.setIssue(appointment.getIssue());
            updatedAppointment.setStatus(appointment.getStatus());
            updatedAppointment.setFees(appointment.getFees());
            
            Appointment ap = updatedAppointment.toEntity();
            appointmentRepository.save(ap);
            return ap;
            
        } else {
            return null; // or throw an exception
        }
    }

	@Override
	public Optional<PatientDTO> getPatientDetails(int aid) {
		Optional<AppointmentDTO> ap = findAppointmentById(aid);
    	int pid = ap.get().getPid();
    	return patientFeignClient.getPatientById(pid);
	}

	@Override
	public Optional<DoctorDTO> getDoctorDetails(int aid) {
		Optional<AppointmentDTO> ap = findAppointmentById(aid);
		int did = ap.get().getDid();
		return doctorFeignClient.getDoctorById(did);
	}


}