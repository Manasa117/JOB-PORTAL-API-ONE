package com.example.jobportal.exceptionhandling;

public class InvalidUserException extends Exception {
	
	String mess;

	public InvalidUserException(String mess) {
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
