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

import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SkillAlreadyExistException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.service.SkillService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class SkillController {

	@Autowired
	SkillService skillService;
	
	@PostMapping("/resumes/{resumeId}/skill")
	public ResponseEntity<ResponseStructure<String>> insertSkill(@PathVariable int resumeId, @RequestBody SkillRequestDto reqskill)
			throws SkillAlreadyExistException, ResumeNotFoundException
	{
		return skillService.insertSkill(reqskill,resumeId);
	}
	
	
	@PutMapping("/resumes/{resumeId}/skill")
	public ResponseEntity<ResponseStructure<String>> updateSkill(@PathVariable int resumeId, @RequestBody SkillRequestDto reqskill)
			throws  ResumeNotFoundException
	{
		return skillService.updateSkill(reqskill,resumeId);
	}
	
	
	@DeleteMapping("/resumes/{resumeId}/skill")
	public ResponseEntity<ResponseStructure<String>> deleteSkill(@PathVariable int resumeId, @PathVariable String skill) throws ResumeNotFoundException, SkillNotFoundException
			 
	{
		return skillService.deleteSkillnResume(resumeId, skill);
	}


	@GetMapping("/skills/{skill}")  
	public ResponseEntity<ResponseStructure<SkillResponseDto>> findSkillByName(@PathVariable String skill) throws SkillNotFoundException 
			 
	{     
		 
		 return skillService.findSkillByName(skill);
		
	}
}
