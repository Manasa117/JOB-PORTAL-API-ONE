package com.example.jobportal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;
	@Autowired
	private UserRepository  userRepo;
	@Autowired
	private SkillRepository skillRepo;



	private Resume convertToResume(ResumeRequestDto resumeRq, Resume resume) {

		resume.setObjective(resumeRq.getObjective());

		return resume;
	}

	private ResumeResponseDto convertToResumeRespnse(Resume resume) {
		ResumeResponseDto ResumeResp = new ResumeResponseDto();
		ResumeResp.setResumeId(resume.getResumeId());
		ResumeResp.setObjective(resume.getObjective());

		return ResumeResp;

	}

	public ResponseEntity<ResponseStructure<String>> insertResume(ResumeRequestDto ResumeReq, int userId) throws UserNotFoundException, IllegalAccssException {


		Optional<User> optUser = userRepo.findById(userId);

		if(optUser.isPresent())
		{    
			User user = optUser.get();
			if(user.getUserRole()==UserRole.APPLICANT)
			{
				Resume resume = convertToResume(ResumeReq, new Resume());
				resume.setUserMap(user); // since
				user.setResumeMap(resume);

				resumeRepo.save(resume);

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Resume data saved successfully");
				respStruc.setData(" 1 Resume ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new IllegalAccssException(" This user cant add resume");
		} 

		else throw new UserNotFoundException("User not found with this Id");
	}





	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findResumeById(int jobId) throws ResumeNotFoundException {
		
		Optional<Resume> optResu = resumeRepo.findById(jobId);
		
		if(optResu.isPresent()) {
			Resume resume = optResu.get();
			
			ResumeResponseDto dto = convertToResumeRespnse(resume);
			
			HashMap<String, String> hashmap = new HashMap<>();
			hashmap.put("user","/users/" + resume.getUserMap().getUserId());
			dto.setOptions(hashmap);
			
			
			ResponseStructure<ResumeResponseDto> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Resume found successfully");
			respStruc.setData(dto);

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(respStruc, HttpStatus.FOUND);
		}
		else
			throw new ResumeNotFoundException("resume with the given ID is not present");
		}

	public ResponseEntity<ResponseStructure<String>> updateResume(@Valid ResumeRequestDto resumeReq, int resumeId) throws ResumeNotFoundException {

		Optional<Resume> optResume = resumeRepo.findById(resumeId);
		
		if(optResume.isPresent()) {
			Resume resume = convertToResume(resumeReq, optResume.get());
			resumeRepo.save(resume);
			
			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Resume updated successfully");
			respStruc.setData("1 Resume data updated successfully");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		}
		else
			throw new ResumeNotFoundException("resume with the given ID is not present");
		}

	
	
	
			
public ResponseEntity<ResponseStructure<String>> deleteResumeById(int resumeId) throws ResumeNotFoundException {
		
		Optional<Resume> optComp = resumeRepo.findById(resumeId);
		if (optComp.isPresent()) {			
			resumeRepo.deleteById(resumeId);
			ResponseStructure<String> responseStruct = new ResponseStructure<>();
			responseStruct.setMessage(" company found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData("Resume DELETED SUCCESSFULLY");
			return new ResponseEntity<ResponseStructure<String>>(responseStruct, HttpStatus.FOUND);
		}
		else throw new ResumeNotFoundException(" Resume  with the given  Id not present");

	}
	



}

