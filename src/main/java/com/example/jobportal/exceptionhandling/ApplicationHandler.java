package com.example.jobportal.exceptionhandling;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.jobportal.utility.ErrorStructure;
@RestControllerAdvice
public class ApplicationHandler  extends ResponseEntityExceptionHandler{

	
	
	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//		List<ObjectError> list = ex.getAllErrors();
//		HashMap<String, String> hashMap = new HashMap<>();
//		for (ObjectError error : list) {
//			FieldError fieldError = (FieldError) error;
//			String fieldName = fieldError.getField();
//			String message = fieldError.getDefaultMessage();
//			hashMap.put(fieldName, message);
//
//		}
//
//		return new ResponseEntity<Object>(hashMap, HttpStatus.BAD_REQUEST);
//	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> list = ex.getAllErrors();
		HashMap<String, String> hashMap = new HashMap<>();
		for (ObjectError error: list) {
			 FieldError  fieldError = (FieldError)error;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			hashMap.put(fieldName, message);

		}

		return new ResponseEntity<Object>(hashMap, HttpStatus.BAD_REQUEST);
		
		
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> UserNotFoundById(UserNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" USER WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFoundById(CompanyNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" COMPANY WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(IllegalAccssException.class)
	public ResponseEntity<ErrorStructure<String>> illegalAccessExcp(IllegalAccssException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" USER NOT AUTHORISED FOR THIS OPERATION");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> illegalAccessExcp(JobNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" JOB WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	
	
	
	@ExceptionHandler(ResumeNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> resumeNotFoundById(ResumeNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" RESUME WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	

	@ExceptionHandler(SkillNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> skillNotFoundById(SkillNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" RESUME WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

//	
//	@ExceptionHandler(SkillAlreadyExistException.class)
//	public ResponseEntity<ErrorStructure<String>> skillExist(SkillAlreadyExistException cnf) {
//		ErrorStructure<String> es = new ErrorStructure<String>();
//		es.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
//		es.setMessage(cnf.getMess()); // message whate we threw in service
//		es.setErrordata(" RESUME WITH THIS ID NOT PRESENT");
//
//		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_ACCEPTABLE);
//
//	}
	
	

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> ProjectNotFoundById(ProjectNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" project WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	
	@ExceptionHandler(EducationNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> EducationFoundById(EducationNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" education WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	
	
	
	@ExceptionHandler(SocialProfileNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> EducationFoundById(SocialProfileNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata(" SOCIALPROFILE WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
	
	
	

	@ExceptionHandler(JobApplicationNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> jobApplicationFoundById( JobApplicationNotFoundException cnf) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(cnf.getMess()); // message whate we threw in service
		es.setErrordata("  JobApplication WITH THIS ID NOT PRESENT");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}
}


