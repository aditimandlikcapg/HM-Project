package com.cg.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-ws", url = "${PRODUCT_SERVICE:http://localhost:9094}")
public interface AuthFeignApp {
	@GetMapping("/validate")
	public boolean getTokenValidity(@RequestHeader("Authorization") String token);
}