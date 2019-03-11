

package com.backend.dao;

import java.util.List;

import com.backend.model.Friend;
import com.backend.model.User;

public interface FriendDao {
	
	public List<User> suggestedUsers(String email);

	public boolean addFriend(Friend friend);
	
	public List<Friend> pendingRequests(String toIdEmail);

	public boolean acceptRequest(Friend request);

	public boolean deleteRequest(Friend request);
	
	public List<User> listOfFriends(String email);

}
