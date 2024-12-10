package com.cg.__SpringRest_Hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.AppointmentController;
import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.enums.Status;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Appointment;
import com.cg.service.IAppointmentService;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {
	
	@Mock
	IAppointmentService appointmentService;
	
	@InjectMocks
	AppointmentController appointmentController;
	
	private AppointmentDTO appointment;
	
	@BeforeEach
	public void setUp()
	{
		appointment = new AppointmentDTO();
        appointment.setAid(1);
        appointment.setDid(101);
        appointment.setPid(202);
        appointment.setDateTime(LocalDateTime.now());
        appointment.setIssue("General Checkup");
        appointment.setStatus(Status.SCHEDULED);
        appointment.setFees(500.0);

	}
	
	@Test
	public void getAppointmentsTest()
	{
		List<AppointmentDTO> app = new ArrayList<>();
		app.add(appointment);
		when(appointmentService.findAllAppointments()).thenReturn(app);
		List<AppointmentDTO> app2 = appointmentService.findAllAppointments();
		assertFalse(app2.isEmpty());
		assertEquals(app.get(0).getIssue(), "General Checkup");
		
	}
	
	 @Test
	    public void getByAppointmentIdTest() throws ResourceNotFoundException {
	        when(appointmentService.findAppointmentById(anyInt())).thenReturn(Optional.of(appointment));

	        ResponseEntity<Optional<AppointmentDTO>> response = appointmentController.getByAppointmentId(1);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(1, response.getBody().get().getAid());
	    }

	    @Test
	    public void createAppointmentTest() {
	        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

	        ResponseEntity<AppointmentDTO> response = appointmentController.createAppointment(appointment.toEntity());
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(1, response.getBody().getAid());
	    }

	    @Test
	    public void deleteAppointmentTest() throws ResourceNotFoundException {
	        when(appointmentService.findAppointmentById(anyInt())).thenReturn(Optional.of(appointment));
	        when(appointmentService.deleteAppointment(anyInt())).thenReturn(true);

	        ResponseEntity<Boolean> response = appointmentController.deleteAppointment(1);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(true, response.getBody());
	    }

	    @Test
	    public void updateAppointmentTest() throws ResourceNotFoundException {
	        when(appointmentService.findAppointmentById(anyInt())).thenReturn(Optional.of(appointment));
	        when(appointmentService.updateAppointment(any(Appointment.class), anyInt())).thenReturn(appointment.toEntity());

	        ResponseEntity<Appointment> response = appointmentController.updateAppointment(1, appointment.toEntity());
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(1, response.getBody().getAid());
	    }

	    @Test
	    public void getPatientDetailsTest() {
	        when(appointmentService.getPatientDetails(anyInt())).thenReturn(Optional.of(new PatientDTO()));

	        Optional<PatientDTO> response = appointmentController.getPatientDetails(1);
	        assertFalse(response.isEmpty());
	    }

	    @Test
	    public void getDoctorDetailsTest() {
	        when(appointmentService.getDoctorDetails(anyInt())).thenReturn(Optional.of(new DoctorDTO()));

	        Optional<DoctorDTO> response = appointmentController.getDoctorDetails(1);
	        assertFalse(response.isEmpty());
	    }
}
