package com.cg.model;

import java.time.LocalDateTime;

import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="appointment_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aid;
	
	@NotNull(message = "did cannot be null")
	private int did;
	
	@NotNull(message = "pid cannot be null")
	private int pid;
	
	private LocalDateTime dateTime;
	
	private String issue;
	
	private Status status;
	
	private double fees;
	
	
}