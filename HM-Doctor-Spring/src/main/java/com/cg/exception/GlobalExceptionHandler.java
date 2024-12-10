package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
	
	   @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   
	   @ExceptionHandler(MethodNotAllowedException.class)
	    public ResponseEntity<ErrorDetails> handleMethodNotAllowedException(MethodNotAllowedException mx, WebRequest request) {
	        // Create the error details object
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), mx.getMessage(), request.getDescription(false));

	        // Return the response entity with status 405 Method Not Allowed
	        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	    }
	 
	// Handler for ValidationException
	    @ExceptionHandler(ValidationException.class)
	    public ResponseEntity<ErrorDetails> handleValidationException(ValidationException ex, WebRequest request) {
	        // Create the error details object with a custom message and description
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

	        // Return the response entity with status 400 (Bad Request)
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
}
