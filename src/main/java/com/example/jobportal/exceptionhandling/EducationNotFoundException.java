package com.example.jobportal.exceptionhandling;

public class EducationNotFoundException extends Exception{
String mess;

public EducationNotFoundException(String mess) {
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
