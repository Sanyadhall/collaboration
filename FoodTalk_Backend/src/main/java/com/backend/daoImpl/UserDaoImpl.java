package com.backend.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.UserDao;
import com.backend.model.User;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	SessionFactory sessionFactory;
	

	public boolean registerUser(User user) {
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
		     session.save(user);
		     return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public User checkLogin(User user) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from User where email=:x and password=:y");	
			query.setString("x",user.getEmail());
			query.setString("y",user.getPassword());
			
			List<User> userDetails=query.list();
			System.out.println(userDetails);
			
			if(userDetails.size()!=0) {
				return userDetails.get(0);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	public boolean updateOnlineStatus(String status, String email) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			User u=session.get(User.class,email);
			u.setOnlineStatus("Online");
			return true;
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return false;
	}

	public User getUser(String email) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			 User u=(User)session.get(User.class,email);
			 return u;
			    
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	public List<User> getUserDetails() {
		try
		{
			Session session=sessionFactory.openSession();
	     	Query query=session.createQuery("from User");
	        List<User> getUserDetails=query.list();
	     	 session.close();
	     	 return getUserDetails;
			 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	public boolean deleteUser(User user) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.delete(user);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return false;
	}

	public User updateUser(User user) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.update(user);
			return user;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	public boolean isEmailUnique(String email)
	{
		try
		{
			Session session= sessionFactory.getCurrentSession();
			User user=(User) session.get(User.class,email);
			if(user==null)
			{
				return true;
            }
			else
			{
				return false;
}
		
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
