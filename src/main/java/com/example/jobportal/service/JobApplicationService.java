//package com.example.jobportal.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.example.jobportal.entity.Job;
//import com.example.jobportal.entity.JobApplication;
//import com.example.jobportal.entity.User;
//import com.example.jobportal.enums.ApplicationStatus;
//import com.example.jobportal.enums.UserRole;
//import com.example.jobportal.exceptionhandling.DuplicateJobApplicationException;
//import com.example.jobportal.exceptionhandling.InvalidJobStatusException;
//import com.example.jobportal.exceptionhandling.InvalidUserException;
//import com.example.jobportal.exceptionhandling.JobApplicationNotFoundException;
//import com.example.jobportal.exceptionhandling.JobNotFoundException;
//import com.example.jobportal.exceptionhandling.UserNotFoundException;
//import com.example.jobportal.repository.JobApplicationRepository;
//import com.example.jobportal.repository.JobRepository;
//import com.example.jobportal.repository.UserRepository;
//import com.example.jobportal.responsedto.JobApplicationResponseDto;
//import com.example.jobportal.utility.ResponseStructure;
//
//@Service
//public class JobApplicationService {
//
//	
//	@Autowired
//	JobApplicationRepository jobApplicationRepo;
//	
//	@Autowired
//	UserRepository userRepo;
//	
//	@Autowired
//	JobRepository jobRepo;
//	
//	
//	public JobApplicationResponseDto jobApplicationToJobApplicationResponseDTO(JobApplication jobApplication)
//	{
//		JobApplicationResponseDto jobApplicationResponseDTO= new JobApplicationResponseDto();
//		jobApplicationResponseDTO.setApplicationId(jobApplication.getApplicationId());
//		jobApplicationResponseDTO.setAppliedDate(jobApplication.getAppliedDate());
//		jobApplicationResponseDTO.setStatus(jobApplication.getStatus());
//		
//		return jobApplicationResponseDTO;
//	}
//
//	public ResponseEntity<ResponseStructure<String>> addResume(int userId, int jobId) 
//	{
//		Optional<User> optionalUser = userRepo.findById(userId);
//		
//		if(optionalUser.isPresent())
//		{
//			User user = optionalUser.get();
//			if(user.getUserRole()==UserRole.APPLICANT)
//			{
//				Optional<Job> optionalJob = jobRepo.findById(jobId);
//				
//				if(optionalJob.isPresent())
//				{
//					Job job = optionalJob.get();
//					System.out.println(job.getJobId());
//					if(job.getOpenStatus()==JobStatus.OPEN)
//					{
//						List<JobApplication> jobApplicationLlist = jobApplicationRepo.findById(job);
//						int count=0;
//						for(JobApplication jobApplication:jobApplicationLlist)
//						{
//							if(jobApplication.getApplicant().getUserId()==userId)
//							{
//								count++;
//							}
//						}
//						if(count==0)
//						{
//							JobApplication jobApplication = new JobApplication();
//							jobApplication.setStatus(ApplicationStatus.NOT_VIEWED);
//							jobApplication.setApplicant(user);
//							jobApplication.setJob(job);
//							
//							jobApplicationRepo.save(jobApplication);
//							
//							ResponseStructure<String> responseStructure = new ResponseStructure<>();
//							responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
//							responseStructure.setMessage("job Apllication object successfully added");
//							responseStructure.setData("job Apllication successfully submitted");
//
//							return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.ACCEPTED);
//						}
//						else
//						{
//							throw new DuplicateJobApplicationException("same applicant cannot add another job application for same job");
//						}
//					}
//					else
//					{
//						throw new InvalidJobStatusException("job is not not opened to apply....");
//					}
//				}
//				else
//				{
//					throw new JobNotFoundException("job for given id does not exist");
//				}
//			}
//			else
//			{
//				throw new InvalidUserException("user type is not valid to add the job application");
//			}
//		}
//		else
//		{
//			throw new UserNotFoundException("user with id not found");
//		}
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<JobApplicationResponseDto>>> findJobApplicationByUser(int userId) 
//	{
//		Optional<User> optionalUser = userRepo.findById(userId);
//				
//			if(optionalUser.isPresent())
//			{
//				List<JobApplication> jobApplicationList = jobApplicationRepo.findAllByApplicant(optionalUser.get());
//				
//				List<JobApplicationResponseDto> jobApplicationResponseDTOList= new ArrayList<>();
//				for(JobApplication jobApplication:jobApplicationList)
//				{
//					jobApplication.setStatus(JobApplicationStatus.VIWED);//every time job application found job application status has to change
//					jobApplicationRepo.save(jobApplication);
//					
//					Map<String,String> userOpt=new HashMap<>();
//					Map<String,String> jobOpt=new HashMap<>();
//					
//					userOpt.put("users", "/users/"+jobApplication.getApplicant().getUserId());
//					jobOpt.put("jobs", "/jobs/"+jobApplication.getJob().getJobId());
//					
//					JobApplicationResponseDto jobApplicationResponseDTO=jobApplicationToJobApplicationResponseDTO(jobApplication);
//					
//					jobApplicationResponseDTO.setUserOptions(userOpt);
//					jobApplicationResponseDTO.setJobOptions(jobOpt);
//					jobApplicationResponseDTOList.add(jobApplicationResponseDTO);
//				}
//				
//				ResponseStructure<List<JobApplicationResponseDto>> responseStructure = new ResponseStructure<>();
//				responseStructure.setStatusCode(HttpStatus.FOUND.value());
//				responseStructure.setMessage("job Apllication object successfully found");
//				responseStructure.setData(jobApplicationResponseDTOList);
//
//				return new ResponseEntity<ResponseStructure<List<JobApplicationResponseDto>>>(responseStructure, HttpStatus.FOUND);
//			}
//			else
//			{
//				throw new UserNotFoundException("user with id not found");
//			}
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<JobApplicationResponseDto>>> findJobApplicationByUJob(int jobId) 
//	{
//		Optional<Job> optionalJob = jobRepo.findById(jobId);
//		
//		if(optionalJob.isPresent())
//		{
//			Job job = optionalJob.get();
//			List<JobApplication> jobApplicationList =jobApplicationRepo.findAllById(job);
//			
//			List<JobApplicationResponseDto> jobApplicationResponseDTOList= new ArrayList<>();
//			for(JobApplication jobApplication:jobApplicationList)
//			{
//				jobApplication.setStatus(JobApplicationStatus.VIWED);
//				jobApplicationRepo.save(jobApplication);
//				
//				Map<String,String> userOpt=new HashMap<>();
//				Map<String,String> jobOpt=new HashMap<>();
//				
//				userOpt.put("users", "/users/"+jobApplication.getApplicant().getUserId());
//				jobOpt.put("jobs", "/jobs/"+jobApplication.getJob().getJobId());
//				
//				JobApplicationResponseDto jobApplicationResponseDTO=jobApplicationToJobApplicationResponseDTO(jobApplication);
//				
//				jobApplicationResponseDTO.setOptions(userOpt);
//				jobApplicationResponseDTO.setOptions(jobOpt);
//				jobApplicationResponseDTOList.add(jobApplicationResponseDTO);
//			}
//			
//			ResponseStructure<List<JobApplicationResponseDto>> responseStructure = new ResponseStructure<>();
//			responseStructure.setStatusCode(HttpStatus.FOUND.value());
//			responseStructure.setMessage("job Apllication object successfully found");
//			responseStructure.setData(jobApplicationResponseDTOList);
//
//			return new ResponseEntity<ResponseStructure<List<JobApplicationResponseDto>>>(responseStructure, HttpStatus.FOUND);
//		}
//		else
//		{
//			throw new JobNotFoundException("job for given id does not exist");
//		}
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<String>>rejectJobApplicationByApplicationId(int jobapplicationId) 
//	{
//		Optional<JobApplication> optionalJobApplication = jobApplicationRepo.findById(jobapplicationId);
//		
//		if(optionalJobApplication.isPresent())
//		{
//			JobApplication jobApplication=optionalJobApplication.get();
//			jobApplication.setStatus(JobApplicationStatus.REJECTED);
//			
//			jobApplicationRepo.save(jobApplication);
//			
//			ResponseStructure<String> responseStructure = new ResponseStructure<>();
//			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
//			responseStructure.setMessage("job Apllication object successfully updated");
//			responseStructure.setData("job Apllication rejected");
//			
//			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.ACCEPTED);
//		}
//		else
//		{
//			throw new JobApplicationNotFoundException("Job Application with given id does not exist");
//		}
//	}
//
//	public ResponseEntity<ResponseStructure<String>> deleteJobApplication(int userId, int jobId) 
//	{
//		Optional<User> optionalUser = userRepo.findById(userId);
//		
//		if(optionalUser.isPresent())
//		{
//			User user = optionalUser.get();
//			if(user.getUserRole()==UserRole.APPLICANT)
//			{
//				Optional<Job> optionalJob = jobRepo.findById(jobId);
//				if(optionalJob.isPresent())
//				{
//					return null;
//				}
//				else
//				{
//					throw new JobNotFoundException("job with given id does not exist");
//				}
//			}
//			else
//			{
//				throw new InvalidUserException("onliy the applicant can delete the job application");
//			}
//		}
//		else
//		{
//			throw new UserNotFoundException("user with id not found");
//		}
//	}
//}
