package com.backend.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.BlogCommentDao;
import com.backend.model.BlogComment;



@Repository("blogCommentDao")
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao
{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean addBlogComment(BlogComment commentObj) {
		try {
		Session session=sessionFactory.getCurrentSession();
		session.save(commentObj);
		return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	
	public List<BlogComment> getAllComments(int blogId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from BlogComment where blogId=:x");
			query.setParameter("x", blogId);
			List<BlogComment> comments=query.getResultList();
			return comments;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	
	public boolean deleteComment(int commentId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			BlogComment blogComment=session.get(BlogComment.class,commentId);
			session.delete(blogComment);
			return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return false;
	}

}


