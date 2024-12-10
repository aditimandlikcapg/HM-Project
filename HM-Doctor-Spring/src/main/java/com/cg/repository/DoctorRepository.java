package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.model.Doctor;



@Repository

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	List<Doctor> findAllByDname(String dname);
}
