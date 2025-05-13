package com.vpp.powerplant.Config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vpp.powerplant.Exception.NoRequestFoundException;

@RestControllerAdvice
public class ExceptionConfig {
	
	@ExceptionHandler(NoRequestFoundException.class)
	public ResponseEntity<String> handleNoRequestFoundException(NoRequestFoundException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
