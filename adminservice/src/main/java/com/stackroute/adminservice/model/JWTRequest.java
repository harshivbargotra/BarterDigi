package com.stackroute.adminservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class JWTRequest {
	
	
	@NotNull(message="username should not be null")
	@NotBlank(message="username should not be blank")
	
	private String username;
	
	@NotNull(message="password cannot be null")
	@NotBlank(message="password cannot be blank")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JWTRequest() {
		super();
		
	}
	public JWTRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	

}
