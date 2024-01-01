package com.example.jobportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.SocialProfile;
import com.example.jobportal.enums.SocialType;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SocialProfileRepository;
import com.example.jobportal.requestdto.SocialProfileRequestDto;
import com.example.jobportal.responsedto.SocialProfileResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class SocialProfileService {

	@Autowired
	private ResumeRepository resumeRepo;
	
	@Autowired
	private SocialProfileRepository   socialRepo;
	
	
	private SocialProfile convertToSocialProfile(SocialProfileRequestDto dto, SocialProfile social)
	{
		social.setUrl(dto.getUrl());
		return social;
	}
	
	private SocialProfileResponseDto convertToSocialResponse(SocialProfile social)
	{
		SocialProfileResponseDto dto = new SocialProfileResponseDto();
		
		dto.setSocialType(social.getSocialType());
		dto.setSociId(social.getSociId());
		dto.setUrl(social.getUrl());
		return dto;
		
	}
	
	
	public ResponseEntity<ResponseStructure<String>> insertSocialProfile(SocialProfileRequestDto socReq, SocialType socialType,
			int resumeId) throws IllegalAccssException 
	{
		Optional<Resume> optResume = resumeRepo.findById(resumeId);

		if (optResume.isPresent()) {
			
			Resume resume = optResume.get();
			
			List<SocialProfile> socialList = resume.getSocialList();
			
		
			
			if (socialList.isEmpty()) {

				for(SocialProfile sp : socialList)
				{
					if(sp.getSocialType()==socialType &&  sp.getSocialType()==SocialType.GITHUB)
						throw new IllegalAccssException("GITHUB already present u can't enter new you can update only");
					if(sp.getSocialType()==socialType &&  sp.getSocialType()==SocialType.GMAIL)
						throw new IllegalAccssException("GMAIL already present u can't enter new you can update only");
					if(sp.getSocialType()==socialType &&  sp.getSocialType()==SocialType.LINKEDIN)
						throw new IllegalAccssException("LINKEDIN already present u can't enter new you can update only");
					if(sp.getSocialType()==socialType &&  sp.getSocialType()==SocialType.PORTFOLID)
						throw new IllegalAccssException("PORTFOLID already present u can't enter new you can update only");
					if(sp.getSocialType()==socialType &&  sp.getSocialType()==SocialType.TWITTER)
						throw new IllegalAccssException("TWITTER already present u can't enter new you can update only");
				}
					
			}
					SocialProfile social= convertToSocialProfile(socReq, new SocialProfile());
				social.setAssociatedResume(resume);
				social.setSocialType(socialType);
				socialRepo.save(social);
				
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Social Profile data saved successfully");
				respStruc.setData(" SocialProfile added successfully");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new IllegalAccssException("resume authorised to do this opertation");
			

	}

}
