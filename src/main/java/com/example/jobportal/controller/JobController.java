package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.JobNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class JobController {
	
	@Autowired
	JobService jobService;
	
	
	
	@PostMapping("/companies/{compId}/jobs")  
	public ResponseEntity<ResponseStructure<String>> inserJob(@RequestBody @Valid JobRequestDto jobReq,@PathVariable int compId) 
			throws CompanyNotFoundException
	{
		
		 return jobService.insertJOb(jobReq,compId);
		
	}
	
	
//	@GetMapping("/jobs/{jobId}")  
//	public ResponseEntity<ResponseStructure<JobResponseDto>> findJobById(@PathVariable int  jobId) throws JobNotFoundException
//	{
//		
//		 return jobService.findJobById(compId);
//		
//	}
//	
//	@PutMapping("/jobs/{jobId}")  
//	public ResponseEntity<ResponseStructure<String>> updateJob(@RequestBody @Valid JobRequestDto jobReq,@PathVariable int compId) throws JobNotFoundException 
//	{
//		
//		 return jobService.updateJob(jobReq,compId);
//		
//	}	
//	
//	
//	@DeleteMapping("/jobs/{jobId}")  
//	public ResponseEntity<ResponseStructure<String>> deleteJobById(@PathVariable int  jobId) throws JobNotFoundException
//	{
//		
//		 return jobService.deleteJobById(jobId);
//		
//	}
//	

	
}
