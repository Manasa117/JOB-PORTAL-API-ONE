package com.example.jobportal.requestdto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.EducationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
public class EducationRequestDto {

	private String degreeType;
	private String stream;
	
	@NotBlank(message = "institutename cannot be blank")
	@NotNull(message = "institutename cannot be null")
	private String instituteName;
	
	private String location;
	
	@NotNull(message = "startDate cannot be null")
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@NotNull(message = "gradStatus cannot be null")
	private Boolean gradStatus;
	
	private float percentageOrCGPA;
	
	
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
