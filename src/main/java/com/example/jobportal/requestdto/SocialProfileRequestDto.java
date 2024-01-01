package com.example.jobportal.requestdto;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.SocialType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Component
public class SocialProfileRequestDto {

	
	private SocialType socialType;
	
	@NotNull(message = "url cannot be null")
	@NotBlank(message = "url cannot be blank")
	private String url;
	

	public SocialType getSocialType() {
		return socialType;
	}

	public void setSocialType(SocialType socialType) {
		this.socialType = socialType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
}
