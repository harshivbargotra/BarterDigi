package com.stackroute.searchservice.entity;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	
	private HttpStatus status;
	private String errorDescription;
	public ErrorMessage() {
		super();
		
	}
	public ErrorMessage(HttpStatus status, String errorMessage) {
		super();
		this.status = status;
		this.errorDescription = errorMessage;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorDescription;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorDescription = errorMessage;
	}
	
	
	
	

}
