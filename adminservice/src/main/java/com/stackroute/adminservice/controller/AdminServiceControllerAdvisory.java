package com.stackroute.adminservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.stackroute.adminservice.entities.ErrorMessage;
import com.stackroute.adminservice.exception.AdminServiceCustomException;


@RestControllerAdvice
public class AdminServiceControllerAdvisory {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AdminServiceCustomException.class)
	public ResponseEntity<ErrorMessage> adminServiceCustomException(AdminServiceCustomException exception,WebRequest request)
	{
		ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> adminServiceException(Exception exception,WebRequest request)
	{
		ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public Map<String,String> handleValidationRules(MethodArgumentNotValidException ex)
	{
		Map<String, String> errorsProduct = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorsProduct.put(fieldName, errorMessage);
		});
		return errorsProduct; 
		
	}

}
