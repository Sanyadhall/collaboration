package com.backend.dao;

import java.util.List;

import com.backend.model.ApplyJob;
import com.backend.model.Job;

public interface JobDao {
	
	public Job addJob(Job job);
	public List<Job> getAllJobs();
	public Job getJob(int id);
	
	public boolean applyJob(ApplyJob applyJob);
	public ApplyJob checkIfApplied(String email,int jobId); 
	public List<ApplyJob> getAllAppliedJobs();

}
