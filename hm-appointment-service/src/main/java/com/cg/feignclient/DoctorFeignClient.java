package com.cg.feignclient;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient (name = "testFeignClient2", url = "${PRODUCT_SERVICE:http://localhost:9092/doctor}", fallback = DoctorFeignClientFallback.class)
public interface DoctorFeignClient {
		
		@GetMapping("/getById/{id}")	
		Optional<DoctorDTO> getDoctorById(@PathVariable int id);
}
