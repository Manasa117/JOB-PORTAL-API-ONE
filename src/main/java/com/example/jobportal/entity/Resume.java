package com.example.jobportal.entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Resume {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int resumeId;
	private String objective;
	
	@OneToOne
	private User userMap;
	
	@ManyToMany
	private List<Skill> skillMap;
	
	@OneToMany
	private List<Project> projectMap;

	
	
	
	public List<Project> getProjectMap() {
		return projectMap;
	}

	public void setProjectMap(List<Project> projectMap) {
		this.projectMap = projectMap;
	}

	public int getResumeId() 
	{
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public User getUserMap() {
		return userMap;
	}

	public void setUserMap(User userMap) {
		this.userMap = userMap;
	}

	public List<Skill> getSkillMap() {
		return skillMap;
	}

	public void setSkillMap(List<Skill> skillMap) {
		this.skillMap = skillMap;
	}
	
	
	
}
