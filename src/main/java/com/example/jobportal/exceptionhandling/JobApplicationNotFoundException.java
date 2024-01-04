package com.example.jobportal.exceptionhandling;

public class JobApplicationNotFoundException extends Exception{

	String mess;

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public JobApplicationNotFoundException(String mess) {
		super();
		this.mess = mess;
	}
}
