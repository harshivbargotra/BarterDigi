package com.stackroute.adminservice.exception;

import org.springframework.security.authentication.BadCredentialsException;

@SuppressWarnings("serial")
public class AdminServiceCustomException extends Exception {
	
	public AdminServiceCustomException(String str)
	{
		super(str);
	}

	public AdminServiceCustomException(String string, BadCredentialsException e) {
		super(string,e);
	}

}
