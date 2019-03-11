package com.backend.dao;

import java.util.List;

import com.backend.model.Blog;

public interface BlogDao {
	public boolean addBlog(Blog blogobj);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public Blog getBlog(int blogId);
	
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	
	public List<Blog> listBlogs(String email);
	public List<Blog> listAllApprovedBlogs();
	public List<Blog> listPendingBlogs();
	public boolean incrementLikes(Blog blog);
}



