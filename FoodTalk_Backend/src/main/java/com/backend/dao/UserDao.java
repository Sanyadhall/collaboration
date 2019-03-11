package com.backend.dao;

import java.util.List;

import com.backend.model.User;

public interface UserDao {

	public boolean registerUser(User user);
	public User checkLogin(User user);
	public boolean isEmailUnique(String email);
	public boolean updateOnlineStatus(String status,String email);
	public User getUser(String email);
	public List<User> getUserDetails();
	public boolean deleteUser(User user);
	public User updateUser(User user);
}


