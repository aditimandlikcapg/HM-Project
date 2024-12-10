package com.cg.model;

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
@Table(name="patient_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	@NotNull(message="name cannot be null")
	@Column(name="pName")
	private String pname;
	
	@Column(name="pPhone")
	private long phone;
	
	@Column(name = "address")
	private String address;
	
	
}