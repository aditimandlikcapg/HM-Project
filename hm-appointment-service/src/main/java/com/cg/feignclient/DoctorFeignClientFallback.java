package com.cg.feignclient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.cg.dto.DoctorDTO;

@Component
public class DoctorFeignClientFallback implements DoctorFeignClient{

		@Override
		public Optional<DoctorDTO> getDoctorById(int id) {
			
			return Optional.ofNullable(new DoctorDTO());
					
		}
	

}
