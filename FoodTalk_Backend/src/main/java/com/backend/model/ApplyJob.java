package com.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ApplyJob_04")

public class ApplyJob {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int applicationId;
    private int jobId;
    private String email;
	private Date appliedOn;
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

}
