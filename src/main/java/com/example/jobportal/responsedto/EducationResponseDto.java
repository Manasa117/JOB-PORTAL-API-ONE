package com.example.jobportal.responsedto;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.EducationType;

@Component
public class EducationResponseDto {

	private EducationType eduType;
	private String degreeType;
	private String stream;
	private String instituteName;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean gradStatus;
	private float percentageOrCGPA;
	
	private HashMap<String, String> options;	
	
	
	public EducationType getEduType() {
		return eduType;
	}
	public HashMap<String, String> getOptions() {
		return options;
	}
	public void setOptions(HashMap<String, String> options) {
		this.options = options;
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
