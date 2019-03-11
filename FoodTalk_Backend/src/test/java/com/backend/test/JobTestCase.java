package com.backend.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.dao.JobDao;
import com.backend.model.ApplyJob;
import com.backend.model.Job;

public class JobTestCase {
	
	static JobDao jobDao;
	
	@BeforeClass
	public static void initialize() 
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.refresh();
		
		jobDao=context.getBean("jobDao",JobDao.class);
		
	}
	
	@Test
	@Ignore
	public void AddJob()
	{
		Date d=new Date();
		Job j= new Job();
		j.setCompanyName("Wipro technologies");
		j.setJobTitle(" senior Full stack Developer");
		j.setLocation("Pune");
		j.setPostedOn(d);
		j.setYrsofExp("1 Year Experience");
		j.setSkillsRequired("Core Java, Advanced Java,Spring, Hibernate, bootstrap,database");
		j.setSalary("5.5 PA");
		j.setJobDescription("full stack development");
		
		
		assertNotNull("Problem in adding Job", jobDao.addJob(j));
		
	}
	
	@Test
	@Ignore
	public void ListOfJobs()
	{
		List<Job> job=jobDao.getAllJobs();
		for(Job j:job)
		{
	System.out.println(j.getCompanyName()+ " "+j.getJobTitle()+" "+j.getJobDescription()+" "+j.getLocation()+" "+j.getSalary()+" "+j.getPostedOn()+" "+j.getSkillsRequired()+" "+j.getYrsofExp());
	 
	
		}
	}
	
	@Test
	@Ignore
	public void getJobById()
	{
		Job j=jobDao.getJob(23);
		System.out.println(j.getCompanyName()+ " "+j.getJobTitle()+" "+j.getJobDescription()+" "+j.getLocation()+" "+j.getSalary()+" "+j.getPostedOn()+" "+j.getSkillsRequired()+" "+j.getYrsofExp());
		
		assertNotNull("User Not found",j);
	}
	
	@Test
	@Ignore
	public void applyJob() 
	{
		
		Date d= new Date();
      	ApplyJob a= new ApplyJob();
      	a.setEmail("divya@gmail.com");
      	a.setJobId(23);
      	a.setAppliedOn(d);
      	
      	assertTrue("Problem in applying job",jobDao.applyJob(a));
	}
	
	
	@Test
	@Ignore
	public void getAllAppliedJobs()
	
	{
	List<ApplyJob> getallJob=jobDao.getAllAppliedJobs();
	for(ApplyJob job:getallJob)
	{
		System.out.println(job.getApplicationId()+" "+job.getJobId()+" "+job.getEmail()+" "+job.getAppliedOn());
	}
	}

}
