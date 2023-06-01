package com.stackroute.sellerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stackroute.sellerservice.exception.SellerServiceException;

@RestControllerAdvice
public class SellerServiceAdvice {
	
	@ExceptionHandler({ SellerServiceException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlerEmployeeException(SellerServiceException e) {
		return e.getMessage();
	}


}
