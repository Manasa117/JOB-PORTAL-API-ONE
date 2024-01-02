package com.example.jobportal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.exceptionhandling.JobNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SkillAlreadyExistException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class SkillService {

	
	@Autowired
	private SkillRepository skillRepo;
    @Autowired
	private ResumeRepository resumRepo;
    @Autowired
    private JobRepository jobRepo;
    
    
    private Skill CheckSkill(String skill )
    {
    	Skill oldSkill = skillRepo.findSkill(skill.toLowerCase());
    	if(oldSkill==null) 
    	{
    		Skill newSkill= new Skill();
    		newSkill.setSkillname(skill.toLowerCase());
    		skillRepo.save(newSkill);
    		return newSkill;
    	}
                 	
    	else return oldSkill;
    	
    }
    
    private List<Skill> convertToSkill(SkillRequestDto reqDto, List<Skill> skillList)
    {    
    	
    	String[] skillsArray = reqDto.getSkillName();
    	
    	for(String sk:skillsArray)
    	{
    		Skill Skill = CheckSkill(sk);
    		
    		if(!skillList.contains(Skill))
    		skillList.add(Skill);
    	}
    	return skillList ;
    	
    }
    
    
    
    
	public ResponseEntity<ResponseStructure<String>> insertSkillinJob(SkillRequestDto skillReq,int jobId) throws JobNotFoundException 
			 {
		
		   Optional<Job> optJob = jobRepo.findById(jobId);
		
		   if(optJob.isPresent()) {
			   Job job = optJob.get();
		                
			         List<Skill> skillList = job.getSkillList();
			         if(!skillList.isEmpty()) {
			        	 convertToSkill(skillReq, skillList);
			         }
			         else convertToSkill(skillReq, new ArrayList<Skill>());
	                   
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Skill data saved successfully");
				respStruc.setData("  SKILLLIST ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
				
		}  else throw new JobNotFoundException("Job with given ID is not present");	
	}
	
	
	
	public ResponseEntity<ResponseStructure<String>> insertSkill(SkillRequestDto skillReq,int resumId) throws ResumeNotFoundException 
	 {

  Optional<Resume> optResum = resumRepo.findById(resumId);

  if(optResum.isPresent()) {
	   Resume resume = optResum.get();
               
	         List<Skill> listSkills = convertToSkill(skillReq,new ArrayList<Skill>());
	         
              resume.setSkillMap(listSkills);
              resumRepo.save(resume);
              
              
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.CREATED.value());
		respStruc.setMessage(" Skill data saved successfully");
		respStruc.setData("  SKILLLIST ADDED  SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		
}  else throw new ResumeNotFoundException("resume with given ID is not present");


}

	

	public ResponseEntity<ResponseStructure<String>> updateSkill(SkillRequestDto skillReq,int resumId) throws ResumeNotFoundException 
	 {

  Optional<Resume> optResum = resumRepo.findById(resumId);

  if(optResum.isPresent()) {
	   Resume resume = optResum.get();
               
	          List<Skill> updSkillList = convertToSkill(skillReq,resume.getSkillMap());
	          
	         
              resume.setSkillMap(updSkillList);
              resumRepo.save(resume);
              
              
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.CREATED.value());
		respStruc.setMessage(" Skill data updated successfully");
		respStruc.setData("  SKILL LIST  UPDATED SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		
}  else throw new ResumeNotFoundException("resume with given id not present");


}

	public ResponseEntity<ResponseStructure<String>> deleteSkillnResume(int resumeId, String skill) throws ResumeNotFoundException, SkillNotFoundException {
	
		
		Optional<Resume> optRes = resumRepo.findById(resumeId);
		if(optRes.isPresent()) {
			Resume resume = optRes.get();
			
			Skill skilltoDel = skillRepo.findSkill(skill.toLowerCase());
			if(skilltoDel!=null) {
				List<Skill> updatedList = removeSkill(skilltoDel, resume.getSkillMap());
				
				resume.setSkillMap(updatedList);
				resumRepo.save(resume);
				
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
				respStruc.setMessage(" Skill data remove successfully");
				respStruc.setData("  SKILL DELETED FROM LIST SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
				
		}  else throw new SkillNotFoundException("skill with given id not present");


		}else throw new ResumeNotFoundException("resume not present with this id");
	}

	
	
	
	private List<Skill> removeSkill(Skill skilltoDel, List<Skill> skillMap) {
		
		if(skilltoDel!=null)
			skillMap.remove(skilltoDel);
		return skillMap;
		
	}

	
	
	
	
	public ResponseEntity<ResponseStructure<SkillResponseDto>> findSkillByName(String skillName) throws SkillNotFoundException {
	    Skill skill = skillRepo.findSkill(skillName.toLowerCase());  // dont forget to convertt to lower case
	    
	  if(skill!=null) {
		  
		   HashMap<String,String> hashMap = new HashMap<>();
		   
	  SkillResponseDto dto = convertToSkillResponse(skill);
	  
	  hashMap.put("Requirement", "requirement to add");
		  dto.setOptions(hashMap);
	ResponseStructure<SkillResponseDto> respStruc = new ResponseStructure<>();
	respStruc.setStatusCode(HttpStatus.FOUND.value());
	respStruc.setMessage(" Skill data fetched  successfully");
	respStruc.setData(dto);

	return new ResponseEntity<ResponseStructure<SkillResponseDto>>(respStruc, HttpStatus.FOUND);
	
}  else  throw new SkillNotFoundException("skill not present in dataBase");

}

	
	
	private SkillResponseDto convertToSkillResponse(Skill skill) {

		SkillResponseDto dto = new SkillResponseDto();
		dto.setSkillId(skill.getSkillId());
		dto.setSkillName(skill.getSkillname());		
		
		return dto;
	}

	

	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
