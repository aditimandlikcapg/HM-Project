package com.cg.feignclient;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cg.dto.PatientDTO;

@Component
public class PatientFeignClientFallback implements PatientFeignClient{


		@Override
		public Optional<PatientDTO> getPatientById(int id) {
			return Optional.ofNullable(new PatientDTO());
		}
	

}
