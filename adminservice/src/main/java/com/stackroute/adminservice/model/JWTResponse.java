package com.stackroute.adminservice.model;

public class JWTResponse {
	
	 private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public JWTResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public JWTResponse() {
		super();
		
	}
	 
	 

}
