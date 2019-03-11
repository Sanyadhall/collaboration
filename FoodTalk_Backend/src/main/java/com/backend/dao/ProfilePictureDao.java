package com.backend.dao;

import com.backend.model.ProfilePicture;

public interface ProfilePictureDao {
	public void uploadProfilePicture(ProfilePicture profilePicture);

	public ProfilePicture getImage(String email);

}
