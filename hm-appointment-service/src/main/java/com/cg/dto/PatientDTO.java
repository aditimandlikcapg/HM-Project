package com.cg.dto;

//import com.cg.model.Doctor;
import com.cg.model.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
	
	private int pid;
	private String pname;
	private int phone;
	private String address;

}
