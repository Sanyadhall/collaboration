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
import com.backend.dao.BlogDao;
import com.backend.model.Blog;

public class BlogTestCase {
	static BlogDao blogDao;
	
	@BeforeClass
	public static void intitalize() {
		
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.refresh();
		
		blogDao=context.getBean("blogDao",BlogDao.class);
	}

	
	@Test
	@Ignore
	public void addBlogTest()
	{
		Date date=new Date();
		
		Blog b= new Blog();
		b.setBlogName("Spring MVC");
		b.setEmail("rahul@gmail.com");
		b.setBlogContext("Spring MVC stands for Model View and Controller.");
		b.setCreateDate(date);
		b.setLikes(0);
		b.setStatus("Pending");

		
		assertTrue("Problem in adding Blog",blogDao.addBlog(b));
		
	}
	
	@Test
	@Ignore
	public void deleteBlogTest()
	{
		
		Blog b=blogDao.getBlog(2);
		assertTrue("Problem in deleting Blog", blogDao.deleteBlog(b));
	}
	
	
	@Test
	@Ignore
	public void updateTestcase()
	{
	    Blog b=blogDao.getBlog(3);
	    b.setBlogContext("Tea is a refreshing and energetic Drink");  
	    assertTrue("Problems in updating Blog",blogDao.updateBlog(b));
	}
	
	@Test
	@Ignore
	public void getBlogTest()
	{
		Blog b=blogDao.getBlog(3);
		System.out.println(b.getBlogName()+" "+b.getEmail()+" "+b.getLikes()+" "+b.getStatus()+" "+b.getCreateDate()+" "+b.getBlogContext());
		assertNotNull("Blog not Found",b);
		
	}
	
	
	@Test
	@Ignore
	public void approveTestcase()
	{
	    Blog b=blogDao.getBlog(3);
	    assertTrue("Problems in updating Blog",blogDao.approveBlog(b));
	}
	
	@Test
	@Ignore
	public void rejectTestcase()
	{
	    Blog b=blogDao.getBlog(3);
	    assertTrue("Problems in updating Blog",blogDao.rejectBlog(b));
	}
	
	
	
	@Test
	@Ignore
	public
	void fetchPendingBlogs() {
		List<Blog> blogs=blogDao.listPendingBlogs();
		for(Blog b:blogs) {
			System.out.println(b);
		}
		assertTrue("No Pending Blogs Found",blogs.size()!=0);
	}	
	
	@Test
	@Ignore
	public void fetchApprovedBlogs()
	{
		List<Blog> blog=blogDao.listAllApprovedBlogs();
		for(Blog b:blog)
		{
			System.out.println(b);
		}
		assertTrue("No Approved Blogs found",blog.isEmpty());
		}
	
	
	@Test
	@Ignore
	public void listAllBlogs()
	{
		List<Blog> blog=blogDao.listBlogs("rahul@gmail.com");
		for(Blog b:blog)
		{
			System.out.println(b);
		}
	}
	
	@Test
	@Ignore
	public void increamentLikes()
	{
	  Blog b=blogDao.getBlog(3);
	  assertTrue("Problem in increament",blogDao.incrementLikes(b));
		
	}
}
