package com.cg.dto;

import java.time.LocalDateTime;

import com.cg.enums.Status;
import com.cg.model.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
	
private int aid;
	
	private int did;
	
	private int pid;
	
	private LocalDateTime dateTime;
	
	private String issue;
	
	private Status status;
	
	private double fees;
	
	
	public static AppointmentDTO fromEntity(Appointment appointment) {
        return new AppointmentDTO(
            appointment.getAid(),
            appointment.getDid(),
            appointment.getPid(),
            appointment.getDateTime(),
            appointment.getIssue(),
            appointment.getStatus(),
            appointment.getFees()
        );
    }
	

    public Appointment toEntity() {
        return new Appointment(
            this.aid,
            this.did,
            this.pid,
            this.dateTime,
            this.issue,
            this.status,
            this.fees
        );
    }

}
