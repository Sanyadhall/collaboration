package com.backend.daoImpl;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.backend.dao.FriendDao;
import com.backend.model.Friend;
import com.backend.model.User;


@Repository("friendDao")
@Transactional
public class FriendDaoImpl implements FriendDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public List<User> suggestedUsers(String email) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			String queryString="select * from UserTable_04 where email in "
					+ "(select email from UserTable_04 where email!=? "
					+ "minus "
					+ "(select toId_email from Friend_04 where fromId_email=? "
					+ "union "
					+ "select fromId_email from Friend_04 where toId_email=? )) ";
			SQLQuery query=session.createSQLQuery(queryString);
			query.setString(0, email);
			query.setString(1, email);
			query.setString(2, email);
			query.addEntity(User.class);
			List<User> suggestedUsers=query.list();
			return suggestedUsers;
		}
			
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean addFriend(Friend friend)
	{
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(friend);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public List<Friend> pendingRequests(String toIdEmail)
	{
		try
		{
			Session session=sessionFactory.getCurrentSession();
			 Query query= session.createQuery("from Friend where status=? and toId.email=?");
			 query.setCharacter(0, 'P');
			 query.setString(1,toIdEmail);
			 List<Friend> pendingRequests=query.list();
			 return pendingRequests;
			 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean acceptRequest(Friend request) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			request.setStatus('A');
			session.update(request);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteRequest(Friend request) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.delete(request);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public List<User> listOfFriends(String email) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			Query query1=session.createQuery("select f.toId From Friend f where f.fromId.email=:x and f.status=:y");
			query1.setParameter("x", email);
			query1.setParameter("y", 'A');
			List<User> friendList1=query1.list();
			
			Query query2=session.createQuery("select f.fromId "
					+ "from Friend f where f.toId.email=:x and f.status=:y");
			query2.setParameter("x", email);
			query2.setParameter("y", 'A');
			List<User> friendList2=query2.list();
			
			friendList1.addAll(friendList2);
			return friendList1;
		}
			
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

}
