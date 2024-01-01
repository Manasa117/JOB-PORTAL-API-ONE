package com.example.jobportal.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.EducationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Component
public class Education {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int EduId;
	private EducationType eduType;
	private String degreeType;
	private String stream;
	private String instituteName;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean gradStatus;
	private float percentageOrCGPA;
	
	@ManyToOne
	private Resume associatedResume;
	
	
	public Resume getAssociatedResume() {
		return associatedResume;
	}
	public void setAssociatedResume(Resume associatedResume) {
		this.associatedResume = associatedResume;
	}
	public int getEduId() {
		return EduId;
	}
	public void setEduId(int eduId) {
		EduId = eduId;
	}
	public EducationType getEduType() {
		return eduType;
	}
	public void setEduType(EducationType eduType) {
		this.eduType = eduType;
	}
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Boolean getGradStatus() {
		return gradStatus;
	}
	public void setGradStatus(Boolean gradStatus) {
		this.gradStatus = gradStatus;
	}
	public float getPercentageOrCGPA() {
		return percentageOrCGPA;
	}
	public void setPercentageOrCGPA(float percentageOrCGPA) {
		this.percentageOrCGPA = percentageOrCGPA;
	}
	
	
}
