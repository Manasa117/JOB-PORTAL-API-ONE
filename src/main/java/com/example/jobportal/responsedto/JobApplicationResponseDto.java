package com.example.jobportal.responsedto;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.ApplicationStatus;

import jakarta.persistence.ManyToOne;

@Component
public class JobApplicationResponseDto {


	private int applicationId;
	private LocalDate appliedDate;
	private ApplicationStatus status;
	private float skillMatching;
	private boolean locationMatching;
	
	private HashMap<String, String> options;
	
	
	@ManyToOne
	private User Applicant;
	
	
	@ManyToOne
	private Job requirement;

	
	
	
	public float getSkillMatching() {
		return skillMatching;
	}

	public void setSkillMatching(float skillMatching) {
		this.skillMatching = skillMatching;
	}

	public boolean isLocationMatching() {
		return locationMatching;
	}

	public void setLocationMatching(boolean locationMatching) {
		this.locationMatching = locationMatching;
	}

	public HashMap<String, String> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public User getApplicant() {
		return Applicant;
	}

	public void setApplicant(User applicant) {
		Applicant = applicant;
	}

	public Job getRequirement() {
		return requirement;
	}

	public void setRequirement(Job requirement) {
		this.requirement = requirement;
	}


	
}
