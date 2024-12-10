package com.cg.dto;

import java.time.LocalDateTime;

import com.cg.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

private int aid;
	
	private int did;
	
	private int pid;
	
	private LocalDateTime dateTime;
	
	private String issue;
	
	private Status status;
	
	private double fees;
}
