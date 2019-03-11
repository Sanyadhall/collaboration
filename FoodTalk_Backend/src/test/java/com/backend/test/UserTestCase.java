package com.backend.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.dao.BlogDao;
import com.backend.dao.UserDao;
import com.backend.model.User;

  

public class UserTestCase {
	
	
	static UserDao userDao;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.refresh();
		
		userDao=context.getBean("userDao",UserDao.class);
		
		
	}
	
	@Test
	@Ignore
	public void registerUserTest()
	{
		User u=new User();
		u.setEmail("suresh@gmail.com");
		u.setFirstName("Suresh");
		u.setLastName("Kumar");
		u.setMobileNumber("9867697854");
		u.setPassword("sur123");
		u.setRole("USER");
		u.setOnlineStatus("Online");
		
		assertTrue("Problem in registering User",userDao.registerUser(u));
	}
	
	@Test
	@Ignore
	public void deleteTest() 
	{
		User user=userDao.getUser("rahul@gmail.com");
		assertTrue("Problem in deleting user", userDao.deleteUser(user));
		
	}
	
	
	@Test
	@Ignore
	public void validateUser() {
		User user=new User();
		user.setEmail("suresh@gmail.com");
		user.setPassword("sur123");
		
		assertNotNull("User not found",userDao.checkLogin(user));
	}

	
	@Test
	@Ignore
	public void updateUser()
	{
		 User u=userDao.getUser("suresh@gmail.com");
		 u.setLastName("Suresh");
		

		 assertNotNull("Problem in updating user",userDao.updateUser(u));
		 
	}
	
	
	
	@Test
	@Ignore
	public void CheckOnlineStatus()
	{
		User u=userDao.getUser("sanya@gmail.com");
		
	 assertTrue("Problem in updating user",userDao.updateOnlineStatus("Online", "sanya@gmail.com"));
		
	}
	
	@Test
	@Ignore
	public void getUserTestCase() 
	{
		User u=userDao.getUser("sanya@gmail.com");
		System.out.println(u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getMobileNumber()+" "+u.getOnlineStatus());
		assertNotNull("User Not found",u);
	}
	
	@Test
	@Ignore
	public void listUsers()
	{
		List<User> u=userDao.getUserDetails();
		for(User user:u)
		{
			System.out.println(user.getEmail()+" "+user.getFirstName()+" "+user.getLastName()+" "+user.getMobileNumber()+" "+user.getOnlineStatus());
		}
		
		
	}
	
	
}
