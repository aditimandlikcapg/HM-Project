package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.model.Patient;



@Repository

public interface PatientRepository extends JpaRepository<Patient,Integer>{
//	List<Patient> findAllByDname(String dname);
}
