package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

}
