package com.example.jobportal.exceptionhandling;

public class InvalidJobStatusException extends Exception {
	
	String mess;

	public InvalidJobStatusException(String mess) {
		super();
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	

}
