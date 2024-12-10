package com.cg.feignclient;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.dto.PatientDTO;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient (name = "testFeignClient", url = "${PRODUCT_SERVICE:http://localhost:9093/patient}")

public interface PatientFeignClient {
	
	@GetMapping("/getById/{id}")	
	Optional<PatientDTO> getPatientById(@PathVariable int id);

}