package com.cg.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityLatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLatestApplication.class, args);
	}
	
	/* 
	 1. UserInfo Table
	 * '4','john@gmail.com','John','$2a$10$rEK/KwCb1yIagS88C0FmfuFJVIJT2h1Ja8YBSjJ99tsTu4krjWA2a','ROLE_USER'
'		5','ashu@gmail.com','Ashu','$2a$10$niyZW1AxGPCGIdXW4veRN.81HjFQ6gq3BWdkXRIBdo08EMm2ICOnq','ROLE_ADMIN'



	2. Get Token
	{
	    "username":"John",
	    "password":"Pwd2"
	}
	
	3. Get resource
	Get http://localhost:8080/products/1
	{
    "productId": 1,
    "name": "product 1",
    "qty": 6,
    "price": 3852.0
}

	4. 	{
	    "username":"Ashu",
	    "password":"Pwd1"
	} 
	5. http://localhost:8080/products/all
	
	*
	*/ 
	
//	remove prod mapping from auth 

}
