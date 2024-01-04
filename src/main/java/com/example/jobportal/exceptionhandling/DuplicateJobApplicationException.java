package com.example.jobportal.exceptionhandling;

public class DuplicateJobApplicationException extends Exception {
	
	String mess;

	public DuplicateJobApplicationException(String mess) {
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
