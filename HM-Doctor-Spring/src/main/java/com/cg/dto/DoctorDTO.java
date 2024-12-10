package com.cg.dto;

import com.cg.model.Doctor;

//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
	
	private int id;
	private String name;
	private String qualification;
	
	public static DoctorDTO fromEntity(Doctor doctor)  //to convert product to DTO (bucket to glass)
	{
		return new DoctorDTO(doctor.getDid(), doctor.getDname(), doctor.getQualification());
	}
	
	public Doctor toEntity() // to convert DTO to product object (glass to bucket)
	{
		return new Doctor(this.id, this.name, this.qualification);
	}

}
