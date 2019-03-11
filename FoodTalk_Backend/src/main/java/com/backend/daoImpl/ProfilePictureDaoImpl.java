package com.backend.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.ProfilePictureDao;
import com.backend.model.ProfilePicture;



@Repository("profilePictureDao")
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public void uploadProfilePicture(ProfilePicture profilePicture)
	{
		try
		{
			System.out.println("Upload Profile Picture");
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(profilePicture);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public ProfilePicture getImage(String email) {
		
		try
		{
			Session session=sessionFactory.getCurrentSession();
			ProfilePicture p= session.get(ProfilePicture.class,email);
			return p;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
