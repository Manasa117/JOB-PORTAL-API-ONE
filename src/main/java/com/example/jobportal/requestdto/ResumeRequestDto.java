package com.example.jobportal.requestdto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.jobportal.entity.Skill;

@Component
public class ResumeRequestDto {

	private String objective;
	
	
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	
	
}
