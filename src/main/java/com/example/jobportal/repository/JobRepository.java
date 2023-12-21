package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

	
	@Query("select j from Job j where j.designation LIKE %?1%")
	public List<Job> findByDesignation(String design);

//	@Query("select j from Job j where l.location LIKE %?1")
//	public List<Job> findByLocation(String loc);
}
