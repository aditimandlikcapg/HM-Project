package com.cg.dto;

//import com.cg.model.Doctor;
import com.cg.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
	
	private int pid;
	private String pname;
	private long phone;
	private String address;
	
	public static PatientDTO fromEntity(Patient patient)  //to convert product to DTO (bucket to glass)
	{
		return new PatientDTO(patient.getPid(), patient.getPname(), patient.getPhone(), patient.getAddress());
	}
	
	public Patient toEntity() // to convert DTO to product object (glass to bucket)
	{
		return new Patient(this.pid, this.pname, this.phone, this.address);
	}

}
