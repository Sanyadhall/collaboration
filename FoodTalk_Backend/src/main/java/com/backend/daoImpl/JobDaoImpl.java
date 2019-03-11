package com.backend.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.JobDao;
import com.backend.model.ApplyJob;
import com.backend.model.Job;


@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public Job addJob(Job job) {
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			 session.save(job);
			  return job;
			     
			     
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
               return null;
	}

	public List<Job> getAllJobs()
	{
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job");
			    List<Job> listJobs = query.list();
			    return listJobs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public Job getJob(int id) {
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			     Job job=session.get(Job.class, id);
			     return job;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean applyJob(ApplyJob applyJob) 
	{
		try
		{
		Session session= sessionFactory.getCurrentSession();
		session.save(applyJob);
		return true;
	}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		return false;
	}

	

	public ApplyJob checkIfApplied(String email, int jobId) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("job Id   : "+jobId);
		System.out.println("email : "+email);
		Query query=session.createQuery("from ApplyJob where jobId=:x and email=:y");
		query.setParameter("x",jobId);
		query.setParameter("y",email);
		
		List<ApplyJob> appliedJobs=query.list();
		if(appliedJobs.size()==0){
			return null;
		}
		else {
		return appliedJobs.get(0);
		}
	}



	public List<ApplyJob> getAllAppliedJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ApplyJob");
		
		return query.list();
	}
}
