package com.backend.dao;

import java.util.List;

import com.backend.model.BlogComment;

public interface BlogCommentDao {
	
	public boolean addBlogComment(BlogComment commentObj);
	public List<BlogComment> getAllComments(int blogId);
	public boolean deleteComment(int commentId);

}
