package com.hotel.service.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	Map<String,Object> Json = new LinkedHashMap<>();
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> notFoundException(ResourceNotFoundException ex){
		Json.put("message", ex.getMessage());
		Json.put("success",false);
		Json.put("status", HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Json);
		
		
	}

}
