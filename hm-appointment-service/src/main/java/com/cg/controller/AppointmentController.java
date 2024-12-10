package com.cg.controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.feignclient.DoctorFeignClientFallback;
import com.cg.model.Appointment;
import com.cg.service.IAppointmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {
	
	private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    IAppointmentService appointmentService;
    
    @Autowired
    DoctorFeignClientFallback doctorFeignClientFallback;
    
    
    
    private static final String TEST_SERVICE = "TestService";

    @GetMapping("/getAll")
    public ResponseEntity<List<AppointmentDTO>> getAppointments(@RequestHeader ("Authorization") String token) throws ResourceNotFoundException {
    	log.info("Received request to get all appointments with token: {}", token);

        if (appointmentService.hasPermission(token)) {
            log.info("Token is valid. Fetching all appointments.");
            List<AppointmentDTO> appointments = appointmentService.findAllAppointments();
            if (appointments.isEmpty()) {
                log.warn("No appointments found.");
                throw new ResourceNotFoundException("No appointments found");
            }
            log.info("Returning {} appointments.", appointments.size());
            return ResponseEntity.ok(appointments);
        } else {
            log.warn("Invalid token: {}", token);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping(path="/getById/{id}")
    public ResponseEntity<Optional<AppointmentDTO>> getByAppointmentId(@PathVariable int id) throws ResourceNotFoundException {
        log.info("Received request to get appointment by ID: {}", id);
        System.out.println("Hi");
        Optional<AppointmentDTO> appointment = appointmentService.findAppointmentById(id);
        if (appointment.isEmpty()) {
            log.warn("No appointment found with ID: {}", id);
            throw new ResourceNotFoundException("No appointment found with ID: " + id);
        }
        log.info("Returning appointment with ID: {}", id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/post")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody Appointment appointment) {
        log.info("Received request to create appointment: {}", appointment);
        AppointmentDTO createdAppointment = appointmentService.createAppointment(appointment);
        log.info("Created appointment with ID: {}", createdAppointment.getAid());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Boolean> deleteAppointment(@PathVariable int id) throws ResourceNotFoundException {
        log.info("Received request to delete appointment with ID: {}", id);
        Optional<AppointmentDTO> appointment = appointmentService.findAppointmentById(id);
        if (appointment.isEmpty()) {
            log.warn("No appointment found with ID: {}", id);
            throw new ResourceNotFoundException("No appointment found with ID: " + id);
        }
        boolean result = appointmentService.deleteAppointment(id);
        log.info("Deleted appointment with ID: {}", id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) throws ResourceNotFoundException {
        log.info("Received request to update appointment with ID: {}", id);
        Optional<AppointmentDTO> app = appointmentService.findAppointmentById(id);
        if (app.isEmpty()) {
            log.warn("No appointment found with ID: {}", id);
            throw new ResourceNotFoundException("No appointment found with ID: " + id);
        }
        Appointment updatedAppointment = appointmentService.updateAppointment(appointment, id);
        log.info("Updated appointment with ID: {}", id);
        return ResponseEntity.ok(updatedAppointment);
    }

    @GetMapping("/patientDetails/{aid}")
    @CircuitBreaker(name = TEST_SERVICE, fallbackMethod = "getPatientDetailsFallback")
    @Retry(name = TEST_SERVICE, fallbackMethod = "getPatientDetailsFallback")
    public Optional<PatientDTO> getPatientDetails(@PathVariable int aid) {
        log.info("Received request to get patient details for appointment ID: {}", aid);
        return appointmentService.getPatientDetails(aid);
    }

    public Optional<PatientDTO> getPatientDetailsFallback(int aid, Throwable throwable) {
        log.error("Fallback triggered for appointment ID: {}, due to: {}", aid, throwable.getMessage());
        return appointmentService.getPatientDetails(aid);
    }

    @GetMapping("/doctorDetails/{aid}")
    @CircuitBreaker(name = TEST_SERVICE, fallbackMethod = "getDoctorDetailsFallBack")
    @Retry(name = TEST_SERVICE, fallbackMethod = "getDoctorDetailsFallBack")
    public Optional<DoctorDTO> getDoctorDetails(@PathVariable int aid) {
        log.info("Received request to get doctor details for appointment ID: {}", aid);
        return appointmentService.getDoctorDetails(aid);
    }

    public Optional<DoctorDTO> getDoctorDetailsFallBack(int aid, Throwable throwable) {
        log.error("Fallback triggered for appointment ID: {}, due to: {}", aid, throwable.getMessage());
        return appointmentService.getDoctorDetails(aid);
    }

//    @GetMapping(path="/getById/{id}")
//    public ResponseEntity<Optional<AppointmentDTO>> getByAppointmentId(@PathVariable int id) throws ResourceNotFoundException {
//    	Optional<AppointmentDTO> appointment = appointmentService.findAppointmentById(id);
//    	if(appointment.isEmpty())
//    		throw new ResourceNotFoundException("no appointment found with id - "+ id);
//    	return ResponseEntity.ok(appointment);
//    }
//
//    @PostMapping("/post")
//    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody Appointment appointment) {
//    	AppointmentDTO createdAppointment = appointmentService.createAppointment(appointment);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
//    }
//
//    @DeleteMapping(path="/delete/{id}")
//    public ResponseEntity<Boolean> deleteAppointment(@PathVariable int id) throws ResourceNotFoundException {
//    	Optional<AppointmentDTO> appointment = appointmentService.findAppointmentById(id);
//    	if(appointment.isEmpty())
//    		throw new ResourceNotFoundException("no appointment found with id - "+ id);
//    	return ResponseEntity.ok(appointmentService.deleteAppointment(id));
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) throws ResourceNotFoundException {
//    	Optional<AppointmentDTO> app = appointmentService.findAppointmentById(id);
//    	if(app.isEmpty())
//    		throw new ResourceNotFoundException("no appointment found with id - "+ id);
//        return ResponseEntity.ok(appointmentService.updateAppointment(appointment, id));
//    }
//    
//    
//    // Circuit breaker and feign implementation
//    
//    @GetMapping("/patientDetails/{aid}")
//    @CircuitBreaker(name = TEST_SERVICE, fallbackMethod = "getPatientDetailsFallback")
//    @Retry(name = TEST_SERVICE, fallbackMethod = "getPatientDetailsFallback")
//    public Optional<PatientDTO> getPatientDetails(@PathVariable int aid)
//    {
//    	return appointmentService.getPatientDetails(aid);
//    }
//    
//    public Optional<PatientDTO> getPatientDetailsFallback(int aid, Throwable throwable)
//    {
//    	System.err.println("Fallback triggered for aid: " + aid + ", due to: " + throwable.getMessage());
//        return appointmentService.getPatientDetails(aid);
//    }
//    
//    @GetMapping("/doctorDetails/{aid}")
//    @CircuitBreaker(name = TEST_SERVICE, fallbackMethod = "getDoctorDetailsFallBack")
//    @Retry(name = TEST_SERVICE, fallbackMethod = "getDoctorDetailsFallBack")
//    public Optional<DoctorDTO> getDoctorDetails(@PathVariable int aid)
//    {
//    	return appointmentService.getDoctorDetails(aid);
//    }
//    
//    public Optional<DoctorDTO> getDoctorDetailsFallBack(int aid, Throwable throwable) {
//        System.err.println("Fallback triggered for aid: " + aid + ", due to: " + throwable.getMessage());
//        return appointmentService.getDoctorDetails(aid); 
//    }
}