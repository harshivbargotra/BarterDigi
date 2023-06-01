package com.stackroute.PaymentService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stackroute.PaymentService.exception.PaymentException;
import com.stripe.exception.*;

@RestControllerAdvice
public class PaymentControllerAdivce {

	@ExceptionHandler({ PaymentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlerPaymentException(PaymentException e) {

		return e.getMessage();
	}

	@ExceptionHandler({ StripeException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String StripeException(StripeException e) {

		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
