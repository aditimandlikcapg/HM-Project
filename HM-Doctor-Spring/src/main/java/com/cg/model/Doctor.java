package com.cg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Doctor_table")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int did;
	@NotNull(message="name cannot be null")
	@Column(name="doctorName")
	private String dname;
	@Column(name="docQualification")
	private String qualification;
	
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int did, String dname, String qualification) {
		super();
		this.did = did;
		this.dname = dname;
		this.qualification = qualification;
	}

	@Override
	public String toString() {
		return "Doctor [did=" + did + ", dname=" + dname + ", qualification=" + qualification + "]";
	}

	
	
	
}