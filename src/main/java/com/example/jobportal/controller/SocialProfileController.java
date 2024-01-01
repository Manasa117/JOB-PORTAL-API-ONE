package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.SocialType;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.requestdto.SocialProfileRequestDto;
import com.example.jobportal.service.SocialProfileService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class SocialProfileController {

	@Autowired
	SocialProfileService socialService;
	
	@PostMapping("/resumes/{resumeId}/socialType/{socialType}/socialProfiles")  
	public ResponseEntity<ResponseStructure<String>> insertSocialProfile(@RequestBody @Valid SocialProfileRequestDto socialReq,
			@PathVariable int resumeId,@PathVariable SocialType socialType) throws IllegalAccssException, ResumeNotFoundException
	{
		
		 return socialService.insertSocialProfile(socialReq,socialType,resumeId);
		
	}
	
	
}
