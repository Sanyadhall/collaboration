package com.backend.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.BlogDao;
import com.backend.model.Blog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public boolean addBlog(Blog blogobj) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(blogobj);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteBlog(Blog blog) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.delete(blog);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateBlog(Blog blog) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.update(blog);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	
	}

	public Blog getBlog(int blogId) {
		try
		{
			Session session=sessionFactory.getCurrentSession(); 
			Blog obj=session.get(Blog.class, blogId);
			return obj;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean approveBlog(Blog blog) {
		try
		{
			Session session=sessionFactory.getCurrentSession(); 
			blog.setStatus("Approved");
			session.update(blog);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean rejectBlog(Blog blog) {
		try
		{
			Session session=sessionFactory.getCurrentSession(); 
			blog.setStatus("Rejected");
			session.update(blog);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public List<Blog> listBlogs(String email) {
		try
		{
			Session session=sessionFactory.openSession(); 
             Query query=session.createQuery("from Blog where email=:x");
             query.setString("x", email);
             List<Blog> listBlogs=query.list();
             return listBlogs;
                   
                    
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Blog> listAllApprovedBlogs() {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Blog where status='Approved'");
		    List<Blog> listAllApprovedBlogs=query.list();
		   
		    System.out.println(listAllApprovedBlogs);
		     return listAllApprovedBlogs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Blog> listPendingBlogs() {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			 Query query=session.createQuery("from Blog where status='Pending'");
			 List<Blog> listPendingBlogs=query.list();
			 return listPendingBlogs;
			           
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean incrementLikes(Blog blog) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			blog.setLikes(blog.getLikes()+1);
			session.update(blog);
			     return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	

}
