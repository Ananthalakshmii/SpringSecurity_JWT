package com.accolite;

public class JwtResponse {
	
	private String jwt; // no need setter because this is output-- generate jwt and send it to the requester-- read only -- final-- doesnt modify

	public String getJwt() {
		return jwt;
	}

	public JwtResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public JwtResponse() {
		super();
	}
	
	

}
