package com.stackroute.searchservice.exception;

@SuppressWarnings("serial")
public class SearchServiceCustomException extends Exception {
	
	public SearchServiceCustomException(String errorMessage)
	{
		super(errorMessage);
	}
	
	public SearchServiceCustomException(String message, Throwable cause)
	{
		super(message,cause);
	}

	public SearchServiceCustomException(Throwable cause)
	{
		super(cause);
	}
}
